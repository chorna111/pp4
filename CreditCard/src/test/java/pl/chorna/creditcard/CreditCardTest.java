package pl.chorna.creditcard;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void itAssignCredit(){
        //arrange
        var card=new CreditCard();
        //act
        card.assignCredit(BigDecimal.valueOf(1000));
        //assert
        assert BigDecimal.valueOf(1000).equals(card.getBalance());
    }
    @Test
    void itAssignCreditV2(){
        //arrange
        var card=new CreditCard();
        //act
        card.assignCredit(BigDecimal.valueOf(1500));
        //assert
        assert BigDecimal.valueOf(1500).equals(card.getBalance());
    }
    @Test
    void itDenyCreditBelowThreshold(){
        var card=new CreditCard();
        try{
            card.assignCredit(BigDecimal.valueOf(50));
            assert  false;

        }catch(CreditBelowThresholdException e) {
            assert true;

        }
    }
}
