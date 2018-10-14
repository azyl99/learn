package pokergame;

/**
 * @author guya
 * @date 2018/8/28
 */
public class Card {
    private final int code;
    private final Suit suit;
    private final Value value;

    public Card(int code) {
        this.code = code;
        this.suit = toSuit(code);
        this.value = toValue(code);
    }

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
        this.code = toCode(suit, value);
    }

    enum Suit {
        SPADE("S", '\u2660', '\u2664'),
        HEART("H", '\u2665', '\u2661'),
        DIAMONDS("D", '\u2666', '\u2662'),
        CLUB("C", '\u2663', '\u2667'),
        JOKER("J", 'J', 'J');

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
        N2("2", 2, 15),
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
        SJOKER("S", 14, 16),
        BJOKER("B", 15, 17),;
        private final String desc;
        private final int faceValue;
        private final int cardValue;

        public String getDesc() {
            return desc;
        }

        public int getFaceValue() {
            return faceValue;
        }

        public int getCardValue() {
            return cardValue;
        }

        Value(String desc, int faceValue, int cardValue) {
            this.desc = desc;
            this.faceValue = faceValue;
            this.cardValue = cardValue;
        }

        @Override
        public String toString() {
            return desc;
        }
    }

    private static Suit toSuit(int code) {
        int type = CardCode.getType(code);
        if (type == 0) {
            throw new CardCodeConvertException();
        }
        if (CardCode.isJoker(code)) {
            return Suit.JOKER;
        }
        if (CardCode.isSpade(code)) {
            return Suit.SPADE;
        }
        if (CardCode.isHeart(code)) {
            return Suit.HEART;
        }
        if (CardCode.isDiamonds(code)) {
            return Suit.DIAMONDS;
        }
        if (CardCode.isClub(code)) {
            return Suit.CLUB;
        }
        throw new CardCodeConvertException();// unreachable
    }

    private static Value toValue(int code) {
        int type = CardCode.getType(code);
        if (type == 0) {
            throw new CardCodeConvertException();
        }
        if (code == CardCode.SJOKER_CODE) {
            return Value.SJOKER;
        }
        if (code == CardCode.BJOKER_CODE) {
            return Value.BJOKER;
        }
        return Value.values()[(code & 0x0F) - 1];
    }

    private static int toCode(Suit suit, Value value) {
        if (suit == Suit.JOKER) {
            if (value == Value.SJOKER) {
                return CardCode.SJOKER_CODE;
            }
            if (value == Value.BJOKER) {
                return CardCode.BJOKER_CODE;
            }
            throw new CardCodeConvertException();
        }
        if (value.getFaceValue() < 1 || value.getFaceValue() > 13) {
            throw new CardCodeConvertException();
        }
        return (suit.ordinal() << 4) + value.getFaceValue();
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

    @Override
    public String toString() {
        return "" + CardCode.getHexCode(code) + " " + suit + " " + value;
    }

    public String abbr() {
        return suit.toString() + value;
    }

    static class CardCodeConvertException extends RuntimeException { }

    public static void main(String[] args) {
        Card card1 = new Card(CardCode.BJOKER_CODE);
        System.out.println(card1);
        Card card2 = new Card(Suit.HEART, Value.N3);
        System.out.println(card2);
        Card card3 = new Card(Suit.CLUB, Value.N2);
        System.out.println(card3);
        System.out.println(card2.compareFace(card3));
        System.out.println(card2.compareCard(card3));
    }
}
