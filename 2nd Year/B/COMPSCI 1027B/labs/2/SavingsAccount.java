
public class SavingsAccount extends BankAccount{
	
	private double interestRate;
	
	public SavingsAccount(double initialAmount, double rate){
		super(initialAmount);
		this.interestRate = rate;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public double calculateInterest() {
		super.deposit(super.getBalance()*interestRate);
		return super.getBalance()*interestRate;
	}
	
	public String toString() {
		double balance = super.getBalance();
		return ("SavingsAccount: balance $"+balance+", interest rate "+interestRate);
	}
	
	public static void main(String[] args) {
        SavingsAccount myAccount = new SavingsAccount(100.0,0.15);
	myAccount.calculateInterest();
	System.out.println(myAccount.toString());
}
}
