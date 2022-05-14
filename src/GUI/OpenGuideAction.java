package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenGuideAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        GuideFrame guideFrame  = new GuideFrame();
    }
}
