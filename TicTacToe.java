
import java.util.Scanner;
import java.util.Random;
import java.lang.Integer;

public class TicTacToe
{

	//public char[][] gameboardN = new char[11][11];

	
	public static void main(String[] args)
	{
			
		char[][] gameboardN = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				        { ' ', '1', ' ', '|', ' ', '2', ' ', '|', ' ', '3', ' ' },
				        { '-', '-', '-', '+', '-', '-', '-', '+', '-', '-', '-' },
				        { ' ', '4', ' ', '|', ' ', '5', ' ', '|', ' ', '6', ' ' },
				        { '-', '-', '-', '+', '-', '-', '-', '+', '-', '-', '-' },
				        { ' ', '7', ' ', '|', ' ', '8', ' ', '|', ' ', '9', ' ' },
			   	        { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };

		char[][] gameboard = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			      	       { ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ' },
			               { '-', '-', '-', '+', '-', '-', '-', '+', '-', '-', '-' },
			      	       { ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ' },
			      	       { '-', '-', '-', '+', '-', '-', '-', '+', '-', '-', '-' },
			      	       { ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ' },
			      	       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
	
		boolean gameOver = false;
		String arg1 = "";
		int arg2 = 0;
		if(args.length == 2)
		{
			arg1 = args[0];
			arg2 = Integer.parseInt(args[1]);
		}
		else if(args.length == 1)
			arg1 = args[0];

	//	System.out.println(arg1);	
		if(args.length == 0)
		{
			//human vs human
			int moves = 0;
			Player p1 = new Human();
			Player p2 = new Human();
			System.out.println("Gameboard: ");
			printgb(gameboardN);
			System.out.println();
			printgb(gameboard);
			while(gameOver == false)
			{
				Scanner scan = new Scanner(System.in);
				System.out.println("Player 1's Turn");		
				makeMove(gameboard, p1, 1);
				if(checkForWin(gameboard) == 'X')
					gameOver = true;

				printgb(gameboard);
				moves++;	
				if(moves == 9 && gameOver == false)
				{
					System.out.println("CAT! It is a tie!");
					gameOver = true;
				}
				else if(gameOver == false)
				{
					System.out.println("\nPlayer 2's Turn");
					makeMove(gameboard, p2, 2);
					if(checkForWin(gameboard) == 'O')
						gameOver = true;
					
					moves++;
					printgb(gameboard);
				}
		
			}
			System.out.println("\n\nThanks for Playing! ");

		}
		else if(args.length == 2)
		{
			if(arg1.equals("-c") == false)
			{
				System.out.println(">Unknown argument\nUsage: java TicTacToe [-c [1|2]]");
				System.exit(0);
			}
			if(arg2 < 1 || arg2 > 2)
			{
				System.out.println(">Unknown argument\nUsgae: java TicTacToe [-c [1|2]]");
				System.exit(0);
			}

			Player p1, p2;	
			if(arg2 == 1)
			{
				p1 = new Computer();
				p2 = new Human();
			}
			else if(arg2 == 2)
			{
				p1 = new Human();
				p2 = new Computer();
			}
			else
			{
				System.out.println("invalid second argument\nYou are Player 1\n");
				p1 = new Human();
				p2 = new Computer();
			}
			printgb(gameboardN);
			//printgb(gameboard);
			int moves = 0;
			while (gameOver == false)
			{

				makeMove(gameboard, p1, 1);
				if(checkForWin(gameboard) == 'X')
					gameOver = true;
					
				moves++;
				printgb(gameboard);
				if(moves == 9 && gameOver == false)
				{
					System.out.println("CAT! It is a tie!");
					gameOver = true;
				}
				else if(gameOver == false)	
				{
					makeMove(gameboard, p2, 2);
					if(checkForWin(gameboard) == 'O')
						gameOver = true;
					moves++;
					printgb(gameboard);
				}

				//System.out.println("\033[H\033[2J");
				//printgb(gameboardN);
				//printgb(gameboard);
			}
			
			System.out.println("GAME OVER!!\n\nThanks for playing!\n");	


		}
		else if(args.length == 1)
		{
			if(arg1.equals("-c"))
			{
				Player p1 = new Computer();
				Player p2 = new Computer();	
				int moves = 0;	
				while(gameOver == false)
				{
					makeMove(gameboard, p1, 1);
					if(checkForWin(gameboard) == 'X')
						gameOver = true;
					
					moves++;
					printgb(gameboard);
					if(moves == 9 && gameOver == false)
					{
						System.out.println("CAT! It is a tie!");
						gameOver = true;
					}
					else if(gameOver == false)
					{
						makeMove(gameboard, p2, 2);	
						moves++;
						printgb(gameboard);
					}
			
					if(checkForWin(gameboard) == 'O')
						gameOver = true;
					
					else {}
					//System.out.println("Move Count: " + moves + "\nGame Over = " + gameOver);
				}

			}
			else				
				System.out.println("-Unknown Flag, program closed.\nUsage: java TicTacToe [-c [1|2]] \n");
	
		}
		else if(args.length == 3 && args[2].equals("-a"))
		{
			System.out.println("Advanced mode selected.");	
		}
		else
		{
			System.out.println("Unknown Flag, program closed.\nUsage: java TicTacToe [-c [1|2]] \n");
		}

	
	

	}
			
		

	//returns X if Player 1 wins, O for Player 2 win, NULL for no win 
	private static char checkForWin(char[][] gb)
	{
		//check for all possible win positions
		//1, 2, 3 win	
		if(gb[1][1] == 'X' && gb[1][5] == 'X' && gb[1][9] == 'X')
		{
			System.out.println("Player 1 wins!");
			return 'X';
		}
		// 4, 5, 6
		else if(gb[3][1] == 'X' && gb[3][5] == 'X' && gb[3][9] == 'X')
		{
			System.out.println("Player 1 wins!");
			return 'X';
		}
		//7, 8, 9
		else if(gb[5][1] == 'X' && gb[5][5] == 'X' && gb[5][9] == 'X')
		{
			System.out.println("Player 1 wins!");
			return 'X';
		}
		//1, 4, 7
		else if(gb[1][1] == 'X' && gb[3][1] == 'X' && gb[5][1] == 'X')
		{
			System.out.println("Player 1 wins!");
			return 'X';
		}
		//2, 5, 8
		else if(gb[1][5] == 'X' && gb[3][5] == 'X' && gb[5][5] == 'X')
		{
			System.out.println("Player 1 wins!");
			return 'X';
		}
		//3, 6, 9
		else if(gb[1][9] == 'X' && gb[3][9] == 'X' && gb[5][9] == 'X')
		{
			System.out.println("Player 1 wins!");
			return 'X';
		}
		//1, 5, 9
		else if(gb[1][1] == 'X' && gb[3][5] == 'X' && gb[5][9] == 'X')
		{
			System.out.println("Player 1 wins!");
			return 'X';
		}
		//3, 5, 7
		else if(gb[1][9] == 'X' && gb[3][5] == 'X' && gb[5][1] == 'X')
		{
			System.out.println("Player 1 wins!");
			return 'X';
		}

		//1, 2, 3 win	
		else if(gb[1][1] == 'O' && gb[1][5] == 'O' && gb[1][9] == 'O')
		{
			System.out.println("Player 2 wins!");
			return 'O';
		}
		// 4, 5, 6
		else if(gb[3][1] == 'O' && gb[3][5] == 'O' && gb[3][9] == 'O')
		{
			System.out.println("Player 2 wins!");
			return 'O';
		}
		//7, 8, 9
		else if(gb[5][1] == 'O' && gb[5][5] == 'O' && gb[5][9] == 'O')
		{
			System.out.println("Player 2 wins!");
			return 'O';
		}
		//1, 4, 7
		else if(gb[1][1] == 'O' && gb[3][1] == 'O' && gb[5][1] == 'O')
		{
			System.out.println("Player 2 wins!");
			return 'O';
		}
		//2, 5, 8
		else if(gb[1][5] == 'O' && gb[3][5] == 'O' && gb[5][5] == 'O')
		{
			System.out.println("Player 2 wins!");
			return 'O';
		}
		//3, 6, 9
		else if(gb[1][9] == 'O' && gb[3][9] == 'O' && gb[5][9] == 'O')
		{
			System.out.println("Player 2 wins!");
			return 'O';
		}
		//1, 5, 9
		else if(gb[1][1] == 'O' && gb[3][5] == 'O' && gb[5][9] == 'O')
		{
			System.out.println("Player 2 wins!");
			return 'O';
		}
		//3, 5, 7
		else if(gb[1][9] == 'O' && gb[3][5] == 'O' && gb[5][1] == 'O')
		{
			System.out.println("Player 2 wins!");
			return 'O';
		}
		else
			return ' ';
			


	}

	private static void printgb(char[][] gb)
	{
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 11; j++)
				System.out.print(gb[i][j]);
							
			System.out.println();
		}
	}

	private static boolean spotIsOpen(char spot)
	{

		if(spot == ' ')
			return true;
		else
			return false;
	}		


	private static void makeMove(char[][] gb, Player p, int plyr)
	{
		Scanner input = new Scanner(System.in);
		char symbol = ' ';
		if(plyr == 1)
			symbol = 'X';
		else if(plyr == 2)
			symbol = 'O';
		
			
		if(p instanceof Human)
		{
			boolean moveMade = false;
			int count = 0;
			while(moveMade == false)
			{
				if(count > 0)
					System.out.println("Spot does not exist or is taken.");

				//code for moving human piece
				System.out.print("Please enter spot to move (1-9): ");
				int spot = input.nextInt();
				System.out.printf("You Entered %d\n", spot);

				switch(spot) 
				{
				case 1:
					if(spotIsOpen(gb[1][1]))
					{
						gb[1][1] = symbol;
						moveMade = true;
					}				
					break;	
				case 2:
					if(spotIsOpen(gb[1][5]))
					{
						gb[1][5] = symbol;
						moveMade = true;
					}	
					break;	
				case 3:
					if(spotIsOpen(gb[1][9]))
					{
						gb[1][9] = symbol;
						moveMade = true;
					}	
					break;		
				case 4:
					if(spotIsOpen(gb[3][1]))
					{
						gb[3][1] = symbol;
						moveMade = true;
					}	
					break;	
				case 5:
					if(spotIsOpen(gb[3][5]))
					{
						gb[3][5] = symbol;
						moveMade = true;
					}	
					break;		
				case 6:
					if(spotIsOpen(gb[3][9]))
					{
						gb[3][9] = symbol;
						moveMade = true;
					}	
					break;		
				case 7:
					if(spotIsOpen(gb[5][1]))
					{
						gb[5][1] = symbol;
						moveMade = true;
					}	
					break;	
				case 8:
					if(spotIsOpen(gb[5][5]))
					{
						gb[5][5] = symbol;
						moveMade = true;
					}	
					break;		
				case 9:
					if(spotIsOpen(gb[5][9]))
					{
						gb[5][9] = symbol;
						moveMade = true;
					}	
					break;
				default:
					System.out.println("Spot does not exist\nPlease pick a different spot");
					break;
	
				}

				count++;
			}
		}
		else if(p instanceof Computer)
		{
			int otherPlyr = 0;
				//code for moving computer piece
			if(plyr == 1)
			{
				otherPlyr = 2;
				symbol = 'X';
			}
			else if(plyr == 2)
			{
				symbol = 'O';
				otherPlyr = 1;
			}

			Random rand = new Random();
			System.out.println("Computer is making a move");
			int spot = 0, count = 0;
			boolean moveMade = false;

			if(checkForWinningSpot(gb, plyr) > 0)				//first check for winning spot, if none found it returns 0 else return spot
			{
				System.out.println("Found winning spot");
				spot = checkForWinningSpot(gb, plyr);	
			}
			else if(checkForWinningSpot(gb, otherPlyr) > 0)			//then check for defence spot, if one found it returns 0 else return spot
			{
				System.out.println("Blocked winning spot");
				spot = checkForWinningSpot(gb, otherPlyr);
			}
			else if(gb[3][5] == ' ')				//if still not spot check for middle is open
				spot = 5;
			else							//if still no spot choose randomly
				spot = rand.nextInt(9) + 1;
		
			while(moveMade == false)
			{
				if(count > 0)
				{
					spot = rand.nextInt(9) + 1;
				}	
				switch(spot) 
				{
				case 1:
					if(spotIsOpen(gb[1][1]))
					{
						gb[1][1] = symbol;
						moveMade = true;
					}
					break;		
				case 2:
					if(spotIsOpen(gb[1][5]))
					{
						gb[1][5] = symbol;
						moveMade = true;
					}
					break;
				case 3:
					if(spotIsOpen(gb[1][9]))
					{
						gb[1][9] = symbol;
						moveMade = true;
					}
					break;		
				case 4:
					if(spotIsOpen(gb[3][1]))
					{
						gb[3][1] = symbol;
						moveMade = true;	
					}
					break;	
				case 5:
					if(spotIsOpen(gb[3][5]))
					{
						gb[3][5] = symbol;
						moveMade = true;
					}
					break;		
				case 6:
					if(spotIsOpen(gb[3][9]))
					{
						gb[3][9] = symbol;
						moveMade = true;
					}
					break;		
				case 7:
					if(spotIsOpen(gb[5][1]))
					{
						gb[5][1] = symbol;
						moveMade = true;
					}
					break;	
				case 8:
					if(spotIsOpen(gb[5][5]))
					{
						gb[5][5] = symbol;
						moveMade = true;
					}
					break;		
				case 9:
					if(spotIsOpen(gb[5][9]))
					{
						gb[5][9] = symbol;
						moveMade = true;
					}
					break;
				default:
					break;
				}
				count++;
				if(count > 10000)
					moveMade = true;									

			}
		}
	
	}
	//returns open spot if there is one, returns 0 if none
	private static int checkForWinningSpot(char[][] gb, int p)
	{
		char symbol = 'X';
		if(p == 2)
			symbol = 'O';
		
		
		for(int i = 1; i <= 9; i++)
		{
			//check each spot to see if its winning
			switch(i)
			{
				case 1:
					if(spotIsOpen(gb[1][1]) && gb[3][1] == symbol && gb[5][1] == symbol)
						return i;
					else if(spotIsOpen(gb[1][1]) && gb[1][5] == symbol && gb[1][9] == symbol)
						return i;
					else if(spotIsOpen(gb[1][1]) && gb[3][5] == symbol && gb[5][9] == symbol)
						return i;			
					else
						break;
				case 2:		
					if(spotIsOpen(gb[1][5]) && gb[1][1] == symbol && gb[1][9] == symbol)
						return i;
					else if(spotIsOpen(gb[1][5]) && gb[3][5] == symbol && gb[5][5] == symbol)
						return i;
					else
						break;
				case 3:
					if(spotIsOpen(gb[1][9]) && gb[1][5] == symbol && gb[1][1] == symbol)
						return i;
					else if(spotIsOpen(gb[1][9]) && gb[3][9] == symbol && gb[5][9] == symbol)
						return i;
					else if(spotIsOpen(gb[1][9]) && gb[3][5] == symbol && gb[5][1] == symbol)
						return i;
					else
						break;
				case 4:
					if(spotIsOpen(gb[3][1]) && gb[1][1] == symbol && gb[5][1] == symbol)
						return i;
					else if(spotIsOpen(gb[3][1]) && gb[3][5] == symbol  && gb[3][9] == symbol)
						return i;
					else
						break;
				case 5:
					if(spotIsOpen(gb[3][5]) && gb[3][1] == symbol && gb[3][9] == symbol)
						return i;
					else if(spotIsOpen(gb[3][5]) && gb[1][5] == symbol && gb[5][5] == symbol)
						return i;
					else if(spotIsOpen(gb[3][5]) && gb[1][1] == symbol && gb[5][9] == symbol)
						return i;
					else if(spotIsOpen(gb[3][5]) && gb[1][9] == symbol && gb[5][1] == symbol)
						return i;
					else
						break;
				case 6:
					if(spotIsOpen(gb[3][9]) && gb[1][9] == symbol && gb[5][9] == symbol)
						return i;
					else if(spotIsOpen(gb[3][9]) && gb[3][5] == symbol && gb[3][1] == symbol)
						return i;
					else
						break;
				case 7:	
					if(spotIsOpen(gb[5][1]) && gb[3][1] == symbol && gb[1][1] == symbol)
						return i;
					else if(spotIsOpen(gb[5][1]) && gb[5][5] == symbol && gb[5][9] == symbol)
						return i;
					else if(spotIsOpen(gb[5][1]) && gb[3][5] == symbol && gb[1][9] == symbol)
						return i;
					else
						break;
				case 8:
					if(spotIsOpen(gb[5][5]) && gb[5][1] == symbol && gb[5][9] == symbol)
						return i;
					else if(spotIsOpen(gb[5][5]) && gb[3][5] == symbol && gb[1][5] == symbol)
						return i;
					else
						break;
				case 9:
					if(spotIsOpen(gb[5][9]) && gb[5][5] == symbol && gb[5][1] == symbol)
						return i;
					else if(spotIsOpen(gb[5][9]) && gb[3][9] == symbol  && gb[1][9] == symbol)
						return i;
					else if(spotIsOpen(gb[5][9]) && gb[3][5] == symbol && gb[1][1] == symbol)
						return i;
					else
						break;
			}	

			//post switch			
		}

		return 0;		
		
		
	}

		
	

}

class Player
{
	boolean isP1 = false;
	public Player()
	{
		
	}

	public static void makeP1(Player p)
	{
		p.isP1 = true;
	}

}

class Computer extends Player
{
	public Computer()
	{

	}
	private char symbol = 'O';

}

class Human extends Player
{
	public Human()
	{

	}
	private char symbol = 'X';

}

