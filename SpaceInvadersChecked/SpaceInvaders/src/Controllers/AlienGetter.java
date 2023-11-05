package Controllers;

import GameObjects.*;

public class AlienGetter {


    public Alien getAlien(String alienType, int posX, int posY, Boolean inFront){
        if(alienType == null){
            return null;
        }
        else if(alienType == "smallTrooper"){
            return new AlienSmallTrooper(posX, posY, inFront);
        }
        else if(alienType == "mediumTrooper"){
            return new AlienMediumTrooper(posX, posY, inFront);
        }
        else if(alienType == "bigTrooper"){
            return new AlienBigTrooper(posX, posY, inFront);
        }
        else if(alienType == "boss"){
            return new AlienBoss(posX, posY);
        }
        return null;
    }


}
