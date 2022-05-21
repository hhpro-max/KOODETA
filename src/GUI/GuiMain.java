package GUI;


import LOGIC.*;

import javax.swing.*;

public class GuiMain {
    public static void main(String[] args) {
        MoshakasatBazi.initCards();
        Farmande farmande = new Farmande();
        Player player = new Player();
        Player player1 = new Robot1();
        Player player2 = new Robot2();
        //Player player3 = new Robot3();
        Player player3 = new Robot4();
        player1.setChoosenCard(0);
        player2.setChoosenCard(0);
        player3.setChoosenCard(0);
        //player.setChoosenCard(0);
        player1.getKartBazis().add(farmande);
        player1.getKartBazis().add(new AdamKosh());
        player.getKartBazis().add(new BozorgZade());
        player.getKartBazis().add(new AdamKosh());
        player2.getKartBazis().add(new BozorgZade());
        player2.getKartBazis().add(new ShahDokht());
        player3.getKartBazis().add(new Safir());
        player3.getKartBazis().add(new Farmande());



        player.setId("A");
        player1.setId("B");
        player2.setId("C");
        player3.setId("D");
        MoshakasatBazi.getPlayers().put(1,player);
        MoshakasatBazi.getPlayers().put(2,player1);
        MoshakasatBazi.getPlayers().put(3,player2);
        MoshakasatBazi.getPlayers().put(4,player3);
        MoshakasatBazi.setPlayer(player);


        MainFrame mainFrame = new MainFrame();

    }
}
