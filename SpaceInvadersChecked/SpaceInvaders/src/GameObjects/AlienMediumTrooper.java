package GameObjects;

public class AlienMediumTrooper extends Alien{

    Boolean inFront;

    public Boolean getInFront() {
        return inFront;
    }
    public AlienMediumTrooper(int positionX, int positionY, Boolean inFront) {
        super(positionX, positionY);
        this.inFront = inFront;
    }

    public int getSize() {
        return 2;
    }
    public void MakeShooter(){
        inFront = true;
    }



}
