package pokergame.v1;

import java.util.Random;

/**
 * @author guya
 * @date 2018/8/27
 */
public class Deck {

    private Card[] cards;

    private Deck() {
        final Card.Suit[] suits = Card.Suit.values();
        final Card.Value[] values = Card.Value.values();
        final int size = suits.length * values.length;

        cards = new Card[size];
        int i = 0;
        for (Card.Suit suit : suits) {
            for (Card.Value value : values) {
                cards[i++] = new Card(suit, value);
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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cards.length; i++) {
            sb.append(cards[i].getSuit()).append(cards[i].getValue()).append(' ');
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
