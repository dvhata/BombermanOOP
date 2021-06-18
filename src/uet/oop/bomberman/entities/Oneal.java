// Enemy xuyên tường, vận tốc thay đổi
package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import uet.oop.bomberman.Control.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.nio.file.Paths;
import java.util.Random;

public class Oneal extends Enemy {

    private int direction;
    public int animationMoveCount = 0; //Thay doi hinh anh
    public int deadAnimation = 99; // 16mls = 1px animation
    private int speed = 1;
    private boolean onealDead = false;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        chooseDirection();
    }


    public void setAllEntityImage(String type) throws NullPointerException {
        int frame = (9 - BombermanGame.speed > 0) ? (9 - BombermanGame.speed) : 1;
        if (animationMoveCount == 3 * frame) animationMoveCount = 0;
        int k = animationMoveCount++ / frame;
        try {
            switch (k % 3) {
                case 0:
                    this.img = Sprite.getSprite(type + "1").getFxImage();
                    return;
                case 1:
                    this.img = Sprite.getSprite(type + "2").getFxImage();
                    return;
                case 2:
                    this.img = Sprite.getSprite(type + "3").getFxImage();
                    return;
            }
        } catch (Exception e) {
            System.out.println("Error loading image oneal");
        }
    }

    public void chooseDirection() {
        Random generator = new Random();
        direction = 1 + Math.abs(generator.nextInt(4));
    }

    public int randomSpeed () {
        // random 1-3
        return 1 + (int) (Math.random() * ((3 - 1) + 1));
    }

    public void moveRandom(int n) {
        switch (n) {
            // go Left
            case 1:
                if (x > 1) {
                    this.x = this.x - 0.05 - 0.025 * (randomSpeed() - 1);
                }
                if(x <= 1) {
                    x = 1;
                    chooseDirection();
                }
                try {
                    setAllEntityImage("oneal_left");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("Error oneal_left");
                }
                break;

            //go right
            case 2:
                if (x < BombermanGame.WIDTH - 2) {
                    this.x = this.x + 0.05 + 0.025 * (randomSpeed() - 1);
                }
                if(x >= BombermanGame.WIDTH - 2) {
                    x = BombermanGame.WIDTH - 2;
                    chooseDirection();
                }
//                if (x == BombermanGame.WIDTH - 2) {
//                    if (Bomber.checkStillCollision(this, "left")) chooseDirection();
//                } else if (x > 1) {
//                    this.x = this.x + 0.05 + 0.025 * (speed - 1);
//                    if (x < 1) x = 1;
//                }
                try {
                    setAllEntityImage("oneal_right");
                } catch (
                        NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("Error oneal_right");
                }
                break;

                // go up
            case 3:
                if (y > 1) {
                    this.y = this.y - 0.05 - 0.025 * (randomSpeed() - 1);
                }
                if(y <= 1) {
                    y = 1;
                    chooseDirection();
                }
//                if (y == 1) {
//                    if (Bomber.checkStillCollision(this, "up")) chooseDirection();
//                } else if (y > 1) {
//                    this.y = this.y - 0.05 - 0.025 * (speed - 1);
//                    if (y < 1) y = 1;
//                }
                try {
                    setAllEntityImage("oneal_left");
                } catch (
                        NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("Error oneal_up");
                }
                break;

                // go down
            case 4:
                // minh dang lam con Oneal thoi :<
                // o con mau xanh xanh
                // dung r
                if (y < BombermanGame.HEIGHT - 2) {
                    this.y = this.y + 0.05 + 0.025 * (randomSpeed() - 1);
                }
                if(y >= BombermanGame.HEIGHT - 2) {
                    y = BombermanGame.HEIGHT - 2;
                    chooseDirection();
                }
//                if (y == BombermanGame.WIDTH - 2) {
//                    if (Bomber.checkStillCollision(this, "down")) chooseDirection();
//                } else if (y > 1) {
//                    this.y = this.y - 0.05 - 0.025 * (speed - 1);
//                    if (y < 1) y = 1;
//                }
                try {
                    setAllEntityImage("oneal_right");
                } catch (
                        NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("Error oneal_down");
                }
                break;
            default:
                break;
        }
    }

    public void setDeadAnimation() {

        img = Sprite.oneal_dead.getFxImage();


    }

    public boolean checkBomberDamage(Entity object) {
        for (int i = 0; i < BombermanGame.getDamagedObjects().size(); i++) {
            if ((int) object.x == (int) BombermanGame.getDamagedObjects().get(i).x
                    && (int) object.y == (int) BombermanGame.getDamagedObjects().get(i).y) {
                if (object instanceof Oneal && BombermanGame.getDamagedObjects().get(i) instanceof Flame) {
                    //System.out.println("Die");
                    return true;
                }
                /**
                 if (object instanceof Bomb) {
                 return true;
                 // ((Bomber) object).destroy();
                 } else if (object instanceof Ballom) {
                 if (BombermanGame.getDamagedObjects().get(i) instanceof Flame || BombermanGame.getDamagedObjects().get(i) instanceof Bomb) {
                 object.destroy();
                 return true;
                 }
                 }
                 */
            }
        }
        return false;
    }

    @Override
    public void update() {

        if (onealDead == true) {
            setDeadAnimation();
            //BombermanGame.getDamagedObjects().remove(this);
        } else moveRandom(direction);
        if(!onealDead) {
            if (checkBomberDamage(this)) {
                onealDead = true;
            }
        }
    }

    @Override
    protected int getX() {
        return 0;
    }

    @Override
    protected int getY() {
        return 0;
    }

    @Override
    protected void restart() {

    }
}
