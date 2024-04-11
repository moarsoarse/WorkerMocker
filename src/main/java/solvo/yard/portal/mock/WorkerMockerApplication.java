package solvo.yard.portal.mock;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WorkerMockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkerMockerApplication.class, args);
    }

}
