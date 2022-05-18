package LOGIC;

public class Robot1 extends Player {
    @Override
    public void play() {
        if (this.coins < 3) {
            this.take2Coins();
        }
        if (this.coins >= 3) {
            this.soeGhasd(MoshakasatBazi.getPlayers().get(1));
        }

    }

    @Override
    public void chooseCard() {
        if (this.choosenCard == null) {
            this.choosenCard = this.kartBazis.size() - 1;
        }
    }
}
