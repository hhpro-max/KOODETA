package RESOURCES;

import java.awt.*;
@FunctionalInterface
public interface IImageLoader {
    Image getImage(ImageResource type);
}
