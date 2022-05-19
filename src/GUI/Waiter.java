package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;


public class Waiter extends JFrame implements Runnable{
    JLabel jLabel;
    Timer timer;
    double timeLeft;
    public static Waiter waiter;
    private Waiter(){
        initFrame();
        initComps();
        timer.start();
    }
    public static Waiter getInstance(){
        if (waiter == null){
            waiter = new Waiter();
        }
        return waiter;
    }
    public void initFrame(){
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(new Dimension(200,200));
        this.setResizable(false);


    }
    public void initComps() {
        timeLeft =5;
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
                }
            }
        };
        timer = new Timer(1000, countDown);
        this.add(jLabel);

    }



    @Override
    public void run() {
        Waiter waiter = new Waiter();
    }
}
