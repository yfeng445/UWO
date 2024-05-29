
public class SavingsAccount extends BankAccount{
	
	private static double rate;
	
	public SavingsAccount(double initialAmount, double rate){
		super(initialAmount);
		this.rate = rate;
	}
	
	public double getInterestRate() {
		return SavingsAccount.rate;
	}
	
	public void calculateInterest() {
		super.deposit(super.getBalance()*rate);
	}
	
	public String toString() {
		return "SavingAccount: balance $"+getBalance()+", interest rate "+rate;
	}
	
	public static void main(String[] args) {
        SavingsAccount myAccount = new SavingsAccount(100.0,0.15);
	myAccount.calculateInterest();
	System.out.println(myAccount.toString());
}
}
