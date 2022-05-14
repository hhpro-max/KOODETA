package GUI;

import LOGIC.Controller;
import LOGIC.KartBazi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InvalidCardsPanel extends JPanel {
    List<ImageIcon> imageIcons;
    public InvalidCardsPanel(){
        imageIcons = new ArrayList<>();
        initPanel();
        initComps();
    }
    public void initPanel() {
        this.setSize(new Dimension(MainFrame.width/4, MainFrame.height/4));
        this.setVisible(true);
        this.setLayout(new GridLayout());
    }
    public void initComps(){
        this.removeAll();
        for (KartBazi i :
                Controller.getInstance().getInvalidCards()) {
            JLabel jLabel = new JLabel(i.getImageIcon());
            this.add(jLabel);
        }
    }

}
