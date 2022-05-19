package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
   static int width = 1900;
   static int height = 1000;

    MainPanel mainPanel;

    public MainFrame(){
        initFrame();


        update();
    }

    public void initFrame(){
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(width,height));
        mainPanel = MainPanel.getInstance();
        this.add(mainPanel);
    }

    public void update(){
        this.repaint();
        this.revalidate();
    }


}
