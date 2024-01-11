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
        player1.setChipsWagered(getWagers(player1));
        int wage1 = player1.getChipsWagered();
        player2.setChipsWagered(getWagers(player2));
        int wage2 = player2.getChipsWagered();
        player3.setChipsWagered(getWagers(player3));
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
            System.out.println(player1.getName() + " has rolled three dice on the table, eager for the outcome. The outcomes are " + player1.getPlayerDice1() + ", " + player1.getPlayerDice2() + ", and " + player1.getPlayerDice3() + ".");
            player1Conditions(wage1, bankerScore);
            System.out.println("<----------------------------->");
            player2.rollDiesPlayer();
            System.out.println(player2.getName() + " has rolled three dice on the table, eager for the outcome. The outcomes are " + player2.getPlayerDice1() + ", " + player2.getPlayerDice2() + ", and " + player2.getPlayerDice3() + ".");
            player2Conditions(wage2, bankerScore);
            System.out.println("<----------------------------->");
            player3.rollDiesPlayer();
            System.out.println(player3.getName() + " has rolled three dice on the table, eager for the outcome. The outcomes are " + player3.getPlayerDice1() + ", " + player3.getPlayerDice2() + ", and " + player3.getPlayerDice3() + ".");
            player3Conditions(wage3, bankerScore);
            System.out.println("<----------------------------->");
        }
        System.out.println();
        System.out.println("These are how many chips each one of yall have...");
        printInfo();
        System.out.println();
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]");
        System.out.println("AND THAT IS THE END OF THE FIRST ROUND. BE READY FOR THE NEXT ONE...");
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]");
    }
    public int getWagers(Player player){
        System.out.print(player.getName() + ", how many chips would you like to wager for this round? ");
        int num = scan.nextInt();
        return num;
    }
    public void printInfo(){
        System.out.println("The banker has: " + banker.getNumOfChips() + " chips");
        System.out.println(player1.getName() + " has: " + player1.getNumberOfChips() + " chips");
        System.out.println(player2.getName() + " has: " + player2.getNumberOfChips() + " chips");
        System.out.println(player3.getName() + " has: " + player3.getNumberOfChips() + " chips");
    }
    public void bankerWin(int firstWage, int secondWage, int thirdWage){
        banker.incrementNumOfChips(firstWage);
        banker.incrementNumOfChips(secondWage);
        banker.incrementNumOfChips(thirdWage);
        player1.decreaseNumOfChips(firstWage);
        player2.decreaseNumOfChips(secondWage);
        player3.decreaseNumOfChips(thirdWage);
    }
    public void playerWin(int wage, Player pl){
        pl.incrementNumOfChips(wage);
        banker.decreaseNumOfChips(wage);
    }
    public void playerWin(int wage1, int wage2, int wage3){
        player1.incrementNumOfChips(wage1);
        player2.incrementNumOfChips(wage2);
        player3.incrementNumOfChips(wage3);
        banker.decreaseNumOfChips(wage1 + wage2 + wage3);
    }
    public void playerLose(int firstWage, Player player){
        player.decreaseNumOfChips(firstWage);
        banker.incrementNumOfChips(firstWage);
    }
    public void player1Conditions(int wage1, int bankerScore){
        if (player1.getPlayerWin() == 0){
            playerWin(wage1, player1);
            printInfo();
        } else if (player1.getPlayerWin() == 1){
            playerLose(wage1, player1);
            printInfo();
        } else if (player1.getPlayerWin() == 2){
            int player1Score = player1.getPlayerScore();
            System.out.println("As a result of a double, " + player1.getName() + "'s score is: " + player1Score);
            // compare score with JUST banker.
            boolean greaterScore = player1Score > bankerScore;
            if (greaterScore || player1Score == bankerScore){
                playerWin(wage1, player1);
                printInfo();
            } else {
                playerLose(wage1, player1);
                printInfo();
            }
        }
    }
    public void player2Conditions(int wage2, int bankerScore){
        if (player2.getPlayerWin() == 0){
            playerWin(wage2, player2);
            printInfo();
        } else if (player2.getPlayerWin() == 1){
            playerLose(wage2, player2);
            printInfo();
        } else if (player2.getPlayerWin() == 2){
            int player2Score = player2.getPlayerScore();
            System.out.println("As a result of a double, " + player2.getName()+ "'s score is: " + player2Score);
            // compare score with JUST banker.
            boolean greaterScore = player2Score > bankerScore;
            if (greaterScore || player2Score == bankerScore){
                playerWin(wage2, player2);
                printInfo();
            } else {
                playerLose(wage2, player2);
                printInfo();
            }
        }
    }
    public void player3Conditions(int wage3, int bankerScore){
        if (player3.getPlayerWin() == 0){
            playerWin(wage3, player3);
            printInfo();
        } else if (player3.getPlayerWin() == 1){
            playerLose(wage3, player3);
            printInfo();
        } else if (player3.getPlayerWin() == 2){
            int player3Score = player3.getPlayerScore();
            System.out.println("As a result of a double, " + player3.getName() + "'s score is: " + player3Score);
            // compare score with JUST banker.
            boolean greaterScore = player3Score > bankerScore;
            if (greaterScore || (player3Score == bankerScore)){
                playerWin(wage3, player3);
                printInfo();
            } else {
                playerLose(wage3, player3);
                printInfo();
            }
        }
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
