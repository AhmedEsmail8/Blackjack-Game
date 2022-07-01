package blackjack;

import java.util.*;

public class BlackJack {
    public static Game game = new Game();
    public static Scanner in = new Scanner(System.in);

    static void setPlayersName() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of player1 :");
        String s1 = in.next();
        game.setPlayer(s1, 0);
        System.out.println("Enter the name of player2 :");
        String s2 = in.next();
        game.setPlayer(s2, 1);
        System.out.println("Enter the name of player3 :");
        String s3 = in.next();
        game.setPlayer(s3, 2);
        System.out.println("Enter the name of player4 :");
        String s4 = in.next();
        game.setPlayer(s4, 3);
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        setPlayersName();
        gui.runGUI(game.cards, game.players[0].arr, game.players[1].arr, game.players[2].arr, game.players[3].arr);

        // players
        for (int i = 0; i < 3; i++) {
            int n;
            System.out.println("Player #" + game.players[i].getName());
            do {
                System.out.println("Press 1 for hit");
                System.out.println("Press 2 for stand");
                n = in.nextInt();
                if (n == 1) {
                    Card card = new Card(game.takeCard());
                    game.players[i].add(card);
                    gui.updatePlayerHand(card, i);
                }
                if (n == 2 || game.players[i].getScore() >= 21)
                    game.maxValidScore(game.players[i].getScore());
                if (game.players[i].getScore() > 21 || (n == 2 && game.players[i].getScore() < game.maxScore))
                    System.out.println("Player #" + game.players[i].getName() + " lost");
            } while (game.players[i].getScore() < 21 && n == 1);
        }

        boolean b = false;
        // dealer
        if (game.maxScore > 0) {
            System.out.println("Dealer #" + game.players[3].getName());
            do {
                Card card = new Card(game.takeCard());
                game.players[3].add(card);
                gui.updateDealerHand(card, game.cards);
                if (game.players[3].getScore() >= 21 || game.players[3].getScore() > game.maxScore) {
                    game.maxValidScore(game.players[3].getScore());
                    break;
                }
            } while (game.players[3].getScore() < 21 && game.players[3].getScore() <= game.maxScore);
        } else {
            System.out.println("Player " + game.players[3].getName() + " won");
            b = true;
            // System.exit(0);
        }

        if (!b) {
            int sum = 0;
            for (int i = 0; i < 4; i++) {
                if (game.players[i].getScore() == game.maxScore)
                    sum++;
            }
            if (game.maxScore > 0) {
                if (sum == 1) {
                    for (int i = 0; i < 4; i++) {
                        if (game.players[i].getScore() == game.maxScore)
                            System.out.println("Player " + game.players[i].getName() + " won");
                    }
                } else
                    System.out.println("PUSH.");
            } else
                System.out.println("PUSH.");
        }
    }
}
