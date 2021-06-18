package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

// THUC THE
public abstract class Entity {
    // protected: truy cap duoc trong lop con cua lop cha
    // private: chi truy cap duoc o lop do
    protected double x;
    protected double y;
    protected Image img;


    public Entity( double x, double y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        // Xoa pixel vo hinh nma khong can xoa
        /**SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        Image base = iv.snapshot(params, null);

         */

        gc.drawImage(img,x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }
    public abstract void update();

    protected abstract int getX();

    protected abstract int getY();


    protected abstract void restart();


}
