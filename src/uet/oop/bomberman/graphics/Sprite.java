package uet.oop.bomberman.graphics;

import javafx.scene.image.*;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {

    public static final int DEFAULT_SIZE = 16;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    public final int SIZE;
    private int _x, _y;
    public int[] _pixels;
    protected int _realWidth;
    protected int _realHeight;
    private SpriteSheet _sheet;

    /*
    |--------------------------------------------------------------------------
    | Board sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite grass = new Sprite(DEFAULT_SIZE, 6, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite brick = new Sprite(DEFAULT_SIZE, 7, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite wall = new Sprite(DEFAULT_SIZE, 5, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite portal = new Sprite(DEFAULT_SIZE, 4, 0, SpriteSheet.tiles, 14, 14);

    /*
    |--------------------------------------------------------------------------
    | Bomber Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite player_up = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles, 12, 16);
    public static Sprite player_down = new Sprite(DEFAULT_SIZE, 2, 0, SpriteSheet.tiles, 12, 15);
    public static Sprite player_left = new Sprite(DEFAULT_SIZE, 3, 0, SpriteSheet.tiles, 10, 15);
    public static Sprite player_right = new Sprite(DEFAULT_SIZE, 1, 0, SpriteSheet.tiles, 10, 16);

    public static Sprite player_up_1 = new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles, 12, 16);
    public static Sprite player_up_2 = new Sprite(DEFAULT_SIZE, 0, 2, SpriteSheet.tiles, 12, 15);

    public static Sprite player_down_1 = new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.tiles, 12, 15);
    public static Sprite player_down_2 = new Sprite(DEFAULT_SIZE, 2, 2, SpriteSheet.tiles, 12, 16);

    public static Sprite player_left_1 = new Sprite(DEFAULT_SIZE, 3, 1, SpriteSheet.tiles, 11, 16);
    public static Sprite player_left_2 = new Sprite(DEFAULT_SIZE, 3, 2, SpriteSheet.tiles, 12, 16);

    public static Sprite player_right_1 = new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.tiles, 11, 16);
    public static Sprite player_right_2 = new Sprite(DEFAULT_SIZE, 1, 2, SpriteSheet.tiles, 12, 16);

    public static Sprite player_dead1 = new Sprite(DEFAULT_SIZE, 4, 2, SpriteSheet.tiles, 14, 16);
    public static Sprite player_dead2 = new Sprite(DEFAULT_SIZE, 5, 2, SpriteSheet.tiles, 13, 15);
    public static Sprite player_dead3 = new Sprite(DEFAULT_SIZE, 6, 2, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Character
    |--------------------------------------------------------------------------
     */
    //BALLOM
    public static Sprite balloom_left1 = new Sprite(DEFAULT_SIZE, 9, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_left2 = new Sprite(DEFAULT_SIZE, 9, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_left3 = new Sprite(DEFAULT_SIZE, 9, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite balloom_right1 = new Sprite(DEFAULT_SIZE, 10, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_right2 = new Sprite(DEFAULT_SIZE, 10, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_right3 = new Sprite(DEFAULT_SIZE, 10, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite balloom_dead = new Sprite(DEFAULT_SIZE, 9, 3, SpriteSheet.tiles, 16, 16);

    //ONEAL
    public static Sprite oneal_left1 = new Sprite(DEFAULT_SIZE, 11, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_left2 = new Sprite(DEFAULT_SIZE, 11, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_left3 = new Sprite(DEFAULT_SIZE, 11, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite oneal_right1 = new Sprite(DEFAULT_SIZE, 12, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_right2 = new Sprite(DEFAULT_SIZE, 12, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_right3 = new Sprite(DEFAULT_SIZE, 12, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite oneal_dead = new Sprite(DEFAULT_SIZE, 11, 3, SpriteSheet.tiles, 16, 16);

    //Doll
    public static Sprite doll_left1 = new Sprite(DEFAULT_SIZE, 13, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_left2 = new Sprite(DEFAULT_SIZE, 13, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_left3 = new Sprite(DEFAULT_SIZE, 13, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite doll_right1 = new Sprite(DEFAULT_SIZE, 14, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_right2 = new Sprite(DEFAULT_SIZE, 14, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_right3 = new Sprite(DEFAULT_SIZE, 14, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite doll_dead = new Sprite(DEFAULT_SIZE, 13, 3, SpriteSheet.tiles, 16, 16);

    //Ghost
    public static Sprite ghost_left1 = new Sprite(DEFAULT_SIZE, 6, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite ghost_left2 = new Sprite(DEFAULT_SIZE, 6, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite ghost_left3 = new Sprite(DEFAULT_SIZE, 6, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite ghost_right1 = new Sprite(DEFAULT_SIZE, 7, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite ghost_right2 = new Sprite(DEFAULT_SIZE, 7, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite ghost_right3 = new Sprite(DEFAULT_SIZE, 7, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite ghost_dead = new Sprite(DEFAULT_SIZE, 6, 8, SpriteSheet.tiles, 16, 16);

    // Minivo
    public static Sprite minvo_left1 = new Sprite(DEFAULT_SIZE, 13, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_left2 = new Sprite(DEFAULT_SIZE, 13, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_left3 = new Sprite(DEFAULT_SIZE, 13, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite minvo_right1 = new Sprite(DEFAULT_SIZE, 14, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_right2 = new Sprite(DEFAULT_SIZE, 14, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_right3 = new Sprite(DEFAULT_SIZE, 14, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite minvo_dead = new Sprite(DEFAULT_SIZE, 13, 3, SpriteSheet.tiles, 16, 16);

    //Kondoria
    public static Sprite kondoria_left1 = new Sprite(DEFAULT_SIZE, 10, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_left2 = new Sprite(DEFAULT_SIZE, 10, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_left3 = new Sprite(DEFAULT_SIZE, 10, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite kondoria_right1 = new Sprite(DEFAULT_SIZE, 11, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_right2 = new Sprite(DEFAULT_SIZE, 11, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_right3 = new Sprite(DEFAULT_SIZE, 11, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite kondoria_dead = new Sprite(DEFAULT_SIZE, 10, 8, SpriteSheet.tiles, 16, 16);

    //ALL
    public static Sprite mob_dead1 = new Sprite(DEFAULT_SIZE, 15, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead2 = new Sprite(DEFAULT_SIZE, 15, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead3 = new Sprite(DEFAULT_SIZE, 15, 2, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Bomb Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb = new Sprite(DEFAULT_SIZE, 0, 3, SpriteSheet.tiles, 15, 15);
    public static Sprite bomb_1 = new Sprite(DEFAULT_SIZE, 1, 3, SpriteSheet.tiles, 13, 15);
    public static Sprite bomb_2 = new Sprite(DEFAULT_SIZE, 2, 3, SpriteSheet.tiles, 12, 14);

    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb_exploded = new Sprite(DEFAULT_SIZE, 0, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_exploded1 = new Sprite(DEFAULT_SIZE, 0, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_exploded2 = new Sprite(DEFAULT_SIZE, 0, 6, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical = new Sprite(DEFAULT_SIZE, 1, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical1 = new Sprite(DEFAULT_SIZE, 2, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical2 = new Sprite(DEFAULT_SIZE, 3, 5, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal = new Sprite(DEFAULT_SIZE, 1, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal1 = new Sprite(DEFAULT_SIZE, 1, 8, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal2 = new Sprite(DEFAULT_SIZE, 1, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal_left_last = new Sprite(DEFAULT_SIZE, 0, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_left_last1 = new Sprite(DEFAULT_SIZE, 0, 8, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_left_last2 = new Sprite(DEFAULT_SIZE, 0, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal_right_last = new Sprite(DEFAULT_SIZE, 2, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_right_last1 = new Sprite(DEFAULT_SIZE, 2, 8, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_right_last2 = new Sprite(DEFAULT_SIZE, 2, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical_top_last = new Sprite(DEFAULT_SIZE, 1, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_top_last1 = new Sprite(DEFAULT_SIZE, 2, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_top_last2 = new Sprite(DEFAULT_SIZE, 3, 4, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical_down_last = new Sprite(DEFAULT_SIZE, 1, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_down_last1 = new Sprite(DEFAULT_SIZE, 2, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_down_last2 = new Sprite(DEFAULT_SIZE, 3, 6, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Brick FlameSegment
    |--------------------------------------------------------------------------
     */
    public static Sprite brick_exploded = new Sprite(DEFAULT_SIZE, 7, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_exploded1 = new Sprite(DEFAULT_SIZE, 7, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_exploded2 = new Sprite(DEFAULT_SIZE, 7, 3, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    public static Sprite powerup_bombs = new Sprite(DEFAULT_SIZE, 0, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_flames = new Sprite(DEFAULT_SIZE, 1, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_speed = new Sprite(DEFAULT_SIZE, 2, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_wallpass = new Sprite(DEFAULT_SIZE, 3, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_detonator = new Sprite(DEFAULT_SIZE, 4, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_bombpass = new Sprite(DEFAULT_SIZE, 5, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_flamepass = new Sprite(DEFAULT_SIZE, 6, 10, SpriteSheet.tiles, 16, 16);

    public static Sprite getSprite(String s) {
        Sprite image;
        // minh sua chut oke
        switch (s) {
            case "balloom_dead":
                image = balloom_dead;
                break;
            case "balloom_left1":
                image = balloom_left1;
                break;
            case "balloom_left2":
                image = balloom_left2;
                break;
            case "balloom_left3":
                image = balloom_left3;
                break;
            case "balloom_right1":
                image = balloom_right1;
                break;
            case "balloom_right2":
                image =  balloom_right2;
            break;
            // ei nma con đỏ ý, con ghost, nó mắc tường r k đi tiếp
            // cái đó mới k hiểu tại sao
            // đo là điều thú vị, điều thú vijthuowngf khó hiểu vcđ :))
            // cái loading ballom image 3 cứ để đã, check hộ con đỏ trước đi
            // ghost oke
            case "balloom_right3":
                image = balloom_right3;
            break;
            case "bomb":
                image = bomb;
            break;
            case "bomb_1":
                image = bomb_1;
            break;
            case "bomb_2":
                image = bomb_2;
            break;
            case "bomb_exploded":
                image = bomb_exploded;
            break;
            case "bomb_exploded1":
                image = bomb_exploded1;
            break;
            case "bomb_exploded2":
                image = bomb_exploded2;
            break;
            case "brick":
                image = brick;
            break;
            case "brick_exploded":
                image = brick_exploded;
            break;
            case "brick_exploded1":
                image = brick_exploded1;
            break;
            case "brick_exploded2":
                image = brick_exploded2;
            break;
            case "doll_dead":
                image = doll_dead;
            break;
            case "doll_left1":
                image = doll_left1;
            break;
            case "doll_left2":
                image = doll_left2;
            break;
            case "doll_left3":
                image = doll_left3;
            break;
            case "doll_right1":
                image = doll_right1;
            break;
            case "doll_right2":
                image = doll_right2;
            break;
            case "doll_right3":
                image = doll_right3;
            break;
            case "explosion_horizontal":
                image = explosion_horizontal;
            break;
            case "explosion_horizontal1":
                image = explosion_horizontal1;
            break;
            case "explosion_horizontal2":
                image = explosion_horizontal2;
            break;
            case "explosion_horizontal_left_last":
                image = explosion_horizontal_left_last;
            break;
            case "explosion_horizontal_left_last1":
                image = explosion_horizontal_left_last1;
            break;
            case "explosion_horizontal_left_last2":
                image = explosion_horizontal_left_last2;
            break;
            case "explosion_horizontal_right_last":
                image = explosion_horizontal_right_last;
            break;
            case "explosion_horizontal_right_last1":
                image = explosion_horizontal_right_last1;
            break;
            case "explosion_horizontal_right_last2":
                image = explosion_horizontal_right_last2;
            break;
            case "explosion_vertical":
                image = explosion_vertical;
            break;
            case "explosion_vertical1":
                image = explosion_vertical1;
            break;
            case "explosion_vertical2":
                image = explosion_vertical2;
            break;
            case "explosion_vertical_down_last":
                image = explosion_vertical_down_last;
            break;
            case "explosion_vertical_down_last1":
                image = explosion_vertical_down_last1;
            break;
            case "explosion_vertical_down_last2":
                image = explosion_vertical_down_last2;
            break;
            case "explosion_vertical_top_last":
                image = explosion_vertical_top_last;
            break;
            case "explosion_vertical_top_last1":
                image = explosion_vertical_top_last1;
            break;
            case "explosion_vertical_top_last2":
                image = explosion_vertical_top_last2;
            break;
            case "grass":
                image = grass;
            break;
            case "kondoria_dead":
                image = kondoria_dead;
            break;
            case "kondoria_left1":
                image = kondoria_left1;
            break;
            case "kondoria_left2":
                image = kondoria_left2;
            break;
            case "kondoria_left3":
                image = kondoria_left3;
            break;
            case "kondoria_right1":
                image = kondoria_right1;
            break;
            case "kondoria_right2":
                image = kondoria_right2;
            break;
            case "kondoria_right3":
                image = kondoria_right3;
            break;
            case "minvo_dead":
                image = minvo_dead;
            break;
            case "minvo_left1":
                image = minvo_left1;
            break;
            case "minvo_left2":
                image = minvo_left2;
            break;
            case "minvo_left3":
                image = minvo_left3;
            break;
            case "minvo_right1":
                image = minvo_right1;
            break;
            case "minvo_right2":
                image = minvo_right2;
            break;
            case "minvo_right3":
                image = minvo_right3;
            break;
            case "mob_dead1":
                image = mob_dead1;
            break;
            case "mob_dead2":
                image = mob_dead2;
            break;
            case "mob_dead3":
                image = mob_dead3;
            break;
            case "oneal_dead":
                image = oneal_dead;
            break;
            case "oneal_left1":
                image = oneal_left1;
            break;
            case "oneal_left2":
                image = oneal_left2;
            break;
            case "oneal_left3":
                image = oneal_left3;
            break;
            case "oneal_right1":
                image = oneal_right1;
            break;
            case "oneal_right2":
                image = oneal_right2;
            break;
            case "oneal_right3":
                image = oneal_right3;
            break;
            case "player_dead1":
                image = player_dead1;
            break;
            case "player_dead2":
                image = player_dead2;
            break;
            case "player_dead3":
                image = player_dead3;
            break;
            case "player_down":
                image = player_down;
            break;
            case "player_down_1":
                image = player_down_1;
            break;
            case "player_down_2":
                image = player_down_2;
            break;
            case "player_left":
                image = player_left;
            break;
            case "player_left_1":
                image = player_left_1;
            break;
            case "player_left_2":
                image = player_left_2;
            break;
            case "player_right":
                image = player_right;
            break;
            case "player_right_1":
                image = player_right_1;
            break;
            case "player_right_2":
                image =player_right_2;
            break;
            case "player_up":
                image =player_up;
            break;
            case "player_up_1":
                image =player_up_1;
            break;
            case "player_up_2":
                image = player_up_2;
            break;
            case "portal":
                image = portal;
            break;
            case "powerup_bombpass":
                image = powerup_bombpass;
            break;
            case "powerup_bombs":
                image = powerup_bombs;
            break;
            case "powerup_detonator":
                image = powerup_detonator;
            break;
            case "powerup_flamepass":
                image = powerup_flamepass;
            break;
            case "powerup_flames":
                image = powerup_flames;
            break;
            case "powerup_speed":
                image =  powerup_speed;
            break;
            case "powerup_wallpass":
                image =  powerup_wallpass;
            break;
            case "ghost_dead":
                image =  ghost_dead;
                break;
            case "ghost_left1":
                image =  ghost_left1;
                break;
            case "ghost_left2":
                image =  ghost_left2;
                break;
            case "ghost_left3":
                image =  ghost_left3;
                break;
            case "ghost_right1":
                image =  ghost_right1;
                break;
            case "ghost_right2":
                image =  ghost_right2;
                break;
            case "ghost_right3":
                image =  ghost_right3;
                break;
            case "wall":
                image = wall;
            break;
            default:
                image = null;
        }
        return image;
    }

    public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        _x = x * SIZE;
        _y = y * SIZE;
        _sheet = sheet;
        _realWidth = rw;
        _realHeight = rh;
        load();
    }

    public Sprite(int size, int color) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color) {
        for (int i = 0; i < _pixels.length; i++) {
            _pixels[i] = color;
        }
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                _pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
            }
        }
    }

    public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
        int calc = animate % time;
        int diff = time / 3;

        if (calc < diff) {
            return normal;
        }

        if (calc < diff * 2) {
            return x1;
        }

        return x2;
    }

    public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
        int diff = time / 2;
        return (animate % time > diff) ? x1 : x2;
    }

    public int getSize() {
        return SIZE;
    }

    public int getPixel(int i) {
        return _pixels[i];
    }

    public Image getFxImage() {
        WritableImage wr = new WritableImage(SIZE, SIZE);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (_pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                } else {
                    pw.setArgb(x, y, _pixels[x + y * SIZE]);
                }
            }
        }
        Image input = new ImageView(wr).getImage();
        return resample(input, SCALED_SIZE / DEFAULT_SIZE);
    }

    private Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(
                W * S,
                H * S
        );

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                final int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(x * S + dx, y * S + dy, argb);
                    }
                }
            }
        }

        return output;
    }
}
