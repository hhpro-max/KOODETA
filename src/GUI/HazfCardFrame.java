package GUI;

import LOGIC.Controller;
import LOGIC.KartBazi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HazfCardFrame extends JFrame {
    public HazfCardFrame(){
        initFrame();
        initComps();
    }
    public void initFrame(){
        this.setVisible(true);
        this.setLayout(new GridLayout());
        this.setResizable(false);
        this.setSize(new Dimension(800,800));
    }
    public void initComps(){
        for (KartBazi kartBazi:
                Controller.getInstance().getStaticPlayer().getKartBazis()) {
            ImageIcon imageIcon = kartBazi.getImageIcon();
            JButton jButton = new JButton(imageIcon);

            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Controller.getInstance().removeCard(imageIcon);
                    dispose();
                    MainPanel.getInstance().update();
                }
            });
            this.add(jButton);
        }
    }
}
