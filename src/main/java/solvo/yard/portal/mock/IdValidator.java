package solvo.yard.portal.mock;

import java.util.UUID;

public final class IdValidator {

    public static void validate(String entity, UUID[] ids) throws Exception {

    }

    private enum Entity{
        CONTRACTOR("Contractor"),
        VEHICLE_TYPE("VehicleType");

        Entity(String name) {

        }
    }
}

