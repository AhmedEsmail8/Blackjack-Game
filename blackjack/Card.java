package blackjack;

public class Card {
    private final int suit, rank, value;

    public Card(int suit, int rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public Card(Card c) {
        this.suit = c.getSuit();
        this.rank = c.getRank();
        this.value = c.getValue();
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Card{" + "suit=" + suit + ", rank=" + rank + ", value=" + value + '}';
    }

}
