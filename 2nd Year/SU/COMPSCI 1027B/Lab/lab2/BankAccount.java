/**
 * BankAccount.java:
 *  This class is a simple model of a general banking account,
 *  to be used for understanding inheritance.
 * @author From the book "Big Java" by Cay Horstmann
 */

public class BankAccount {
    
    /** Attribute:
      * Keeps track of current balance */
    private double balance; 
    
    /** Creates a new instance of BankAccount 
     * @param initialAmount initial amount deposited into new account
     */
    public BankAccount(double initialAmount) {
        balance = initialAmount;
    }
    
    /** Accessor method for balance 
     * @return current balance
     */
    public double getBalance(){
        return balance;
    }
    
    /** Deposit money into account 
     * @param amount    amount to be deposited
     */
    public void deposit (double amount) {
        balance = balance + amount;
    }
    
    /** Withdraw money from account 
     * @param amount    amount to be withdrawn
     */
    public void withdraw(double amount) { 
        if (amount <= balance) 
            balance = balance - amount;
        else
          System.out.println("Insufficient funds for withdrawal of $" + amount);
    }
    
    /** Represent bank account as a String for display purposes
     * @return String representation of bank account
     */
    
    public String toString(){
      return ("BankAccount: balance $" + balance);
    }      
}


