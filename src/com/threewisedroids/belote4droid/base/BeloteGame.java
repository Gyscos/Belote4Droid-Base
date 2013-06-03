package com.threewisedroids.belote4droid.base;

/**
 * Represents a running game of Belote. Holds informations known to all players.
 * 
 * @author gyscos
 * 
 */
public class BeloteGame {
    static int getPlayerDistance(int from, int to) {
        return (to >= from) ? (to - from) : (to - from + 4);
    }

    static int getTeam(int player) {
        return player % 2;
    }

    /**
     * The current game's trump.
     */
    int   trump;

    int   contract;

    /**
     * Id of the player who's about to play. In the range [0,3].
     */
    int   currentPlayer;

    /**
     * Currently played trick. May be empty if no card is on the table.
     */
    Trick currentTrick;

    /**
     * Current score of each team.
     */
    int   scores[] = new int[2];

    void completeTrick() {
        currentPlayer = computeTrickWinner(currentTrick);
        scores[getCurrentTeam()] += computeTrickScore(currentTrick);
        newTrick();
    }

    int computeTrickScore(Trick trick) {
        int score = 0;
        for (int player = 0; player < 4; player++) {
            score += getCardValue(trick.cards[player]);
        }
        return score;
    }

    int computeTrickWinner(Trick trick) {
        int winner = 0;
        int best = 0;
        for (int player = 0; player < 4; player++) {
            int strength = getCardStrength(trick.cards[player]);
            if (strength > best) {
                best = strength;
                winner = player;
            }
        }
        return winner;
    }

    int getCardStrength(int card) {
        return Card.getFaceStrength(Card.getFace(card),
                Card.getColor(card) == trump);
    }

    int getCardValue(int card) {
        return Card.getFaceValue(Card.getFace(card),
                Card.getColor(card) == trump);
    }

    int getCurrentTeam() {
        return getTeam(currentPlayer);
    }

    void newTrick() {
        currentTrick = new Trick();
        currentTrick.firstPlayer = currentPlayer;
    }

    void nextPlayer() {
        currentPlayer++;
        currentPlayer %= 4;
    }

    void playCard(int card) {
        // The current player play the given card.
        currentTrick.cards[currentPlayer] = card;
        // Trigger event...
        // Is the trick complete ?
        if (getPlayerDistance(currentPlayer, currentTrick.firstPlayer) == 1) {
            completeTrick();
        } else {
            nextPlayer();
        }
    }
}
