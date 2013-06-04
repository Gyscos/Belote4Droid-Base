package com.threewisedroids.belote4droid.base;

public class RuleSet {
    enum GameType
    {
        BELOTE, COINCHE
    }

    GameType gameType      = GameType.BELOTE;

    boolean  allowAllTrump = false;
    boolean  allowNoTrump  = false;

    int      endScore      = 1000;
}
