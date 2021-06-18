package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import uet.oop.bomberman.Control.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.nio.file.Paths;

public class Bomb extends AnimateEntity {

    protected double _timeToExplode = 120;
    protected boolean _removed = false;

    public void remove() {
        _removed = true;
    }

    public boolean isRemoved() {
        return _removed;
    }

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public static boolean checkBomb(Entity object) {
        for(int i = 0; i < BombermanGame.getStillObjects().size(); i++) {
            if((int) Math.round(object.x) == (int) Math.round(BombermanGame.getStillObjects().get(i).x)
                    && (int) Math.round(object.y) == (int) Math.round(BombermanGame.getStillObjects().get(i).y)) {
                if(BombermanGame.getStillObjects().get(i) instanceof Bomb) {
                    // duplicate bomb
                    /**
                     if(object instanceof Flame) {
                     BombermanGame.getStillObjects().get(i).destroy();
                     }
                     */
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void update() {

        if(_timeToExplode > 0) {
            _timeToExplode--;
            img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 30).getFxImage();
        }
        else {
            img = Sprite.movingSprite(Sprite.bomb_exploded,Sprite.bomb_exploded1,Sprite.bomb_exploded2,_animate,30).getFxImage();
            //remove();
            BombermanGame.getStillObjects().remove(this);
            BombermanGame.addBombRate(1);
            //BombermanGame.getStillObjects().add(new Grass((int) Math.round(x), (int) Math.round(y), Sprite.grass.getFxImage()));
            MediaPlayer mediaPlayer;
            String s = "res/Sound/BOM_EXPLOSION.wav";
            Media h = new Media(Paths.get(s).toUri().toString());
            mediaPlayer = new MediaPlayer(h);
            mediaPlayer.play();
            for(int i =0; i<=4; i++) {
                makeFire(i);
            }
        }

        animate();

    }

    private void makeFire(int direct) {
        int vertical = 0, horizotal = 0;
        boolean stop = false;

        for(int j = 0; j<=1; j++) {
            //Image image = Sprite.grass.getFxImage();
            Flame flame = new Flame((int) x + horizotal*j, (int) y + vertical*j, img);
            flame.setDirection(direct);
            switch (direct) {
                case 0:
                    vertical = -1;
                    if (j == 1) img = Sprite.explosion_vertical_top_last.getFxImage();
                    else img =Sprite.explosion_vertical.getFxImage();
                    break;
                case 1:
                    vertical = 1;
                    if(j == 1) img = Sprite.explosion_vertical_down_last.getFxImage();
                    else img =Sprite.explosion_vertical.getFxImage();
                    break;
                case 2:
                    horizotal = 1;
                    if(j == 0) img = Sprite.explosion_horizontal_right_last.getFxImage();
                    else img = Sprite.explosion_horizontal.getFxImage();
                    break;
                case 3:
                    horizotal = -1;
                    if(j == 0) img = Sprite.explosion_horizontal_left_last.getFxImage();
                    else img = Sprite.explosion_horizontal.getFxImage();
                    break;
                case 4:
                    img = Sprite.bomb_exploded.getFxImage();

            }

            for(int m = 0; m<BombermanGame.getStillObjects().size(); m++) {
                Entity entity = BombermanGame.getStillObjects().get(m);
                if((int) flame.x == (int)entity.getX() && (int) flame.y == (int)entity.getY()) {
                    if(entity instanceof Wall || entity instanceof Brick) {
                        stop = true;
                        break;
                    }
                }
            }
            if(!stop) {
                BombermanGame.getFlames().add(flame);
                BombermanGame.getDamagedObjects().add(flame);
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