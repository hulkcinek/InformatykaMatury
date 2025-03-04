package inf2016czerwiec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadanie {

    List<Liczba> liczby = new ArrayList<>();

    public void start() {
        odczytaj();
        policzSystemyOsemkowe();
        policzSystemyCzworkoweBezZer();
        policzSystemyDwojkoweParzyste();
        obliczSumeSystemowOsemkowych();
        znajdzMinIMaxDziesietne();
    }

    private void znajdzMinIMaxDziesietne() {
        int min = liczby.stream()
                .map(Liczba::getDziesietna)
                .min(Integer::compareTo)
                .orElse(-1);
        Liczba minLiczba = liczby.stream()
                .filter(l -> l.getDziesietna() == min)
                .toList()
                .getFirst();

        int max = liczby.stream()
                .map(Liczba::getDziesietna)
                .max(Integer::compareTo)
                .orElse(-1);
        Liczba maxLiczba = liczby.stream()
                .filter(l -> l.getDziesietna() == max)
                .toList()
                .getFirst();

        System.out.println("Min: " + minLiczba + " w dziesietnym -> " + minLiczba.getDziesietna());
        System.out.println("Max: " + maxLiczba + " w dziesietnym -> " + maxLiczba.getDziesietna());
    }

    private void obliczSumeSystemowOsemkowych() {
        long wynik = liczby.stream()
                .filter(l -> l.getSystem() == 8)
                .mapToInt(Liczba::getDziesietna)
                .sum();
        System.out.println(wynik);
    }

    private void policzSystemyDwojkoweParzyste() {
        long wynik = liczby.stream()
                .filter(l -> l.getSystem() == 2)
                .map(Liczba::getWartosc)
                .filter(l -> l.charAt(l.length()-1) == '0')
                .count();
        System.out.println(wynik);
    }

    private void policzSystemyCzworkoweBezZer() {
        long wynik = liczby.stream()
                .filter(l -> l.getSystem() == 4)
                .filter(l -> !l.getWartosc().contains("0"))
                .count();
        System.out.println(wynik);
    }

    private void policzSystemyOsemkowe() {
        long wynik = liczby.stream()
                .filter(l -> l.getSystem() == 8)
                .count();
        System.out.println(wynik);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2016czerwiec/liczby.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()){
            String liczba = s.nextLine();
            if (liczba.isEmpty())
                continue;
            String wartosc = liczba.substring(0, liczba.length()-1);
            int system = Character.getNumericValue(liczba.charAt(liczba.length()-1));
            liczby.add(new Liczba(wartosc, system));
        }
    }
}
