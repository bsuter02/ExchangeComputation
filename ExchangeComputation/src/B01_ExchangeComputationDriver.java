/**This class is the driver class of the ExchangeComputation program. It contains only a main method.
 * @author bsuter*/
public class B01_ExchangeComputationDriver extends B01_ExchangeComputationLogic{
    /**This main method contains a few lines of code that shows how this program works. This currently only
     * uses one account, but multiple account classes can be created as needed.*/
    public static void main(String[] args){
        B01_ExchangeComputationLogic b = new B01_ExchangeComputationLogic();
        b.setExchangeRate(1.5);
        System.out.println("Current exchange rate: $1 USD = " + b.getExchangeRate() + " SWD");

        Account a = new Account();
        a.openAccount(230.79);
        a.displayUSDbalance();
        a.depositUSD(22.21);
        b.withdrawSWD(a,30.30);
        System.out.println(b.displaySWDbreakdown(30.30));
        a.closeAccount();
    }
}
