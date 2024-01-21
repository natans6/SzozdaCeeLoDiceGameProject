import java.util.InputMismatchException;
import java.util.Scanner;

public class Ceelo {
    Scanner scan = new Scanner(System.in);
    private Player player1;
    private Player player2;
    private Player player3;
    private Banker banker;
    private int wage1;
    private int wage2;
    private int wage3;
    private int MAX_WAGER_LIMIT;
    private boolean pl1InGame = true;
    private boolean pl2InGame = true;
    private boolean pl3InGame = true;
    private boolean wantToPlay = true;
    private int highestChipCountEver = 0;

    // Constructor
    public Ceelo(){

    }

    public int getHighestChipCountEver() {
        return highestChipCountEver;
    }

    public void play(){
        intro();
        while (wantToPlay){
            mainMenu();
            afterGameEnds();
        }
    }
    public void intro(){
        System.out.println("<---------------------------------------------------------------------->");
        System.out.println("Welcome to the game CEE-LOOO!!! \nYou have the chance to either win or lose money...\nPlease only play this game if you are sure.");
    }
    // Main menu where user decides between starting a new game, viewing the highest score achieved, and quiting the game
    public void mainMenu(){
        System.out.println("<---------------------------------------------------------------------->");
        System.out.println("Would you like to: ");
        System.out.println("(S)tart a new game");
        System.out.println("(V)iew High Score");
        System.out.println("(Q)uit");
        System.out.print("What's your decision: ");
        String answer = scan.nextLine();
        if (answer.equals("S") || answer.equals("s")){
            System.out.println("<---------------------------------------------------------------------->");
            System.out.println("Each player starts with 100 chips each and the banker starts with 1000 chips.");
            System.out.println("Each player and the one banker will roll 3 dices. The combinations will determine if you win or lose your round.");
            System.out.println("Rolling a 4, 5, 6 combination or a triple matching combination results in the player or banker automatically winning ");
            System.out.println("Rolling a 1, 2, 3 combination results in the player or banker automatically losing.");
            System.out.println("If a player or banker rolls two of the same numbers with the last number being different, that third number relates to their 'score.' ");
            System.out.println("This score then gets compared to the banker or vice versa to determine who wins the round.");
            System.out.println("Each player has to place a bet; banker does not...");
            System.out.println("GOOD LUCK!!!");
            System.out.println("<---------------------------------------------------------------------->");
            askName();
            System.out.println("<---------------------------------------------------------------------->");
            rounds();
        } else if (answer.equals("V") || answer.equals("v")){
            System.out.println("<---------------------------------------------------------------------->");
            System.out.println("The high score so far is: " + getHighestChipCountEver() + " chips!");
            System.out.println("<---------------------------------------------------------------------->");
        } else if (answer.equals("Q") || answer.equals("q")){
            System.out.println("Hope you all had fun playing...");
            System.exit(0);
        }
    }
    // Main game loop
    public void rounds(){
        int i = 1;
        while ((banker.checkIfInGame() && (player1.checkIfInGame() || player2.checkIfInGame() || player3.checkIfInGame())) && banker.checkIfInGame()){
            System.out.println("At this time, please input the amount of chips you would like to wager for the round...");
            if (player1.checkIfInGame()){
                System.out.println(player1.getName() + ", this is how many chips you currently have: " + (ConsoleUtility.YELLOW + player1.getNumberOfChips() + ConsoleUtility.RESET));
                player1.setChipsWagered(getWagers(player1));
                wage1 = player1.getChipsWagered();
            } else {
                pl1InGame = false;
                wage1 = 0;
            }
            if (player2.checkIfInGame()){
                System.out.println(player2.getName() + ", this is how many chips you currently have: " + (ConsoleUtility.YELLOW + player2.getNumberOfChips() + ConsoleUtility.RESET));
                player2.setChipsWagered(getWagers(player2));
                wage2 = player2.getChipsWagered();
            } else {
                pl2InGame = false;
                wage2 = 0;
            }
            if (player3.checkIfInGame()){
                System.out.println(player3.getName() + ", this is how many chips you currently have: " + (ConsoleUtility.YELLOW + player3.getNumberOfChips() + ConsoleUtility.RESET));
                player3.setChipsWagered(getWagers(player3));
                wage3 = player3.getChipsWagered();
            } else {
                pl3InGame = false;
                wage3 = 0;
            }
            banker.rollDiesBanker();
            System.out.println("The banker rolled three dice on the table, eager for the outcome. The outcomes are " + (ConsoleUtility.GREEN + banker.getBankerDice1() + ConsoleUtility.RESET)  + ", " + (ConsoleUtility.GREEN + banker.getBankerDice2() + ConsoleUtility.RESET) + ", and " + (ConsoleUtility.GREEN + banker.getBankerDice3() + ConsoleUtility.RESET) + ".");
            System.out.println("<---------------------------------------------------------------------->");
            if (banker.getBankerWin() == 0){
                checkPlayersInGameBankerWin(wage1, wage2, wage3);
            } else if (banker.getBankerWin() == 1){
                checkPlayersInGamePlayerWin(wage1, wage2, wage3);
            } else if (banker.getBankerWin() == 2){
                int bankerScore = banker.getBankerScore();
                System.out.println("As a result of a double, the banker's score is: " + (ConsoleUtility.PURPLE + bankerScore + ConsoleUtility.RESET));
                System.out.println("<----------------------------->");
                if (pl1InGame){
                    try{
                        Thread.sleep(3000);
                        player1.rollDiesPlayer();
                        System.out.println(player1.getName() + " has rolled three dice on the table, eager for the outcome. The outcomes are " + (ConsoleUtility.GREEN + player1.getPlayerDice1() + ConsoleUtility.RESET) + ", " + (ConsoleUtility.GREEN + player1.getPlayerDice2() + ConsoleUtility.RESET) + ", and " + (ConsoleUtility.GREEN + player1.getPlayerDice3() + ConsoleUtility.RESET) + ".");
                        player1Conditions(wage1, bankerScore);
                        System.out.println("<----------------------------->");
                    } catch (Exception e){
                        System.out.println("error");
                    }
                }
                if (pl2InGame){
                    try{
                        Thread.sleep(3000);
                        player2.rollDiesPlayer();
                        System.out.println(player2.getName() + " has rolled three dice on the table, eager for the outcome. The outcomes are " + (ConsoleUtility.GREEN + player2.getPlayerDice1() + ConsoleUtility.RESET) + ", " + (ConsoleUtility.GREEN + player2.getPlayerDice2() + ConsoleUtility.RESET) + ", and " + (ConsoleUtility.GREEN + player2.getPlayerDice3() + ConsoleUtility.RESET) + ".");
                        player2Conditions(wage2, bankerScore);
                        System.out.println("<----------------------------->");
                    } catch (Exception e){
                        System.out.println("error");
                    }
                }
                if (pl3InGame){
                    try {
                        Thread.sleep(3000);
                        player3.rollDiesPlayer();
                        System.out.println(player3.getName() + " has rolled three dice on the table, eager for the outcome. The outcomes are " + (ConsoleUtility.GREEN + player3.getPlayerDice1() + ConsoleUtility.RESET) + ", " + (ConsoleUtility.GREEN + player3.getPlayerDice2() + ConsoleUtility.RESET) + ", and " + (ConsoleUtility.GREEN + player3.getPlayerDice3() + ConsoleUtility.RESET) + ".");
                        player3Conditions(wage3, bankerScore);
                        System.out.println("<----------------------------->");
                    } catch (Exception e){
                        System.out.println("error");
                    }
                }
            }
            try {
                Thread.sleep(3000);
            } catch (Exception e){
                System.out.println("error");
            }
            System.out.println("These are how many chips each one of y'all have...");
            printInfo();
            System.out.println("<----------------------------->");
            System.out.println();
            printEndOfRound(i);
            i++;
            System.out.println();
            System.out.println("<----------------------------->");
            // checking who's in game
            System.out.println("Players who lost:");
            if (!player1.checkIfInGame()){
                System.out.println("Since " + (ConsoleUtility.CYAN + player1.getName() + ConsoleUtility.RESET) + " has no more chips left, the player cannot play anymore. Unlucky...");
            }
            if (!player2.checkIfInGame()){
                System.out.println("Since " + (ConsoleUtility.CYAN + player2.getName() + ConsoleUtility.RESET) + " has no more chips left, the player cannot play anymore. Unlucky...");
            }
            if (!player3.checkIfInGame()){
                System.out.println("Since " + (ConsoleUtility.CYAN + player3.getName() + ConsoleUtility.RESET) + " has no more chips left, the player cannot play anymore. Unlucky...");
            }
            if (!banker.checkIfInGame()){
                System.out.println("Since the " + (ConsoleUtility.CYAN + "banker" + ConsoleUtility.RESET) + " has no more chips left, the banker lost. Unlucky...");
                banker.setNumberOfChips(0);
            }
            System.out.println("<----------------------------->");
            try {
                Thread.sleep(7000);
                try {
                    ConsoleUtility.clearScreen();
                } catch (Exception e) {
                    System.out.println("error");
                }
            } catch (Exception e){
                System.out.println("error");
            }
            System.out.println(ConsoleUtility.PURPLE + "<----------------------------->");
            System.out.println(("FINAL CHIP COUNTS FOR THE ROUND"));
            printInfo();
            System.out.println("<----------------------------->");
            System.out.print(ConsoleUtility.RESET);
        }
        if (!banker.checkIfInGame()){
            System.out.println("Since the banker has no more chips remaining, the players have broken the bank and as a result have won the game. And now lets see who had the most amount of chips!");
            int amountChips1 = player1.getNumberOfChips();
            int amountChips2 = player2.getNumberOfChips();
            int amountChips3 = player3.getNumberOfChips();
            if ((amountChips1 > amountChips2) && (amountChips1 > amountChips3)){
                System.out.println((ConsoleUtility.CYAN + player1.getName() + ConsoleUtility.RESET) + " has won the game with " + player1.getNumberOfChips() + " chips!!! Congratulations and come back next time...");
            } else if ((amountChips2 > amountChips1) && (amountChips2 > amountChips3)){
                System.out.println((ConsoleUtility.CYAN + player2.getName() + ConsoleUtility.RESET) + " has won the game with " + player2.getNumberOfChips() + " chips!!! Congratulations and come back next time...");
            } else if ((amountChips3 > amountChips1) && (amountChips3 > amountChips2)){
                System.out.println((ConsoleUtility.CYAN + player3.getName() + ConsoleUtility.RESET) + " has won the game with " + player3.getNumberOfChips() + " chips!!! Congratulations and come back next time...");
            } else{
                checkTie();
            }
            System.out.println("<----------------------------->");
        } else if (!(player1.checkIfInGame() && player2.checkIfInGame() && player3.checkIfInGame())){
            System.out.println("It seems that all three players have lost all of their chips. Therefore, I declare the " + (ConsoleUtility.CYAN + "banker" + ConsoleUtility.RESET) + " the winner and the players the losers. Get out of here...");
            System.out.println("The banker has won with " + banker.getNumOfChips() + " chips!! Unlucky to the players, maybe next time...");
            System.out.println(ConsoleUtility.PURPLE + "<----------------------------->" + ConsoleUtility.RESET);
        }
        try {
            Thread.sleep(7000);
        } catch (Exception e){
            System.out.println("error");
        }
    }
    // Asks users for wage amount for the round
    public int getWagers(Player player){
        System.out.print(player.getName() + ", how many chips would you like to wager for this round? ");

        int availableChips = player.getNumberOfChips();
        MAX_WAGER_LIMIT = player.getNumberOfChips();
        // Set a limit for wager based on the player's current chip count
        int maxWager = Math.min(availableChips, MAX_WAGER_LIMIT);  // MAX_WAGER_LIMIT is the maximum allowed wager

        // Get player input for wager
        int wager = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter wager (up to " + maxWager + " chips): ");
                wager = scan.nextInt();
                scan.nextLine();

                // Validate the wager to be within the allowed limit
                if (wager >= 0 && wager <= maxWager) {
                    validInput = true;
                } else {
                    System.out.println("Invalid wager. Enter a wager between 0 and " + maxWager + ".");
                }
            } catch (InputMismatchException e) {
                // User entered a non-integer value, prompt for input again
                System.out.println("Invalid input. Please enter a valid integer.");
                scan.nextLine(); // Clear the input buffer
            }
        }

        return wager;
    }
    // Checks who are in the game and then implements the correct incrementation and decremental of chips based on the banker winning the round.
    public void checkPlayersInGameBankerWin(int wage1, int wage2, int wage3){
        System.out.println(ConsoleUtility.CYAN + "The banker has rolled an automatic win roll. Therefore he wins " + (wage1 + wage2 + wage3) + " chips in total!" + ConsoleUtility.RESET);
        if (player1.checkIfInGame() && player2.checkIfInGame() && player3.checkIfInGame()){
            bankerWin(wage1, wage2, wage3);
            printInfo();
        } else if (player1.checkIfInGame() && player2.checkIfInGame()){
            bankerWin(wage1, wage2, 0);
            printInfo();
        } else if (player1.checkIfInGame() && player3.checkIfInGame()){
            bankerWin(wage1, 0, wage3);
            printInfo();
        } else if (player2.checkIfInGame() && player3.checkIfInGame()){
            bankerWin(0, wage2, wage3);
            printInfo();
        } else if (player1.checkIfInGame()){
            bankerWin(wage1, 0, 0);
            printInfo();
        } else if (player2.checkIfInGame()){
            bankerWin(0, wage2, 0);
            printInfo();
        } else if (player3.checkIfInGame()){
            bankerWin(0, 0, wage3);
            printInfo();
        }
        System.out.println("<----------------------------->");
    }
    // Checks who are in the game and then implements the correct incrementation and decremental of chips based on the player winning the round.
    public void checkPlayersInGamePlayerWin(int wage1, int wage2, int wage3){
        System.out.println(ConsoleUtility.CYAN + "The banker has rolled an automatic lose roll. Therefore he loses " + (wage1 + wage2 + wage3) + " chips in total and the players gain their own wages back!" + ConsoleUtility.RESET);
        if (player1.checkIfInGame() && player2.checkIfInGame() && player3.checkIfInGame()){
            playerWin(wage1, wage2, wage3);
            printInfo();
        } else if (player1.checkIfInGame() && player2.checkIfInGame()){
            playerWin(wage1, wage2, 0);
            printInfo();
        } else if (player1.checkIfInGame() && player3.checkIfInGame()){
            playerWin(wage1, 0, wage3);
            printInfo();
        } else if (player2.checkIfInGame() && player3.checkIfInGame()){
            playerWin(0, wage2, wage3);
            printInfo();
        } else if (player1.checkIfInGame()){
            playerWin(wage1, 0, 0);
            printInfo();
        } else if (player2.checkIfInGame()){
            playerWin(0, wage2, 0);
            printInfo();
        } else if (player3.checkIfInGame()){
            playerWin(0, 0, wage3);
            printInfo();
        }
        System.out.println("<----------------------------->");
    }
    // Checks if players have the same amount of chips at the end of the game
    public void checkTie(){
        if (player1.getNumberOfChips() == player2.getNumberOfChips() && player1.getNumberOfChips() == player3.getNumberOfChips()){
            System.out.println("There seems to be a three-way tie between " + (ConsoleUtility.CYAN + player1.getName() + ConsoleUtility.RESET) + ", " + (ConsoleUtility.CYAN + player2.getName() + ConsoleUtility.RESET) + ", and " + (ConsoleUtility.CYAN + player3.getName() + ConsoleUtility.RESET) + ".");
        } else if (player1.getNumberOfChips() == player2.getNumberOfChips()){
            System.out.println("There seems to be a tie between " + (ConsoleUtility.CYAN + player1.getName() + ConsoleUtility.RESET) + " and " + (ConsoleUtility.CYAN + player2.getName() + ConsoleUtility.RESET) + ".");
        } else if (player1.getNumberOfChips() == player3.getNumberOfChips()){
            System.out.println("There seems to be a tie between " + (ConsoleUtility.CYAN + player1.getName() + ConsoleUtility.RESET) + " and " + (ConsoleUtility.CYAN + player3.getName() + ConsoleUtility.RESET) + ".");
        } else if (player2.getNumberOfChips() == player3.getNumberOfChips()){
            System.out.println("There seems to be a tie between " + (ConsoleUtility.CYAN + player2.getName() + ConsoleUtility.RESET) + " and " + (ConsoleUtility.CYAN + player3.getName() + ConsoleUtility.RESET) + ".");
        }
    }
    // Prints amount of chips every player and the banker has
    public void printInfo(){
        System.out.println("The banker has: " + banker.getNumOfChips() + " chips");
        System.out.println(player1.getName() + " has: " + player1.getNumberOfChips() + " chips");
        System.out.println(player2.getName() + " has: " + player2.getNumberOfChips() + " chips");
        System.out.println(player3.getName() + " has: " + player3.getNumberOfChips() + " chips");
        // Update highestChipCountEver for players only
        int currentHighest = Math.max(player1.getNumberOfChips(), Math.max(player2.getNumberOfChips(), player3.getNumberOfChips()));
        highestChipCountEver = Math.max(highestChipCountEver, currentHighest);
    }
    // Prints a message indicating the end of a round
    public void printEndOfRound(int i){
        System.out.print(ConsoleUtility.PURPLE);
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]");
        System.out.println("                  AND THAT IS THE END OF ROUND " + i + "...");
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]");
        System.out.print(ConsoleUtility.RESET);
    }
    // Method for incrementing banker's chips and decrementing player's chips
    public void bankerWin(int firstWage, int secondWage, int thirdWage){
        banker.incrementNumOfChips(firstWage);
        banker.incrementNumOfChips(secondWage);
        banker.incrementNumOfChips(thirdWage);
        player1.decreaseNumOfChips(firstWage);
        player2.decreaseNumOfChips(secondWage);
        player3.decreaseNumOfChips(thirdWage);
    }
    // Method for incrementing player's chips and decrementing banker's chips
    public void playerWin(int wage, Player pl){
        pl.incrementNumOfChips(wage);
        banker.decreaseNumOfChips(wage);
    }
    // Method for incrementing player's chips and decrementing banker's chips if banker automatically loses the round
    public void playerWin(int wage1, int wage2, int wage3){
        player1.incrementNumOfChips(wage1);
        player2.incrementNumOfChips(wage2);
        player3.incrementNumOfChips(wage3);
        banker.decreaseNumOfChips(wage1 + wage2 + wage3);
    }
    // Method for incrementing banker's chips and decrementing player's chips when a player loses the round
    public void playerLose(int firstWage, Player player){
        player.decreaseNumOfChips(firstWage);
        banker.incrementNumOfChips(firstWage);
    }
    // Consists of the conditions needed to be met for a player to win a round; contains the playerWin method, playerLose method, and the comparison of scores with the banker
    public void player1Conditions(int wage1, int bankerScore){
        if (player1.getPlayerWin() == 0){
            playerWin(wage1, player1);
            System.out.println(ConsoleUtility.CYAN + (player1.getName() + " had an automatic win roll! Therefore, he wins " + wage1 + " chips!") + ConsoleUtility.RESET);
            printInfo();
        } else if (player1.getPlayerWin() == 1){
            playerLose(wage1, player1);
            System.out.println(ConsoleUtility.CYAN + (player1.getName() + " had an automatic lose roll. Therefore, he lost " + wage1 + " chips...") + ConsoleUtility.RESET);
            printInfo();
        } else if (player1.getPlayerWin() == 2){
            int player1Score = player1.getPlayerScore();
            System.out.println("As a result of a double, " + player1.getName() + "'s score is: " + (ConsoleUtility.PURPLE + player1Score + ConsoleUtility.RESET));
            // compare score with JUST banker.
            boolean greaterScore = player1Score > bankerScore;
            if (greaterScore || player1Score == bankerScore){
                playerWin(wage1, player1);
                System.out.println(ConsoleUtility.CYAN + (player1.getName() + " had a greater or equal score! Therefore, he wins " + wage1 + " chips!") + ConsoleUtility.RESET);
                printInfo();
            } else {
                playerLose(wage1, player1);
                System.out.println(ConsoleUtility.CYAN + (player1.getName() + " had a lower score! Therefore, he lost " + wage1 + " chips...") + ConsoleUtility.RESET);
                printInfo();
            }
        }
    }
    // Consists of the conditions needed to be met for a player to win a round; contains the playerWin method, playerLose method, and the comparison of scores with the banker
    public void player2Conditions(int wage2, int bankerScore){
        if (player2.getPlayerWin() == 0){
            playerWin(wage2, player2);
            System.out.println(ConsoleUtility.CYAN + (player2.getName() + " had an automatic win roll! Therefore, he wins " + wage2 + " chips!") + ConsoleUtility.RESET);
            printInfo();
        } else if (player2.getPlayerWin() == 1){
            playerLose(wage2, player2);
            System.out.println(ConsoleUtility.CYAN + (player2.getName() + " had an automatic lose roll. Therefore, he lost " + wage2 + " chips...") + ConsoleUtility.RESET);
            printInfo();
        } else if (player2.getPlayerWin() == 2){
            int player2Score = player2.getPlayerScore();
            System.out.println("As a result of a double, " + player2.getName()+ "'s score is: " + (ConsoleUtility.PURPLE + player2Score + ConsoleUtility.RESET));
            // compare score with JUST banker.
            boolean greaterScore = player2Score > bankerScore;
            if (greaterScore || player2Score == bankerScore){
                playerWin(wage2, player2);
                System.out.println(ConsoleUtility.CYAN + (player2.getName() + " had a greater or equal score! Therefore, he wins " + wage2 + " chips!") + ConsoleUtility.RESET);
                printInfo();
            } else {
                playerLose(wage2, player2);
                System.out.println(ConsoleUtility.CYAN + (player2.getName() + " had a lower score! Therefore, he lost " + wage2 + " chips...") + ConsoleUtility.RESET);
                printInfo();
            }
        }
    }
    // Consists of the conditions needed to be met for a player to win a round; contains the playerWin method, playerLose method, and the comparison of scores with the banker
    public void player3Conditions(int wage3, int bankerScore){
        if (player3.getPlayerWin() == 0){
            playerWin(wage3, player3);
            System.out.println(ConsoleUtility.CYAN + (player3.getName() + " had an automatic win roll! Therefore, he wins " + wage3 + " chips!") + ConsoleUtility.RESET);
            printInfo();
        } else if (player3.getPlayerWin() == 1){
            playerLose(wage3, player3);
            System.out.println(ConsoleUtility.CYAN + (player3.getName() + " had an automatic lose roll. Therefore, he lost " + wage3 + " chips...") + ConsoleUtility.RESET);
            printInfo();
        } else if (player3.getPlayerWin() == 2){
            int player3Score = player3.getPlayerScore();
            System.out.println("As a result of a double, " + player3.getName() + "'s score is: " + (ConsoleUtility.PURPLE + player3Score + ConsoleUtility.RESET));
            // compare score with JUST banker.
            boolean greaterScore = player3Score > bankerScore;
            if (greaterScore || (player3Score == bankerScore)){
                playerWin(wage3, player3);
                System.out.println(ConsoleUtility.CYAN + (player3.getName() + " had a greater or equal score! Therefore, he wins " + wage3 + " chips!") + ConsoleUtility.RESET);
                printInfo();
            } else {
                playerLose(wage3, player3);
                System.out.println(ConsoleUtility.CYAN + (player3.getName() + " had a lower score! Therefore, he lost " + wage3 + " chips...") + ConsoleUtility.RESET);
                printInfo();
            }
        }
    }
    // Loads up main menu after game ends using a sleep method
    public void afterGameEnds(){
        System.out.println(ConsoleUtility.PURPLE + "Time to go back to the main menu!" + ConsoleUtility.RESET);
        try{
            Thread.sleep(5000);
            ConsoleUtility.clearScreen();
        } catch (Exception e){
            System.out.println("error");
        }
    }
    public void reset(String name1, String name2, String name3){
        this.player1 = new Player(name1, 100);
        this.player2 = new Player(name2, 100);
        this.player3 = new Player(name3, 100);
        this.banker = new Banker(1000);
    }
    public void resetRoles(){
        pl1InGame = true;
        pl2InGame = true;
        pl3InGame = true;
    }
    // Asks the name of each player and creates the objects for all 3 players and banker consisting of 1000 chips
    private void askName(){
        System.out.print("Hello player 1! Please write your desired name: ");
        String name = scan.nextLine();
        // make players
        player1 = new Player(name, 100);
        System.out.print("Hello player 2! Please write your desired name: ");
        String name1 = scan.nextLine();
        player2 = new Player(name1, 100);
        System.out.print("Hello player 3! Please write your desired name: ");
        String name2 = scan.nextLine();
        player3 = new Player(name2, 100);
        //  make banker
        banker = new Banker(1000);
        reset(name, name1, name2);
        resetRoles();
    }
}
