package solvo.yard.portal.mock;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class LogistMock {
    private final ZeebeClient client;
    private static Logger log = LoggerFactory.getLogger(LogistMock.class);
    private Map<String,Object> openRequests = new HashMap<>();

    @Autowired
    public LogistMock(ZeebeClient client) {
        this.client = client;
    }

    @JobWorker(type = "id-check")
    public void checkIds(final ActivatedJob job, @Variable String entity, @Variable List<UUID> ids) {
        logJob(job, entity.concat(": "+ ids.toString()));
    }

    @JobWorker(type = "request-post")
    public Map<String, Object> createRequest(final ActivatedJob job, @Variable Map<String, Object> TransportRequest) {
        logJob(job, TransportRequest);
        HashMap<String, Object> output = new HashMap<String, Object>();
        output.put("TransportRequest", TransportRequest);
        return output;
    }

    @JobWorker(type = "request-put")
    public Map<String, Object> issueRequest(final ActivatedJob job, @Variable Map<String, Object>  TransportRequest) {
        logJob(job, TransportRequest);
        openRequests.put(TransportRequest.get("ID").toString(), TransportRequest);
        return createRequest(job, TransportRequest);
    }


    @JobWorker(type = "request-get-open")
    public Map<String, Object> getOpenRequests(final ActivatedJob job) {
        logJob(job, null);
        return Map.of("openList", openRequests.values());
    }


    @JobWorker(type = "offer-post")
    public Map<String, Object> createOffer(final ActivatedJob job, @Variable Map<String, Object> Offer) {
        logJob(job, Offer);
        bidOffer( Offer);
        return Map.of("Offer", Offer);
    }


    private void bidOffer( Map<String, Object> Offer) {
        client.newPublishMessageCommand()
                .messageName("NewOffer")
                .correlationKey(((Map<String, Object>) Offer.get("request")).get("ID").toString())
                .variable("Offer", Offer)
                .send().join();
    }

    @JobWorker(type = "stop-bidding")
    public void stopBidding(final ActivatedJob job, @Variable String  requestId) {
        logJob(job, requestId);
        openRequests.remove(requestId);
    }



    private static void logJob( final ActivatedJob job, Object parameterValue) {
        log.info(
                "complete job\n>>> [type: {}, key: {}, element: {}, workflow instance: {}]\n{deadline; {}]\n[headers: {}]\n[variable parameter: {}\n[variables: {}]",
                job.getType(),
                job.getKey(),
                job.getElementId(),
                job.getProcessInstanceKey(),
                Instant.ofEpochMilli(job.getDeadline()),
                job.getCustomHeaders(),
                parameterValue,
                job.getVariables());
    }
}
