package LOGIC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Player {
    List<KartBazi> kartBazis = new ArrayList<>();
    public Actions takedAction = Actions.NOTHING;
    Actions lastAction = Actions.NOTHING;
    int coins = 0;
    boolean bolof = false;
    boolean safe = false;
    String id;
    Integer choosenCard = null;

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
            this.kartBazis.remove(i);
        } catch (Exception e) {
            System.out.println(e.getMessage());

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
            i.takedAction = Actions.GET_MONEY;
        }
        this.lastAction = Actions.GET_MONEY;
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
                player.takedAction = Actions.KOODETA;
                player.choosenCard = null;
                return true;
            }
            try {


                player.removeKard(player.choosenCard);


            } catch (Exception e) {

                Controller.getInstance().warnPlayer();

            }
            MoshakasatBazi.changeNobat();
            player.takedAction = Actions.KOODETA;
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
            if (player.getKartBazis().size() == 1) {
                player.removeKard(0);
                player.takedAction = Actions.SOE_GHASD;
                player.choosenCard = null;
                return true;
            }
            try {
                player.removeKard(player.choosenCard);
            } catch (Exception e) {
                Controller.getInstance().warnPlayer();
            }
            MoshakasatBazi.changeNobat();
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
            this.coins += 2;
            int tadadCoin = player.getCoins();
            player.setCoins(tadadCoin - 2);
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
        } else if (player.getCoins() == 1) {
            this.lastAction = Actions.BAJ_GIRI;
            player.takedAction = Actions.BAJ_GIRI;
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
        MoshakasatBazi.changeNobat();
    }

    public void moaveze1coin(KartBazi kartBazi1, KartBazi kartBazi2) {
        if (this.coins >= 1) {
            this.coins--;
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
            MoshakasatBazi.changeNobat();
        }
    }


    public boolean reAction() {
        switch (takedAction) {
            case BAJ_GIRI:
                bolof = true;
                this.lastAction = Actions.REACTED_ON_BAJGIRI;
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
                this.lastAction = Actions.REACTED_ON_GETMONEY;
                this.takedAction = Actions.NOTHING;
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
                this.takedAction = Actions.NOTHING;
                bolof = true;
                for (KartBazi i :
                        kartBazis) {
                    if (i instanceof ShahDokht) {
                        bolof = false;
                        break;
                    }
                }
                return true;
        }
        return false;
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
