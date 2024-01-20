public class Banker {
    private int numOfChips;
    private int bankerDice1;
    private int bankerDice2;
    private int bankerDice3;
    private Die die2;
    private int win2;

    public Banker(int numOfChips){
        this.numOfChips = numOfChips;
    }

    public int getNumOfChips(){
        return numOfChips;
    }
    public int getBankerDice1(){
        return bankerDice1;
    }
    public int getBankerDice2(){
        return bankerDice2;
    }
    public int getBankerDice3(){
        return bankerDice3;
    }
    public int getBankerWin(){
        return win2;
    }
    public int getBankerScore(){
        return die2.getScore();
    }
    public void setNumberOfChips(int newChips) {
        numOfChips = newChips;
    }
    // Rolls the 3 die for the banker
    public void rollDiesBanker(){
        die2 = new Die();
        die2.dieSequence();
        bankerDice1 = die2.getDice1();
        bankerDice2 = die2.getDice2();
        bankerDice3 = die2.getDice3();
        win2 = die2.getWin();
    }
    // Checks if the banker has more than 0 chips to continue playing the game
    public boolean checkIfInGame(){
        return numOfChips > 0;
    }
    // Increments the number of chips for the banker based on the player bet
    public void incrementNumOfChips(int numOfChipsWon){
        numOfChips += numOfChipsWon;
    }
    // Decrements the number of chips for the banker based on the player bet
    public void decreaseNumOfChips(int newNumOfChipsWagered){
        numOfChips -= newNumOfChipsWagered;
    }
}
