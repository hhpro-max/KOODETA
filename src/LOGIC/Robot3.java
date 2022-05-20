package LOGIC;

public class Robot3 extends Player{
    @Override
    public void play(){
        chooseCard();
        if (this.coins < 3){
            this.take2Coins();
        }else {
            try {
                this.soeGhasd(MoshakasatBazi.getPlayers().get(3));
            }catch (Exception e){

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
