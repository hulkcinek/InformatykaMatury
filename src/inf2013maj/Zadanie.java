package inf2013maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadanie {

    List<String> liczbyOsemkowy = new ArrayList<>();

    public void start() {
        odczytaj();
        zliczPierwszaRownaOstatniej();
        zliczPierwszaRownaOstatniejDlaDziesienych();
        znajdzMinIMaxNiemalejacych();
    }

    private void znajdzMinIMaxNiemalejacych() {
        int min = liczbyOsemkowy.stream()
                .filter(this::czyNieMalejaca)
                .map(Integer::parseInt)
                .min(Integer::compareTo)
                .orElse(-1);
        int max = liczbyOsemkowy.stream()
                .filter(this::czyNieMalejaca)
                .map(Integer::parseInt)
                .max(Integer::compareTo)
                .orElse(-1);
        long wynik = liczbyOsemkowy.stream()
                        .filter(this::czyNieMalejaca)
                        .count();

        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println(wynik);
    }

    private void zliczPierwszaRownaOstatniejDlaDziesienych() {
        long wynik = liczbyOsemkowy.stream()
                .map(l -> Integer.parseInt(l, 8))
                .map(String::valueOf)
                .filter(l -> l.charAt(0) == l.charAt(l.length()-1))
                .count();
        System.out.println(wynik);
    }

    private boolean czyNieMalejaca(String s){
        char[] litery = s.toCharArray();
        for (int i = 1; i < litery.length; i++) {
            if (litery[i-1] <= litery[i]) continue;
            return false;
        }
        return true;
    }

    private void zliczPierwszaRownaOstatniej() {
        long wynik = liczbyOsemkowy.stream()
                .filter(l -> l.charAt(0) == l.charAt(l.length()-1))
                .count();
        System.out.println(wynik);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2013maj/dane.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (s.hasNextLine()){
            String liczba = s.nextLine();
            if (liczba.isEmpty()) continue;
            liczbyOsemkowy.add(liczba);
        }
    }
}
