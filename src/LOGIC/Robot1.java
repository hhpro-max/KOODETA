package LOGIC;

public class Robot1 extends Player{
    @Override
    public void play(){
        this.kodeta(MoshakasatBazi.getPlayers().get(1),0);
        this.takeSafeCoin();
    }
}
