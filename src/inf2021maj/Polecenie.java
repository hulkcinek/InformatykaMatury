package inf2021maj;

public enum Polecenie{
    DOPISZ("DOPISZ"),
    USUN("USUN"),
    ZMIEN("ZMIEN"),
    PRZESUN("PRZESUN");

    String tresc;
    Polecenie(String tresc) {
        this.tresc = tresc;
    }
}