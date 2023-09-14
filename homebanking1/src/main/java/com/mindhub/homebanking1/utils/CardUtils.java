package com.mindhub.homebanking1.utils;

import java.util.Random;

public class CardUtils {

    public static String generateRandomVIN() {

        Random random = new Random();

        long numberAl = random.nextLong() % 90000000L + 10000000L;

        if (numberAl < 0) {

            numberAl = -numberAl;
        }

        String vin = "VIN-" + numberAl;

        return vin;
    }





    public static String generateRandomCreditCardNumber() {

        Random random = new Random();


        StringBuilder creditCardNumber = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int grupo = random.nextInt(10000);
            String grupoStr = String.format("%04d", grupo);
            creditCardNumber.append(grupoStr);

            if (i < 3) {
                creditCardNumber.append(" ");
            }
        }

        return creditCardNumber.toString();
    }


    public static int generateRandomCVV() {

        Random random = new Random();


        int cvv = random.nextInt(1000);

        return cvv;
    }

}
