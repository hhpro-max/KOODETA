package GUI;

import LOGIC.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Take2CoinsAction implements ActionListener {
    JOptionPane jOptionPane = new JOptionPane();
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Controller.getInstance().checkNobat()){
            if (!Controller.getInstance().checkCoins()){
                jOptionPane.showMessageDialog(null,"TEDAD 10 SEKE DARI BAYAD KODETA KONI");
            }else {
                Controller.getInstance().getStaticPlayer().take2Coins();
            }
        }else {
            jOptionPane.showMessageDialog(null,"NOBATET NIST");
        }
        MainPanel.getInstance().update();
    }
}
