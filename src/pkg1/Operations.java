package pkg1;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Operations {

	static String file = "account.csv";

	public static void createAccount(Account account) {
		if (!isAccountExist(account.getAccountNo())) {
			List<String> list = Arrays.asList(Integer.toString(account.getAccountNo()), account.getName(),
					Integer.toString(account.getBal()));
			try {
				int x = 2;
				FileWriter fw = new FileWriter(file, true);// since a file already exist so we need to append the data
															// to
															// the file, hence true is used to append the data
				for (String o : list) {
					fw.append(o);
					if (x-- != 0)// to add ',' only upto 2 places
						fw.append(",");// Delimiter to separate the text with columns in table
				}
				fw.append("\n");
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			System.out.println(".....Account already exist");

	}

	
	public static void addOrWithdrawAmount(int acno, int amt, String mode, String txnType) {
		if (isAccountExist(acno)) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line = br.readLine();
				line = br.readLine();// to skip to next line other wise column names will raise ex(NumberformatEx)
										// bcuz first row is heading of col which are in string
				while (line != null) {
					String[] str = line.split(",");
					int accno = Integer.parseInt(str[0]);
					String name = str[1];
					int bal = Integer.parseInt(str[2]);

					if (acno == accno) {

						String search = Integer.toString(accno) + "," + name + "," + Integer.toString(bal);
						if (txnType == "add") {
							int bal1 = bal + amt;
							String replace = Integer.toString(accno) + "," + name + "," + Integer.toString(bal1);
							updateAcountCSV(search, replace);
							updateAcountStatementCSV(LocalDateTime.now(), "Cr", acno, amt, bal1, mode);// Credit when
																										// money is
																										// added
						}
						if (txnType == "withdraw") {
							int bal1 = bal - amt;
							String replace = Integer.toString(accno) + "," + name + "," + Integer.toString(bal1);
							updateAcountCSV(search, replace);
							updateAcountStatementCSV(LocalDateTime.now(), "Dr", acno, amt, bal1, mode);// Debit when
																										// money is
																										// added
						}
						break;
					}

					line = br.readLine();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else
			System.out.println("......Account dosen't Exist");

	}

	
	private static void updateAcountStatementCSV(LocalDateTime date, String string, int acno, int amt, int bal1,
			String mode) {
		List<String> list = Arrays.asList(date.toString(), string, Integer.toString(acno), Integer.toString(amt),
				Integer.toString(bal1), mode);
		try {
			int x = 5;
			FileWriter fw = new FileWriter("account_statement.csv", true);// since a file already exist so we need to
																			// append the data to
			// the file, hence true is used to append the data
			for (String o : list) {
				fw.append(o);
				if (x-- != 0)// to add ',' only upto 5 places
					fw.append(",");// Delimiter to separate the text with columns in table
			}
			fw.append("\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void checkBalance(int accno) {
		if (isAccountExist(accno)) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line = br.readLine();//to read file line by line
				line = br.readLine();// to skip to next line other wise column names will raise ex(NumberformatEx)
										// bcuz first row is heading of col which are in string
				while (line != null) {
					String[] str = line.split(",");
					int accNo = Integer.parseInt(str[0]);
					//String name = str[1];
					int bal = Integer.parseInt(str[2]);
					if (accno == accNo)
						System.out.println("Balance of Account No " + accno + " is " + bal);
					line = br.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else
			System.out.println("......Account dosen't Exist");
	}

	
	private static void updateAcountCSV(String search, String replace) {
		try {
			FileReader fr = new FileReader(file);
			String s;
			String str = "";
			try (BufferedReader br = new BufferedReader(fr)) {
				while ((s = br.readLine()) != null) {
					str = str + s + "\n";
				}
				str = str.replaceAll(search, replace);
				FileWriter fw = new FileWriter(file);
				fw.write(str);
				fw.close();
				br.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	static boolean isAccountExist(int accNo) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			line = br.readLine();// to skip to next line other wise column names will raise ex(NumberformatEx)
									// bcuz first row is heading of col which are in string
			while (line != null) {
				String[] str = line.split(",");
				int accno = Integer.parseInt(str[0]);
				if (accno == accNo)
					return true;
				line = br.readLine();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;

	}

}