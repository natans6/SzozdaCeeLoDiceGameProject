public class Die {
    private int dice1;
    private int dice2;
    private int dice3;
    private int score = 0;
    public static int win = 5;
    private boolean repeat = true;
    public Die(){

    }

    public int rollDie(){
        return (int) (Math.random() * 6) + 1;
    }

    public void dieSequence(){
        int[] tempRollHolder = new int[3];
        while(repeat){
            dice1 = rollDie();
            dice2 = rollDie();
            dice3 = rollDie();
            tempRollHolder[0] = dice1;
            tempRollHolder[1] = dice2;
            tempRollHolder[2] = dice3;
            if (tempRollHolder[0] == 4 && tempRollHolder[1] == 5 && tempRollHolder[2] == 6 || tempRollHolder[0] == 4 && tempRollHolder[1] == 6 && tempRollHolder[2] == 5 || tempRollHolder[0] == 5 && tempRollHolder[1] == 4 && tempRollHolder[2] == 6 || tempRollHolder[0] == 5 && tempRollHolder[1] == 6 && tempRollHolder[2] == 4 || tempRollHolder[0] == 6 && tempRollHolder[1] == 4 && tempRollHolder[2] == 5 || tempRollHolder[0] == 6 && tempRollHolder[1] == 5 && tempRollHolder[2] == 4){
                win = 0;
                repeat = false;
            } else if (tempRollHolder[0] == 1 && tempRollHolder[1] == 2 && tempRollHolder[2] == 3 || tempRollHolder[0] == 1 && tempRollHolder[1] == 3 && tempRollHolder[2] == 2 || tempRollHolder[0] == 2 && tempRollHolder[1] == 1 && tempRollHolder[2] == 3 || tempRollHolder[0] == 2 && tempRollHolder[1] == 3 && tempRollHolder[2] == 1 || tempRollHolder[0] == 3 && tempRollHolder[1] == 1 && tempRollHolder[2] == 2 || tempRollHolder[0] == 3 && tempRollHolder[1] == 2 && tempRollHolder[2] == 1){
                win = 1;
                repeat = false;
            } else if (tempRollHolder[0] == tempRollHolder[1] || tempRollHolder[1] == tempRollHolder[2] || tempRollHolder[0] == tempRollHolder[2]){
                win = 2;
                if (tempRollHolder[0] == tempRollHolder[1]){
                    score = tempRollHolder[2];
                } else if (tempRollHolder[1] == tempRollHolder[2]){
                    score = tempRollHolder[0];
                } else {
                    score = tempRollHolder[1];
                }
                repeat = false;
            }
        }

    }

    public int getScore(){
        return score;
    }
    public int getWin(){
        return win;
    }
    public int getDice1(){
        return dice1;
    }
    public int getDice2(){
        return dice2;
    }
    public int getDice3(){
        return dice3;
    }
}
