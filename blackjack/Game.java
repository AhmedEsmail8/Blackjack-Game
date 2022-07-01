package blackjack;

import java.util.*;

public class Game {
    public Player players[] = new Player[4];
    public Card cards[] = new Card[52];
    public int maxScore = -1;
    private static final Random rand = new Random();

    public Game() {
        for (int i = 0; i < 4; i++) {
            players[i] = new Player();
        }
        // add all cards
        for (int i = 0, suit = 0, j = 0; j < 52; i++, j++) {
            int value;
            if (i >= 10)
                value = 10;
            else
                value = i + 1;
            cards[j] = new Card(suit, i, value);
            if (i % 12 == 0 && i != 0) {
                suit++;
                i -= 13;
            }
        }
    }

    // check if the card has already taken
    // if the card already taken return true
    public boolean taken(int suit, int rank, int value) {
        boolean b = true;
        for (int i = 0; i < 52; i++) {
            if (checkcard(cards[i], suit, rank, value) == true) {
                cards[i] = null;
                b = false;
                break;
            }
        }
        return b;
    }

    public boolean checkcard(Card c, int suit, int rank, int value) {
        if (c == null)
            return false;
        else if (c.getRank() == rank && c.getSuit() == suit && c.getValue() == value)
            return true;
        else
            return false;
    }

    public void setPlayer(String name, int k) {
        // players[k]=new Player();
        players[k].setName(name);
        // add two cards for the player
        for (int o = 0; o < 2; o++) {
            Card obj = new Card(takeCard());
            players[k].add(obj);
        }
    }

    public Card takeCard() {
        while (true) {
            int rank = rand.nextInt(13), s = rand.nextInt(4);
            int value;
            if (rank >= 10)
                value = 10;
            else
                value = rank + 1;
            // check if the card isn't taken
            boolean taken = taken(s, rank, value);
            if (taken == false) {
                Card obj = new Card(s, rank, value);
                System.out.println(obj.toString());
                return obj;
            }
        }

    }

    public String getPlayerName(int k) {
        return players[k].getName();
    }

    public void maxValidScore(int k) {
        if (k <= 21)
            maxScore = Math.max(maxScore, (Math.min(21, k)));
    }
}
