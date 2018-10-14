package pokergame;

import java.util.Random;

/**
 * @author guya
 * @date 2018/8/28
 */
public class Deck {

    private Card[] cards;
    private int cursor;


    private Deck() {
        int i = 0;
        cards = new Card[54];
        for (int code = 0; code < 80; code++) {
            if (CardCode.getType(code) != 0) {
                cards[i++] = new Card(code);
            }
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < cards.length; i++) {
            int j = random.nextInt(cards.length);
            Card temp = cards[j];
            cards[j] = cards[i];
            cards[i] = temp;
        }
    }

    /**
     * 从第 cursor 张牌开始打乱
     * @param cards
     * @param cursor
     */
    public static void shuffle(Card[] cards, int cursor) {
        Random random = new Random();
        for (int i = cursor; i < cards.length; i++) {
            int j = random.nextInt(cards.length);
            Card temp = cards[j];
            cards[j] = cards[i];
            cards[i] = temp;
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cards.length; i++) {
            sb.append(cards[i].abbr()).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck);
        deck.shuffle();
        System.out.println(deck);
    }
}
