package blackjack;

public class Player {
    private String name;
    private int score = 0, j = 0;
    Card arr[] = new Card[10];

    public void add(Card card) {
        score += card.getValue();
        arr[j] = new Card(card.getSuit(), card.getRank(), card.getValue());
        j++;
    }

    public int getNumberOfCards() {
        return j;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
