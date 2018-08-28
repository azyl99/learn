package pokergame;

/**
 * @author guya
 * @date 2018/8/28
 */
public class CardCode {

    static final int SJOKER_CODE = 63 + 15;
    static final int BJOKER_CODE = 63 + 16;

    static final int SPADE = 0x00000100;
    static final int HEART = 0x00000200;
    static final int DIAMONDS = 0x00000400;
    static final int CLUB = 0x00000800;
    static final int DIGIT = 0x00001000;
    static final int JQK = 0x000002000;
    static final int JOKER = 0x000004000;

    private final static int[] type;

    static {
        type = new int[128];
        for (int i = 0; i < 128; i++) {
            type[i] = i;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 10; j++) {
                type[(i << 4) + j] += DIGIT;
            }
            for (int j = 11; j <= 13; j++) {
                type[(i << 4) + j] += JQK + j;
            }
        }
        type[SJOKER_CODE] += JOKER;
        type[BJOKER_CODE] += JOKER;
    }

    static int getType(int card) {
        return ((card & 0xFFFFFF80) == 0 ? (type[card] & 0xFFFFFF00) == 0 ? 0 : type[card] : 0);
    }

    static boolean isType(int card, int type) {
        return (getType(card) & type) != 0;
    }

    public static int getVaildCode(int card) {
        return getType(card) & 0xFF;
    }

    public static boolean isSpade(int card) {
        return isType(card, SPADE);
    }

    public static boolean isHeart(int card) {
        return isType(card, HEART);
    }

    public static boolean isDiamonds(int card) {
        return isType(card, DIAMONDS);
    }

    public static boolean isClub(int card) {
        return isType(card, CLUB);
    }

    public static boolean isDigit(int card) {
        return isType(card, DIGIT);
    }

    public static boolean isJQK(int card) {
        return isType(card, JQK);
    }

    public static boolean isJoker(int card) {
        return isType(card, JOKER);
    }

    public static String getHexCode(int card) {
        return String.format("0x%x", card);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 128; i++) {
            System.out.println(i + " " + type[i] + " " + getType(i));
        }
    }
}
