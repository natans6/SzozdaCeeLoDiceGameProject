public class Player {
    private String name;
    private int chipsWagered;
    private int numberOfChips;
    private int playerScore;
    private Die die1;
    private int playerDice1;
    private int playerDice2;
    private int playerDice3;
    private int win;

    public Player(String name, int playerScore, int numberOfChips){
        this.name = name;
        this.playerScore = playerScore;
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
    public void setChipsWagered(int newChips){
        chipsWagered = newChips;
    }
    public String getName(){
        return name;
    }

    public void rollDiesPlayer(){
        die1 = new Die();
        die1.dieSequence();
        playerDice1 = die1.getDice1();
        playerDice2 = die1.getDice2();
        playerDice3 = die1.getDice3();
        win = die1.getWin();
    }

    public boolean checkIfInGame(){
        return numberOfChips >= 0;
    }
    public void incrementNumOfChips(int newChipsWagered){
        numberOfChips += newChipsWagered;
    }
    public void decreaseNumOfChips(int newChipsWagered){
        numberOfChips -= newChipsWagered;
    }
}
