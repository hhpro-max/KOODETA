package LOGIC;

import RESOURCES.ImageResource;
import RESOURCES.ResourceManager;

import javax.swing.*;

public class KartBazi {
  public  ImageIcon imageIcon = new ImageIcon();
  public ImageIcon imageIcon1 = new ImageIcon(ResourceManager.get(ImageResource.BACK));

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public ImageIcon getImageIcon1() {
        return imageIcon1;
    }

    public void setImageIcon1(ImageIcon imageIcon1) {
        this.imageIcon1 = imageIcon1;
    }
}

