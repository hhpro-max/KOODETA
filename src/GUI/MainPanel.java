package GUI;

import LOGIC.Actions;
import LOGIC.Controller;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    JButton rahnama;
    JButton takeSafeCoin;
    JButton take2coins;
    JButton take3Coins;
    JButton bajGiri;

    JButton pickCard1;
    JButton pickCard2;

    JTextField idPlayer;
    JLabel idPlayer1;
    JButton koodeta;

    JButton soeGhasd;

    JButton moaveze;
    JButton moaveze1coin;

    JOptionPane jOptionPane;

    PlayerCards playerCards1;
    JLabel tedadSeke;
    JLabel tedadSeke1;

    PlayerCards playerCards2;
    JLabel tedadseke2;

    JScrollPane jScrollPane;
    InvalidCardsPanel invalidCardsPanel;

    public static MainPanel mainPanel;

    private MainPanel() {
        initPanel();
        initComps();
        align();
        setActionListener();
    }

    public static MainPanel getInstance() {
        if (mainPanel == null) {
            mainPanel = new MainPanel();
        }
        return mainPanel;
    }

    public void initPanel() {
        this.setSize(new Dimension(MainFrame.width, MainFrame.height));
        this.setVisible(true);
        this.setLayout(null);
    }

    public void initComps() {
        rahnama = new JButton("RAHNAMA");
        takeSafeCoin = new JButton("TAKE 1 COIN");
        take2coins = new JButton("TAKE 2 COINS");
        take3Coins = new JButton("TAKE 3 COINS");

        pickCard1 = new JButton("CARD 1");
        pickCard2 = new JButton("CARD 2");

        tedadSeke = new JLabel("COINS :");
        tedadSeke1 = new JLabel(String.valueOf(Controller.getInstance().getStaticPlayer().getCoins()));
        tedadseke2 = new JLabel(String.valueOf(Controller.getInstance().getPlayer(2).getCoins()));

        idPlayer = new JTextField();
        idPlayer1 = new JLabel("SHOMARE BAZIKON :");

        koodeta = new JButton("KOODETA !");
        bajGiri = new JButton("BAJ GIRI");
        soeGhasd = new JButton("SOE GHASD !");

        moaveze = new JButton("MOAVEZE");
        moaveze1coin = new JButton("TAVIZ 1 KART");

        jOptionPane = new JOptionPane();
        invalidCardsPanel = new InvalidCardsPanel();
        jScrollPane = new JScrollPane(invalidCardsPanel);

        playerCards1 = new PlayerCards(Controller.getInstance().getKardPLayer(1));
        playerCards2 = new PlayerCards(Controller.getInstance().getKardPLayer(2));
    }

    public void align() {
        rahnama.setBounds(MainFrame.width - 150, MainFrame.height - (MainFrame.height - 30), 100, 30);
        this.add(rahnama);
        takeSafeCoin.setBounds(MainFrame.width - (MainFrame.width - 10), MainFrame.height - 100, 150, 30);
        this.add(takeSafeCoin);
        take2coins.setBounds(MainFrame.width - (MainFrame.width - 180), MainFrame.height - 100, 150, 30);
        this.add(take2coins);
        idPlayer1.setBounds(MainFrame.width - (MainFrame.width - 350), MainFrame.height - 100, 150, 30);
        this.add(idPlayer1);
        idPlayer.setBounds(MainFrame.width - (MainFrame.width - 500), MainFrame.height - 100, 120, 30);
        this.add(idPlayer);
        koodeta.setBounds(MainFrame.width - (MainFrame.width - 640), MainFrame.height - 100, 100, 30);
        this.add(koodeta);
        take3Coins.setBounds(MainFrame.width - (MainFrame.width - 90), MainFrame.height - 150, 150, 30);
        this.add(take3Coins);
        tedadSeke.setBounds(MainFrame.width / 2 - 50, MainFrame.height - 150, 100, 30);
        this.add(tedadSeke);
        tedadSeke1.setBounds(MainFrame.width / 2 + 5, MainFrame.height - 150, 150, 30);
        this.add(tedadSeke1);
        tedadseke2.setBounds(MainFrame.width - 275,MainFrame.height - 300,30,30);
        this.add(tedadseke2);
        soeGhasd.setBounds(MainFrame.width - (MainFrame.width - 690), MainFrame.height - 150, 150, 30);
        this.add(soeGhasd);
        bajGiri.setBounds(MainFrame.width - (MainFrame.width - 760), MainFrame.height - 100, 150, 30);
        this.add(bajGiri);
        playerCards1.setBounds((MainFrame.width / 2) - 250, MainFrame.height - 450, 450, 300);
        this.add(playerCards1, 0);
        playerCards2.setBounds((MainFrame.width) - 500, MainFrame.height - 650, 450, 300);
        this.add(playerCards2, 0);
        moaveze.setBounds(MainFrame.width - (MainFrame.width - 920), MainFrame.height - 100, 150, 30);
        this.add(moaveze);
        moaveze1coin.setBounds(MainFrame.width - (MainFrame.width - 1080), MainFrame.height - 100, 150, 30);
        this.add(moaveze1coin);
        jScrollPane.setBounds(MainFrame.width/2 - 250, MainFrame.height/2 - 250, 300, 300);
        this.add(jScrollPane);

    }

    public void setActionListener() {
        rahnama.addActionListener(new OpenGuideAction());
        takeSafeCoin.addActionListener(new takeSafeCoinAction());
        take2coins.addActionListener(new Take2CoinsAction());
        take3Coins.addActionListener(new Take3CoinsAction());

        koodeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Controller.getInstance().checkNobat()){
                    if (!idPlayer.getText().isEmpty()) {
                        if (Controller.getInstance().koodeta(Integer.parseInt(idPlayer.getText()))) {
                            jOptionPane.showMessageDialog(null, "KODETA BA MOVAFAGHIAT TAKMIL SHOD");
                        } else {
                            jOptionPane.showMessageDialog(null, "SEKE KAFI DARY GOLAM? (SHAYAD PLAYER MOJOD NIST)");
                        }
                    } else {
                        jOptionPane.showMessageDialog(null, "ID PLAYER RA VARED KONID");
                    }
                }else {
                    jOptionPane.showMessageDialog(null, "Nobatet nist");
                }
                update();
                updateKards();
            }
        });
        soeGhasd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Controller.getInstance().checkNobat()){
                    if (!idPlayer.getText().isEmpty()) {
                        if (Controller.getInstance().soeGhasd(Integer.parseInt(idPlayer.getText()))) {
                            jOptionPane.showMessageDialog(null, "SOEGHASD BA MOVAFAGHIAT TAKMIL SHOD");
                        } else {
                            jOptionPane.showMessageDialog(null, "SEKE KAFI DARY GOLAM? (SHAYAD PLAYER MOJOD NIST)");
                        }
                    } else {
                        jOptionPane.showMessageDialog(null, "ID PLAYER RA VARED KONID");
                    }
                }else {
                    jOptionPane.showMessageDialog(null, "NOBATET NIST");
                }
                update();
                updateKards();
            }

        });
        bajGiri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Controller.getInstance().checkNobat()){
                    if (!idPlayer.getText().isEmpty()) {
                        if (Controller.getInstance().bajGiri(Integer.parseInt(idPlayer.getText()))) {
                            jOptionPane.showMessageDialog(null, "BAJGIRI BA MOVAFAGHIAT TAKMIL SHOD");
                        } else {
                            jOptionPane.showMessageDialog(null, "SEKE KAFI DARE GOLAM? (SHAYAD PLAYER MOJOD NIST)");
                        }
                    } else {
                        jOptionPane.showMessageDialog(null, "ID PLAYER RA VARED KONID");
                    }
                }else {
                    jOptionPane.showMessageDialog(null, "NOBATET NIST");
                }
                update();
            }
        });
        moaveze.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                          if (Controller.getInstance().checkNobat()) {
                                              ChangeCardsFrame changeCardsFrame = new ChangeCardsFrame(1);
                                          }
                                          else {
                                              jOptionPane.showMessageDialog(null, "NOBATET NIST");
                                          }

                                      }
                                  }
        );
        moaveze1coin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Controller.getInstance().checkNobat()){
                    if (Controller.getInstance().getStaticPlayer().getCoins()>=1){
                        TavizCardFrame tavizCardFrame = new TavizCardFrame();
                    }else {
                        jOptionPane.showMessageDialog(null,"HADAGHAL YE SEKE BAYAD BEDI");
                    }
                }else {
                    jOptionPane.showMessageDialog(null,"Nobatet nist");
                }
            }
        });
    }


    public void update() {

        if (!(tedadSeke1 == null)) {
            this.remove(tedadSeke1);
        }
        if (!(tedadseke2 == null)) {
            this.remove(tedadseke2);
        }
        try {
            tedadSeke1 = new JLabel(String.valueOf(Controller.getInstance().getStaticPlayer().getCoins()));
        }catch (Exception e){
            //todo
        }
        if (Controller.getInstance().getPlayer(2) != null) {
            tedadseke2 = new JLabel(String.valueOf(Controller.getInstance().getPlayer(2).getCoins()));
        }

        tedadSeke1.setBounds(MainFrame.width / 2 + 5, MainFrame.height - 150, 150, 30);
        this.add(tedadSeke1);
        tedadseke2.setBounds(MainFrame.width - 275,MainFrame.height - 300,30,30);
        this.add(tedadseke2);
       // checkRemove(); todo
        updateKards();
        repaint();
        revalidate();
    }

    public void updateKards() {
        invalidCardsPanel.initComps();
        invalidCardsPanel.repaint();
        invalidCardsPanel.revalidate();

        this.remove(playerCards1);
        this.remove(playerCards2);
        playerCards1 = new PlayerCards(Controller.getInstance().getKardPLayer(1));
        playerCards2 = new PlayerCards(Controller.getInstance().getKardPLayer(2));
        playerCards1.setBounds((MainFrame.width / 2) - 250, MainFrame.height - 450, 450, 300);
        this.add(playerCards1, 0);
        playerCards2.setBounds((MainFrame.width) - 500, MainFrame.height - 650, 450, 300);
        this.add(playerCards2, 0);
        playerCards1.repaint();
        playerCards2.repaint();
        playerCards1.revalidate();
        playerCards2.revalidate();
        repaint();
        revalidate();

    }
    public void showWarnMessage(){
        JOptionPane.showMessageDialog(null,"YE KART ENTEKHAB KON");
    }
    /*  public void checkRemove(){ todo
        if (Controller.getInstance().getStaticPlayer().takedAction.equals(Actions.KOODETA)){
            if (pickCard1 != null){
                remove(pickCard1);
            }
            if (pickCard2 != null){
                remove(pickCard2);
            }
            pickCard1 = new JButton("CARD 1");
            pickCard2 = new JButton("CARD 2");
            pickCard1.setBounds((MainFrame.width / 2) - 250, MainFrame.height - 250, 100, 30);
            this.add(pickCard1,1);
            pickCard2.setBounds((MainFrame.width / 2) - 50, MainFrame.height - 250, 100, 30);
            this.add(pickCard2,1);
        }else {
            if (pickCard1 != null){
                remove(pickCard1);
            }
            if (pickCard2 != null){
                remove(pickCard2);
            }
        }
    }

     */


}
