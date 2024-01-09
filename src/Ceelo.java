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
        System.out.println("Welcome to the table of CEE-LOOO!!!");
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
        System.out.println(die.getDice1());
        System.out.println(die.getDice2());
        System.out.println(die.getDice3());
        if (Die.win == 0){
            banker.incrementNumOfChips(wage1);
            banker.incrementNumOfChips(wage2);
            banker.incrementNumOfChips(wage3);
            player1.decreaseNumOfChips(wage1);
            player2.decreaseNumOfChips(wage2);
            player3.decreaseNumOfChips(wage3);
            System.out.println("Banker won");
        } else if (Die.win == 1){
            player1.incrementNumOfChips(wage1);
            player2.incrementNumOfChips(wage2);
            player3.incrementNumOfChips(wage3);
            banker.decreaseNumOfChips(wage1 + wage2 + wage3);
            System.out.println("Players won");
        } else if (Die.win == 2){
            System.out.println("You got lucky");
        }
    }
    public int getWagers(){
        System.out.print("Player, how many chips would you like to wager for this round? ");
        int num = scan.nextInt();
        return num;
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
