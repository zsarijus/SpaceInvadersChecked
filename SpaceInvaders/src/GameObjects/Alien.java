package GameObjects;

import lombok.Getter;
import lombok.Setter;


public abstract class Alien {
    int originalPositionX;
    int originalPositionY;
    int positionX;
    int positionY;

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
    public abstract int getSize();




    public Alien(int positionX, int positionY) {
        this.originalPositionX = positionX;
        this.originalPositionY = positionY;
        this.positionX = originalPositionX;
        this.positionY = originalPositionY;
    }


    public void ChangePosition(int positionX, int positionY){
        this.positionX = originalPositionX + positionX;
        this.positionY = originalPositionY - positionY;
    }

    public abstract void MakeShooter();

    public abstract Boolean getInFront();

}
