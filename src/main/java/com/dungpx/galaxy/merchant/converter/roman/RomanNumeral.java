package com.dungpx.galaxy.merchant.converter.roman;

/**
 * <b>File Created</b>: Feb 19, 2019
 *
 * <b>Author</b>: dungpx
 */
enum RomanNumeral {
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);

    RomanNumeral(int value) {
        this.value = value;
    }

    private int value;

    public static int getValueFromChar(char c) {
        return RomanNumeral
                .valueOf(String.valueOf(c))
                .value;
    }
}
