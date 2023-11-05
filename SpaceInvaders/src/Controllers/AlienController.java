package Controllers;

import GameObjects.*;

import java.util.ArrayList;
import java.util.Random;

public class AlienController {


    ArrayList<Alien> aliens = new ArrayList<Alien>();

    Alien alienBoss = null;
    AlienGetter alienGetter = new AlienGetter();

    int chanceForBossToSpawn = 5;

    private Boolean movingRight = true;
    private int currentPositionX = 0, cureentPositionY = 0;
    public AlienController(int x, int y) {
        Boolean isInFront = null;
        String type;
        for(int i = 0; i < 11;i++){
            isInFront = false;
            for(int d = 0; d < 6;d++){
                if(d == 5) isInFront = true;
                if(d < 2) type = "smallTrooper";
                else if(d < 4) type = "mediumTrooper";
                else type = "bigTrooper";
                aliens.add(alienGetter.getAlien(type, x + i * 6, y + d, isInFront));
            }
        }

    }

    public ArrayList<Alien> GetAliens(){
        return aliens;
    }

    public int[][] GetAliens(int[][] battlefield){
        aliens.forEach((a) -> {
            if(a.getSize() == 1){
                battlefield[a.getPositionY()][a.getPositionX()] = 4;
            }
            else if(a.getSize() == 2){
                battlefield[a.getPositionY()][a.getPositionX() - 1] = 421;
                battlefield[a.getPositionY()][a.getPositionX()] = 422;
                battlefield[a.getPositionY()][a.getPositionX() + 1] = 423;
            }
            else if(a.getSize() == 3){
                battlefield[a.getPositionY()][a.getPositionX() - 2] = 431;
                battlefield[a.getPositionY()][a.getPositionX() - 1] = 432;
                battlefield[a.getPositionY()][a.getPositionX()] = 433;
                battlefield[a.getPositionY()][a.getPositionX() + 1] = 434;
                battlefield[a.getPositionY()][a.getPositionX() + 2] = 435;
            }
        });
        if(alienBoss != null){
            battlefield[alienBoss.getPositionY()][alienBoss.getPositionX() - 3] = 421;
            battlefield[alienBoss.getPositionY()][alienBoss.getPositionX() - 2] = 441;
            battlefield[alienBoss.getPositionY()][alienBoss.getPositionX() - 1] = 442;
            battlefield[alienBoss.getPositionY()][alienBoss.getPositionX()] = 433;
            battlefield[alienBoss.getPositionY()][alienBoss.getPositionX() + 1] = 442;
            battlefield[alienBoss.getPositionY()][alienBoss.getPositionX() + 2] = 441;
            battlefield[alienBoss.getPositionY()][alienBoss.getPositionX() + 3] = 423;
        }
        return battlefield;
    }

    public void UpdateArmyMovement(){
        if(movingRight == true && currentPositionX < 9){
            currentPositionX++;
        }
        else if (movingRight == true){
            cureentPositionY--;
            movingRight = false;
        }
        else if(movingRight == false && currentPositionX > -9){
            currentPositionX--;
        }
        else if (movingRight == false){
            cureentPositionY--;
            movingRight = true;
        }
        UpdateAliensMovement();
        if(alienBoss == null){
            Random rand = new Random();
            int i;
            i = rand.nextInt(100);
            if(i <= chanceForBossToSpawn){
                alienBoss = alienGetter.getAlien("boss", 10, 1, false);
                alienBoss = new AlienBoss(10, 1);
            }
        }
        else{
            alienBoss.ChangePosition(2, 0);
        }
    }

    private void UpdateAliensMovement(){
        aliens.forEach((a) -> a.ChangePosition(currentPositionX, cureentPositionY));
    }


    public ArrayList<EarthDefensiveBlock> CheckAndComputeAlienCollisions(ArrayList<EarthDefensiveBlock> earthDefensiveBlocks){
        aliens.forEach(a-> {
            earthDefensiveBlocks.forEach(e -> {
                for(int i = 0; i < e.getDefensiveBlockBricks().size(); i++){
                    if(e.getDefensiveBlockBricks().get(i).getPositionX() == a.getPositionX() && e.getDefensiveBlockBricks().get(i).getPositionY() == a.getPositionY()){
                        e.getDefensiveBlockBricks().remove(i);
                    }
                }
            });
        });

        return earthDefensiveBlocks;
    }

    public void ChangeShooter(int x, int y){
        aliens.forEach((a) -> {
            if(a.getPositionX() == x && a.getPositionY() == y) a.MakeShooter();
        });
    }

    public BulletsController UpdateArmyFire(BulletsController bulletsController, int chanceToShoot){
        Random rand = new Random();
        aliens.forEach((a) -> {
            int i = 0;
            if(a.getInFront() == true){
                i = rand.nextInt(100);
                if(i <= chanceToShoot){
                    bulletsController.CreateBullet(a.getPositionX(), a.getPositionY() - 1, true);
                }
            }
        });
        return bulletsController;
    }

    public Boolean CheckIfArmyDefeated(){
        if(aliens.size() > 0){
            return false;
        }
        else return true;
    }

    public Boolean CheckIfArmyReachedEarth(){
        Boolean reachedEnd = false;
        for(int a = 0; a < aliens.size(); a++){
            if(aliens.get(a).getPositionY() == 18){
                reachedEnd = true;
                break;
            }
        }
        return reachedEnd;
    }

}