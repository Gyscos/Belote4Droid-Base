package com.threewisedroids.belote4droid.base;

public class Card {
    public final static int color_heart   = 0;
    public final static int color_club    = 1;
    public final static int color_diamond = 2;
    public final static int color_spades  = 3;

    public final static int face_7        = 0;
    public final static int face_8        = 1;
    public final static int face_9        = 2;
    public final static int face_10       = 3;
    public final static int face_J        = 4;
    public final static int face_Q        = 5;
    public final static int face_K        = 6;
    public final static int face_A        = 7;
    public final static int face_count    = 8;

    public static int getColor(long card) {
        return (int) (card / face_count);
    }

    public static int getFace(long card) {
        return (int) (card % face_count);
    }

    public static int getFaceStrength(int face, boolean trump) {
        switch (face) {
            case face_7:
                return trump ? 10 : 0;
            case face_8:
                return trump ? 11 : 1;
            case face_9:
                return trump ? 18 : 2;
            case face_J:
                return trump ? 19 : 3;
            case face_Q:
                return trump ? 14 : 4;
            case face_K:
                return trump ? 15 : 5;
            case face_10:
                return trump ? 16 : 6;
            case face_A:
                return trump ? 17 : 7;
            default:
                assert (false);
                return 0;
        }
    }

    public static int getFaceValue(int face, boolean trump) {
        switch (face) {
            case face_7:
            case face_8:
                return 0;
            case face_9:
                return trump ? 14 : 0;
            case face_10:
                return 10;
            case face_J:
                return trump ? 20 : 2;
            case face_Q:
                return 3;
            case face_K:
                return 4;
            case face_A:
                return 11;
            default:
                assert (false);
                return 0;
        }
    }

    public static long makeCard(int color, int face) {
        return face + (long) (color) * face_count;
    }
}
