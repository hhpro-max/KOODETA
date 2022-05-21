package GUI;


import LOGIC.*;
import SETTINGS.InitGame;

import javax.swing.*;

public class GuiMain {
    public static void main(String[] args) {
        MoshakasatBazi.initCards();
        InitGame.getInstance().initGame();


        MainFrame mainFrame = new MainFrame();

    }
}
