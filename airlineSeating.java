package finalProject;
import java.util.Scanner;

public class airlineSeating {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner (System.in);
		String seatTaken = "0";
		boolean notReadyToQuit = true;
		int fcRow = 0;
		int fcSeat = 0;
		int ecRow = 0;
		int ecSeat = 0;
		int [] fcReturnArray = new int [2];
		int [] ecReturnArray = new int [2];

		String [] [] firstClassSeating = new String [5][4]; 
		String [] [] economyClassSeating = new String [15][6];


		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 4; k++) {
				firstClassSeating[i][k] = seatTaken;
			}		
		}
		for (int i = 0; i < 15; i++) {
			for (int k = 0; k < 6; k++) {
				economyClassSeating[i][k] = seatTaken;
			}		
		}

		System.out.println("Welcome to the Airline Seating Program.");

		do {
			System.out.println("\nWould you like to:\n1. Add Passengers?\n2. Show Seating?\n3. Quit?");
			System.out.println("\nFirst Class --> Row: " + fcRow + " Seat: " + fcSeat);
			System.out.println("Economy Class --> Row: " + ecRow + " Seat: " + ecSeat);


			String userInput = keyboard.next();

			if (userInput.equals("1")) {
				System.out.println("You are now adding passengers.");
				System.out.println("What class would you like to be seated in?\n1. First \nor \n2. Economy?");
				userInput = keyboard.next();
				if (userInput.equals("1") || userInput.equals("First") || userInput.equals("FIRST") || userInput.equals("first") || userInput.equals("F") || userInput.equals("f")) {
					fcReturnArray = addPassengers(firstClassSeating, economyClassSeating, fcRow, fcSeat, fcReturnArray, userInput);

				}
				else {
					ecReturnArray = addPassengers(firstClassSeating, economyClassSeating, ecRow, ecSeat, ecReturnArray, userInput);

				}

				if (userInput.equals("1") || userInput.equals("First") || userInput.equals("FIRST") || userInput.equals("first") || userInput.equals("F") || userInput.equals("f")) {
					fcRow = fcReturnArray [0];
					fcSeat = fcReturnArray [1];
				}
				else {
					ecRow = ecReturnArray [0];
					ecSeat = ecReturnArray [1];
				}

			}
			else if (userInput.equals("2")) {
				showSeating(firstClassSeating, economyClassSeating);
			}

			else if (userInput.equals("3")) {
				quit();
				notReadyToQuit = false;
			}
			else {
				System.out.println("Invalid Entry.");

			}
		} while(notReadyToQuit);

	}

	public static int[] addPassengers(String [][] firstClassSeating, String [][] economyClassSeating, int row, int seat, int [] returnArray, String subUserInput) {
		Scanner subKeyboard = new Scanner (System.in);
		String classSeating = null;
		int numberOfPassengers;
		String seatingPreference;
		String passengerDemands = null;
		boolean inputOutOfRange = true;

		//---------------------------------------------------First Class------------------------------------------------------------------------------
		if (subUserInput.equals("1") || subUserInput.equals("First") || subUserInput.equals("FIRST") || subUserInput.equals("first") || subUserInput.equals("F") || subUserInput.equals("f")) {
			classSeating = "F";
			System.out.println("First Class.\n");
			System.out.println("How many people are travelling together in your group?\nIn first class, we can only seat up to 2 passengers together at most.");
			subUserInput = subKeyboard.next();

			if (subUserInput.equals("1")) {
				numberOfPassengers = 1;
				System.out.println("1 Passenger\n");

				System.out.println("Seating Preference:\n1. Aisle?\nor\n2. Window?");
				subUserInput = subKeyboard.next();
				if (subUserInput.equals("1") ||  subUserInput.equals("Aisle") || subUserInput.equals("AISLE") || subUserInput.equals("aisle") ||subUserInput.equals("A") || subUserInput.equals("a")) {
					seatingPreference = "Aisle";
					System.out.println("Aisle Seating\n");

					passengerDemands = classSeating + "." + numberOfPassengers + "." + seatingPreference;
					System.out.println("You have requested seating preference:\n" + passengerDemands);
					inputOutOfRange = false;
					returnArray = seatMatcher(passengerDemands, firstClassSeating, economyClassSeating, row, seat, returnArray);

				}

				else if (subUserInput.equals("2") ||  subUserInput.equals("Window") || subUserInput.equals("WINDOW") || subUserInput.equals("window") ||subUserInput.equals("W") || subUserInput.equals("w")) {
					seatingPreference = "Window";
					System.out.println("Window Seating\n");

					passengerDemands = classSeating + "." + numberOfPassengers + "." + seatingPreference;
					System.out.println("You have requested seating preference: \n" + passengerDemands);
					inputOutOfRange = false;
					returnArray = seatMatcher(passengerDemands, firstClassSeating, economyClassSeating, row, seat, returnArray);
				}
				else {
					System.out.println("Your input was out of range or did not represent a number, please try again");
				}
			}
			else if (subUserInput.equals("2")) {
				numberOfPassengers = 2;
				System.out.println("2 Passengers\n");
				seatingPreference = "Any";
				System.out.println("Any Seating\n");

				passengerDemands = classSeating + "." + numberOfPassengers + "." + seatingPreference;
				System.out.println("You have requested seating preference: \n" + passengerDemands);
				inputOutOfRange = false;
				returnArray = seatMatcher(passengerDemands, firstClassSeating, economyClassSeating, row, seat, returnArray);
			}

			else {
				System.out.println("Your input was out of range or did not represent a number, please try again");
			}
		}
		//---------------------------------------------------Economy Class------------------------------------------------------------------------------
		else {

			classSeating = "E";
			System.out.println("Economy Class.\n");
			System.out.println("How many people are travelling together in your group?\nIn the economy class, we can only seat up to 3 passengers together at most.");

			do {	
				subUserInput = subKeyboard.next();

				if (subUserInput.equals("1") || subUserInput.equals("2")) {
					numberOfPassengers = Integer.parseInt(subUserInput.trim());
					System.out.println(numberOfPassengers+ " Passenger(s)");

					System.out.println("Seating Preference?\n1. Aisle?\nor\n2. Window?\nor\n3. Center?");
					subUserInput = subKeyboard.next();

					if (subUserInput.equals("1") ||  subUserInput.equals("Aisle") || subUserInput.equals("AISLE") || subUserInput.equals("aisle") ||subUserInput.equals("A") || subUserInput.equals("a")) {
						seatingPreference = "Aisle";
						System.out.println("Aisle Seating\n");

						passengerDemands = classSeating + "." + numberOfPassengers + "." + seatingPreference;
						System.out.println("You have requested seating preference: \n" + passengerDemands);
						inputOutOfRange = false;
						returnArray = seatMatcher(passengerDemands, firstClassSeating, economyClassSeating, row, seat, returnArray);
					}

					else if (subUserInput.equals("2") ||  subUserInput.equals("Window") || subUserInput.equals("WINDOW") || subUserInput.equals("window") ||subUserInput.equals("W") || subUserInput.equals("w")) {
						seatingPreference = "Window";
						System.out.println("Window Seating\n");

						passengerDemands = classSeating + "." + numberOfPassengers + "." + seatingPreference;
						System.out.println("You have requested seating preference: \n" + passengerDemands);
						inputOutOfRange = false;
						returnArray = seatMatcher(passengerDemands, firstClassSeating, economyClassSeating, row, seat, returnArray);
					}

					else {
						seatingPreference = "Center";
						System.out.println("Center Seating\n");

						passengerDemands = classSeating + "." + numberOfPassengers + "." + seatingPreference;
						System.out.println("You have requested seating preference: \n" + passengerDemands);
						inputOutOfRange = false;
						returnArray = seatMatcher(passengerDemands, firstClassSeating, economyClassSeating, row, seat, returnArray);

					}
				}

				else if (subUserInput.equals("3")) {

					numberOfPassengers = 3;
					System.out.println(numberOfPassengers+ " Passenger(s)");

					seatingPreference = "Any";
					System.out.println("Any Seating\n");

					passengerDemands = classSeating + "." + numberOfPassengers + "." + seatingPreference;
					System.out.println("You have requested seating preference:\n" + passengerDemands);
					inputOutOfRange = false;
					returnArray = seatMatcher(passengerDemands, firstClassSeating, economyClassSeating, row, seat, returnArray);
				}

				else {
					System.out.println("Your input was out of range or did not represent a number, please try again");
				}

			} while(inputOutOfRange);
		}	

		return returnArray;
	}

	public static int[] seatMatcher(String passengerDemands, String[][] firstClassSeating, String [][]economyClassSeating, int row, int seat, int [] returnArray) {
		int addingSeats = 0;
		boolean searching = true;

		if (passengerDemands.contains("F.")) {
			if (passengerDemands.contains("1.")) {
				addingSeats = 1;


				if (passengerDemands.contains("Aisle")) {
					while (searching) {
						if (seat == 0) {
							seat++;
							returnArray [1] = seat;
						}

						else if (seat == 1) {
							if (firstClassSeating [row][seat].equals("1")) {
								seat++;
								returnArray [1] = seat;
							}	
						}

						else if (seat == 2) {
							if (firstClassSeating [row][seat].equals("1")) {
								seat++;
								returnArray [1] = seat;
							}					
						}

						else if (seat == 3) {
							seat++;
							returnArray [1] = seat;
						}

						if ((seat == 1 || seat == 2) || (row == 4 && seat >= 2) ) {
							searching = false;
						}

						if (seat == 4) {
							returnArray = nextRowCheck(row, seat, returnArray, passengerDemands, addingSeats);
							row = returnArray [0];
							seat = returnArray [1];
						}						
					}
					firstClassSeating [row][seat] = "1";
					seat++;
				}


				if (passengerDemands.contains("Window")) {


					while (searching) {
						if (seat == 0) {
							if (firstClassSeating [row][seat].equals("1")) {
								seat++;
								returnArray [1] = seat;
							}	
						}

						else if (seat == 1) {
							seat++;
							returnArray [1] = seat;
						}

						else if (seat == 2) {
							seat++;
							returnArray [1] = seat;		
						}

						else if (seat == 3) {
							if (firstClassSeating [row][seat].equals("1")) {
								seat++;
								returnArray [1] = seat;
							}
						}

						if ((seat == 0 || seat == 3) || (row == 4 && seat >= 3) ) {
							searching = false;
						}

						if (seat == 4) {
							returnArray = nextRowCheck(row, seat, returnArray, passengerDemands, addingSeats);
							row = returnArray [0];
							seat = returnArray [1];
						}						
					}
					firstClassSeating [row][seat] = "1";
					seat++;
				}
			}

			if (passengerDemands.contains("2.")) {
				addingSeats = 2;

				returnArray = nextRowCheck(row, seat, returnArray, passengerDemands, addingSeats);
				row = returnArray [0];
				seat = returnArray [1];

				firstClassSeating [row][seat] = "1";
				seat++;
				firstClassSeating [row][seat] = "1";
				seat++;
			}
		}

		else if (passengerDemands.contains("E.")) {
			if (passengerDemands.contains("1.")) {
				addingSeats = 1;

				returnArray = nextRowCheck(row, seat, returnArray, passengerDemands, addingSeats);
				row = returnArray [0];
				seat = returnArray [1];

				economyClassSeating [row][seat] = "1";
				seat++;

				if (passengerDemands.contains("Aisle")) {

				}
				else if (passengerDemands.contains("Window")) {

				}
				else if (passengerDemands.contains("Center")) {

				}
			}

			else if (passengerDemands.contains("2.")) {
				addingSeats = 2;

				returnArray = nextRowCheck(row, seat, returnArray, passengerDemands, addingSeats);
				row = returnArray [0];
				seat = returnArray [1];

				economyClassSeating [row][seat] = "1";
				seat++;
				economyClassSeating [row][seat] = "1";
				seat++;
			}

			else if (passengerDemands.contains("3.")) {
				addingSeats = 3;

				returnArray = nextRowCheck(row, seat, returnArray, passengerDemands, addingSeats);
				row = returnArray [0];
				seat = returnArray [1];

				economyClassSeating [row][seat] = "1";
				seat++;
				economyClassSeating [row][seat] = "1";
				seat++;
				economyClassSeating [row][seat] = "1";
				seat++;
			}
		}

		if (passengerDemands.contains("F.")) {
			returnArray [0] = row;
			returnArray [1] = seat;

		}
		else if (passengerDemands.contains("E.")) {
			returnArray [0] = row;
			returnArray [1] = seat;
		}

		return returnArray; 
	}


	public static int[] nextRowCheck(int row, int seat, int [] returnArray, String passengerDemands, int addingSeats) {
		addingSeats--;
		if (passengerDemands.contains("F.")) {

			if ((addingSeats + seat) >= 4 || (addingSeats + seat) >= 4) {
				if (seat == 4) {
					System.out.println("\nYour seating preference cannot be accomodated in this row, you will be seated in the following row.");	
				}

				row++;

				if (passengerDemands.contains("Aisle")) {
					seat = 1;
					returnArray [0] = row;
					returnArray [1] = seat;	
				}
				else {
					seat = 0;					
					returnArray [0] = row;
					returnArray [1] = seat;
				}
			}
		}

		else if (passengerDemands.contains("E.")) {
			if ((addingSeats + seat) >= 6 || (addingSeats + seat) >= 6) {
				if (seat == 6) {
					System.out.println("\nYour seating preference cannot be accomodated in this row, you will be seated in the following row.");	
				}

				row++;
				seat = 0;
				returnArray [0] = row;
				returnArray [1] = seat;
			}
		}

		return returnArray;
	}

	public static void showSeating(String [][] firstClassSeating, String [][] economyClassSeating) {

		for (int i = 0; i < 20 ;i++) {
			System.out.print("\n" + (i+1) + ": ");

			if (i>=5) {

				if (i>=9) {
					for (int k = 0; k <= 3; k++) {
						System.out.print(economyClassSeating[i-5][k]);
						k++;
						System.out.print(economyClassSeating[i-5][k]);
						k++;
						System.out.print(economyClassSeating[i-5][k] + " ");
					}				
				}
				else {
					for (int k = 0; k <= 3; k++) {
						System.out.print(" " + economyClassSeating[i-5][k]);
						k++;
						System.out.print(economyClassSeating[i-5][k]);
						k++;
						System.out.print(economyClassSeating[i-5][k]);
					}
				}
			}
			else {
				for (int k = 0; k <= 2; k++) {
					System.out.print("  " + firstClassSeating[i][k]);
					k++;
					System.out.print(firstClassSeating[i][k]);

				}
			}
		}
	}



	public static void quit() {
		System.out.println("Thanks for using the Airline Seating Program. Goodbye!");
	}


}
