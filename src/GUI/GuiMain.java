package GUI;


import LOGIC.*;

import javax.swing.*;

public class GuiMain {
    public static void main(String[] args) {
        MoshakasatBazi.initCards();
        Farmande farmande = new Farmande();
        Player player = new Player();
        Player player1 = new Robot1();
        player1.setChoosenCard(0);
        //player.setChoosenCard(0);
        player1.getKartBazis().add(farmande);
        player1.getKartBazis().add(farmande);
        player1.setCoins(10);
        player.getKartBazis().add(new Safir());
        player.getKartBazis().add(new AdamKosh());

        player.setId("A");
        player1.setId("B");
        MoshakasatBazi.getPlayers().put(1,player);
        MoshakasatBazi.getPlayers().put(2,player1);
        MoshakasatBazi.setPlayer(player);


        MainFrame mainFrame = new MainFrame();

    }
}
