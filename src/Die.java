public class Die {
    private int dice1;
    private int dice2;
    private int dice3;
    private int score;

    public Die(int score){
        this.score = 0;
    }

    public int rollDie(){
        return (int) (Math.random() * 6) + 1;
    }
    public void assignedDiceRolls(){
        dice1 = rollDie();
        dice2 = rollDie();
        dice3 = rollDie();
    }

    public boolean dieSequence(){
        int[] tempRollHolder = new int[3];
        tempRollHolder[0] = dice1;
        tempRollHolder[1] = dice2;
        tempRollHolder[2] = dice3;
        if (tempRollHolder[0] == 4 && tempRollHolder[1] == 5 && tempRollHolder[2] == 6 || tempRollHolder[0] == 4 && tempRollHolder[1] == 6 && tempRollHolder[2] == 5 || tempRollHolder[0] == 5 && tempRollHolder[1] == 4 && tempRollHolder[2] == 6 || tempRollHolder[0] == 5 && tempRollHolder[1] == 6 && tempRollHolder[2] == 4 || tempRollHolder[0] == 6 && tempRollHolder[1] == 4 && tempRollHolder[2] == 5 || tempRollHolder[0] == 6 && tempRollHolder[1] == 5 && tempRollHolder[2] == 4){
            return true;
        } else if (tempRollHolder[0] == 1 && tempRollHolder[1] == 2 && tempRollHolder[2] == 3 || tempRollHolder[0] == 1 && tempRollHolder[1] == 3 && tempRollHolder[2] == 2 || tempRollHolder[0] == 2 && tempRollHolder[1] == 1 && tempRollHolder[2] == 3 || tempRollHolder[0] == 2 && tempRollHolder[1] == 3 && tempRollHolder[2] == 1 || tempRollHolder[0] == 3 && tempRollHolder[1] == 1 && tempRollHolder[2] == 2 || tempRollHolder[0] == 3 && tempRollHolder[1] == 2 && tempRollHolder[2] == 1){
            return false;
        }
    }
}
