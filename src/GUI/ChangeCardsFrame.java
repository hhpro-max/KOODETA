package GUI;

import LOGIC.Controller;
import LOGIC.KartBazi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChangeCardsFrame extends JFrame {
    int maker = 0;
    int width = 800;
    int height = 800;
    int id;
    List<JButton> jButtons;
    public List<KartBazi> listNahayy;
    public ChangeCardsFrame(int id){

        listNahayy = new ArrayList<>();
        initFrame();
        this.id = id;
        initComps();
    }
    public void initFrame(){
        this.setVisible(true);
        this.setLayout(new GridLayout());
        this.setResizable(false);
        this.setSize(new Dimension(width,height));
    }
    public void initComps(){
        jButtons = new ArrayList<>();
        List<KartBazi> kartBazis = new ArrayList<>();
        kartBazis.addAll(Controller.getInstance().getKardPLayer(id));
        kartBazis.addAll(Controller.getInstance().getRandomKart());
        for (KartBazi i:
             kartBazis) {
            JButton jButton = new JButton(i.getImageIcon());
            jButton.setName(String.valueOf(maker));
            maker++;
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listNahayy.add(kartBazis.get(Integer.parseInt(jButton.getName())));
                    remove(jButton);
                    repaint();
                    revalidate();
                    if (listNahayy.size()>=2){
                        Controller.getInstance().moaveze(listNahayy);
                        dispose();
                        MainPanel.getInstance().updateKards();
                    }
                }
            });
            this.add(jButton);
            jButtons.add(jButton);
        }
    }
}
