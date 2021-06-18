package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Enemy extends Entity {
    public Enemy(double x, double y, Image img) {
        super(x, y, img);
    }

//    protected boolean isdead = false;

    @Override
    public void update() {

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
