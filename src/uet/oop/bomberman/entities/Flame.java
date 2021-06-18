package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Control.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends AnimateEntity {
    public Flame(int x, int y, Image img) {
        super(x, y, img);
    }

    private int timeleft = 60;
    private int Direction;
    private boolean isFinissh;

    @Override
    public void update() {

        switch(Direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, timeleft, 30).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, timeleft, 30).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, timeleft, 30).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, timeleft, 30).getFxImage();
                break;
            case 4:
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, timeleft, 30).getFxImage();
                break;
        }
        timeleft--;
        //System.out.println("Flame ton tai: " + timeleft);

        if (timeleft == 0) {
            BombermanGame.getFlames().remove(this);
            BombermanGame.getDamagedObjects().remove(this);
        }
    }

    public void setDirection(int a) {
        Direction = a;
    }
    public void setEnded(boolean a) {
        isFinissh = a;
    }

//    @Override
//    public void update() {
//        img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1,Sprite.explosion_horizontal2, _animate, 30).getFxImage();
//        //BombermanGame.getStillObjects().remove(this);
//        animate();
//    }

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
