package pkg1;

import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);

	public static Account addUser() {
		System.out.println("Enter user name");
		String name = sc.next();
		System.out.println("Enter user Acc");
		int accNo = sc.nextInt();
		System.out.println("Enter user Bal");
		int bal = sc.nextInt();

		return new Account(accNo, name, bal);
	}

	static int menuList() {
		System.out.println();
		System.out.println("Enter 0 to Exit");
		System.out.println("Enter 1 to add Record");
		System.out.println("Enter 2 to add Amount");
		System.out.println("Enter 3 to Withdraw Money");
		System.out.println("Enter 4 to check balance");
		System.out.println();
		return sc.nextInt();
	}

	public static void main(String[] args) {
		int choice;
		while ((choice = menuList()) != 0) {
			switch (choice) {
			case 1:
				Account account = addUser();
				Operations.createAccount(account);
				break;

			case 2:
				String mode = selectModeToAddAmount();
				if (mode == "online" || mode == "cash") {
					System.out.println("Enter Account Number");
					int acno = sc.nextInt();
					System.out.println("Enter Amount");
					int amt = sc.nextInt();
					Operations.addOrWithdrawAmount(acno, amt, mode, "add");
				} else
					System.out.println("Invalid mode");
				break;

			case 3:
				String modeWithdraw = selectModeToWithdrawAmount();
				if (modeWithdraw == "online" || modeWithdraw == "cash" || modeWithdraw == "atm") {
					System.out.println("Enter Account Number");
					int acno = sc.nextInt();
					System.out.println("Enter Amount");
					int amt = sc.nextInt();
					Operations.addOrWithdrawAmount(acno, amt, modeWithdraw, "withdraw");
				} else
					System.out.println("Invalid mode");
				break;

			case 4:
				System.out.println("Enter Account no");
				Operations.checkBalance(sc.nextInt());
				break;

			default:
				System.out.println("Invalid input");
			}
		}

	}

	static String selectModeToAddAmount() {
		System.out.println("Select Mode");
		System.out.println("Enter 1 for online mode");
		System.out.println("Enter 2 for cash mode");
		int x = sc.nextInt();
		if (x == 1)
			return "online";
		else if (x == 2)
			return "cash";
		else
			return "null";
	}

	static String selectModeToWithdrawAmount() {
		System.out.println("Select Mode");
		System.out.println("Enter 1 for online mode");
		System.out.println("Enter 2 for cash mode");
		System.out.println("Enter 3 for atm mode");
		int x = sc.nextInt();
		if (x == 1)
			return "online";
		else if (x == 2)
			return "cash";
		else if (x == 3)
			return "atm";
		else
			return "null";
	}

}
