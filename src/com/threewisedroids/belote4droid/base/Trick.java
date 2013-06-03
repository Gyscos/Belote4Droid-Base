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
    int[] cards = new int[4];

    /**
     * Id of the player who started the trick.
     */
    int   firstPlayer;
}
