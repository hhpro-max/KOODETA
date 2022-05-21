package LOGIC;

public class Robot4 extends Player{

    @Override
    public void play(){
        this.chooseTargetPlayer();
        checkIfTargetIsSafe();
        chooseCard();
        if (targetPlayer1.isSafe()){
            this.take3Coins();
        }else if (targetPlayer1.coins > 0 && this.coins<7){
            this.bajGiri(targetPlayer1);
        }
        else if (this.coins >= 7){
            this.kodeta(targetPlayer1);
        }else {
            this.soeGhasd(targetPlayer1);
        }
    }
    int shomaresh = 0;
    public void checkIfTargetIsSafe(){
        shomaresh++;
        if (this.targetPlayer1.isSafe()){
            chooseTargetPlayer();
        }
        if (shomaresh <= 6 && targetPlayer1.isSafe()){
            checkIfTargetIsSafe();
        }
        shomaresh = 0;
    }
    @Override
    public void chooseCard() {
        if (this.choosenCard == null) {
            this.choosenCard = this.kartBazis.size() - 1;
        }
    }
}
