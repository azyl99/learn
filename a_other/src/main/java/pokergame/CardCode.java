package pokergame;

/**
 * @author guya
 * @date 2018/8/28
 */
final class CardCode {

    static final int SJOKER_CODE = 63 + 15;
    static final int BJOKER_CODE = 63 + 16;

    static final int SPADE = 0x00000100;
    static final int HEART = 0x00000200;
    static final int DIAMONDS = 0x00000400;
    static final int CLUB = 0x00000800;
    static final int DIGIT = 0x00001000;
    static final int JQK = 0x000002000;
    static final int JOKER = 0x000004000;

    private final static int[] type = {
            0,
            SPADE + DIGIT + 1,
            SPADE + DIGIT + 2,
            SPADE + DIGIT + 3,
            SPADE + DIGIT + 4,
            SPADE + DIGIT + 5,
            SPADE + DIGIT + 6,
            SPADE + DIGIT + 7,
            SPADE + DIGIT + 8,
            SPADE + DIGIT + 9,
            SPADE + DIGIT + 10,
            SPADE + JQK + 11,
            SPADE + JQK + 12,
            SPADE + JQK + 13,
            0,
            0,                      /* 0x0F */
            0,
            HEART + DIGIT + 1,
            HEART + DIGIT + 2,
            HEART + DIGIT + 3,
            HEART + DIGIT + 4,
            HEART + DIGIT + 5,
            HEART + DIGIT + 6,
            HEART + DIGIT + 7,
            HEART + DIGIT + 8,
            HEART + DIGIT + 9,
            HEART + DIGIT + 10,
            HEART + JQK + 11,
            HEART + JQK + 12,
            HEART + JQK + 13,
            0,
            0,                      /* 0x1F */
            0,
            DIAMONDS + DIGIT + 1,
            DIAMONDS + DIGIT + 2,
            DIAMONDS + DIGIT + 3,
            DIAMONDS + DIGIT + 4,
            DIAMONDS + DIGIT + 5,
            DIAMONDS + DIGIT + 6,
            DIAMONDS + DIGIT + 7,
            DIAMONDS + DIGIT + 8,
            DIAMONDS + DIGIT + 9,
            DIAMONDS + DIGIT + 10,
            DIAMONDS + JQK + 11,
            DIAMONDS + JQK + 12,
            DIAMONDS + JQK + 13,
            0,
            0,                      /* 0x2F */
            0,
            CLUB + DIGIT + 1,
            CLUB + DIGIT + 2,
            CLUB + DIGIT + 3,
            CLUB + DIGIT + 4,
            CLUB + DIGIT + 5,
            CLUB + DIGIT + 6,
            CLUB + DIGIT + 7,
            CLUB + DIGIT + 8,
            CLUB + DIGIT + 9,
            CLUB + DIGIT + 10,
            CLUB + JQK + 11,
            CLUB + JQK + 12,
            CLUB + JQK + 13,
            0,
            0,                      /* 0x3F */
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            JOKER + 14,
            JOKER + 15,             /* 0x4F */
    };

    static int getType(int card) {
        return (type[card] & 0xFFFFFF00) == 0 ? 0 : type[card];
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
        return String.format("0x%02x", card);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 128; i++) {
            System.out.println(i + " " + type[i] + " " + getType(i));
        }
    }
}
