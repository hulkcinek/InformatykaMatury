package inf2024majStara;

import java.util.Objects;

public class Punkt {
    private final int y;
    private final int x;

    public Punkt(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Punkt punkt = (Punkt) o;
        return y == punkt.y && x == punkt.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
