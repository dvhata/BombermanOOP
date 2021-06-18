// Static: tat ca cac lop deu su dung duoc
package uet.oop.bomberman.Control;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Ballom;
import uet.oop.bomberman.entities.Ghost;
import uet.oop.bomberman.entities.Oneal;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static int WIDTH = 31;
    public static int HEIGHT = 13;
    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;

    private GraphicsContext gc;
    private Canvas canvas;

    protected static int bombRate = BOMBRATE;

    public static int getBombRadius() {
        return bombRadius;
    }

    protected static int bombRadius = BOMBRADIUS;

    public static int getBombRate() {
        return bombRate;
    }

    public static void addBombRate(int i) {
        bombRate += i;
    }

    private static final List<Entity> entities = new ArrayList<>();

    private static final List<Entity> stillObjects = new ArrayList<>();

    private static final List<Entity> damagedObjects = new ArrayList<>();

    private static final List<Entity> effectObject = new ArrayList<>();

    private static final List<Flame> flames = new ArrayList<>();

    public static List<Flame> getFlames() {
        return flames;
    }

    public static void main(String[] args) {
        MediaPlayer mediaPlayer;
        String s = "res/Sound/LEVEL1.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
        Application.launch(BombermanGame.class);
    }

    public static List<Entity> getStillObjects() {
        return stillObjects; }

    public static List<Entity> getEntities() {
        return entities;
    }

    public static List<Entity> getDamagedObjects () {
        return damagedObjects;
    }

    public static List<Entity> getEffectObjects () {
        return effectObject;
    }

    public static KeyInput keyInput = new KeyInput();


    // Bomber status
    public static int life = 3;
    public static final int MAX_LIFE = 3;
    public static int flame = 1;
    public static final int MAX_FLAME = 5;
    public static int speed = 1;
    public static final int MAX_SPEED = 4;
    public static int bombs = 1;
    public static final int MAX_BOMBS = 10;

    @Override
    public void start(Stage stage) {

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);


        // Them scene vao stage
        stage.setScene(scene);
        stage.setTitle("Bomberman Game");
        stage.show();


        /**
        try {
            File file = new File ("\\bomberman-starter-starter-2\\src\\Sound\\LEVEL1.mp3");
            Sound sound = new Sound(file.toURI().toString());
            sound.mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading sound file");
        }

         */
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                scene.setOnKeyPressed(keyEvent -> {
                    switch (keyEvent.getCode()) {
                        case UP:
                            keyInput.up = true;
                            break;
                        case DOWN:
                            keyInput.down = true;
                            break;
                        case LEFT:
                            keyInput.left = true;
                            break;
                        case RIGHT:
                            keyInput.right = true;
                            break;
                        case SPACE:
                            keyInput.space = true;
                            break;
                        default:
                            break;
                    }
                } );
                scene.setOnKeyReleased(keyEvent -> {
                    switch (keyEvent.getCode()) {
                        case UP:
                            keyInput.up = false;
                            break;
                        case DOWN:
                            keyInput.down = false;
                            break;
                        case LEFT:
                            keyInput.left = false;
                            break;
                        case RIGHT:
                            keyInput.right = false;
                            break;
                        case SPACE:
                            keyInput.space = false;
                            break;
                        default:
                            break;
                    }
                });
                update();

            }
        };
        timer.start();

        try {
             createMap();
         } catch (Exception e) {
            System.out.println("ERROR create map");
        }

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }



    public void createMap() throws FileNotFoundException{

        Entity object = null;
        try {
            File myFile = new File("D:\\Code\\OOP\\bomberman-starter-starter-2\\res\\levels\\Level1.txt");
            Scanner sc = new Scanner(myFile);
            int level = sc.nextInt();
            HEIGHT = sc.nextInt();
            WIDTH = sc.nextInt();
            sc.nextLine();
            for (int line = 0; line < HEIGHT; line++) {
                String eachLine = sc.nextLine();
                for (int i = 0; i < WIDTH; i++) {
                    char temp = eachLine.charAt(i);
                    switch (temp)
                    {
                        case '#':
                            object = new Wall(i,line,Sprite.wall.getFxImage());
                            break;
                        case '*':
                            object = new Brick(i,line,Sprite.brick.getFxImage());
                            stillObjects.add(new Grass(i,line,Sprite.grass.getFxImage()));
                            break;
                        case 'x':
                            object = new Portal(i,line,Sprite.portal.getFxImage());
                            stillObjects.add(new Grass(i,line,Sprite.grass.getFxImage()));
                            break;
                        case '1':
                            object = new Ballom(i,line,Sprite.balloom_left1.getFxImage());
                            damagedObjects.add(object);
                            stillObjects.add(new Grass(i,line,Sprite.grass.getFxImage()));
                            break;
                        case '2':
                            object = new Oneal(i,line,Sprite.oneal_left1.getFxImage());
                            damagedObjects.add(object);
                            stillObjects.add(new Grass(i,line,Sprite.grass.getFxImage()));
                            break;
                        case '3':
                            object = new Ghost(i,line,Sprite.ghost_left1.getFxImage());
                            damagedObjects.add(object);
                            stillObjects.add(new Grass(i,line,Sprite.grass.getFxImage()));
                            break;
                        case 'b':
                            object = new Bomb(i,line,Sprite.bomb.getFxImage());
                            stillObjects.add(new Grass(i,line,Sprite.grass.getFxImage()));
                            break;

//                        case 'f':
//                            object = new Flame(i,line,Sprite.powerup_flames.getFxImage());
//                            stillObjects.add(new Grass(i,line,Sprite.grass.getFxImage()));
//                            break;
                        case 's':
                            object = new Speed(i,line,Sprite.powerup_speed.getFxImage());
                            stillObjects.add(new Grass(i,line,Sprite.grass.getFxImage()));
                            break;
                        default:
                            object = new Grass(i,line,Sprite.grass.getFxImage());
                    }
                    stillObjects.add(object);
                }

            }
        } catch (Exception e) {
            System.out.println("ERROR FILE READING");
            e.printStackTrace();
        }
    }

    public void update() {

        for(Flame flame : flames) {
            flame.update();
        }

        for (Entity entity : entities) {
            entity.update();
        }
        for (Entity entity : effectObject) {
         //   effectObject.update();
        }
        for (Entity damagedObject : damagedObjects) {
            damagedObject.update();
        }
        for (Entity stillObject : stillObjects) {
            stillObject.update();
        }
    }


    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        damagedObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        flames.forEach(g -> g.render(gc));
        effectObject.forEach(g -> g.render(gc));

    }

    public static Bomber getBomber() {
        if (BombermanGame.getEntities().get(BombermanGame.getEntities().size() - 1)  instanceof Bomber) {
            return (Bomber) BombermanGame.entities.get(BombermanGame.getEntities().size() - 1);
        } else {
            return null;
        }
    }

}
