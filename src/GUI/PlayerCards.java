package GUI;




import LOGIC.AdamKosh;
import LOGIC.Controller;
import LOGIC.KartBazi;

import javax.swing.*;
import java.awt.*;
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
                JLabel jLabel = new JLabel(j.getImageIcon());
                this.add(jLabel);
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
