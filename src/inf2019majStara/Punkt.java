package inf2019majStara;

public class Punkt {
    final int Y;
    final int X;

    public Punkt(int y, int x) {
        Y = y;
        X = x;
    }

    public int mniejszaWspolrzedna() {
        return Integer.min(Y, X);
    }
    public int wiekszaWspolrzedna() {
        return Integer.max(Y, X);
    }
}
