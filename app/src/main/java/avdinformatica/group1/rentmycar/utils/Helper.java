package avdinformatica.group1.rentmycar.utils;

import java.util.UUID;


public class Helper {

        public static String generateRandomSessionString() {
            String uuid = UUID.randomUUID().toString();
            return uuid;
        }


}
