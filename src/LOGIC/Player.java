package LOGIC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Player {
    List<KartBazi> kartBazis = new ArrayList<>();
    Actions takedAction = Actions.NOTHING;
    Actions lastAction = Actions.NOTHING;
    int coins = 0;
    boolean bolof = false;
    boolean safe = false;
    String id;
    public void takeSafeCoin(){
      this.coins++;
        safe = true;
        System.out.println(this.id + " -> " + "BANK : TOOK SAFE COIN");
        MoshakasatBazi.changeNobat();
    }

    public void take2Coins(){
        this.coins = this.coins +2;
        safe = false;
        System.out.println(this.id + " -> " + "BANK : TOOK 2 COINS");
    }




    public void removeKard(int i){
        try {
            MoshakasatBazi.getInValidKards().add(kartBazis.get(i));
            this.kartBazis.remove(i);
       }catch (Exception e){
            System.out.println(e.getMessage());

        }
        checkLose(this);
    }
    public void checkLose(Player player){
        if (player.getKartBazis().size() == 0){
            for (Map.Entry<Integer,Player> i:
                 MoshakasatBazi.getPlayers().entrySet()) {
                if (i.getValue().equals(player)){
                    MoshakasatBazi.getPlayers().remove(i.getKey());
                    return;
                }
            }
        }
    }
    public void take3Coins(){
        for (Player i:
             MoshakasatBazi.getPlayers().values()) {
            i.takedAction = Actions.GET_MONEY;
        }
        this.lastAction = Actions.GET_MONEY;
        safe = false;
        this.coins = this.coins + 3;
        System.out.println(this.id + " -> " + "BANK : TOOK 3 COINS");
        for (KartBazi i:
                kartBazis) {
            if (i instanceof BozorgZade){

                bolof = false;
                return;
            }
        }

        bolof = true;
    }

    public boolean kodeta(Player player,int i){
        if (player == null){
            return false;
        }
        safe = false;
        if (player.equals(this)){
            return false;
        }
        if (i <= player.getKartBazis().size() - 1) {
            String idPlayer;
            try {
                idPlayer = player.getId();
            } catch (Exception e) {
                return false;
            }

            if (this.coins >= 7) {
                this.coins -= 7;
                player.removeKard(i);
                MoshakasatBazi.changeNobat();

                System.out.println(this.getId() + "->" + idPlayer + ": KOODETA");
                return true;
            }
        }
        return false;
    }
    public boolean soeGhasd(Player player, int j){
        if (player.isSafe()){
            System.out.println(this.id+"->"+player.id+" : IS SAFE");
            return false;
        }
        safe = false;
        if (this.coins >= 3) {

            if (j <= player.getKartBazis().size() - 1){
                String idPlayer;
                try {
                    idPlayer = player.getId();
                } catch (Exception e) {
                    System.out.println(e.getMessage()+" ERROR");
                    return false;
                }
                System.out.println(this.getId() + "->" + idPlayer + ": SOEGHASD");
                player.removeKard(j);

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
            bolof = true;
        }
        return false;
    }

    public boolean bajGiri(Player player){
        if (player.isSafe()){
            System.out.println(this.id+"->"+player.id+" : IS SAFE");
            return false;
        }
        String idPlayer;
        try {
            idPlayer = player.getId();
            if (idPlayer.equals(this.id)){
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        safe = false;
        bolof = true;
        if (player.getCoins() >=2 ){
            this.lastAction = Actions.BAJ_GIRI;
            player.takedAction = Actions.BAJ_GIRI;
            this.coins +=2;
            int tadadCoin = player.getCoins();
            player.setCoins(tadadCoin -2);
            for (KartBazi i:
                    kartBazis) {
                if (i instanceof Farmande){
                    bolof = false;
                    break;
                }
            }
            System.out.println(player.getId() + "->" + idPlayer + ": BAJGIRI");
            return true;
        }else if (player.getCoins() == 1){
            this.lastAction = Actions.BAJ_GIRI;
            player.takedAction = Actions.BAJ_GIRI;
            this.coins += 1;
            player.setCoins(0);
            for (KartBazi i:
                    kartBazis) {
                if (i instanceof Farmande){
                    bolof = false;
                    break;
                }
            }
            System.out.println(player.getId() + "->" + idPlayer + ": BAJGIRI");
            return true;
        }
        return false;
    }

    public void moaveze(KartBazi kartBazi1,KartBazi kartBazi2){
        safe = false;
        bolof = true;
        for (KartBazi i:
                kartBazis) {
            if (i instanceof Safir){
                bolof = false;
                break;
            }
        }
        this.kartBazis.clear();
        kartBazis.add(kartBazi1);
        kartBazis.add(kartBazi2);
    }
    public void moaveze1coin(KartBazi kartBazi1,KartBazi kartBazi2){
        if (this.coins>=1) {
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
        }
    }


    public boolean reAction(){
        switch (takedAction){
            case BAJ_GIRI:
                bolof = true;
                this.lastAction = Actions.REACTED_ON_BAJGIRI;
                this.takedAction = Actions.NOTHING;
                for (KartBazi i:
                     kartBazis) {
                    if (i instanceof Safir || i instanceof Farmande){
                        bolof = false;
                        break;
                    }
                }
                return true;

            case GET_MONEY:
                this.lastAction = Actions.REACTED_ON_GETMONEY;
                this.takedAction = Actions.NOTHING;
                bolof = true;
                for (KartBazi i:
                        kartBazis) {
                    if ( i instanceof BozorgZade){
                        bolof = false;
                        break;
                    }
                }
                return true;

            case SOE_GHASD:
                this.lastAction = Actions.REACTED_ON_SOEGHASD;
                this.takedAction = Actions.NOTHING;
                bolof = true;
                for (KartBazi i:
                        kartBazis) {
                    if ( i instanceof ShahDokht){
                        bolof = false;
                        break;
                    }
                }
                return true;
        }
        return  false;
    }

    public boolean checkReaction(boolean b){
        if (b){
            return reAction();
        }
        return false;
    }

    public void play(){

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

}
