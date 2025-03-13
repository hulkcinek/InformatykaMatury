package inf2024majStara;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Statek {
    Set<Punkt> punkty;
    Typ typ;

    public Statek(Punkt...punkty) {
        this.punkty = Arrays.stream(punkty).collect(Collectors.toSet());
        typ = Typ.of(this.punkty.size());
    }

    enum Typ{
        JEDNOMASZTOWIEC(1),
        DWUMASZTOWIEC(2);

        final int iloscMasztow;

        Typ(int i) {
            iloscMasztow = i;
        }

        public static Typ of(int i){
            for (Typ value : Typ.values()) {
                if (value.iloscMasztow == i)
                    return value;
            }
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Statek statek = (Statek) o;
        return Objects.equals(punkty, statek.punkty) && typ == statek.typ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(punkty, typ);
    }
}
