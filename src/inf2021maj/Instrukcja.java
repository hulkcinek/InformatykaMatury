package inf2021maj;

public class Instrukcja {
    Polecenie polecenie;
    char litera;

    public Instrukcja(Polecenie polecenie, char litera) {
        this.polecenie = polecenie;
        this.litera = litera;
    }

    @Override
    public String toString() {
        return polecenie.tresc + " " + litera;
    }
}
