package com.threewisedroids.belote4droid.base;

public interface BeloteGameListener {
    public void onBeloteContractStart(int firstPlayer, int contractCard);

    public void onCardPlayed(int card);

    public void onCoinche();

    public void onCoincheContractStart(int firstPlayer);

    public void onContract(ContractType contract);

    public void onGameCanceled();

    public void onGameEnd();

    public void onGameStart(int firstPlayer);

    public void onNewTrick();

    public void onPass();

    public void onTrickComplete(int winner);
}
