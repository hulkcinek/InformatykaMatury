package inf2017czerwiec;

import java.util.*;

public class Punkt {
    private final int x;
    private final int y;
    private final Polozenie polozenie;

    public Punkt(int x, int y) {
        this.x = x;
        this.y = y;
        this.polozenie = znajdzPolozenie();
    }

    private Polozenie znajdzPolozenie() {
        if (Math.abs(x) < 5000 && Math.abs(y) < 5000) return Polozenie.WEWNATRZ;
        if (Math.abs(x) > 5000 || Math.abs(y) > 5000) return Polozenie.NAZEWNATRZ;
        return Polozenie.NABOKACH;
    }

    public long znajdzOdleglosc(Punkt p){
        int dx = this.x - p.getX();
        int dy = this.y - p.getY();
        return Math.round(Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
    }

    public boolean czyCyfropodobne(){
        Set<Integer> cyfryX = new HashSet<>();
        int tempX = x;
        while (tempX > 0){
            cyfryX.add(tempX % 10);
            tempX /= 10;
        }

        Set<Integer> cyfryY = new HashSet<>();
        int tempY = y;
        while (tempY > 0) {
            cyfryY.add(tempY % 10);
            tempY /= 10;
        }

        return cyfryY.equals(cyfryX);
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

    public Polozenie getPolozenie() {
        return polozenie;
    }

    enum Polozenie{
        WEWNATRZ,
        NABOKACH,
        NAZEWNATRZ
    }
}
