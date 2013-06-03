package com.threewisedroids.belote4droid.base;

public enum ContractType {

    contract_80(80),
    contract_90(90),
    contract_100(100),
    contract_110(110),
    contract_120(120),
    contract_130(130),
    contract_140(140),
    contract_150(150),
    contract_160(160),
    contract_capot(250);

    public int value;

    ContractType(int value) {
        this.value = value;
    }
}
