package Utils;

public class StaticVariables {
    public static final int ScreenWidth = 512;
    public static final int ScreenHeight = 512;
    public static final int BrickWidth = ScreenWidth >> 4;
    public static final int PlayerWidth = ScreenWidth >> 3;
    public static final int PlayerHeight = PlayerWidth >> 1;

    public static final int START_SCENE = 0;
    public static final int GAME_SCENE = 1;
    public static final int SCORE_SCENE = 2;
    public static final int SCENE_END = 3;

    public static final int LEVEL_EASY = 1;
    public static final int LEVEL_NORMAL = 2;
    public static final int LEVEL_HARD = 3;

    public static final int OBJ_ENUM_PLAYER = 0;
    public static final int OBJ_ENUM_BALL = 1;
    public static final int OBJ_ENUM_BRICK = 2;
    public static final int OBJ_ENUM_WALL = 3;
    public static final int OBJ_ENUM_ITEM = 4;
    public static final int OBJ_ENUM_END = 5;


    // Dir
    public static final int DIR_NORTH = 0;
    public static final int DIR_SOUTH = 1;
    public static final int DIR_EAST = 2;
    public static final int DIR_WEST = 3;
    public static final int DIR_END = -1;

    public static final float EPSILON = 1e-6f;

    // ITEM TYPE
    public static final int ITEM_TYPE_WIDTH_LONG = 0;
    public static final int ITEM_TYPE_WIDTH_SHORT = 1;
    public static final int ITEM_TYPE_BALL_SLOW = 2;
    public static final int ITEM_TYPE_BALL_FAST = 3;
    public static final int ITEM_TYPE_END = 4;


}
