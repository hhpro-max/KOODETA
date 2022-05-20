package LOGIC;

import GUI.Waiter;
import GUI.Waiter2;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MoshakasatBazi {
    static Map<Integer,Player> players = new HashMap<>();
    static List<KartBazi> validKards = new ArrayList<>();
    static List<KartBazi> inValidKards = new ArrayList<>();
    static int nobat = 1;
    static int dor = 1;

    static Player player;
    static boolean baresi = false;
    public static void initCards(){
        if (validKards.isEmpty()){
            AdamKosh adamKosh = new AdamKosh();
            BozorgZade bozorgZade = new BozorgZade();
            Farmande farmande = new Farmande();
            Safir safir = new Safir();
            ShahDokht shahDokht = new ShahDokht();
            validKards.add(adamKosh);
            validKards.add(adamKosh);
            validKards.add(adamKosh);

            validKards.add(bozorgZade);
            validKards.add(bozorgZade);
            validKards.add(bozorgZade);

            validKards.add(farmande);
            validKards.add(farmande);
            validKards.add(farmande);

            validKards.add(safir);
            validKards.add(safir);
            validKards.add(safir);

            validKards.add(shahDokht);
            validKards.add(shahDokht);
            validKards.add(shahDokht);

        }
        Collections.shuffle(validKards);
    }
    public static void changeNobat(){
        if (nobat == 1){
            dor++;
            if (dor % 2 == 1){
                Robot2.beChaleshKeshideha.clear();
            }
        }
        checkFinish();
        if (nobat < 4){
            nobat++;
        }else {
            nobat = 1;
        }
        if (players.containsKey(nobat)) {
            player = players.get(nobat);

            try {
                checkChallenge();
            }catch (Exception e){
                System.out.println(e.getMessage() + "   THIS IS NOT IMPORTANT ERROR XD");
            }
            playNext();


            try {
                if ( player.equals(Controller.getInstance().getStaticPlayer()) && !Controller.getInstance().getStaticPlayer().takedAction.equals(Actions.NOTHING)){
                    Waiter waiter = new Waiter(Controller.getInstance().getStaticPlayer().takedAction.toString());
                }
                if (!Controller.getInstance().getStaticPlayer().challengeAction.equals(Actions.NOTHING)){
                    Waiter2 waiter2 = new Waiter2(Controller.getInstance().getStaticPlayer().challengeAction.toString());
                }
            }catch (Exception e){
                System.out.println("YOU LOST AND YOU CAN JUST WATCH THE GAME!");
            }

        }else {

            changeNobat();
        }
    }
    public static void checkChallenge(){

        for (Player i:
             MoshakasatBazi.getPlayers().values()) {
            if (i != null &&  !i.equals(Controller.getInstance().getStaticPlayer())){
                i.challengeSetOn();
            }
        }

    }
    public static void playNext(){

        player.play();
        Controller.getInstance().updateMainPanel();
    }
    public static void checkFinish(){
        for (Player j:
             players.values()) {
            j.checkLose(j);
        }
        if (players.size() == 1){
            for (Map.Entry<Integer,Player> i:
                 players.entrySet()) {
                if (i.getValue() != null){
                    System.out.println(i.getValue().getId() + " WON !");
                }
            }
            System.exit(0);
        }
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        MoshakasatBazi.player = player;
    }



    public static int getNobat() {
        return nobat;
    }

    public static void setNobat(int nobat) {
        MoshakasatBazi.nobat = nobat;
    }

    public static Map<Integer, Player> getPlayers() {
        return players;
    }

    public static void setPlayers(Map<Integer, Player> players) {
        MoshakasatBazi.players = players;
    }

    public static List<KartBazi> getValidKards() {
        return validKards;
    }

    public static void setValidKards(List<KartBazi> validKards) {
        MoshakasatBazi.validKards = validKards;
    }

    public static List<KartBazi> getInValidKards() {
        return inValidKards;
    }

    public static void setInValidKards(List<KartBazi> inValidKards) {
        MoshakasatBazi.inValidKards = inValidKards;
    }
}
