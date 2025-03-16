package inf2024majStara;

public enum Typ{
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

