package LOGIC;

import java.util.Map;

public class Robot2 extends Player{
    public Robot2(){

    }
    @Override
    public void play(){
        chooseCard();
        if (this.coins < 9){
            this.take2Coins();
        }else {
           this.kodeta(MoshakasatBazi.getPlayers().get(2));
        }
    }
    @Override
    public void challengeSetOn(){
        if (jaigah == 0) {
            findJaigah();
        }
        if (this.challengeAction != Actions.NOTHING && (MoshakasatBazi.nobat == jaigah+1 || MoshakasatBazi.nobat == jaigah-1)){
            this.challenge();
        }
    }
    public void findJaigah(){
        for (Map.Entry<Integer,Player> i: MoshakasatBazi.getPlayers().entrySet()){
            if (i.getValue().equals(this)){
                jaigah = i.getKey();
                return;
            }
        }
    }
    @Override
    public void chooseCard(){
        if (this.choosenCard == null) {
            this.choosenCard = this.kartBazis.size() - 1;
        }
    }
}
