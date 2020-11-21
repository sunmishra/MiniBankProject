package pkg1;

public class Account {
	private int accountNo;
	private String name;
	private int bal;
	
	public Account(int accountNo, String name, int bal) {
		this.accountNo = accountNo;
		this.name = name;
		this.bal = bal;
	}
	
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBal() {
		return bal;
	}
	public void setBal(int bal) {
		this.bal = bal;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", name=" + name + ", bal=" + bal + "]";
	}
	
	
	
}
