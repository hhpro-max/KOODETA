package LOGIC;

import RESOURCES.ImageResource;
import RESOURCES.ResourceManager;

import javax.swing.*;

public class Farmande extends KartBazi{
    ImageIcon imageIcon = new ImageIcon(ResourceManager.get(ImageResource.BAJ_GIR));

    public void bajGiri(){
        //TODO
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
