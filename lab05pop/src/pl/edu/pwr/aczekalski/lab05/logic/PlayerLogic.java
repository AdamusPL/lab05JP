package pl.edu.pwr.aczekalski.lab05.logic;

import pl.edu.pwr.aczekalski.lab05.model.Field;
import pl.edu.pwr.aczekalski.lab05.model.Player;

import java.util.ArrayList;

public class PlayerLogic {
    ArrayList<ArrayList<Field>> fieldLabelsArray; //field arraylist from main method

    public PlayerLogic(ArrayList<ArrayList<Field>> fieldLabelsArray) {
        this.fieldLabelsArray = fieldLabelsArray;
    }

    public void move(Player player) {
        if (player.direction && canMove(player.positionY - 1)) { //Player goes up
            if (fieldLabelsArray.get(player.positionY - 1).get(player.positionX).set()) { //If field under him is busy
                fieldLabelsArray.get(player.positionY).get(player.positionX).unSet();
                player.positionY -= 1;
                fieldLabelsArray.get(player.getPositionY()).get(player.getPositionX()).label.setText(Character.toString(player.sign));
                fieldLabelsArray.get(player.getPositionY() + 1).get(player.getPositionX()).label.setText("."); //Up
                return;
            }
        }

        if (!player.direction && canMove(player.positionY + 1)) { //Player goes down
            if (fieldLabelsArray.get(player.positionY + 1).get(player.positionX).set()) { //If field beneath him is busy
                fieldLabelsArray.get(player.positionY).get(player.positionX).unSet();
                player.positionY += 1;
                fieldLabelsArray.get(player.getPositionY()).get(player.getPositionX()).label.setText(Character.toString(player.sign));
                fieldLabelsArray.get(player.getPositionY() - 1).get(player.getPositionX()).label.setText("."); //Up
                return;
            }
        }

        player.direction = !player.direction;
    }

    private boolean canMove(int pos) { //method checking if player didn't come out of the field
        return !(pos < 0 || pos >= fieldLabelsArray.size());
    }

}
