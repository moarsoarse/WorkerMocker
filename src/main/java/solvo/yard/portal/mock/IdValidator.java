package solvo.yard.portal.mock;

import io.camunda.zeebe.spring.client.exception.ZeebeBpmnError;

import java.util.UUID;

public final class IdValidator {

    public static void validate(String entity, UUID[] ids) throws ZeebeBpmnError {

    }

    private enum Entity{
        CONTRACTOR("Contractor"),
        VEHICLE_TYPE("VehicleType");

        Entity(String name) {

        }
    }
}

