import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
class QuickStart 
{
    public class TicTacToe
    {
        // Static Methods belong to the class itself and not instances of the class. They are good for utility methods that help others.
        // Only satic context can access these array lists. 
        static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
        static ArrayList<Integer> computerPositions = new ArrayList<Integer>();
        static public boolean sentinel = true;

        public static void main (String[] args) 

        {
            System.out.println("Rules: You must get three in a row to win. If you win you will be notified. If the CPU wins you will also be notified.");
            System.out.println("You will be prompted to enter an integer 1-9. The top left box is 1 the top middle is 2, top right is 3, the left middle is 4, etc.");
            char[][] gameBoard = 
                    {{' ', '|', ' ', '|', ' '}, 
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '},
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '}};
            
            printGameBoard(gameBoard);
            
            // This while loop allows the user and the cpu to take turns.
            while (true)
            {
                String winResult = checkWin();
                checkWin();
                if (winResult.equals("Congratulations you won!") || winResult.equals("You Lost!") || winResult.equals("You've Tied!")) {
                    System.out.println(winResult);
                    System.exit(1);
                }

                // Allows me to use "scan" to read input from user. 
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter where you want to go (1-9): ");
                int userPosition = scan.nextInt();
                
                // Ensures that the same position aren't chosen twice.
                while(playerPositions.contains(userPosition) || computerPositions.contains(userPosition))
                {
                    System.out.println("This spot is taken, please choose another spot.");
                    userPosition = scan.nextInt();
                }
                selectLocation(gameBoard, userPosition, "user");
                // Creates a random object of "Random" class.
                Random rand = new Random();

                //Allows the computer's position to be randomized at 1-9
                int computerPosition = rand.nextInt(9) + 1;

                // Ensures computer doesn't already choose a chosen position.
                while(playerPositions.contains(computerPosition) || computerPositions.contains(computerPosition))
                {
                    computerPosition = rand.nextInt(9) +1;
                }

                selectLocation(gameBoard, computerPosition, "computer");
                printGameBoard(gameBoard);

                checkWin();
                if (winResult.equals("Congratulations you won!") || winResult.equals("You Lost!") || winResult.equals("You've Tied!")) {
                    System.out.println(winResult);
                    break;
                }    
            }
        }
        public static void printGameBoard(char[][] gameBoard)
        {
            for(char[] row : gameBoard) 
            {
                for(char c: row) 
                {
                    System.out.print(c);
                }
                System.out.println();
            }
        }
        // Prints the X's and O's 

        public static void selectLocation(char[][] gameBoard, int position, String player)
        {
            char symbol = ' ';
            if(player.equals("user"))
            {
                symbol = 'X';
                playerPositions.add(position);
            }
            else if(player.equals("computer"))
            {
                symbol = 'O';
                computerPositions.add(position);
            }
            switch(position)
            {
                case 1:
                    gameBoard[0][0] = symbol;
                    break;
                case 2:
                    gameBoard[0][2] = symbol;
                    break;
                case 3:
                    gameBoard[0][4] = symbol;
                    break;
                case 4:
                    gameBoard[2][0] = symbol;
                    break;
                case 5:
                    gameBoard[2][2] = symbol;
                    break;
                case 6:
                    gameBoard[2][4] = symbol;
                    break;
                case 7:
                    gameBoard[4][0] = symbol;
                    break;
                case 8:
                    gameBoard[4][2] = symbol;
                    break;
                case 9:
                    gameBoard[4][4] = symbol;
                    break;
                default:
                    break;
            }
        }
        public static String checkWin()
        {
            // Defining Win Conditions
            List topRow = Arrays.asList(1, 2, 3);
            List midRow = Arrays.asList(4, 5, 6);
            List botRow = Arrays.asList(7, 8, 9);
            List leftCol = Arrays.asList(1, 4, 7);
            List midCol = Arrays.asList(2, 5, 8);
            List rightCol= Arrays.asList(3, 6, 9);
            List topLeftDiagonal = Arrays.asList(1, 5, 9);
            List topRightDiagonal = Arrays.asList(3, 5, 7);

            // This is creating a new list of lists called winCon that will hold all of the win conditions.
            List<List> winCon = new ArrayList<List>();        
            winCon.add(topRow);
            winCon.add(midRow);
            winCon.add(botRow);
            winCon.add(leftCol);
            winCon.add(midCol);
            winCon.add(rightCol);
            winCon.add(topLeftDiagonal);
            winCon.add(topRightDiagonal);

            // Essentially this is declaring a variable of datype List as l and iterating through the win conditions. 
            for (List l : winCon)
            {
                if(playerPositions.containsAll(l))
                {
                    return "Congratulations you won!";
                }
                else if (computerPositions.containsAll(l))
                {
                    return "You Lost!";
                }
                // After the program checks if the user has won or the computer has won, if not, it will check if the board is filled.
                else if (playerPositions.size()+ computerPositions.size() == 9)
                {
                    return "You've Tied!";
                }
            }
            return "";
        }
    }
}