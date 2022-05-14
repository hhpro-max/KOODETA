package GUI;

import LOGIC.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Take3CoinsAction implements ActionListener {
    JOptionPane jOptionPane = new JOptionPane();
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Controller.getInstance().checkCoins()){
            jOptionPane.showMessageDialog(null,"TEDAD 10 SEKE DARI BAYAD KODETA KONI");
        }else {
            Controller.getInstance().getStaticPlayer().take3Coins();

        }
        MainPanel.getInstance().update();
    }
}
