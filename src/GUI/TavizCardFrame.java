package GUI;

import LOGIC.Controller;
import LOGIC.KartBazi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TavizCardFrame extends ChangeCardsFrame {
    public TavizCardFrame(){
        super(1);
        initThis();
    }
    public void initThis(){
        for (JButton i:
             jButtons) {
            for (ActionListener j:
                 i.getActionListeners()) {
                i.removeActionListener(j);
            }
        }
        maker = 0;
        for (JButton i:
             jButtons) {
            this.remove(i);
        }
        jButtons.clear();
        jButtons = new ArrayList<>();
        List<KartBazi> kartBazis = new ArrayList<>();
        kartBazis.addAll(Controller.getInstance().getKardPLayer(id));
        List<KartBazi> felan = Controller.getInstance().getRandomKart();
        felan.remove(0);
        kartBazis.addAll(felan);
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
                        Controller.getInstance().tavizkart(listNahayy);
                        dispose();
                        MainPanel.getInstance().updateKards();
                        MainPanel.getInstance().update();
                    }
                }
            });
            this.add(jButton);
            jButtons.add(jButton);
        }
    }
}
