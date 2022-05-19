package LOGIC;

import GUI.MainFrame;
import GUI.MainPanel;
import sun.applet.Main;

import java.util.concurrent.TimeUnit;

public class Robot1 extends Player {
    @Override
    public void play() {
        if (this.coins < 7) {
            this.take3Coins();
        }
        else {

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
