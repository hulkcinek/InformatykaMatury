package inf2016maj;

public class Punkt {
    final int x;
    final int y;
    final Polozenie polozenie;

    public Punkt(int x, int y) {
        this.x = x;
        this.y = y;
        polozenie = znajdzPolozenie();
        if (polozenie == Polozenie.NA_KOLE) System.out.println(this);
    }

    private Polozenie znajdzPolozenie() {
        double odlegloscOdSrodkaKola = Math.pow(x - 200, 2) + Math.pow(y - 200, 2);
        int kwadratPromienia = 200 * 200;
        if (odlegloscOdSrodkaKola > kwadratPromienia) return Polozenie.POZA_KOLEM;
        if (odlegloscOdSrodkaKola == kwadratPromienia) return Polozenie.NA_KOLE;
        return Polozenie.W_KOLE;
    }

    public Polozenie getPolozenie() {
        return polozenie;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", this.x, this.y);
    }
}
