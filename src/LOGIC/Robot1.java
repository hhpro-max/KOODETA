package LOGIC;

import GUI.MainFrame;
import GUI.MainPanel;
import sun.applet.Main;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Robot1 extends Player {
    public Robot1(){

    }
    @Override
    public void play() {
        this.chooseTargetPlayer();
        chooseCard();
        if (this.coins < 7) {
            this.take3Coins();
        }
        else {

            this.kodeta(this.targetPlayer1);
        }

    }
    @Override
    public void undo(){

    }

    @Override
    public void chooseCard() {
        if (this.choosenCard == null) {
            this.choosenCard = 0;
        }
    }


}
