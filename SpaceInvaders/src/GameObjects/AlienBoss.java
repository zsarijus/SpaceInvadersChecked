package GameObjects;

public class AlienBoss extends Alien {

    Boolean movesRight = true;
    public AlienBoss(int positionX, int positionY) {
        super(positionX, positionY);
    }

@Override
    public void ChangePosition(int positionX, int positionY){
        if(movesRight == true && this.positionX < 74){
            this.positionX += positionX;
        }
        else if(movesRight == true){
            movesRight = false;
        }
        if(movesRight == false && this.positionX > 11){
            this.positionX -= positionX;
        }
        else if(movesRight == false){
            movesRight = true;
        }

    }

    public int getSize() {
        return 4;
    }

    public void MakeShooter(){}


    public Boolean getInFront() {
        return false;
    }

}
