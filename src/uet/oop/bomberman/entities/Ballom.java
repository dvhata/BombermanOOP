// enemy trái phải
package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import uet.oop.bomberman.Control.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.nio.file.Paths;
import java.util.Random;

public class Ballom extends Enemy {

    private int direction;
    public int animationMoveCount = 0; //Thay doi hinh anh
    public int deadAnimation = 99; // 16mls = 1px animation
    public boolean ballomDead = false;

    public Ballom(int x, int y, Image img) {
        super(x, y, img);
        chooseDirection();
    }

    public void setAllEntityImage(String type) throws NullPointerException {
        int frame = (9 - BombermanGame.speed > 0) ? (9 - BombermanGame.speed) : 1;
        if (animationMoveCount == 3 * frame) animationMoveCount = 0;
        int k = animationMoveCount++ / frame;
            switch (k % 3) {
                case 0: {
                    try {
                        this.img = Sprite.getSprite(type + "1").getFxImage();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error loading ballom image 1");
                    }
                    break;
                }
                case 1: {
                    try {
                        this.img = Sprite.getSprite(type + "2").getFxImage();

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error loading ballom image 2");
                    }
                    break;
                }
                case 2: {
                    try {
                        this.img = Sprite.getSprite(type + "3").getFxImage();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error loading ballom image 3");
                    }
                    break;
                }
            }
    }

    public void moveRandom (int n) {
        switch (n) {
            // go Left
            case 1:
                if (x > 1) {
                    if (Bomber.checkStillCollision(this, "left")) {
                        this.x = this.x - 0.02 - 0.025 * (BombermanGame.speed - 1);
                        if (x < 1) x = 1;
                    } else {
                        chooseDirection();
                    }
                }
                try {
                    setAllEntityImage("balloom_left");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("Error ballom_left");
                }
                break;
            //go right
            case 2:
                if (x < BombermanGame.WIDTH - 2 ) {
                    if (Bomber.checkStillCollision(this, "right")) {
                        this.x = this.x + 0.02 + 0.025 * (BombermanGame.speed - 1);
                        if (x > BombermanGame.WIDTH - 2) x = BombermanGame.WIDTH - 2;
                    } else {
                        chooseDirection();
                    }
                }
                try {
                    setAllEntityImage("balloom_right");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("Error ballom_right");
                }
        }
    }

    public void chooseDirection () {
        Random generator = new Random();
        //System.out.println("Number random: " + new Random().nextInt(2));
        direction = 1 + Math.abs(generator.nextInt(2));
    }

    @Override
    public void update() {
        //chooseDirection();

        if (ballomDead == true) {
            setDeadAnimation();
            //BombermanGame.getDamagedObjects().remove(this);
        } else moveRandom(direction);
        if(!ballomDead) {
            if (checkBomberDamage(this)) {
                ballomDead = true;
            }
        }
    }

    public void setDeadAnimation() {


        img = Sprite.balloom_dead.getFxImage();

    }

    public boolean checkBomberDamage(Entity object) {
        for (int i = 0; i < BombermanGame.getDamagedObjects().size(); i++) {
            if ((int) object.x == (int) BombermanGame.getDamagedObjects().get(i).x
                    && (int) object.y == (int) BombermanGame.getDamagedObjects().get(i).y) {
                if (object instanceof Ballom && BombermanGame.getDamagedObjects().get(i) instanceof Flame) {
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
