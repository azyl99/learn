package pokergame.v1;


import lombok.Data;

/**
 * 没有王
 *
 * @author guya
 * @date 2018/8/27
 */
@Data
public class Card {

    private Suit suit;
    private Value value;
    private int code;

    public final static Card[] codes;
    static {
        codes = new Card[66];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Card card = codes[(i << 4) + j];
                card.setSuit(Suit.values()[i]);
                card.setValue(Value.values()[j]);
            }
        }
    }

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
        this.code = suit.ordinal() << 4;
    }

    enum Suit {
        SPADE("S", '\u2660', '\u2664'),
        HEART("H", '\u2665', '\u2661'),
        DIAMONDS("D", '\u2666', '\u2662'),
        CLUB("C", '\u2663', '\u2667');

        String desc;
        char blackSymbol;
        char WhiteSymbol;

        Suit(String desc, char blackSymbol, char whiteSymbol) {
            this.desc = desc;
            this.blackSymbol = blackSymbol;
            WhiteSymbol = whiteSymbol;
        }

        @Override
        public String toString() {
            return desc;
        }
    }

    enum Value {
        ACE("A", 1, 14),
        N2("2", 2, 2),
        N3("3", 3, 3),
        N4("4", 4, 4),
        N5("5", 5, 5),
        N6("6", 6, 6),
        N7("7", 7, 7),
        N8("8", 8, 8),
        N9("9", 9, 9),
        N10("T", 10, 10),
        JACK("J", 11, 11),
        QUEEN("Q", 12, 12),
        KING("K", 13, 13),
        ;
        String desc;
        int faceValue;
        int cardValue;
        Value(String desc, int faceValue, int cardValue) {
            this.desc = desc;
            this.faceValue = faceValue;
            this.cardValue = cardValue;
        }

        @Override
        public String toString() {
            return desc;
        }

        public String getDesc() {
            return desc;
        }

        public int getFaceValue() {
            return faceValue;
        }

        public int getCardValue() {
            return cardValue;
        }
    }

    public int compareFace(Card card) {
        return compareFace(this.value, card.value);
    }

    public int compareCard(Card card) {
        return compareCard(this.value, card.value);
    }

    private static int compareFace(Value value1, Value value2) {
        return value1.faceValue - value2.faceValue;
    }
    private static int compareCard(Value value1, Value value2) {
        return value1.cardValue - value2.cardValue;
    }

    public String toString() {
        return suit.toString() + value.toString();
    }
}
