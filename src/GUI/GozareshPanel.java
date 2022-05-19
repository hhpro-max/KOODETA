package GUI;

import javax.swing.*;
import java.awt.*;

public class GozareshPanel extends JPanel {
    public static GozareshPanel gozareshPanel;

    private GozareshPanel(){


        initPanel();
    }
    public static GozareshPanel getInstance(){
        if (gozareshPanel == null){
            gozareshPanel = new GozareshPanel();
        }
        return gozareshPanel;
    }
    public void initPanel() {
        this.setSize(new Dimension(100,500));
        this.setVisible(true);
        this.setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
    }
    public void initGozaresh(String s){
        JLabel jLabel = new JLabel(s);
        this.add(jLabel);
    }
}
