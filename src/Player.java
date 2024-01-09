public class Player {
    private String name;
    private int chipsWagered;
    private int numberOfChips;
    private int playerScore;
    private Die die;

    public Player(String name, int playerScore, int numberOfChips){
        this.name = name;
        this.playerScore = playerScore;
        this.numberOfChips = numberOfChips;
        chipsWagered = 0;
        die = new Die();
    }

    public int getChipsWagered(){
        return chipsWagered;
    }
    public void setChipsWagered(int newChips){
        chipsWagered = newChips;
    }
    public void setScore(){
        playerScore = die.getScore() ;
    }

    public void rollDiesPlayer(){
        die.dieSequence();
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
