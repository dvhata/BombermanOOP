package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Control.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

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
