package SETTINGS;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import LOGIC.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class InitGame {
    public static InitGame initGame;

    private InitGame() {

    }

    public static InitGame getInstance() {
        if (initGame == null) {
            initGame = new InitGame();
        }
        return initGame;
    }

    public void initGame() {
        Player player = new Player();
        Player player2 = null;
        Player player3 = null;
        Player player4 = null;

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src/SETTINGS/InitJson.json"));

            JSONObject jsonObject =  (JSONObject) obj;
//player one
            String name1 = (String) jsonObject.get("CARD1");
            player.getKartBazis().add(getCard(name1));
            String name2 = (String) jsonObject.get("CARD2");
            player.getKartBazis().add(getCard(name2));
            player.setId("A");
            MoshakasatBazi.setPlayer(player);
            MoshakasatBazi.getPlayers().put(1,player);
//player two
            Long integer = (Long) jsonObject.get("player2");
            player2 = getPlayer(integer);
            String name3 = (String) jsonObject.get("CARD3");
            player2.getKartBazis().add(getCard(name1));
            String name4 = (String) jsonObject.get("CARD4");
            player2.getKartBazis().add(getCard(name2));
            player2.setId("B");
            MoshakasatBazi.getPlayers().put(2,player2);
            System.out.println(player2.getClass());
//player three
            Long integer2 = (Long) jsonObject.get("player3");
            player3 = getPlayer(integer2);
            String name5 = (String) jsonObject.get("CARD5");
            player3.getKartBazis().add(getCard(name1));
            String name6 = (String) jsonObject.get("CARD6");
            player3.getKartBazis().add(getCard(name2));
            player3.setId("C");
            MoshakasatBazi.getPlayers().put(3,player3);
            System.out.println(player3.getClass());
//player four
            Long integer3 = (Long) jsonObject.get("player4");
            player4 = getPlayer(integer3);
            String name7 = (String) jsonObject.get("CARD7");
            player4.getKartBazis().add(getCard(name1));
            String name8 = (String) jsonObject.get("CARD8");
            player4.getKartBazis().add(getCard(name2));
            player4.setId("D");
            MoshakasatBazi.getPlayers().put(4,player4);
            System.out.println(player4.getClass());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public KartBazi getCard(String name){
        switch (name) {
            case "ShahDokht":
                return new ShahDokht();
            case "Safir":
                return new Safir();
            case "AdamKosh":
                return new AdamKosh();
            case "BozorgZade":
                return new BozorgZade();
            case "Farmande":
                return new Farmande();
        }
        return null;
    }
    public Player getPlayer(long a){
        switch ((int) a){
            case 2:
                return new Robot1();
            case 3:
                return new Robot2();
            case 4:
                return new Robot3();
            case 5:
                return new Robot4();
        }
        return null;
    }

}
