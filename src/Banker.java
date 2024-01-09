public class Banker {
    private int numOfChips;
    private int bankerScore;
    private Die die;
    private Player player;

    public Banker(int bankerScore, int numOfChips){
        this.bankerScore = bankerScore;
        this.die = new Die();
    }

    public int getBankerScore(){
        return bankerScore;
    }
    public void setScore(){
        bankerScore = die.getScore() ;
    }
    public void rollDiesBanker(){
        die.dieSequence();
    }
    public boolean checkIfInGame(){
        return numOfChips >= 0;
    }
    public void incrementNumOfChips(int numOfChipsWon){
        numOfChips += numOfChipsWon;
    }
    public void decreaseNumOfChips(int newNumOfChipsWagered){
        numOfChips -= newNumOfChipsWagered;
    }
}
