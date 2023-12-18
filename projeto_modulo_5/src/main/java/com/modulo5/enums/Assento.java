package com.modulo5.enums;

public enum Assento {

    A1("A1"),
    B1("B1"),
    C1("C1"),
    D1("D1"),
    E1("E1"),
    F1("F1"),
    A2("A2"),
    B2("B2"),
    C2("C2"),
    D2("D2"),
    E2("E2"),
    F2("F2"),
    A3("A3"),
    B3("B3"),
    C3("C3"),
    D3("D3"),
    E3("E3"),
    F3("F3"),
    A4("A4"),
    B4("B4"),
    C4("C4"),
    D4("D4"),
    E4("E4"),
    F4("F4"),
    A5("A5"),
    B5("B5"),
    C5("C5"),
    D5("D5"),
    E5("E5"),
    F5("F5"),
    A6("A6"),
    B6("B6"),
    C6("C6"),
    D6("D6"),
    E6("E6"),
    F6("F6"),
    A7("A7"),
    B7("B7"),
    C7("C7"),
    D7("D7"),
    E7("E7"),
    F7("F7"),
    A8("A8"),
    B8("B8"),
    C8("C8"),
    D8("D8"),
    E8("E8"),
    F8("F8"),
    A9("A9"),
    B9("B9"),
    C9("C9"),
    D9("D9"),
    E9("E9"),
    F9("F9"),
    A10("A10"),
    B10("B10"),
    C10("C10"),
    D10("D10"),
    E10("E10"),
    F10("F10");

    private String sigla;

    Assento(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
