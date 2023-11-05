package GameObjects;

public class AlienSmallTrooper extends Alien{

    Boolean inFront;

    public Boolean getInFront() {
        return inFront;
    }
    public AlienSmallTrooper(int positionX, int positionY, Boolean inFront) {
        super(positionX, positionY);
        this.inFront = inFront;
    }

    public int getSize() {
        return 1;
    }
    public void MakeShooter(){
        inFront = true;
    }



}
