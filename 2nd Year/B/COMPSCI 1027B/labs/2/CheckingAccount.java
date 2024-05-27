/**
 * CheckingAccount.java:
 *  This class is a simple model of a chequing account,
 *  to be used for understanding inheritance.
 * @author from the book "Big Java" by Cay Horstmann + CS027
 */

public class CheckingAccount extends BankAccount{
    
    /* Attribute: */
    /* Counts number of transactions on the checking account */
    private int transactionCount;
    
    /* Constants: */
    /*  Number of free transactions */
    private static final int FREE_TRANSACTIONS = 3;
    /* Fee per transaction */
    private static final double TRANSACTION_FEE = 2.0;
    
    /** Constructor: Creates a new instance of CheckingAccount 
     * @param initialAmount Initial deposit for the new account
     */
    public CheckingAccount(double initialAmount) {
        super (initialAmount);
        transactionCount = 0;
    }
    
    /** Method to access the transaction count
     * @return transactionCount   the number of transactions
     */
    public int getTransactionCount(){
      return transactionCount;
    }
    
    /** Method to deposit money into the checking account
     * @param amount    amount to be deposited 
     */
    public void deposit(double amount){
        transactionCount++;
        super.deposit(amount);
    }
    
    /** Method to withdraw money from the checking account
     * @param amount    amount to be withdrawn
     */
    public void withdraw(double amount){
        transactionCount++;
        super.withdraw(amount);
    }
    
    /** Method to calculate fees and deduct this amount from the balance
     *  transactionCount is reset to 0 to start counting new transactions
     */
    public void deductFees(){
        if (transactionCount > FREE_TRANSACTIONS)
        {
            double fees = TRANSACTION_FEE * (transactionCount - FREE_TRANSACTIONS);
            super.withdraw(fees);
        }
        transactionCount = 0;
    }
    /** Method to represent checking account as a String for display purposes
     * @return String representation of checking account
     */
    
    public String toString(){
      return ("CheckingAccount: balance $" + getBalance() + ", transactions " + transactionCount);
    }      
}
