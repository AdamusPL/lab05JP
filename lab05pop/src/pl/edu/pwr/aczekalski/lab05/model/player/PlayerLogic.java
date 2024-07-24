package pl.edu.pwr.aczekalski.lab05.model.player;

import pl.edu.pwr.aczekalski.lab05.model.field.Field;

import java.util.ArrayList;

public class PlayerLogic {
    private ArrayList<ArrayList<Field>> fieldLabelsArray; //field arraylist from main method

    public PlayerLogic(ArrayList<ArrayList<Field>> fieldLabelsArray) {
        this.fieldLabelsArray = fieldLabelsArray;
    }

    protected void move(Player player) {
        if (player.isDirection() && canMove(player.getPositionY() - 1)) { //Player goes up
            if (fieldLabelsArray.get(player.getPositionY() - 1).get(player.getPositionX()).set()) { //If field under him is busy
                fieldLabelsArray.get(player.getPositionY()).get(player.getPositionX()).unSet();
                player.setPositionY(player.getPositionY() - 1);
                fieldLabelsArray.get(player.getPositionY()).get(player.getPositionX()).getLabel().setText(Character.toString(player.getSign()));
                fieldLabelsArray.get(player.getPositionY() + 1).get(player.getPositionX()).getLabel().setText("."); //Up
                return;
            }
        }

        if (!player.isDirection() && canMove(player.getPositionY() + 1)) { //Player goes down
            if (fieldLabelsArray.get(player.getPositionY() + 1).get(player.getPositionX()).set()) { //If field beneath him is busy
                fieldLabelsArray.get(player.getPositionY()).get(player.getPositionX()).unSet();
                player.setPositionY(player.getPositionY() + 1);
                fieldLabelsArray.get(player.getPositionY()).get(player.getPositionX()).getLabel().setText(Character.toString(player.getSign()));
                fieldLabelsArray.get(player.getPositionY() - 1).get(player.getPositionX()).getLabel().setText("."); //Up
                return;
            }
        }

        player.setDirection(!player.isDirection());
    }

    private boolean canMove(int pos) { //method checking if player didn't come out of the field
        return !(pos < 0 || pos >= fieldLabelsArray.size());
    }

}
