package GUI;

import LOGIC.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Waiter extends JFrame {
    JLabel jLabel;
    Timer timer;
    public JButton jButton;
    double timeLeft;
    String chalengeName;
    public boolean isOpen;
    public Waiter(String chalengeName){
        this.chalengeName = chalengeName;
        initFrame();
        initComps();
        timer.start();
    }

    public void initFrame(){
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(new Dimension(200,200));
        this.setResizable(false);
        this.setLocation(MainFrame.width - 300,100);

    }
    public void initComps() {
        isOpen = true;
        timeLeft =5;
        jButton = new JButton(chalengeName);
        jButton.setBounds(50,60,100,30);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JFrame j:
                     MainFrame.jFrameList) {
                    j.dispose();
                }
                MainFrame.jFrameList.clear();
                Controller.getInstance().getStaticPlayer().reAction();
                dispose();
                MainPanel.getInstance().update();
            }
        });
        this.add(jButton);
        jLabel = new JLabel("00 : 00 : 5.0");
        jLabel.setBounds(40,20,100,30);
        ActionListener countDown = null;
        countDown = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                jLabel.setText("00 : 00 : " + String.valueOf(timeLeft));

                repaint();
                revalidate();
                if (timeLeft <= 0) {
                    timer.stop();
                    dispose();
                    isOpen = false;
                }
            }
        };
        timer = new Timer(1000, countDown);
        this.add(jLabel);

    }



}
