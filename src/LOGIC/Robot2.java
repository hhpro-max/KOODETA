package LOGIC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Robot2 extends Player{
    public static List<Player> beChaleshKeshideha = new ArrayList<>();
    public Robot2(){

    }
    @Override
    public void play(){
        this.chooseTargetPlayer();
        chooseCard();
        if (this.coins < 7) {
            this.take2Coins();
        }
        else {

            this.kodeta(this.targetPlayer1);
        }
    }
    @Override
    public void challengeSetOn(){
        if (this.challengeAction != Actions.NOTHING && MoshakasatBazi.dor % 2 ==1 && beChaleshKeshideha.isEmpty()){
            this.challenge();
        }
    }

    @Override
    public void chooseCard(){
        if (this.choosenCard == null) {
            this.choosenCard = this.kartBazis.size() - 1;
        }
    }
}
