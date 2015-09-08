import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bank {
	
	public static double balance = 0;
	public static String[] transactions;
	//public static FileReader reader = new FileReader("D:/Programmering/workspace/Bank/transactions.txt");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AddInterest();
		boolean exit = false;
		while(!exit){
		transactions = ReadFromFile();
		exit = MainMenu();
		}		
		BalanceToFile(balance);
		

	}
	public static boolean MainMenu(){
		Scanner intReader = new Scanner(System.in);
		int choice;
		//do{
			System.out.println("Your current funds are: "+balance);
			System.out.println("Welcome to Bankman");
			System.out.println("What would you like to do today?");
			System.out.println("1. Deposit");
			System.out.println("2. Withdraw");
			System.out.println("3. View transactions");
			System.out.println("4. Exit");
			
			choice = intReader.nextInt();
			int amount;
			
			switch(choice){
			case 1:
				System.out.println("How much would you like to deposit?");
				amount = intReader.nextInt();
				Deposit(amount);
				return false;
				//break;
				
			case 2:
				System.out.println("How much would you like to withdraw?");
				amount = intReader.nextInt();
				Withdraw(amount);
				return false;
				//break;
			case 3:
				System.out.println("Your transactions:");
				//transactions = ReadFromFile();
				for(int i = 0; i<transactions.length; i++){
					if(transaction[i] !=null){
					System.out.println(transactions[i]);}
				}
				return false;
								
					
							
			
			default:
				//System.exit(0);
				return true;
			
			}
		//}//while(choice != 3);
		
	}
	
	public static void Deposit(int amount){
		balance += amount;
		System.out.println("You deposited "+amount+"kr");
		TransactionToFile("Deposit: ", amount);
	}
	
	public static void Withdraw(int amount){
		if(balance>= amount){
			balance-=amount;
			System.out.println("You withdrew "+amount+"kr");
			TransactionToFile("Withdrawal: ", amount);
		}
		else
			System.out.println("Balance too low");
		
	}
	
	public static void AddInterest(){
		
		try {
			String filePath = "D:/Programmering/workspace/Bank/balance.txt";
			FileReader reader = new FileReader(filePath);
			BufferedReader buffer = new BufferedReader(reader);
			String balanceStr = buffer.readLine();
			if(balanceStr != null){
				balance = Double.parseDouble(balanceStr);
			}
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		if(balance !=0){
		balance = balance*1.0793;
		balance = (double)Math.round(balance * 100000) / 100000;}
	}
	public static String[] ReadFromFile(){
		transactions = new String[10];
		
		try{
			String filePath = "D:/Programmering/workspace/Bank/transactions.txt";
			FileReader reader = new FileReader(filePath);
			BufferedReader buffer = new BufferedReader(reader);
			String text = "";
			
			
			for(int i = 0; i<transactions.length; i++){
				text = buffer.readLine();				
				transactions[i] = text;	
			}
				
						
			buffer.close();
			reader.close();
		
			
			}catch(Exception ex){
				ex.printStackTrace();		}
		return transactions;
		}
		
		public static void TransactionToFile(String type, int amount){
			try {
				FileWriter transaction = new FileWriter("D:/Programmering/workspace/Bank/transactions.txt");
				BufferedWriter bufferTransaction = new BufferedWriter(transaction);
				String [] temparray = new String[transactions.length];
				for(int i = 0; i < temparray.length; i++){
					if(i!=temparray.length-1){
						temparray[i+1]=transactions[i]; 
											
					}
				}
				temparray[0] = type + amount;
				
				for(String s : temparray){
					if(s!=null){						
					bufferTransaction.write(s);
					bufferTransaction.newLine();
					}
				}
				
				
				
				bufferTransaction.close();
				
							
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		public static void BalanceToFile(double balance){
			
			try {
				FileWriter balanceLog = new FileWriter("D:/Programmering/workspace/Bank/balance.txt");
				BufferedWriter bufferBalance = new BufferedWriter(balanceLog);
				
				String balanceString = Double.toString(balance);
				bufferBalance.write(balanceString);
				bufferBalance.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}


}
