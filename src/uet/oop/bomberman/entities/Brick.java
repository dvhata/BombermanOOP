package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Control.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    public Brick(double x, double y, Image img) {
        super(x, y, img);
    }

    protected boolean isDes = false;

    public boolean checkBomberDamage(Entity object) {
        for (int i = 0; i < BombermanGame.getDamagedObjects().size(); i++) {
            if ((int) object.x == (int) BombermanGame.getDamagedObjects().get(i).x
                    && (int) object.y == (int) BombermanGame.getDamagedObjects().get(i).y) {

                if (object instanceof Bomber && BombermanGame.getDamagedObjects().get(i) instanceof Flame) {
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
        if(checkBomberDamage(this)) {
            img = Sprite.movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1,Sprite.brick_exploded2, 10, 30).getFxImage();
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
