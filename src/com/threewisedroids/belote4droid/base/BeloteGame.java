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
    int                trump;

    /**
     * Card used to make the contract.
     */
    int                contractCard;

    /**
     * Current contract
     */
    Contract           contract;

    /**
     * Follows the game events (for the UI update).
     */
    BeloteGameListener gameListener;

    /**
     * First player this round.
     */
    int                firstPlayer;

    /**
     * Id of the player who's about to play. In the range [0,3].
     */
    int                currentPlayer;

    /**
     * Currently played trick. May be empty if no card is on the table.
     */
    Trick              currentTrick;

    /**
     * Current score of each team.
     */
    int                scores[] = new int[2];

    /**
     * Set of rules used for the game.
     */
    RuleSet            ruleSet;

    void cancelGame() {
        gameListener.onGameCanceled();
    }

    /**
     * Coinche the game. Sur-coinche if it was already coinched.
     */
    void coinche() {
        contract.coincheCount++;
        gameListener.onCoinche();
        currentPlayer = contract.author;
    }

    void completeTrick() {
        currentPlayer = computeTrickWinner(currentTrick);
        scores[getCurrentTeam()] += computeTrickScore(currentTrick);
        gameListener.onTrickComplete(currentPlayer);
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

    void endGame() {
        firstPlayer++;
        firstPlayer %= 4;
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

    void makeContract(ContractType contractType) {
        contract = new Contract(currentPlayer, contractType);

        gameListener.onContract(contractType);
        if (ruleSet.gameType == RuleSet.GameType.BELOTE) {
            startGame();
        } else {
            // Just some more
            nextPlayer();
        }
    }

    void newTrick() {
        currentTrick = new Trick();
        currentTrick.firstPlayer = currentPlayer;
        gameListener.onNewTrick();
    }

    void nextPlayer() {
        currentPlayer++;
        currentPlayer %= 4;
    }

    void pass() {
        gameListener.onPass();
        nextPlayer();
        if (contract == null) {
            cancelGame();
        } else if (contract.coincheCount > 0) {
            startGame();
        } else if (currentPlayer == contract.author) {
            startGame();
        }
    }

    void playCard(int card) {
        // The current player play the given card.
        currentTrick.cards[currentPlayer] = card;
        gameListener.onCardPlayed(card);
        // Trigger event...
        // Is the trick complete ?
        if (getPlayerDistance(currentPlayer, currentTrick.firstPlayer) == 1) {
            completeTrick();
        } else {
            nextPlayer();
        }
    }

    void setupGame(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    void startBeloteContracts(int contractCard) {
        this.contractCard = contractCard;
        currentPlayer = firstPlayer;
        gameListener.onBeloteContractStart(firstPlayer, contractCard);
    }

    void startCoincheContracts() {
        currentPlayer = firstPlayer;
        gameListener.onCoincheContractStart(firstPlayer);
    }

    void startGame() {
        currentPlayer = firstPlayer;
        gameListener.onGameStart(firstPlayer);
    }
}
