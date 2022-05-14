package GUI;

import RESOURCES.ImageResource;
import RESOURCES.ResourceManager;

import javax.swing.*;
import java.awt.*;

public class GuideFrame extends JFrame {
    int width = 800;
    int height = 800;

    JLabel jLabel;
    ImageIcon imageIcon;

    public GuideFrame(){
        initFrame();
        initComps();

        update();
    }

    public void initFrame(){
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(width,height));
    }
    public void initComps(){
        imageIcon = new ImageIcon(ResourceManager.get(ImageResource.GUIDE));
        jLabel = new JLabel(imageIcon);
        jLabel.setBounds(-10,-15,width,height);
        this.add(jLabel);
    }


    public void update(){
        this.repaint();
        this.revalidate();
    }
}
