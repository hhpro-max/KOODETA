package LOGIC;

import GUI.GozareshPanel;

import java.util.*;

public class Player {
    List<KartBazi> kartBazis = new ArrayList<>();
    public Actions takedAction = Actions.NOTHING;
    Actions lastAction = Actions.NOTHING;
    public Actions challengeAction = Actions.NOTHING;
    int coins = 0;
    boolean bolof = false;
    boolean safe = false;
    String id;
    Integer choosenCard = null;
    public int mainPlayerChooseCard = 0;
    boolean challengedChange = false;
    Player targetPlayer1 = null;
    int jaigah = 0;

    public Player() {

    }

    public void takeSafeCoin() {
        this.coins++;
        safe = true;
        Controller.getInstance().gozaresh(this.id + " -> " + "BANK : TOOK SAFE COIN");

        MoshakasatBazi.changeNobat();
    }

    public void take2Coins() {

        this.coins = this.coins + 2;
        safe = false;
        Controller.getInstance().gozaresh(this.id + " -> " + "BANK : TOOK 2 COINS");
        MoshakasatBazi.changeNobat();
    }


    public void removeKard(int i) {
        try {
            MoshakasatBazi.getInValidKards().add(kartBazis.get(i));
            GozareshPanel.getInstance().initGozaresh(this.id + " -> KILL : " + kartBazis.get(i).toString());
            this.kartBazis.remove(i);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "  FROM REMOVE CARD FUNCTION");
            checkLose(this);
            this.removeKard(0);
        }
        checkLose(this);
    }

    public void checkLose(Player player) {
        if (player.getKartBazis().size() == 0) {
            for (Map.Entry<Integer, Player> i :
                    MoshakasatBazi.getPlayers().entrySet()) {
                if (i.getValue().equals(player)) {
                    MoshakasatBazi.getPlayers().remove(i.getKey());
                    MoshakasatBazi.checkFinish();
                    return;
                }
            }
        }
    }

    public void take3Coins() {

        for (Player i :
                MoshakasatBazi.getPlayers().values()) {
            if (!i.equals(this)) {
                i.takedAction = Actions.GET_MONEY;
            }
        }
        this.lastAction = Actions.GET_MONEY;
        setChallengePlayer(Actions.GET_MONEY);
        safe = false;
        this.coins = this.coins + 3;
        Controller.getInstance().gozaresh(this.id + " -> " + "BANK : TOOK 3 COINS");
        MoshakasatBazi.changeNobat();
        for (KartBazi i :
                kartBazis) {
            if (i instanceof BozorgZade) {

                bolof = false;
                return;
            }
        }

        bolof = true;
    }

    public boolean kodeta(Player player) {


        if (player == null) {
            return false;
        }
        player.chooseCard();
        safe = false;
        if (player.equals(this)) {
            return false;
        }

        String idPlayer;
        try {
            idPlayer = player.getId();
        } catch (Exception e) {
            return false;
        }
        if (this.coins >= 7) {
            this.coins -= 7;
            Controller.getInstance().gozaresh(this.getId() + "->" + idPlayer + ": KOODETA");


            if (player.getKartBazis().size() == 1) {
                player.removeKard(0);
                player.choosenCard = null;
                MoshakasatBazi.changeNobat();
                return true;
            }
            try {


                player.removeKard(player.choosenCard);


            } catch (Exception e) {

                Controller.getInstance().warnPlayer();

            }
            MoshakasatBazi.changeNobat();
            player.choosenCard = null;
            return true;
        }


        return false;
    }

    public boolean soeGhasd(Player player) {
        bolof = true;
        if (player.isSafe()) {
            Controller.getInstance().gozaresh(this.id + "->" + player.id + " : IS SAFE");
            return false;
        }
        safe = false;
        if (this.coins >= 3) {


            String idPlayer;
            try {
                idPlayer = player.getId();
            } catch (Exception e) {
                System.out.println(e.getMessage() + " ERROR");
                return false;
            }
            Controller.getInstance().gozaresh(this.getId() + "->" + idPlayer + ": SOEGHASD");
            MoshakasatBazi.changeNobat();
            if (player.getKartBazis().size() == 1) {
                player.removeKard(0);
                player.takedAction = Actions.SOE_GHASD;
                setChallengePlayer(Actions.SOE_GHASD);
                player.choosenCard = null;
                return true;
            }
            try {
                player.removeKard(player.choosenCard);
            } catch (Exception e) {
                Controller.getInstance().warnPlayer();
            }

            this.coins -= 3;
            for (KartBazi i :
                    kartBazis) {
                if (i instanceof AdamKosh) {
                    bolof = false;
                    break;
                }
            }
            this.lastAction = Actions.SOE_GHASD;
            player.takedAction = Actions.SOE_GHASD;
            setChallengePlayer(Actions.SOE_GHASD);
            return true;


        }
        return false;
    }

    public boolean bajGiri(Player player) {

        if (player.isSafe()) {
            Controller.getInstance().gozaresh(this.id + "->" + player.id + " : IS SAFE");
            return false;
        }
        String idPlayer;
        try {
            idPlayer = player.getId();
            if (idPlayer.equals(this.id)) {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        safe = false;
        bolof = true;
        if (player.getCoins() >= 2) {
            this.lastAction = Actions.BAJ_GIRI;
            player.takedAction = Actions.BAJ_GIRI;
            setChallengePlayer(Actions.BAJ_GIRI);
            this.coins += 2;
            int tadadCoin = player.getCoins();
            player.coins -= 2;
            for (KartBazi i :
                    kartBazis) {
                if (i instanceof Farmande) {
                    bolof = false;
                    break;
                }
            }
            Controller.getInstance().gozaresh(this.id + " -> " + idPlayer + " : BAJGIRI");
            setChallengePlayer(Actions.BAJ_GIRI);
            MoshakasatBazi.changeNobat();
            return true;
        } else if (player.getCoins() == 1) {
            this.lastAction = Actions.BAJ_GIRI;
            player.takedAction = Actions.BAJ_GIRI;
            setChallengePlayer(Actions.BAJ_GIRI);
            this.coins += 1;
            player.setCoins(0);
            for (KartBazi i :
                    kartBazis) {
                if (i instanceof Farmande) {
                    bolof = false;
                    break;
                }
            }
            Controller.getInstance().gozaresh(player.getId() + "->" + idPlayer + ": BAJGIRI");
            MoshakasatBazi.changeNobat();
            return true;
        }
        return false;
    }

    public void moaveze(KartBazi kartBazi1, KartBazi kartBazi2) {

        safe = false;
        bolof = true;
        for (KartBazi i :
                kartBazis) {
            if (i instanceof Safir) {
                bolof = false;
                break;
            }
        }

        this.kartBazis.clear();
        kartBazis.add(kartBazi1);
        kartBazis.add(kartBazi2);


        GozareshPanel.getInstance().initGozaresh(this.id + " -> : CHANGED HIS CARDS");
        this.lastAction = Actions.MOAVEZE;

        if (!challengedChange){
            MoshakasatBazi.changeNobat();
            setChallengePlayer(Actions.MOAVEZE);
        }else {
            setChallengePlayer(Actions.NOTHING);
        }
        challengedChange = false;
    }

    public void moaveze1coin(KartBazi kartBazi1, KartBazi kartBazi2) {
        if (this.coins >= 1) {
            this.coins--;
            safe = false;
            if (this.kartBazis.size() == 1){
                if (kartBazis.contains(kartBazi1)){
                    kartBazis.remove(kartBazi1);
                    kartBazis.add(kartBazi2);
                }else if (kartBazis.contains(kartBazi2)){
                    kartBazis.remove(kartBazi2);
                    kartBazis.add(kartBazi1);
                }
            }else {
                kartBazis.clear();
                kartBazis.add(kartBazi1);
                kartBazis.add(kartBazi2);
            }
            GozareshPanel.getInstance().initGozaresh(this.id + " -> : CHANGED ONE OF HIS CARDS");
            if (!challengedChange){
                MoshakasatBazi.changeNobat();
            }
            challengedChange = false;
        }
    }


    public boolean reAction() {
        switch (takedAction) {
            case BAJ_GIRI:
                bolof = true;
                this.coins += 2;
                GozareshPanel.getInstance().initGozaresh(this.id + " -> REACTED ON BAJGIRI");
                this.lastAction = Actions.REACTED_ON_BAJGIRI;
                setChallengePlayer(Actions.REACTED_ON_BAJGIRI);
                this.takedAction = Actions.NOTHING;
                for (KartBazi i :
                        kartBazis) {
                    if (i instanceof Safir || i instanceof Farmande) {
                        bolof = false;
                        break;
                    }
                }
                return true;

            case GET_MONEY:
                loop:
                for (Player i :
                        MoshakasatBazi.getPlayers().values()) {
                    if (!i.equals(this) && i.getLastAction().equals(Actions.GET_MONEY)) {
                        i.coins -= 3;
                        break loop;
                    }
                }
                this.lastAction = Actions.REACTED_ON_GETMONEY;
                setChallengePlayer(Actions.REACTED_ON_GETMONEY);
                this.takedAction = Actions.NOTHING;
                GozareshPanel.getInstance().initGozaresh(this.id + " -> REACTED ON KOMAK KHAREJI");

                bolof = true;
                for (KartBazi i :
                        kartBazis) {
                    if (i instanceof BozorgZade) {
                        bolof = false;
                        break;
                    }
                }
                return true;

            case SOE_GHASD:
                this.lastAction = Actions.REACTED_ON_SOEGHASD;
                setChallengePlayer(Actions.REACTED_ON_SOEGHASD);
                this.undo();
                this.takedAction = Actions.NOTHING;
                GozareshPanel.getInstance().initGozaresh(this.id + " -> REACTED ON SOEGHASD");

                return true;

        }
        return false;
    }

    public void setChallengePlayer(Actions actions) {
        for (Player j :
                MoshakasatBazi.getPlayers().values()) {
            if (!j.equals(this)) {
                j.challengeAction = actions;
            }
        }
    }

    public void challenge() {
        Player targetPlayer = null;
        soich:
        switch (challengeAction) {
            case GET_MONEY:

                for (Player i :
                        MoshakasatBazi.getPlayers().values()) {
                    if (i.getLastAction().equals(Actions.GET_MONEY)) {
                        targetPlayer = i;
                    }
                }
                if (targetPlayer != null && !targetPlayer.equals(this)) {
                    GozareshPanel.getInstance().initGozaresh(this.id + " -> " + targetPlayer.id + " : CHALLENGE");
                    for (KartBazi j :
                            targetPlayer.getKartBazis()) {
                        if (j instanceof BozorgZade) {
                            targetPlayer.lastAction = Actions.NOTHING;
                            if (Controller.getInstance().getStaticPlayer().equals(this)) {
                                Controller.getInstance().warnPlayer();
                            } else {
                                this.removeKard(this.choosenCard);
                            }
                            targetPlayer.getKartBazis().remove(j);
                            Collections.shuffle(MoshakasatBazi.getValidKards());
                            targetPlayer.challengedChange = true;
                            targetPlayer.moaveze(targetPlayer.getKartBazis().get(0), MoshakasatBazi.validKards.get(0));
                            break soich;
                        }
                    }
                    targetPlayer.lastAction = Actions.NOTHING;
                    targetPlayer.coins = targetPlayer.coins - 3;

                    if (targetPlayer.equals(Controller.getInstance().getStaticPlayer())) {
                        targetPlayer.removeKard(targetPlayer.mainPlayerChooseCard);
                    } else {
                        targetPlayer.removeKard(targetPlayer.choosenCard);
                    }

                }
                break;
            case SOE_GHASD:
                for (Player i :
                        MoshakasatBazi.getPlayers().values()) {
                    if (i.getLastAction().equals(Actions.SOE_GHASD)) {
                        targetPlayer = i;
                    }
                }
                if (targetPlayer != null&& !targetPlayer.equals(this)) {
                    GozareshPanel.getInstance().initGozaresh(this.id + " -> " + targetPlayer.id + " : CHALLENGE");

                    for (KartBazi j :
                            targetPlayer.getKartBazis()) {
                        if (j instanceof AdamKosh) {
                            targetPlayer.lastAction = Actions.NOTHING;
                            MoshakasatBazi.getPlayers().values().remove(this);
                            MoshakasatBazi.checkFinish();
                            targetPlayer.getKartBazis().remove(j);
                            Collections.shuffle(MoshakasatBazi.getValidKards());
                            targetPlayer.challengedChange = true;
                            targetPlayer.moaveze(targetPlayer.getKartBazis().get(0), MoshakasatBazi.validKards.get(0));
                            break soich;
                        }
                    }
                    targetPlayer.lastAction = Actions.NOTHING;
                    if (targetPlayer.equals(Controller.getInstance().getStaticPlayer())) {
                        targetPlayer.removeKard(targetPlayer.mainPlayerChooseCard);
                    } else {
                        targetPlayer.removeKard(targetPlayer.choosenCard);
                    }
                }
                break;

            case BAJ_GIRI:
                for (Player i :
                        MoshakasatBazi.getPlayers().values()) {
                    if (i.getLastAction().equals(Actions.BAJ_GIRI)) {
                        targetPlayer = i;
                    }
                }
                if (targetPlayer != null&& !targetPlayer.equals(this)) {
                    GozareshPanel.getInstance().initGozaresh(this.id + " -> " + targetPlayer.id + " : CHALLENGE");

                    for (KartBazi j :
                            targetPlayer.getKartBazis()) {
                        if (j instanceof Farmande) {
                            targetPlayer.lastAction = Actions.NOTHING;
                            if (Controller.getInstance().getStaticPlayer().equals(this)) {
                                Controller.getInstance().warnPlayer();
                            } else {
                                this.removeKard(this.choosenCard);
                            }                            targetPlayer.getKartBazis().remove(j);
                            Collections.shuffle(MoshakasatBazi.getValidKards());
                            targetPlayer.challengedChange = true;
                            targetPlayer.moaveze(targetPlayer.getKartBazis().get(0), MoshakasatBazi.validKards.get(0));
                            break soich;
                        }
                    }
                    targetPlayer.lastAction = Actions.NOTHING;
                    targetPlayer.coins = targetPlayer.coins - 2;
                    if (targetPlayer.equals(Controller.getInstance().getStaticPlayer())) {
                        targetPlayer.removeKard(targetPlayer.mainPlayerChooseCard);
                    } else {
                        targetPlayer.removeKard(targetPlayer.choosenCard);
                    }
                }
                break;
            case REACTED_ON_SOEGHASD:
                for (Player i :
                        MoshakasatBazi.getPlayers().values()) {
                    if (i.getLastAction().equals(Actions.REACTED_ON_SOEGHASD)) {
                        targetPlayer = i;
                    }
                }
                if (targetPlayer != null&& !targetPlayer.equals(this)) {
                    GozareshPanel.getInstance().initGozaresh(this.id + " -> " + targetPlayer.id + " : CHALLENGE");

                    for (KartBazi j :
                            targetPlayer.getKartBazis()) {
                        if (j instanceof ShahDokht) {
                            targetPlayer.lastAction = Actions.NOTHING;
                            if (Controller.getInstance().getStaticPlayer().equals(this)) {
                                Controller.getInstance().warnPlayer();
                            } else {
                                this.removeKard(this.choosenCard);
                            }                            targetPlayer.getKartBazis().remove(j);
                            Collections.shuffle(MoshakasatBazi.getValidKards());
                            targetPlayer.challengedChange = true;
                            targetPlayer.moaveze(targetPlayer.getKartBazis().get(0), MoshakasatBazi.validKards.get(0));
                            break soich;
                        }
                    }
                    targetPlayer.lastAction = Actions.NOTHING;
                    if (targetPlayer.equals(Controller.getInstance().getStaticPlayer())) {
                        targetPlayer.removeKard(targetPlayer.mainPlayerChooseCard);
                    } else {
                        targetPlayer.removeKard(targetPlayer.choosenCard);
                    }
                }
                break;
            case REACTED_ON_BAJGIRI:
                for (Player i :
                        MoshakasatBazi.getPlayers().values()) {
                    if (i.getLastAction().equals(Actions.REACTED_ON_BAJGIRI)) {
                        targetPlayer = i;
                    }
                }
                if (targetPlayer != null&& !targetPlayer.equals(this)) {
                    GozareshPanel.getInstance().initGozaresh(this.id + " -> " + targetPlayer.id + " : CHALLENGE");

                    for (KartBazi j :
                            targetPlayer.getKartBazis()) {
                        if (j instanceof Farmande || j instanceof Safir) {
                            targetPlayer.lastAction = Actions.NOTHING;
                            if (Controller.getInstance().getStaticPlayer().equals(this)) {
                                Controller.getInstance().warnPlayer();
                            } else {
                                this.removeKard(this.choosenCard);
                            }                            targetPlayer.getKartBazis().remove(j);
                            Collections.shuffle(MoshakasatBazi.getValidKards());
                            targetPlayer.challengedChange = true;
                            targetPlayer.moaveze(targetPlayer.getKartBazis().get(0), MoshakasatBazi.validKards.get(0));
                            break soich;
                        }
                    }
                    targetPlayer.lastAction = Actions.NOTHING;
                    if (targetPlayer.equals(Controller.getInstance().getStaticPlayer())) {
                        targetPlayer.removeKard(targetPlayer.mainPlayerChooseCard);
                    } else {
                        targetPlayer.removeKard(targetPlayer.choosenCard);
                    }
                }
                break;
            case REACTED_ON_GETMONEY:
                for (Player i :
                        MoshakasatBazi.getPlayers().values()) {
                    if (i.getLastAction().equals(Actions.REACTED_ON_GETMONEY)) {
                        targetPlayer = i;
                    }
                }
                if (targetPlayer != null&& !targetPlayer.equals(this)) {
                    GozareshPanel.getInstance().initGozaresh(this.id + " -> " + targetPlayer.id + " : CHALLENGE");

                    for (KartBazi j :
                            targetPlayer.getKartBazis()) {
                        if (j instanceof BozorgZade) {
                            targetPlayer.lastAction = Actions.NOTHING;
                            if (Controller.getInstance().getStaticPlayer().equals(this)) {
                                Controller.getInstance().warnPlayer();
                            } else {
                                this.removeKard(this.choosenCard);
                            }                            targetPlayer.getKartBazis().remove(j);
                            Collections.shuffle(MoshakasatBazi.getValidKards());
                            targetPlayer.challengedChange = true;
                            targetPlayer.moaveze(targetPlayer.getKartBazis().get(0), MoshakasatBazi.validKards.get(0));
                            break soich;
                        }
                    }
                    targetPlayer.lastAction = Actions.NOTHING;
                    if (targetPlayer.equals(Controller.getInstance().getStaticPlayer())) {
                        targetPlayer.removeKard(targetPlayer.mainPlayerChooseCard);
                    } else {
                        targetPlayer.removeKard(targetPlayer.choosenCard);
                    }
                }
                break;
            case MOAVEZE:
                for (Player i :
                        MoshakasatBazi.getPlayers().values()) {
                    if (i.getLastAction().equals(Actions.MOAVEZE)) {
                        targetPlayer = i;
                    }
                }
                if (targetPlayer != null&& !targetPlayer.equals(this)) {
                    GozareshPanel.getInstance().initGozaresh(this.id + " -> " + targetPlayer.id + " : CHALLENGE");

                    for (KartBazi j :
                            targetPlayer.getKartBazis()) {
                        if (j instanceof Safir) {
                            targetPlayer.lastAction = Actions.NOTHING;
                            if (Controller.getInstance().getStaticPlayer().equals(this)) {
                                Controller.getInstance().warnPlayer();
                            } else {
                                this.removeKard(this.choosenCard);
                            }
                            targetPlayer.getKartBazis().remove(j);
                            Collections.shuffle(MoshakasatBazi.getValidKards());
                            targetPlayer.challengedChange = true;
                            targetPlayer.moaveze(targetPlayer.getKartBazis().get(0), MoshakasatBazi.validKards.get(0));
                            break soich;
                        }
                    }
                    targetPlayer.lastAction = Actions.NOTHING;
                    if (targetPlayer.equals(Controller.getInstance().getStaticPlayer())) {
                        targetPlayer.removeKard(targetPlayer.mainPlayerChooseCard);
                    } else {
                        targetPlayer.removeKard(targetPlayer.choosenCard);
                    }
                }
                break;
        }

        if (targetPlayer != null && this instanceof Robot2){
            Robot2.beChaleshKeshideha.add(targetPlayer);
        }



    }

    public void challengeSetOn() {

    }

    public void undo() {

    }
    int tried = 0;
    public void chooseTargetPlayer() {
        Random random = new Random();
        int a = random.nextInt(5);
        try {
            if (tried >= 10){
                targetPlayer1 = takePlayer();
                return;
            }
            if (MoshakasatBazi.getPlayers().get(a) != null && !MoshakasatBazi.getPlayers().get(a).equals(this)) {
                targetPlayer1 = MoshakasatBazi.getPlayers().get(a);
                tried = 0;
            } else {
                chooseTargetPlayer();
                tried++;
            }
        } catch (Exception e) {
            chooseTargetPlayer();
        }
    }
    public Player takePlayer(){
        Player player = null;
        for (Player i:
             MoshakasatBazi.getPlayers().values()) {
            if (i != null && !i.equals(this)){
                player = i;
            }
        }
        return player;
    }

    public boolean checkReaction(boolean b) {
        if (b) {
            return reAction();
        }
        return false;
    }

    public boolean checkNobat() {
        return MoshakasatBazi.getPlayer().equals(this);
    }

    public void play() {

    }

    public void chooseCard() {

    }


    public List<KartBazi> getKartBazis() {
        return kartBazis;
    }

    public void setKartBazis(List<KartBazi> kartBazis) {
        this.kartBazis = kartBazis;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public boolean isBolof() {
        return bolof;
    }

    public void setBolof(boolean bolof) {
        this.bolof = bolof;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Actions getTakedAction() {
        return takedAction;
    }

    public void setTakedAction(Actions takedAction) {
        this.takedAction = takedAction;
    }

    public Actions getLastAction() {
        return lastAction;
    }

    public void setLastAction(Actions lastAction) {
        this.lastAction = lastAction;
    }

    public Integer getChoosenCard() {
        return choosenCard;
    }

    public void setChoosenCard(Integer choosenCard) {
        this.choosenCard = choosenCard;
    }


}
