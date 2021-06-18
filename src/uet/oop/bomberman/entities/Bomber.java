package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import uet.oop.bomberman.Control.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.nio.file.Paths;
import java.util.List;

public class Bomber extends Entity {

    private boolean isAlive = true;
    private boolean bomberDie = false;

    private static int currentBombs = 0;
    public int animationMoveCount = 0; //Thay doi hinh anh
    private static int deadAnimation = 99;
    private int _timeBetweenPutBombs = 0;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public boolean isDead() {
        isAlive = false;
        return false;
    }

    // Truyen vao 1 object, xem no co phai la still hay khong
    public static boolean checkStillObject(Entity object) {
        List<Entity> entities = BombermanGame.getStillObjects();
        for (Entity e:entities) {
            if (Math.round(object.x) == Math.round(e.x)  && Math.round(object.y) == Math.round(e.y)) {
                if (object instanceof Wall) return true;
                if (object instanceof Brick) return true;
            }

        }
        return false;
    }

    public void setDeadAnimation() {
        if (deadAnimation > 0) {
            MediaPlayer mediaPlayer;
            String s = "res/Sound/DIE.wav";
            Media h = new Media(Paths.get(s).toUri().toString());
            mediaPlayer = new MediaPlayer(h);
            mediaPlayer.play();

            int k = deadAnimation-- / 33;
            switch (k % 3) {
                case 2:
                    this.img = Sprite.player_dead1.getFxImage();
                    return;
                case 1:
                    this.img = Sprite.player_dead2.getFxImage();
                    return;
                case 0:
                    this.img = Sprite.player_dead3.getFxImage();
            }

        }

    }



    // check flame, enemies xem co chet khong?
    // object: bomber
    // damagedObjects: enemy

    // tạo 1 class Enemy để xét nha
    // oke
    public boolean checkBomberDamage(Entity object) {
        for (int i = 0; i < BombermanGame.getDamagedObjects().size(); i++) {
            if ((int) object.x == (int) BombermanGame.getDamagedObjects().get(i).x
                    && (int) object.y == (int) BombermanGame.getDamagedObjects().get(i).y) {
                if (object instanceof Bomber && BombermanGame.getDamagedObjects().get(i) instanceof Enemy) {
                    //System.out.println("Die");
                    return true;
                }
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

    /**
     public void destroy() {
     BombermanGame.life = -1;
     if (BombermanGame.life == 0) {
     bomberDie = true;
     return;
     }
     BombermanGame.getBomber().restart();
     }
     */

    public void setAllEntityImage(String type) throws NullPointerException {
        int frame = (9 - BombermanGame.speed > 0) ? (9 - BombermanGame.speed) : 1;
        if (animationMoveCount == 3 * frame) animationMoveCount = 0;
        int k = animationMoveCount++ / frame;
        switch (k % 3) {
            case 0:
                assert Sprite.getSprite(type) != null;
                try {
                    this.img = Sprite.getSprite(type).getFxImage();
                    return;
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("ERROR loading image");
                }

            case 1:
                assert Sprite.getSprite(type) != null;
                try {
                    this.img = Sprite.getSprite(type + "_1").getFxImage();
                    return;
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("ERROR loading image");
                }
            case 2:
                assert Sprite.getSprite(type) != null;
                try {
                    this.img = Sprite.getSprite(type + "_2").getFxImage();
                    return;
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("ERROR loading image");
                }
        }
    }


    public void rebornBomber() {
        isAlive = true;
    }


    // Check bomber collide with Still Entities
    public static boolean checkStillCollision(Entity object, String type) {
        double unit = (object instanceof Bomber) ? 0.9 : 1.0;
        switch (type) {
            case "right":
                for (int i = 0; i < BombermanGame.getStillObjects().size(); i++) {
                    Entity still = BombermanGame.getStillObjects().get(i);
                    if (((double) Math.round(Math.abs(-object.y + still.y) * 10) / 10) < (double)Math.round(unit *10) / 10
                            && ((double)Math.round((still.x - object.x) * 10) / 10 == (double)Math.round(unit * 10) / 10)) {
                        if (still instanceof Wall || still instanceof Brick) {
                            if (object instanceof Bomber)
                                System.out.println(still.toString() + "x =  " + still.x + "; y  = " + still.y);
                            //result = false;
                            return false;
                        } /**
                         else if (still instanceof Bomb) {
                         result = false;
                         }
                         */
                    }
                }
                break;
            case "left":
                for (int i = 0; i < BombermanGame.getStillObjects().size(); i++) {
                    Entity still = BombermanGame.getStillObjects().get(i);
                    if (((double) Math.round(Math.abs(object.y - still.y) * 10) / 10) < (double)Math.round(unit *10) / 10
                            && ((double)Math.round((-still.x + object.x) * 10) / 10 == (double)Math.round(unit * 10) / 10)) {
                        if (still instanceof Wall || still instanceof Brick) {
                            if (object instanceof Bomber)
                                System.out.println(still.toString() + "x =  " + still.x + "; y  = " + still.y);
                            return false;
                            /**
                             } else if (still instanceof Bomb) {
                             result = false;
                             */
                        }
                    }
                }
                break;
            case "up":
                for (int i = 0; i < BombermanGame.getStillObjects().size(); i++) {
                    Entity still = BombermanGame.getStillObjects().get(i);
                    if (((double)Math.round(Math.abs(-object.x + still.x) * 10) / 10) < (double) Math.round(unit *10) / 10
                            && (double)Math.round((-still.y + object.y) * 10) / 10 == (double) Math.round(unit *10) / 10) {
                        if (still instanceof Wall || still instanceof Brick) {
                            if (object instanceof Bomber) System.out.println(still.toString() + "x =  " + still.x + "; y  = " + still.y);
                            return false;
                            /**
                             } else if (still instanceof Bomb) {
                             result = false;
                             */
                        }
                    }
                }
                break;
            case "down":
                for (int i = 0; i < BombermanGame.getStillObjects().size(); i++) {
                    Entity still = BombermanGame.getStillObjects().get(i);
                    if (((double)Math.round(Math.abs(-object.x + still.x) * 10) / 10) < (double)Math.round(unit *10) / 10
                            && (double)Math.round((still.y - object.y) * 10) / 10 == (double) Math.round(unit *10) / 10) {
                        if (still instanceof Wall || still instanceof Brick) {
                            if (object instanceof Bomber)
                                System.out.println(still.toString() + "x =  " + still.x + "; y  = " + still.y);
                            return false;
                        }
                        /**
                         } else if (still instanceof Bomb) {
                         result = false;
                         }
                         */
                    }
                }
                break;
            default:
                break;
        }
        // if (object instanceof Bomber) System.out.println(type);
        return true;
    }

    @Override
    public void update() {
        // Check xem bomber chet chua
        // hai ham nay dam nhau a
        // vang
        // the sao k xoa bot 1 cai
        // vi no ca 2 cai moi dung
        // ohh
        // oke dung r
        // nma sao no k mat di :((
        // muon mat thi day
        if (bomberDie == true) {
            setDeadAnimation();
            if (deadAnimation == 0) {
                isAlive = false;
            }
            return;
        }
        if (BombermanGame.keyInput.up) {
            if (y > 1) // (BombermanGame.speed - 1) * 0.05* //do co 1 hàng, 1 cot wall
            {
                if (checkStillCollision(this, "up")) {
                    this.y =  y - 0.07 - 0.025 * (BombermanGame.speed - 1);
                    if (y < 1) y = 1;
                }
            }
            try {
                setAllEntityImage("player_up");
            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("Error up");
            }
            //System.out.println("Bomber: x: " + (double) Math.round(x * 10) / 10 + ", y = " + (double) Math.round(y * 10) / 10);
            //System.out.println("doneUp-----------------------------------------------------------------------");
        }
        if (BombermanGame.keyInput.down) {
            if (y < BombermanGame.HEIGHT - 2 ) { // (BombermanGame.speed - 1) * 0.05
                if (checkStillCollision(this, "down")) {
                    this.y = this.y + 0.07 + 0.025 * (BombermanGame.speed - 1);
                    if (y > BombermanGame.HEIGHT - 2) y = BombermanGame.HEIGHT - 2;
                }
            }
            try {
                setAllEntityImage("player_down");
            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("Error down");
            }
            //System.out.println("Bomber: x: " + (double) Math.round(x * 10) / 10 + ", y = " + (double) Math.round(y * 10) / 10);
            //System.out.println("doneDown----------------------------------------------------------------------");
        }
        if (BombermanGame.keyInput.left) {
            if (x > 1) {
                if (checkStillCollision(this, "left")) {
                    this.x = this.x - 0.07 - 0.025 * (BombermanGame.speed - 1);
                    if (x < 1) x = 1;
                }
            }
            try {
                setAllEntityImage("player_left");
            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("Error left");
            }
            //System.out.println("Bomber: x: " + (double) Math.round(x * 10) / 10 + ", y = " + (double) Math.round(y * 10) / 10);
            //System.out.println("doneLeft-----------------------------------------------------------------");
        }

        if (BombermanGame.keyInput.right) {
            if (x < BombermanGame.WIDTH - 2 ) {
                if (checkStillCollision(this, "right")) {
                    this.x = this.x + 0.07 + 0.025 * (BombermanGame.speed - 1);
                    if (x > BombermanGame.WIDTH - 2) x = BombermanGame.WIDTH - 2;
                }
            }
            //
            try {
                setAllEntityImage("player_right");
            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("Error right");
            }
            //System.out.println("Bomber: x: " + (double) Math.round(x * 10) / 10 + ", y = " + (double) Math.round(y * 10) / 10);
            //System.out.println("doneRight-----------------------------------------------------------------");
        }
        if(BombermanGame.keyInput.space) {
            if(_timeBetweenPutBombs <= 0 && BombermanGame.getBombRate() > 0) {

                boolean ex = false;
                for(int i = 0; i<BombermanGame.getStillObjects().size(); i++) {
                    if(BombermanGame.getStillObjects().get(i) instanceof Bomb
                            && (int) Math.round(BombermanGame.getStillObjects().get(i).getX()) == (int) Math.round(x)
                            && (int) Math.round(BombermanGame.getStillObjects().get(i).getY()) == (int) Math.round(y)) {
                        ex = true;
                    }
                    if(ex) break;
                }
                if(!ex) {
                    MediaPlayer mediaPlayer;
                    String s = "res/Sound/BOM_SET.wav";
                    Media h = new Media(Paths.get(s).toUri().toString());
                    mediaPlayer = new MediaPlayer(h);
                    mediaPlayer.play();
                    BombermanGame.getStillObjects().add(new Bomb((int) Math.round(x), (int) Math.round(y), Sprite.bomb.getFxImage()));
                }

                BombermanGame.addBombRate(-1);

                _timeBetweenPutBombs = 0;

            }
        }

        if(!bomberDie) {
            if (checkBomberDamage(this)) {
                bomberDie = true;
            }
        }
        /**
         if (BombermanGame.keyInput.space) {
         if (currentBombs == BombermanGame.bombs) return; // max of bombs
         if (!(Math.abs(x - (int) x - 0.5) <= 0.075 || Math.abs(y - (int) y - 0.5) <= 0.075)) {
         int bomb_x = (int) Math.round(x);
         int bomb_y = (int) Math.round(y);
         Bomb bomb = new Bomb(bomb_x, bomb_y, Sprite.bomb.getFxImage());
         if (!Bomb.checkBomb(bomb)) {
         BombermanGame.getStillObjects().add(bomb);
         currentBombs++;
         }
         }


         }
         */
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

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }


}


/**
 * private int speed = 2;
 * private boolean available = false;
 * private int alive = 0;
 * private int currentPointX = 32;
 * private int currentPointY = 32;
 * public int frameCount = 0;
 * public int count = 0;
 * private int maxBomb = 3;
 * private static int bombCount;
 * <p>
 * public Bomber(int x, int y, Image img) {
 * super( x, y, img);
 * }
 *
 * @Override public boolean canMove(int x, int y) {
 * for (Entity e : BombermanGame.getStillObjects()) {
 * if((e.getX() == x*32 && e.getY() == y*32) && (!(e instanceof Grass)) && (!(e instanceof Portal)) ) {
 * return false;
 * }
 * }
 * return true;
 * }
 * @Override public void update() {
 * if (BombermanGame.inputList.contains("RIGHT")) {
 * frameCount += speed;
 * if (canMove(x + 1, y) || currentPointX != x * 32) {
 * while (currentPointY != y * 32) {
 * if (currentPointY < y * 32) currentPointY += speed;
 * else currentPointY -= speed;
 * }
 * currentPointX += speed;
 * }
 * img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, frameCount, 30).getFxImage();
 * } else if (BombermanGame.inputList.contains("LEFT")) {
 * frameCount += speed;
 * if (canMove(x - 1, y) || currentPointX != x * 32) {
 * while (currentPointY != y * 32)
 * {
 * if (currentPointY < y * 32) currentPointY += speed;
 * else currentPointY -= speed;
 * }
 * currentPointX -= speed;
 * }
 * img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, frameCount, 30).getFxImage();
 * } else if (BombermanGame.inputList.contains("DOWN")) {
 * frameCount += speed;
 * if (canMove(x, y + 1) || currentPointY != y * 32) {
 * while (currentPointX != x * 32)
 * {
 * if (currentPointX < x * 32) currentPointX += speed;
 * else currentPointX -= speed;
 * }
 * currentPointY += speed;
 * }
 * img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, frameCount, 30).getFxImage();
 * } else if (BombermanGame.inputList.contains("UP")) {
 * frameCount += speed;
 * if (canMove(x, y - 1) || currentPointY != y * 32) {
 * while (currentPointX != x * 32)
 * {
 * if (currentPointX < x * 32) currentPointX += speed;
 * else currentPointX -= speed;
 * }
 * currentPointY -= speed;
 * }
 * img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, frameCount, 30).getFxImage();
 * } else if (BombermanGame.inputList.contains("SPACE")) {
 * //BombermanGame.getEntities().add(new Bomb(x, y, Sprite.bomb_2.getFxImage()));
 * }
 * <p>
 * if (currentPointX % 32 > 16) x = currentPointX / 32 + 1;
 * else x = currentPointX / 32;
 * if (currentPointY % 32 > 16) y = currentPointY / 32 + 1;
 * else y = currentPointY / 32;
 * }
 * @Override public void render(GraphicsContext gc) {
 * gc.drawImage(img, currentPointX, currentPointY);
 * }
 * <p>
 * public boolean isCollide() {
 * for(Entity e : BombermanGame.getEntities()) {
 * if(e.getX() == this.getX()*32 && e.getY() == this.getY()*32 && (e instanceof Ballom || e instanceof Oneal)) {
 * alive = 0;
 * return false;
 * }
 * }
 * return  true;
 * }
 * <p>
 * public static void reduceBombCount() {
 * bombCount--;
 * }
 */

