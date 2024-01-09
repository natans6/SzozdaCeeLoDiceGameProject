import java.sql.SQLOutput;
import java.util.Scanner;

public class Ceelo {
    private Player player1;
    private Player player2;
    private Player player3;
    private Banker banker;
    private Die die;
    Scanner scan = new Scanner(System.in);

    public Ceelo(){
        die = new Die();
    }

    public void play(){
        intro();
        roundOne();
    }

    public void intro(){
        System.out.println("Welcome to the game CEE-LOOO!!! \nYou have the chance to either win or lose money...\nPlease only play this game if you are sure.");
        System.out.println("<---------------------------------------------------------------------->");
        askName();
    }
    public void roundOne(){
        player1.setChipsWagered(getWagers());
        int wage1 = player1.getChipsWagered();
        player2.setChipsWagered(getWagers());
        int wage2 = player2.getChipsWagered();
        player3.setChipsWagered(getWagers());
        int wage3 = player3.getChipsWagered();
        die.dieSequence();
        System.out.println("The banker rolled three dice on the table, eager for the outcome. The outcomes are " + die.getDice1() + ", " + die.getDice2() + ", and " + die.getDice3() + ".");
        System.out.println("<---------------------------------------------------------------------->");
        if (Die.win == 0){
            bankerWin(wage1, wage2, wage3);
            printInfo();
        } else if (Die.win == 1){
            playerWin(wage1, wage2, wage3);
            printInfo();
        } else if (Die.win == 2){
            int bankerScore = die.getScore();
            System.out.println("As a result of a double, the banker's score is: " + bankerScore);
            player1.rollDiesPlayer();
            System.out.println("<----------------------------->");
            System.out.println("Player 1 has rolled three dice on the table, eager for the outcome. The outcomes are " + player1.getPlayerDice1() + ", " + player1.getPlayerDice2() + ", and " + player1.getPlayerDice3() + ".");
            System.out.println("<----------------------------->");
            player2.rollDiesPlayer();
            System.out.println("Player 2 has rolled three dice on the table, eager for the outcome. The outcomes are " + player2.getPlayerDice1() + ", " + player2.getPlayerDice2() + ", and " + player2.getPlayerDice3() + ".");
            System.out.println("<----------------------------->");
            player3.rollDiesPlayer();
            System.out.println("Player 3 has rolled three dice on the table, eager for the outcome. The outcomes are " + player3.getPlayerDice1() + ", " + player3.getPlayerDice2() + ", and " + player3.getPlayerDice3() + ".");
            System.out.println("<----------------------------->");
        }
    }
    public int getWagers(){
        System.out.print("Player, how many chips would you like to wager for this round? ");
        int num = scan.nextInt();
        return num;
    }
    public void printInfo(){
        System.out.println("The banker has: " + banker.getNumOfChips() + " chips");
        System.out.println("Player 1 has: " + player1.getNumberOfChips() + " chips");
        System.out.println("Player 2 has: " + player2.getNumberOfChips() + " chips");
        System.out.println("Player 3 has: " + player3.getNumberOfChips() + " chips");
    }
    public void bankerWin(int firstWage, int secondWage, int thirdWage){
        banker.incrementNumOfChips(firstWage);
        banker.incrementNumOfChips(secondWage);
        banker.incrementNumOfChips(thirdWage);
        player1.decreaseNumOfChips(firstWage);
        player2.decreaseNumOfChips(secondWage);
        player3.decreaseNumOfChips(thirdWage);
        System.out.println("Banker won");
    }
    public void playerWin(int firstWage, int secondWage, int thirdWage){
        player1.incrementNumOfChips(firstWage);
        player2.incrementNumOfChips(secondWage);
        player3.incrementNumOfChips(thirdWage);
        banker.decreaseNumOfChips(firstWage + secondWage + thirdWage);
        System.out.println("Players won");
    }
    private void askName(){
        System.out.print("Hello player 1! Please write your desired name: ");
        String name = scan.nextLine();
        player1 = new Player(name, 0, 1000);
        System.out.print("Hello player 2! Please write your desired name: ");
        String name1 = scan.nextLine();
        player2 = new Player(name1, 0, 1000);
        System.out.print("Hello player 3! Please write your desired name: ");
        String name2 = scan.nextLine();
        player3 = new Player(name2, 0, 1000);
        //  make banker
        banker = new Banker(0, 1000);
    }
}
