import java.util.ArrayList;
/**This class contains the Account class, exchange rate variable, and the methods for setting/getting the
 * exchange rate. In addition, this class also contains all methods needed to convert USD to SWD using the
 * user defined exchange rate.
 *
 * @author bsuter */
public class B01_ExchangeComputationLogic {
    /**Double number representing the exchange rate where one USD would equal this much in SWD.*/
    private double exchangeRate;
    /**This class contains all methods pertaining to USD balance, opening, and closing accounts.
     * @author bsuter */
    public static class Account{
        /**This double represents the balance of the account in USD.*/
        double USDbalance = 0.0;
        /**@return Double of USD in account, rounded to appropriate decimal place.*/
        public double getUSDbalance(){
           USDbalance = Math.round(USDbalance*100.0)/100.0;
           return(USDbalance);
       }
       /**One argument method, setting the balance in the account.
        *
        * @param newBalance The provided balance assigned to the USDbalance class variable.*/
        public void setUSDbalance(double newBalance){
           USDbalance = newBalance;
        }
        /**One argument method opening an instance of the account class at a certain defined balance.
         *
         * @param startingBalance Defines the balance in the account when it opens.*/
        public void openAccount(double startingBalance){
            this.setUSDbalance(startingBalance);
        }
        /**This method prints the current balance of the account to the screen and the most efficient way to divide it in USD.*/
        public void displayUSDbalance(){
            double balance = this.getUSDbalance();
            System.out.println("\nCurrent balance: $" + balance);
            ArrayList<Integer> usdBreakdown = displayUSDbalanceBreakdown();
            System.out.println("USD: $20: "+usdBreakdown.get(0) + "; $10: "+usdBreakdown.get(1)+"; $5: "+usdBreakdown.get(2)+"; $1: "+usdBreakdown.get(3)+
                    "; 25c: "+usdBreakdown.get(4)+"; 10c: "+usdBreakdown.get(5)+"; $5: "+usdBreakdown.get(6)+"; 1c: "+usdBreakdown.get(7)+"\n");
        }
        /**@return An array of integers representing the most efficient way to divide the account balance in USD.*/
        public ArrayList<Integer> displayUSDbalanceBreakdown(){
            ArrayList<Integer> array = new ArrayList<>();
            int remainder = (int) Math.round(this.getUSDbalance()*100);

            int USD20 = remainder/2000;
            remainder = remainder-(2000*USD20);
            int USD10 = remainder/1000;
            remainder = remainder-(1000*USD10);
            int USD5 = remainder/500;
            remainder = remainder-(500*USD5);
            int USD1 = remainder/100;
            remainder = remainder-(100*USD1);
            int quarter = remainder/25;
            remainder = remainder-(25*quarter);
            int dime = remainder/10;
            remainder = remainder-(10*dime);
            int nickle = remainder/5;
            remainder = remainder-(5*nickle);
            int penny = remainder;

            array.add(USD20);
            array.add(USD10);
            array.add(USD5);
            array.add(USD1);
            array.add(quarter);
            array.add(dime);
            array.add(nickle);
            array.add(penny);

            return(array);
        }
        /**One argument method that takes a double representing an amount to be deposited into the USD account and adds that value to the account.
         * @param deposit Double amount of USD to be added to the account.*/
        public void depositUSD(double deposit){
            this.setUSDbalance(this.getUSDbalance() + deposit);
        }
        /**One argument method that takes a double representing an amount to be withdrawn into the USD account and subtracts that value from the account.
         * @param withdraw Double amount of USD to be taken from the account.*/
        public void withdrawUSD(double withdraw){
            this.setUSDbalance(this.getUSDbalance() - withdraw);
        }
        /**This method displays the final amount of USD to be dispersed after the account is closed, hw it's divided out, and sets the balance to zero indicating
         * it has been closed.*/
        public void closeAccount() {
            double balance = this.getUSDbalance();
            System.out.println("\nClosing Account. Balance returned: " + balance);
            displayUSDbalanceBreakdown();
            this.setUSDbalance(0.0);
        }
    }
    /**@return Double value of the exchangeRate variable.*/
    public double getExchangeRate(){
        return(exchangeRate);
    }
    /**One argument method setting the value of the exchange rate, where $1 USD = $'exchangeRate' in SWD.
     *
     * @param exchangeRate Double value of the new, user-defined exchangeRate.*/
    public void setExchangeRate(double exchangeRate){
        this.exchangeRate = exchangeRate;
    }
    /**One argument method that takes in an amount of SWD and returns an array of integers representing the most efficient way to distribute that cash.
     *
     * @param swdOut User-defined SWD amount.
     * @return Array of integers representing the most efficient breakdown of converted cash in SWD.*/
    public ArrayList<Integer> displaySWDbreakdown(double swdOut){
        int remainder = (int) Math.round(swdOut*100);
        ArrayList<Integer> array = new ArrayList<>();

        int swd25 = remainder/2500;
        remainder = remainder-(2500*swd25);
        int swd10 = remainder/1000;
        remainder = remainder-(1000*swd10);
        int swd5 = remainder/500;
        remainder = remainder-(500*swd5);
        int swd1 = remainder/100;
        remainder = remainder-(100*swd1);
        int twenties = remainder/20;
        remainder = remainder-(20*twenties);
        int eights = remainder/8;
        remainder = remainder-(8*eights);
        int nickles = remainder/5;
        remainder = remainder-(5*nickles);
        int pennies = remainder;

        array.add(swd25);
        array.add(swd10);
        array.add(swd5);
        array.add(swd1);
        array.add(twenties);
        array.add(eights);
        array.add(nickles);
        array.add(pennies);

        return(array);
    }
    /**One argument method taking a user-defined amount of SWD and converting it into it's USD equivalent.
     *
     * @param SWD Double representing the SWD amount to be divided out.
     * @return returns a double USD amount rounded to the second decimal place.*/
    public double convertSWDtoUSD(double SWD){
        return(Math.round((SWD*100.0)/exchangeRate)/100.0);
    }
    /**Two argument method that checks if an account is able to withdraw a defined amount of SWD and then does so using
     * the convertSWDtoUSD() and displaySWDbreakdown() methods.
     * @param a Account in which the SWD is being withdrawn from.
     * @param swdOut Defined amount of SWD to be withdrawn from the account if sufficient funds are there.*/
    public void withdrawSWD(Account a, double swdOut){
        double usdConversion = convertSWDtoUSD(swdOut);
        if(a.getUSDbalance() < usdConversion){
            System.out.println("Insufficient funds available.");
        }
        else{
            a.setUSDbalance(a.getUSDbalance()-(usdConversion));
            System.out.println("$" + usdConversion + " USD withdrawn and converted to " + swdOut + " SWD.");
            ArrayList<Integer> swdBreakdown = displaySWDbreakdown(swdOut);
            System.out.println("SWD: $25: " + swdBreakdown.get(0) + " $10: " + swdBreakdown.get(1) + " $5: " + swdBreakdown.get(2) + " $1: " + swdBreakdown.get(3) +
                    " 20c: " + swdBreakdown.get(4) + " 8c: " + swdBreakdown.get(5) + " $5: " + swdBreakdown.get(6) + " 1c: " + swdBreakdown.get(7)+ "\n");

        }
    }
}
