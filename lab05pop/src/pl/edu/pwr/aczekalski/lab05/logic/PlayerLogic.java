package pl.edu.pwr.aczekalski.lab05.logic;

import pl.edu.pwr.aczekalski.lab05.model.Field;
import pl.edu.pwr.aczekalski.lab05.model.Player;

import java.util.ArrayList;

public class PlayerLogic {
    ArrayList<ArrayList<Field>> fieldLabelsArray; //arraylista pola przekazana z głównej metody

    public PlayerLogic(ArrayList<ArrayList<Field>> fieldLabelsArray){
        this.fieldLabelsArray=fieldLabelsArray;
    }

    public void move(Player player){
        if(player.direction && canMove(player.positionY - 1)){ //gracz idzie w górę
            if(fieldLabelsArray.get(player.positionY - 1).get(player.positionX).set()){ //jeśli pole pod nim jest zajęte
                fieldLabelsArray.get(player.positionY).get(player.positionX).unSet();
                player.positionY -= 1;
                fieldLabelsArray.get(player.getPositionY()).get(player.getPositionX()).label.setText(Character.toString(player.sign));
                fieldLabelsArray.get(player.getPositionY()+1).get(player.getPositionX()).label.setText("."); //w górę
//                System.out.println("Gracz nr: "+player.id+", pozycja: "+ player.positionY);
                return;
            }
        }

        if(!player.direction && canMove(player.positionY + 1)){ //gracz idzie w dół
            if(fieldLabelsArray.get(player.positionY + 1).get(player.positionX).set()){ //jeśli pole nad nim jest zajęte
                fieldLabelsArray.get(player.positionY).get(player.positionX).unSet();
                player.positionY += 1;
                fieldLabelsArray.get(player.getPositionY()).get(player.getPositionX()).label.setText(Character.toString(player.sign));
                fieldLabelsArray.get(player.getPositionY()-1).get(player.getPositionX()).label.setText("."); //w górę
//                System.out.println("Gracz nr: "+player.id+", pozycja: "+ player.positionY);
                return;
            }
        }

        player.direction = !player.direction;
    }

    private boolean canMove(int pos){ //metoda sprawdzająca czy gracz nie wyszedł poza pole boiska
        return !(pos < 0 || pos >= fieldLabelsArray.size());
    }

}
