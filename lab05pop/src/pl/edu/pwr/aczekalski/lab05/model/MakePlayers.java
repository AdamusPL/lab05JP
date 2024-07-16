package pl.edu.pwr.aczekalski.lab05.model;

import pl.edu.pwr.aczekalski.lab05.logic.PlayerLogic;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MakePlayers {
    ArrayList<ArrayList<Field>> fieldLabelsArray; //field arraylist from main method

    public MakePlayers(ArrayList<ArrayList<Field>> fieldLabelsArray) {
        this.fieldLabelsArray = fieldLabelsArray;

    }

    public ArrayList<Player> putPlayers(int numberOfPlayers, int numberOfRows, int columnNumber) {
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Integer> picked = new ArrayList<>();
        char sign = 'a';
        for (int i = 0; i < numberOfPlayers; i++) {
            PlayerLogic playerLogic = new PlayerLogic(fieldLabelsArray);
            int randomNum = ThreadLocalRandom.current().nextInt(0, numberOfRows);
            if (!picked.contains(randomNum)) {
                picked.add(randomNum);
                Player player = new Player(sign, i, columnNumber, randomNum, playerLogic, players);
                player.start();
                players.add(player);
                sign++;
            } else i--;
        }

        return players;
    }
}
