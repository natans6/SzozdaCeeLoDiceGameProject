public class Banker {
    private int numOfChips;
    private int bankerScore;
    private Die die;
    private Player player;

    public Banker(int bankerScore, int numOfChips){
        this.bankerScore = bankerScore;
        this.numOfChips = numOfChips;
        this.die = new Die();
    }

    public int getNumOfChips(){
        return numOfChips;
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
