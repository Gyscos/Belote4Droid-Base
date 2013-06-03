package com.threewisedroids.belote4droid.base;

/**
 * A trick is a set of one card for each player.
 * 
 * @author gyscos
 * 
 */
public class Trick {
    /**
     * Content of the trick.
     */
    long[] cards = new long[4];

    /**
     * Id of the player who started the trick.
     */
    int    firstPlayer;
}
