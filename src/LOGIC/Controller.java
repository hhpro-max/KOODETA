package LOGIC;

import GUI.MainPanel;

import java.util.ArrayList;
import java.util.List;

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
        if (player.getCoins() >= 10) {
            return false;
        }
        return true;
    }

    public boolean koodeta(int id) {


        if (!MoshakasatBazi.getPlayers().containsKey(id)) {
            return false;
        }
        if (player.getCoins() >= 7) {
            if (player.kodeta(MoshakasatBazi.getPlayers().get(id), 0)) {

                return true;
            }
        }
        return false;
    }

    public boolean soeGhasd(int id) {


        if (!MoshakasatBazi.getPlayers().containsKey(id)) {
            return false;
        }
        if (player.getCoins() >= 3) {
            if (player.soeGhasd(MoshakasatBazi.getPlayers().get(id), 0)) {

                return true;
            }
        }
        return false;
    }

    public boolean bajGiri(int id) {


        if (!MoshakasatBazi.getPlayers().containsKey(id)) {
            return false;
        }
        if (player.bajGiri(MoshakasatBazi.getPlayers().get(id))) {

            return true;
        }


        return false;
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
        player.moaveze(kartBazis.get(0),kartBazis.get(1));
    }
    public void tavizkart(List<KartBazi> kartBazis){
        player.moaveze1coin(kartBazis.get(0),kartBazis.get(1));
    }
    public List<KartBazi> getInvalidCards(){
        return MoshakasatBazi.getInValidKards();
    }
    public boolean checkNobat(){
        if (MoshakasatBazi.getPlayer().equals(player)){
            return true;
        }else {
            return false;
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


