package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

/**
 * Entity có khả năng di chuyển.
 */

public abstract class AnimateEntity extends Entity {
    protected int _animate = 0;
    protected final int MAXanimate = 7500;

    public AnimateEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void animate() {
        if(_animate < MAXanimate) {
            _animate++;
        } else {
            _animate = 0;
        }
    }

    //public abstract boolean collide(Entity e);
}
