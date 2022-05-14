package LOGIC;

import RESOURCES.ImageResource;
import RESOURCES.ResourceManager;

import javax.swing.*;

public class AdamKosh extends KartBazi{
    ImageIcon imageIcon = new ImageIcon(ResourceManager.get(ImageResource.ADAM_KOSH));

    public void soeGhasd(){
        //TODO
    }

    @Override
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }
}
