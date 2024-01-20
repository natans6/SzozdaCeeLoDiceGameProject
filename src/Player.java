public class Player {
    private String name;
    private int chipsWagered;
    private int numberOfChips;
    private Die die1;
    private int playerDice1;
    private int playerDice2;
    private int playerDice3;
    private int win;

    public Player(String name, int numberOfChips){
        this.name = name;
        this.numberOfChips = numberOfChips;
        chipsWagered = 0;
    }

    public int getChipsWagered(){
        return chipsWagered;
    }
    public int getNumberOfChips(){
        return numberOfChips;
    }
    public int getPlayerDice1(){
        return playerDice1;
    }
    public int getPlayerDice2(){
        return playerDice2;
    }
    public int getPlayerDice3(){
        return playerDice3;
    }
    public int getPlayerWin(){
        return win;
    }
    public int getPlayerScore(){
        return die1.getScore();
    }
    public String getName(){
        return name;
    }
    public void setChipsWagered(int newChips){
        chipsWagered = newChips;
    }
    // Rolls the 3 die for the player
    public void rollDiesPlayer(){
        die1 = new Die();
        die1.dieSequence();
        playerDice1 = die1.getDice1();
        playerDice2 = die1.getDice2();
        playerDice3 = die1.getDice3();
        win = die1.getWin();
    }
    // Checks if the player has more than 0 chips to continue playing the game
    public boolean checkIfInGame(){
        return numberOfChips > 0;
    }
    // Increments the number of chips for a player based on their bet
    public void incrementNumOfChips(int newChipsWagered){
        numberOfChips += newChipsWagered;
    }
    // Decrements the number of chips for a player based on their bet
    public void decreaseNumOfChips(int newChipsWagered){
        numberOfChips -= newChipsWagered;
    }
}
