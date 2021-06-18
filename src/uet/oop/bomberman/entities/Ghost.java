// enemy trái phải trên dưới
package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import uet.oop.bomberman.Control.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.nio.file.Paths;
import java.util.Random;

public class Ghost extends Enemy {

    private int direction;
    public int animationMoveCount = 0; //Thay doi hinh anh
    public int deadAnimation = 99; // 16mls = 1px animation
    private int speed = 1;

    public boolean isGhostDie() {
        return ghostDie;
    }

    private boolean ghostDie = false;

    public Ghost(double x, double y, Image img) {
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
            System.out.println("Error loading image ghost");
        }
    }


    public void chooseDirection() {
        Random generator = new Random();
        direction = 1 + Math.abs(generator.nextInt(4));
    }

    public void randomSpeed () {

    }


    public void moveRandom(int n) {
        switch (n) {
            // go Left
            case 1:
                if (x > 1) {
                    if (Bomber.checkStillCollision(this, "left")) {
                        this.x = this.x - 0.025 - 0.025 * (speed - 1);
                        if (x < 1) x = 1;
                    } else {
                        chooseDirection();
                    }
                } else {
                    x = 1;
                    chooseDirection();
                }
                try {
                    setAllEntityImage("ghost_left");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("Error ghost_left");
                }
                break;
            //go right
            case 2:
                if (x < BombermanGame.WIDTH - 2) {
                    if (Bomber.checkStillCollision(this, "right")) {
                        this.x = this.x + 0.025 + 0.025 * (speed - 1);
                        if (x > BombermanGame.WIDTH - 2) x = BombermanGame.WIDTH - 2;
                    } else {
                        chooseDirection();
                    }
                } else {
                    x = BombermanGame.WIDTH - 2;
                    chooseDirection();
                }
                try {
                    setAllEntityImage("ghost_right");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("Error ghost_right");
                }
                break;
            // go_up
            case 3:
                if (y > 1) // (BombermanGame.speed - 1) * 0.05* //do co 1 hàng, 1 cot wall
                {
                    if (Bomber.checkStillCollision(this, "up")) {
                        this.y = y - 0.025 - 0.025 * (speed - 1);
                        if (y < 1) y = 1;
                    } else {
                        chooseDirection();
                    }
                } else {
                    y = 1;
                    chooseDirection();
                }
                try {
                    setAllEntityImage("ghost_left");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("Error ghost_up");
                }
                break;
            case 4:
                if (y < BombermanGame.HEIGHT - 2) { // (BombermanGame.speed - 1) * 0.05
                    if (Bomber.checkStillCollision(this, "down")) {
                        this.y = this.y + 0.025 + 0.025 * (speed - 1);
                        if (y > BombermanGame.HEIGHT - 2) y = BombermanGame.HEIGHT - 2;
                    } else {
                        chooseDirection();
                    }
                } else {
                    y = BombermanGame.HEIGHT - 2;
                    chooseDirection();
                }
                try {
                    setAllEntityImage("ghost_right");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("Error ghost_down");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void update() {
        //chooseDirection();

        if (ghostDie == true) {
            setDeadAnimation();
            //BombermanGame.getDamagedObjects().remove(this);
        }
        else moveRandom(direction);
        if(!ghostDie) {
            if (checkBomberDamage(this)) {
                ghostDie = true;
            }
        }

    }

    public void setDeadAnimation() {

            img = Sprite.ghost_dead.getFxImage();
            //BombermanGame.getDamagedObjects().remove(this);

    }

    public boolean checkBomberDamage(Entity object) {
        for (int i = 0; i < BombermanGame.getDamagedObjects().size(); i++) {
            if ((int) object.x == (int) BombermanGame.getDamagedObjects().get(i).x
                    && (int) object.y == (int) BombermanGame.getDamagedObjects().get(i).y) {
                if (object instanceof Ghost && BombermanGame.getDamagedObjects().get(i) instanceof Flame) {
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
