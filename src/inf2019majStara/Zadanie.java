package inf2019majStara;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

public class Zadanie {

    List<char[][]> dzialki = new ArrayList<>();

    public void start() {
        odczytaj();
        policzDzialkiZWiekszosciaTrawy();
        znajdzOdwrocone();
        znajdzMaksymalnyPlac();
    }

    private void znajdzMaksymalnyPlac() {
        int max = dzialki.stream()
                .map(this::policzMaksymalnaDlugoscBokuPlacu)
                .max(Integer::compareTo)
                .orElse(-1);
        System.out.println("Maksymalna dlugosc boku: " + max);
        System.out.println("Dla dzialki: ");
        IntStream.range(0, dzialki.size())
                .filter(i -> policzMaksymalnaDlugoscBokuPlacu(dzialki.get(i)) == max)
                .map(i -> i + 1)
                .forEach(System.out::println);
    }

    private int policzMaksymalnaDlugoscBokuPlacu(char[][] dzialka){
        List<Punkt> przeszkody = new ArrayList<>();
        for (int y = 0; y < dzialka.length; y++) {
            for (int x = 0; x < dzialka[0].length; x++) {
                if (dzialka[y][x] == Element.PRZESZKODA.znak)
                    przeszkody.add(new Punkt(y, x));
            }
        }
        Integer wynik = przeszkody.stream()
                .map(Punkt::wiekszaWspolrzedna)
                .min(Integer::compareTo)
                .orElse(-1);
        return wynik;
    }

    private void znajdzOdwrocone() {
        for (int i = 0; i < dzialki.size(); i++) {
            char[][] odwrocona = odwrocDzialke(dzialki.get(i));
            for (int j = i + 1; j < dzialki.size(); j++) {
                if (Arrays.deepEquals(odwrocona, dzialki.get(j)))
                    System.out.println((i + 1) + " " + (j + 1));
            }
        }
    }

    private char[][] odwrocDzialke(char[][] dzialka){
        char[][] odwrocona = new char[dzialka.length][dzialka[0].length];
        for (int i = 0; i < dzialka.length; i++) {
            for (int j = 0; j < dzialka[0].length; j++) {
                odwrocona[dzialka.length - 1 - i][dzialka[0].length - 1 - j] = dzialka[i][j];
            }
        }
        return odwrocona;
    }

    private void policzDzialkiZWiekszosciaTrawy() {
        long wynik = dzialki.stream()
                .filter(dzialka -> {
                    int iloscTrawy = 0;
                    for (int i = 0; i < dzialka.length; i++) {
                        for (int j = 0; j < dzialka[0].length; j++) {
                            Element obecny = Element.of(dzialka[i][j]);
                            if (Element.TRAWA.equals(obecny))
                                iloscTrawy++;
                        }
                    }
                    return iloscTrawy * 1.0 / (dzialka.length * dzialka[0].length) >= 0.7;
                })
                .count();
        System.out.println(wynik);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2019majStara/dzialki.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<char[]> dzialka = new ArrayList<>();
        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()){
                dzialki.add(dzialka.toArray(new char[0][]));
                dzialka.clear();
            }else {
                dzialka.add(line.toCharArray());
            }
        }
    }

    enum Element {
        TRAWA('*'),
        PRZESZKODA('X'),
        PUSTE('.');
        final char znak;

        Element(char znak) {
            this.znak = znak;
        }

        public static Element of(char c){
            for (Element e : Element.values()){
                if (e.znak == c) return e;
            }
            return null;
        }
    }
}
