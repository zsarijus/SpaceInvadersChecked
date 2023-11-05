package Controllers;

import GameObjects.Bullet;
import GameObjects.EarthDefensiveBlock;
import GameObjects.PlayerController;

import java.util.ArrayList;

public class BulletsController {
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public void CreateBullet(int xPos, int yPos, Boolean enemyBullet){
        bullets.add(new Bullet(xPos, yPos, enemyBullet));
    }

    public int[][] GetBullets(int[][] battlefield){
        bullets.forEach((b) -> {
            battlefield[b.getPositionY()][b.getPositionX()] = 6;
            if(b.getEnemyBullet() == true) battlefield[b.getPositionY() + 1][b.getPositionX()] = 6;
            else battlefield[b.getPositionY() - 1][b.getPositionX()] = 6;
        });
        return battlefield;
    }

    public void MoveBullets(){
        for(int i = 0; i < bullets.size(); i++){
            if(bullets.get(i).getPositionY() < 17 && bullets.get(i).getEnemyBullet() == true) bullets.get(i).MoveBullet();
            else if (bullets.get(i).getEnemyBullet() == true) bullets.remove(i);
            else if(bullets.get(i).getPositionY() > 3 && bullets.get(i).getEnemyBullet() == false) bullets.get(i).MoveBullet();
            else if (bullets.get(i).getEnemyBullet() == false) bullets.remove(i);
        }
    }

    public ArrayList<EarthDefensiveBlock> CheckBulletsDefensiveBlockCollision(ArrayList<EarthDefensiveBlock> earthDefensiveBlocks){
        for(int d = 0; d < bullets.size(); d++){
            CheckDefensiveBlocksInBulletPosition(earthDefensiveBlocks, d);

        }
        return earthDefensiveBlocks;
    }

    private void CheckDefensiveBlocksInBulletPosition(ArrayList<EarthDefensiveBlock> earthDefensiveBlocks, int d) {
        Boolean hit = false;
        for(int c = 0; c < earthDefensiveBlocks.size(); c++){
            hit = CheckIfAnyBricksInBlockWereHit(earthDefensiveBlocks, d, hit, c);
            if(hit == true) break;
        }
    }

    private Boolean CheckIfAnyBricksInBlockWereHit(ArrayList<EarthDefensiveBlock> earthDefensiveBlocks, int d, Boolean hit, int c) {
        for(int i = 0; i < earthDefensiveBlocks.get(c).getDefensiveBlockBricks().size(); i++){
            if(earthDefensiveBlocks.get(c).getDefensiveBlockBricks().get(i).getPositionX() == bullets.get(d).getPositionX() && earthDefensiveBlocks.get(c).getDefensiveBlockBricks().get(i).getPositionY() == bullets.get(d).getPositionY()){
                hit = ComputeBrickBulletCollision(earthDefensiveBlocks, c, i, d);
                break;
            }
        }
        if(hit == false)
            for(int i = 0; i < earthDefensiveBlocks.get(c).getDefensiveBlockBricks().size(); i++){
                if(earthDefensiveBlocks.get(c).getDefensiveBlockBricks().get(i).getPositionX() == bullets.get(d).getPositionX() && earthDefensiveBlocks.get(c).getDefensiveBlockBricks().get(i).getPositionY() == bullets.get(d).getPositionY() - 1 && bullets.get(d).getEnemyBullet() == false){
                    hit = ComputeBrickBulletCollision(earthDefensiveBlocks, c, i, d);
                    break;
                }
                else if(earthDefensiveBlocks.get(c).getDefensiveBlockBricks().get(i).getPositionX() == bullets.get(d).getPositionX() && earthDefensiveBlocks.get(c).getDefensiveBlockBricks().get(i).getPositionY() == bullets.get(d).getPositionY() + 1 && bullets.get(d).getEnemyBullet() == true){
                    hit = ComputeBrickBulletCollision(earthDefensiveBlocks, c, i, d);
                    break;
                }
            }
        return hit;
    }

    private Boolean ComputeBrickBulletCollision(ArrayList<EarthDefensiveBlock> earthDefensiveBlocks, int c, int i, int d) {
        earthDefensiveBlocks.get(c).getDefensiveBlockBricks().remove(i);
        bullets.remove(d);
        return true;
    }


    public AlienController CheckBulletsAliensCollision(AlienController alienController){
        for(int d = 0; d < bullets.size(); d++){
            if(bullets.get(d).getEnemyBullet() == false){
                AlliensInBulletPositionCollisionCheck(alienController, d);
            }
        }
        return alienController;
    }

    private void AlliensInBulletPositionCollisionCheck(AlienController alienController, int d) {
        Boolean hit = false;
        for(int c = 0; c < alienController.GetAliens().size(); c++){
            if(alienController.GetAliens().get(c).getPositionX() + (alienController.GetAliens().get(c).getSize() - 1) >= bullets.get(d).getPositionX() && alienController.GetAliens().get(c).getPositionX() - (alienController.GetAliens().get(c).getSize() - 1) <= bullets.get(d).getPositionX() && alienController.GetAliens().get(c).getPositionY() == bullets.get(d).getPositionY()){
                ComputeBulletAlienCollision(alienController, d, c);
                hit = true;
                break;
            }
            if(hit == true) break;
        }
        if(hit == false)
            for(int c = 0; c < alienController.GetAliens().size(); c++){
                if(alienController.GetAliens().get(c).getPositionX() + (alienController.GetAliens().get(c).getSize() - 1) >= bullets.get(d).getPositionX() && alienController.GetAliens().get(c).getPositionX() - (alienController.GetAliens().get(c).getSize() - 1) <= bullets.get(d).getPositionX() && alienController.GetAliens().get(c).getPositionY() == bullets.get(d).getPositionY() - 1){
                    ComputeBulletAlienCollision(alienController, d, c);
                    break;
                }
            }
    }

    private void ComputeBulletAlienCollision(AlienController alienController, int d, int c) {
        alienController.ChangeShooter(alienController.GetAliens().get(c).getPositionX(), alienController.GetAliens().get(c).getPositionY() - 1);
        alienController.GetAliens().remove(c);
        bullets.remove(d);
    }

    public PlayerController CheckBulletsPlayerCollision(PlayerController playerController){
        for(int d = 0; d < bullets.size(); d++){
            if(playerController.getPositionX() == bullets.get(d).getPositionX() && playerController.getPositionY() == bullets.get(d).getPositionY()){
                playerController.PlayerHit(false);
                bullets.remove(d);
                break;
            }
            else if(playerController.getPositionX() == bullets.get(d).getPositionX() && playerController.getPositionY() == bullets.get(d).getPositionY() + 1 && bullets.get(d).getEnemyBullet() == true){
                playerController.PlayerHit(false);
                bullets.remove(d);
                break;
            }
        }
        return playerController;
    }

}
