package GUI;

import LOGIC.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Waiter2 extends Waiter{
    public Waiter2(String chalengeName) {
        super(chalengeName);
        this.setLocation(MainFrame.width - 600,100);
        for (ActionListener i:
             jButton.getActionListeners()) {
            jButton.removeActionListener(i);
        }
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.closeAll();
                Controller.getInstance().getStaticPlayer().challenge();
                MainPanel.getInstance().update();
            }
        });
        jLabel1.setText("CHALLENGE");
        repaint();
        revalidate();
    }

}
