package inf2016czerwiec;

public class Liczba {
    private final String wartosc;
    private final int system;
    private final int dziesietna;


    public Liczba(String wartosc, int system) {
        this.wartosc = wartosc;
        this.system = system;
        this.dziesietna = konwertujNaDziesietny();
    }

    private int konwertujNaDziesietny() {
        return Integer.parseInt(wartosc, system);
    }

    public String getWartosc() {
        return wartosc;
    }


    public int getSystem() {
        return system;
    }

    public int getDziesietna() {
        return dziesietna;
    }

    @Override
    public String toString() {
        return wartosc + system;
    }
}
