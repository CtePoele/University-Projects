// Created by: Christopher tePoele - cpt150130
// CS 4348.501
// 10/25/19

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Project2 implements Runnable
{
	private int num;
	private static Semaphore tellerSem = new Semaphore( 0, true );
	private static Semaphore officerSem = new Semaphore( 0, true );
	private static Semaphore readySem = new Semaphore( 0, true );
	private static Semaphore readyOSem = new Semaphore( 0, true );
	private static Semaphore editIDSem = new Semaphore( 1, true );
	private static Semaphore registeredCID = new Semaphore(0, true );
	private static Semaphore gotCID = new Semaphore( 0, true );
	private static Semaphore registeredTID = new Semaphore( 0, true );
	private static Semaphore gotTID = new Semaphore( 0, true );
	private static Semaphore registeredOID = new Semaphore( 0, true );
	private static Semaphore gotOID = new Semaphore( 0, true );
	private static Semaphore registeredOCID = new Semaphore( 0, true );
	private static Semaphore gotOCID = new Semaphore( 0, true );
	private static Semaphore registeredDetails = new Semaphore( 0, true );
	private static Semaphore gotDetails = new Semaphore( 0, true );
	static int customerID;
	static int tellerID;
	static int officerID;
	static int transactionAmount;
	static int customerBalance;
	static boolean isDeposit;
	static int endBalCustomer0;
	static int endBalCustomer1;
	static int endBalCustomer2;
	static int endBalCustomer3;
	static int endBalCustomer4;
	static int endLoanCustomer0;
	static int endLoanCustomer1;
	static int endLoanCustomer2;
	static int endLoanCustomer3;
	static int endLoanCustomer4;
	

	// For Thread Creation
	Project2( int num ){
		this.num = num;
	}

	// All Threads Start Here
	public void run(){
		// New Thread Creation Announcement
		String Name = Thread.currentThread().getName();
		String Type = Name.substring(0, Name.indexOf(" "));
		System.out.println(Name + " created");

		// Thread Role Determination
		
		// Customer Thread Code
		if(Type.contentEquals("Customer")) {
			int balance = 1000;
			int visitNum = 0;
			int task = -1;
			int randAmount = -1;
			int totalLoan = 0;
			Random r = new Random();
			String StringNumber = Name.replaceAll("[^0-9]+", "");
			int Number = Integer.parseInt(StringNumber);
			int localTID = -1;
			int localOID = -1;
			
			// Visit Bank if visitNum != 3
			do {
				// Tally Visit
				visitNum++;

				// Determine Task
				task = r.nextInt(3) + 1;
				
				// Deposit Task
				if(task == 1) {
					randAmount = (r.nextInt(5) + 1) * 100;
					
					// Customer - Bank Teller Interaction
					try {
						tellerSem.acquire();
						readySem.release();
						editIDSem.acquire();
						customerID = Number;
						registeredCID.release();
						gotCID.acquire();
						registeredTID.acquire();
						localTID = tellerID;
						gotTID.release();
						System.out.println(Name + " requests of teller " + localTID + " to make a deposit of $" + randAmount);
						transactionAmount = randAmount;
						customerBalance = balance;
						isDeposit = true;
						registeredDetails.release();
						gotDetails.acquire();
						balance = customerBalance;
						editIDSem.release();
					} catch (InterruptedException e) {
					}
					// Deposit Task Successful
					System.out.println(Name + " gets receipt from teller " + localTID);
				}
				
				// Withdrawal Task
				else if(task == 2) {
					randAmount = (r.nextInt(5) + 1) * 100;
					
					// Customer - Bank Teller Interaction
					try {
						tellerSem.acquire();
						readySem.release();
						editIDSem.acquire();
						customerID = Number;
						registeredCID.release();
						gotCID.acquire();
						registeredTID.acquire();
						localTID = tellerID;
						gotTID.release();
						System.out.println(Name + " requests of teller " + localTID + " to make a withdrawal of $" + randAmount);
						transactionAmount = randAmount;
						customerBalance = balance;
						isDeposit = false;
						registeredDetails.release();
						gotDetails.acquire();
						balance = customerBalance;
						editIDSem.release();
					} catch (InterruptedException e) {
					}
					// Withdrawal Task Successful
					System.out.println(Name + " gets cash and receipt from teller " + localTID);
				}
				
				// Loan Task
				else if(task == 3) {
					randAmount = (r.nextInt(5) + 1) * 100;

					// Customer - Loan Officer Interaction
					try {
						officerSem.acquire();
						readyOSem.release();
						editIDSem.acquire();
						customerID = Number;
						registeredOCID.release();
						gotOCID.acquire();
						registeredOID.acquire();
						localOID = officerID;
						gotOID.release();
						System.out.println(Name + " requests of loan officer " + localOID + " to apply for a loan of $" + randAmount);
						transactionAmount = randAmount;
						customerBalance = balance;
						registeredDetails.release();
						gotDetails.acquire();
						balance = customerBalance;
						editIDSem.release();
					} catch (InterruptedException e) {
					}
					// Loan Task Successful
					totalLoan = totalLoan + randAmount;
					System.out.println(Name + " gets loan from loan officer " + localOID);
				}

			}while(visitNum < 3);
				
			// When Customer has made 3 visits
			System.out.println(Name + " departs the bank");
			
			// Record Thread End Results
			for(int i = 0; i < 5; i++) {
				if(Number == 0) {
					endBalCustomer0 = balance - totalLoan;
					endLoanCustomer0 = totalLoan;
				}	
				if(Number == 1) {
					endBalCustomer1 = balance - totalLoan;
					endLoanCustomer1 = totalLoan;
				}
				if(Number == 2) {
					endBalCustomer2 = balance - totalLoan;
					endLoanCustomer2 = totalLoan;
				}
				if(Number == 3) {
					endBalCustomer3 = balance - totalLoan;
					endLoanCustomer3 = totalLoan;
				}
				if(Number == 4) {
					endBalCustomer4 = balance - totalLoan;
					endLoanCustomer4 = totalLoan;
				}
			}
		}
		
		// Bank Teller Thread Code
		else if(Type.contentEquals("Teller")) {
			String StringNumber = Name.replaceAll("[^0-9]+", "");
			int Number = Integer.parseInt(StringNumber);
			int localCID;
			int localAmount;
			int localBalance;
			boolean localIsDeposit;
			
			// Infinite loop until thread.stop()
			do {
				// Bank Teller - Customer Interaction
				try {
					tellerSem.release();
					readySem.acquire();
					registeredCID.acquire();
					localCID = customerID;
					System.out.println(Name + " begins serving customer " + localCID);
					gotCID.release();
					tellerID = Number;
					registeredTID.release();
					gotTID.acquire();
					registeredDetails.acquire();
					localAmount = transactionAmount;
					localBalance = customerBalance;
					localIsDeposit = isDeposit;
					if(localIsDeposit) {
						System.out.println(Name + " processes the deposit of $" + localAmount + " for customer " + localCID);
						localBalance = localBalance + localAmount;
						customerBalance = localBalance;
					}
					else {
						System.out.println(Name + " processes the withdrawal of $" + localAmount + " for customer " + localCID);
						localBalance = localBalance - localAmount;
						customerBalance = localBalance;
					}
					gotDetails.release();
				} catch (InterruptedException e) {
				}
				// Interaction Successful
			}while(true);
		}
		
		// Loan Officer Thread Code
		else if(Type.contentEquals("Loan")) {
			String StringNumber = Name.replaceAll("[^0-9]+", "");
			int Number = Integer.parseInt(StringNumber);
			int localOCID;
			int localAmount;
			int localBalance;
			
			// Infinite loop until thread.stop()
			do {
				// Loan Officer - Customer Interaction
				try {
					officerSem.release();
					readyOSem.acquire();
					registeredOCID.acquire();
					localOCID = customerID;
					System.out.println(Name + " begins serving customer " + localOCID);
					gotOCID.release();
					officerID = Number;
					registeredOID.release();
					gotOID.acquire();
					registeredDetails.acquire();
					localAmount = transactionAmount;
					localBalance = customerBalance;
					System.out.println(Name + " approves loan for customer " + localOCID);
					localBalance = localBalance + localAmount;
					customerBalance = localBalance;
					gotDetails.release();
				} catch (InterruptedException e) {
				}
				// Interaction Successful
			}while(true);
		}
	}
	
	// Program Starts Here
	public static void main(String args[]){
		
		int i;
		
		// Setup for Customer Threads
		final int NumCustomers = 5;
		Project2 custThr[] = new Project2[NumCustomers];
		Thread myCustThr[] = new Thread[NumCustomers];
		
		// Setup for Bank Teller Threads
		final int NumTellers = 2;
		Project2 tellerThr[] = new Project2[NumTellers];
		Thread myTellerThr[] = new Thread[NumTellers];
		
		// Setup for Loan Officer Threads
		final int NumOfficers = 1;
		Project2 officerThr[] = new Project2[NumOfficers];
		Thread myOfficerThr[] = new Thread[NumOfficers];

		// Creating Customer Threads
		for(i = 0; i < NumCustomers; ++i) {
			custThr[i] = new Project2(i);
			myCustThr[i] = new Thread( custThr[i] );
			myCustThr[i].setName("Customer " + i);
			myCustThr[i].start();
		}
		
		// Creating Bank Teller Threads
		for(i = 0; i < NumTellers; ++i) {
			tellerThr[i] = new Project2(i);
			myTellerThr[i] = new Thread( tellerThr[i] );
			myTellerThr[i].setName("Teller " + i);
			myTellerThr[i].start();
		}
		
		// Creating Loan Officer Threads
		for(i = 0; i < NumOfficers; ++i) {
			officerThr[i] = new Project2(i);
			myOfficerThr[i] = new Thread( officerThr[i] );
			myOfficerThr[i].setName("Loan Officer " + i);
			myOfficerThr[i].start();
		}
		
		// Wait here until all Customer Threads are finished
		for(i = 0; i < NumCustomers; i++) {
			try {
				myCustThr[i].join();
				System.out.println("Customer " + i + " is joined by main");
			} catch (InterruptedException e) {
			}
		}
		
		// Stop Bank Teller Threads
		for(i = 0; i < NumTellers; i++) {
			myTellerThr[i].stop();
			System.out.println("Teller " + i + " is joined by main");
		}
		
		// Stop Loan Officer Threads
		for(i = 0; i < NumOfficers; i++) {
			myOfficerThr[i].stop();
			System.out.println("Loan Officer " + i + " is joined by main");
		}
		
		// Print Bank Simulation Summary
		System.out.println();
		System.out.println();
		System.out.println("		Bank Simulation Summary");
		System.out.println();
		System.out.println("		Ending balance	Loan Amount");
		System.out.println();
		System.out.println("Customer 0	" + endBalCustomer0 + "		" + endLoanCustomer0);
		System.out.println("Customer 1	" + endBalCustomer1 + "		" + endLoanCustomer1);
		System.out.println("Customer 2	" + endBalCustomer2 + "		" + endLoanCustomer2);
		System.out.println("Customer 3	" + endBalCustomer3 + "		" + endLoanCustomer3);
		System.out.println("Customer 4	" + endBalCustomer4 + "		" + endLoanCustomer4);
		System.out.println();
		System.out.println("Totals		" + (endBalCustomer0 + endBalCustomer1 + endBalCustomer2 + endBalCustomer3 + endBalCustomer4) + "		" + (endLoanCustomer0 + endLoanCustomer1 + endLoanCustomer2 + endLoanCustomer3 + endLoanCustomer4));

		// Program Finished
		return;
	}
}