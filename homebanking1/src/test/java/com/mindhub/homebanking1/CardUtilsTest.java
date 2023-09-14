package com.mindhub.homebanking1;

import com.mindhub.homebanking1.services.CardService;

import com.mindhub.homebanking1.utils.CardUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CardUtilsTest {
    @Autowired
    private CardService cardService;




    @Test
    void generateRandomVIN() {
        String vinCardNumber = CardUtils.generateRandomVIN();
        assertThat(vinCardNumber, is(not(emptyOrNullString())));
    }


    @Test
    void generateRandomVINDifferent() {
        String vinCardNumber1 = CardUtils.generateRandomVIN();
        String vinCardNumber2 = CardUtils.generateRandomVIN();
        assertNotEquals(vinCardNumber1, vinCardNumber2);
    }


    @Test
    void generateRandomVINValidFormat() {
        String vinCardNumber = CardUtils.generateRandomVIN();
        assertTrue(vinCardNumber.startsWith("VIN-"));
    }




    @Test
    void generateRandomCreditCardNumber() {
        String cardNumber = CardUtils.generateRandomCreditCardNumber();
        assertThat(cardNumber,is(not(emptyOrNullString())));
    }


    @Test
    void generateRandomCreditCardNumberDifferent() {
        String cardNumber = CardUtils.generateRandomCreditCardNumber();
        String cardNumber1 = CardUtils.generateRandomCreditCardNumber();
        assertNotEquals(cardNumber, cardNumber1);
    }


    @Test
    void generateRandomCreditCardNumberValidFormatWithSpaces() {
        String cardNumber = CardUtils.generateRandomCreditCardNumber();
        assertThat(cardNumber, matchesPattern("\\d{4} \\d{4} \\d{4} \\d{4}"));
    }




    @Test
    void generateRandomCVV() {
        int cvvNumber = CardUtils.generateRandomCVV();
        assertTrue(cvvNumber > 0);

    }


    @Test
    void generateRandomCVVInRange() {
        int cvvNumber = CardUtils.generateRandomCVV();
        assertTrue(cvvNumber >= 100 && cvvNumber <= 999);
    }


    @Test
    void generateRandomCVVIsDifferent() {
        int cvvNumber1 = CardUtils.generateRandomCVV();
        int cvvNumber2 = CardUtils.generateRandomCVV();
        assertNotEquals(cvvNumber1, cvvNumber2);
    }



}
