package GUI;




import LOGIC.AdamKosh;
import LOGIC.Controller;
import LOGIC.KartBazi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlayerCards extends JPanel {
    GridLayout gridLayout;
    List<KartBazi> kartBaziList;


    public PlayerCards(List<KartBazi> kartBaziList){
       this.kartBaziList = kartBaziList;
        initPanel();
        initComps();
    }
    public void initPanel(){
        this.setSize(new Dimension(MainFrame.width/10,MainFrame.height/10));
        this.setVisible(true);
        gridLayout = new GridLayout();
        this.setLayout(gridLayout);
    }
    public void initComps(){
        if (kartBaziList == null){
            return;
        }
        if (Controller.getInstance().isThisPlayer()){
            for (KartBazi j:
                    kartBaziList) {
                JButton jButton = new JButton(j.getImageIcon());
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {

                            String[] idk = j.getClass().toString().split("\\.");
                            System.out.println(idk[1]);
                            Controller.getInstance().getStaticPlayer().mainPlayerChooseCard = Controller.getInstance().getStaticPlayer().getKartBazis().indexOf(j);
                            System.out.println(Controller.getInstance().getStaticPlayer().mainPlayerChooseCard);
                        }catch (Exception e1){

                        }
                    }
                });
                this.add(jButton);
            }
        }else {
            for (KartBazi i:
                 kartBaziList) {
                JLabel jLabel = new JLabel(i.getImageIcon1());
                this.add(jLabel);
            }
        }
    }
}
