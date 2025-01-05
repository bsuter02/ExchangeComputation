import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**This class is the testing class for ExchangeComputation. It contains seven tests that
 * test all logic based methods in the logic file.
 *
 * @author bsuter*/
public class B01_ExchangeComputationJUnitTest {
    /**This method tests the open, close, deposit, and withdraw methods in the account class.*/
    @Test
    void accountOpenAndCloseTest(){
        B01_ExchangeComputationLogic.Account a = new B01_ExchangeComputationLogic.Account();
        a.openAccount(230.17);
        assertEquals(230.17, a.getUSDbalance());
        a.depositUSD(0.01);
        assertEquals(230.18, a.getUSDbalance());
        a.withdrawUSD(220.01);
        assertEquals(10.17,a.getUSDbalance());

        a.closeAccount();
        assertEquals(0.0,a.getUSDbalance());
    }
    /**This method tests the account class to ensure the correct amount of decimals is allowed to be presented to the user. Ensuring that
     * during computation, there are no rounding errors.*/
    @Test
    void accountOverflowTest(){
        B01_ExchangeComputationLogic.Account a = new B01_ExchangeComputationLogic.Account();
        a.openAccount(405.3599999);
        assertEquals(405.36, a.getUSDbalance());
    }
    /**This method test exactly as the name suggests, the getters and setters for the exchangeRate variable.*/
    @Test
    void exchangeRateGetSetTest(){
        B01_ExchangeComputationLogic b = new B01_ExchangeComputationLogic();
        b.setExchangeRate(2.01);
        assertEquals(2.01,b.getExchangeRate());
    }
    /**This method tests if the conversion between USD and SWD through a defined exchange rate is functioning as it should.*/
    @Test
    void exchangeUSDtoSWDTest(){
        B01_ExchangeComputationLogic b = new B01_ExchangeComputationLogic();
        b.setExchangeRate(2.0);

        B01_ExchangeComputationLogic.Account a = new B01_ExchangeComputationLogic.Account();
        a.openAccount(40.00);
        assertEquals(10.00,b.convertSWDtoUSD(20.00));
        b.withdrawSWD(a,20.00);
        assertEquals(30.00,a.getUSDbalance());
    }
    /**This method ensures that the array returned by displayUSDbalanceBreakdown accurately represent the most efficient division of USD.*/
    @Test
    void efficientUSDBreakdownTest(){
        B01_ExchangeComputationLogic.Account a = new B01_ExchangeComputationLogic.Account();

        a.openAccount(36.41);
        ArrayList<Integer> test1 = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            test1.add(i,1);
        }
        assertEquals(test1, a.displayUSDbalanceBreakdown());
        a.setUSDbalance(45.67);
        test1.clear();
        test1.add(2);
        test1.add(0);
        test1.add(1);
        test1.add(0);
        test1.add(2);
        test1.add(1);
        test1.add(1);
        test1.add(2);
        assertEquals(test1, a.displayUSDbalanceBreakdown());
    }
    /**This method ensures that the array returned by displaySWDbreakdown accurately represent the most efficient division of SWD.*/
    @Test
    void efficientSWDBreakdownTest(){
        B01_ExchangeComputationLogic b = new B01_ExchangeComputationLogic();
        ArrayList<Integer> test1 = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            test1.add(i,1);
        }
        assertEquals(test1, b.displaySWDbreakdown(41.34));

        test1.clear();
        test1.add(3);
        test1.add(1);
        test1.add(0);
        test1.add(0);
        test1.add(4);
        test1.add(1);
        test1.add(0);
        test1.add(1);
        assertEquals(test1, b.displaySWDbreakdown(85.89));
    }
    /**This method ensures that the array returned by both breakdown methods are able to accurately round given a value with more than two decimals.*/
    @Test
    void breakdownOverflowTest(){
        B01_ExchangeComputationLogic.Account a = new B01_ExchangeComputationLogic.Account();

        a.openAccount(36.4111111);
        ArrayList<Integer> test1 = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            test1.add(i,1);
        }
        assertEquals(test1, a.displayUSDbalanceBreakdown());

        B01_ExchangeComputationLogic b = new B01_ExchangeComputationLogic();
        assertEquals(test1, b.displaySWDbreakdown(41.3411111));

        test1.set(7,2);
        a.setUSDbalance(36.4199999);
        assertEquals(test1, a.displayUSDbalanceBreakdown());
        assertEquals(test1, b.displaySWDbreakdown(41.349999));
    }
}
