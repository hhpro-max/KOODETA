package LOGIC;

import RESOURCES.ImageResource;
import RESOURCES.ResourceManager;

import javax.swing.*;

public class Safir extends KartBazi{
     ImageIcon imageIcon = new ImageIcon(ResourceManager.get(ImageResource.SAFIR));

    public void moaveze(){
        //todo
    }
    public void denyBajgiri(){
        //todo
    }

    @Override
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    @Override
    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }
}
