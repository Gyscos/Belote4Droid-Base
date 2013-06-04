package com.threewisedroids.belote4droid.base;

public class Contract {
    public int          author;
    public ContractType type;
    public int          coincheCount = 0;

    public Contract(int author, ContractType type) {
        this.author = author;
        this.type = type;
    }
}
