package LOGIC;

import RESOURCES.ImageResource;
import RESOURCES.ResourceManager;

import javax.swing.*;

public class BozorgZade extends KartBazi{
     ImageIcon imageIcon = new ImageIcon(ResourceManager.get(ImageResource.BOZORH_ZADE));

    public void getMoney(){
        //TODO
    }

    public void denyMoney(){
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
