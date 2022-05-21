package LOGIC;

import java.util.Collections;

public class Robot3 extends Player {
    @Override
    public void play() {
        this.chooseTargetPlayer();
        chooseCard();
        boolean doIhaveSafir = false;
        boolean doIhaveAdamkosh = false;
        int indexOf = -1;
        for (KartBazi i :
                this.kartBazis) {
            if (i instanceof Safir) {
                doIhaveSafir = true;
                indexOf = kartBazis.indexOf(i);
                break;
            }
        }
        for (KartBazi i :
                this.kartBazis) {
            if (i instanceof AdamKosh) {
                doIhaveAdamkosh = true;
                break;
            }
        }
        if (doIhaveAdamkosh) {
            if (this.coins >= 3) {
                if (targetPlayer1.isSafe()){
                    if (this.coins >= 7){
                        this.kodeta(targetPlayer1);
                        return;
                    }else {
                        take2Coins();
                        return;
                    }
                }else {
                    this.soeGhasd(targetPlayer1);
                    return;
                }
            } else {
                this.takeSafeCoin();
                return;
            }
        } else if (doIhaveSafir) {
            Collections.shuffle(MoshakasatBazi.getValidKards());
            if (MoshakasatBazi.getValidKards().get(0) instanceof AdamKosh) {
                this.moaveze(kartBazis.get(0), MoshakasatBazi.getValidKards().get(0));
                return;
            } else if (MoshakasatBazi.getValidKards().get(1) instanceof AdamKosh) {
                this.moaveze(kartBazis.get(0), MoshakasatBazi.getValidKards().get(1));
                return;
            }else {
                take2Coins();
                return;
            }
        }else if (this.coins >= 7){
            this.kodeta(targetPlayer1);
            return;
        }
        else if (this.coins >= 1) {
            Collections.shuffle(MoshakasatBazi.getValidKards());
            if (MoshakasatBazi.getValidKards().get(0) instanceof AdamKosh) {

                this.moaveze1coin(this.kartBazis.get(0), MoshakasatBazi.getValidKards().get(0));
                return;
            } else if (MoshakasatBazi.getValidKards().get(1) instanceof AdamKosh) {
                this.moaveze1coin(this.kartBazis.get(0), MoshakasatBazi.getValidKards().get(1));
                return;
            }else {
                this.take2Coins();
            }
        }
        else {
            this.take2Coins();
            return;
        }

    }

    @Override
    public void chooseCard() {
        if (this.choosenCard == null) {
            this.choosenCard = this.kartBazis.size() - 1;
        }
    }
}
