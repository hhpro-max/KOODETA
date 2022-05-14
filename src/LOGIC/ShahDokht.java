package LOGIC;

import RESOURCES.ImageResource;
import RESOURCES.ResourceManager;

import javax.swing.*;

public class ShahDokht extends KartBazi{
     ImageIcon imageIcon = new ImageIcon(ResourceManager.get(ImageResource.SHAH_DOKHT));

    public void denyKiller(){
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
