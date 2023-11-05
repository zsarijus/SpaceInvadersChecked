package GameObjects;

public class AlienBigTrooper extends Alien{

    Boolean inFront;

    public Boolean getInFront() {
        return inFront;
    }
    public AlienBigTrooper(int positionX, int positionY, Boolean inFront) {
        super(positionX, positionY);
        this.inFront = inFront;
    }

    public int getSize() {
        return 3;
    }
    public void MakeShooter(){
        inFront = true;
    }



}
