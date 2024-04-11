package solvo.yard.portal.mock;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import io.camunda.zeebe.spring.client.exception.ZeebeBpmnError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Component
public class Mocker {

  private static Logger log = LoggerFactory.getLogger(Mocker.class);

  @JobWorker(type = "id-check")
  public void checkIds(final ActivatedJob job, @Variable String entity, @Variable UUID[] ids) {
    logJob(job, null);
  }

  @JobWorker(type = "request-post")
  public Map<String, Object> createRequest(final ActivatedJob job, @Variable Map<String, Object> request) {
    logJob(job, request);
    return Collections.singletonMap("someResult", "42");
  }

  @JobWorker(type = "request-put", fetchAllVariables = true)
  public void issueRequest(
      final JobClient client, final ActivatedJob job, @Variable String someResult) {
    logJob(job, someResult);
    throw new ZeebeBpmnError("DOESNT_WORK", "This will actually never work :-)");
  }

  private static void logJob(final ActivatedJob job, Object parameterValue) {
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
