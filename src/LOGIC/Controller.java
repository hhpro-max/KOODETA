package LOGIC;

import GUI.HazfCardFrame;
import GUI.MainPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Controller {

    Player player = getStaticPlayer();

    public static Controller controller;

    boolean isThisPlayer = false;

    private Controller() {

    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public Player getPlayer(int id) {
        if (MoshakasatBazi.getPlayers().containsKey(2)) {
            return MoshakasatBazi.getPlayers().get(id);
        }
        return null;
    }

    public Player getStaticPlayer() {
        return MoshakasatBazi.getPlayers().get(1);
    }

    public boolean checkCoins() {
        return player.getCoins() < 10;
    }

    public boolean koodeta(int id) {


        if (!MoshakasatBazi.getPlayers().containsKey(id)) {
            System.out.println("KOODETA -> PLAYER MOJOD NIST! ");
            return false;
        }
        if (player.getCoins() >= 7) {
            return player.kodeta(MoshakasatBazi.getPlayers().get(id));
        }
        return false;
    }

    public boolean soeGhasd(int id) {


        if (!MoshakasatBazi.getPlayers().containsKey(id)) {
            return false;
        }
        if (player.getCoins() >= 3) {
            return player.soeGhasd(MoshakasatBazi.getPlayers().get(id));
        }
        return false;
    }

    public boolean bajGiri(int id) {


        if (!MoshakasatBazi.getPlayers().containsKey(id)) {
            return false;
        }
        return player.bajGiri(MoshakasatBazi.getPlayers().get(id));
    }

    public List<KartBazi> getKardPLayer(int id) {
        if (MoshakasatBazi.getPlayers().containsKey(id) && MoshakasatBazi.getPlayers().get(id).equals(player)){
            isThisPlayer = true;

        }else if (MoshakasatBazi.getPlayers().containsKey(id)){
            isThisPlayer = false;
        }
        else {
            return null;
        }
        return   MoshakasatBazi.getPlayers().get(id).getKartBazis();
    }
    public List<KartBazi> getRandomKart(){
        List<KartBazi> kartBazis = new ArrayList<>();
        if (MoshakasatBazi.getValidKards().size()>=2){
            kartBazis.add(MoshakasatBazi.getValidKards().get(0));
            kartBazis.add(MoshakasatBazi.getValidKards().get(1));
            MoshakasatBazi.initCards();
        }
        return kartBazis;
    }
    public void moaveze(List<KartBazi> kartBazis){

            player.moaveze(kartBazis.get(0), kartBazis.get(1));

    }
    public void tavizkart(List<KartBazi> kartBazis){
        player.moaveze1coin(kartBazis.get(0),kartBazis.get(1));
    }
    public List<KartBazi> getInvalidCards(){
        return MoshakasatBazi.getInValidKards();
    }
    public boolean checkNobat(){
        return MoshakasatBazi.getPlayer().equals(player);
    }
    public int chooseCard(){
        //todo
        return 0;
    }
    public void warnPlayer(){
        //todo
        if (getStaticPlayer().getChoosenCard() != null){
            return;
        }
        MainPanel.getInstance().showWarnMessage();
        HazfCardFrame hazfCardFrame = new HazfCardFrame();
        // final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
       // executorService.scheduleAtFixedRate(task(), 10, 10, TimeUnit.MINUTES);

    }
    public void removeCard(ImageIcon imageIcon){
        for (KartBazi i:
             getStaticPlayer().getKartBazis()) {
            if (imageIcon.equals(i.getImageIcon())){
                MoshakasatBazi.getInValidKards().add(i);
                getStaticPlayer().getKartBazis().remove(i);
                break;
            }
        }
    }



    public void updateMainPanel(){
        MainPanel.getInstance().update();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static Controller getController() {
        return controller;
    }

    public static void setController(Controller controller) {
        Controller.controller = controller;
    }

    public boolean isThisPlayer() {
        return isThisPlayer;
    }

    public void setThisPlayer(boolean thisPlayer) {
        isThisPlayer = thisPlayer;
    }
}


