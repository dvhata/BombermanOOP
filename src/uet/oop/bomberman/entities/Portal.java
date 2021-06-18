package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Portal extends Entity{
    public Portal(int x, int y, Image img) {
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
