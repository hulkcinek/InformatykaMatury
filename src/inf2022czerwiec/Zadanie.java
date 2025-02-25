package inf2022czerwiec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Zadanie {

    List<Integer> liczby = new ArrayList<>();

    public void start() {
        odczytaj();
        znajdzOdbiciaPodzielnePrzez17();
        System.out.println();
        znajdzNajwiekszaRozniceDoOdbicia();
        System.out.println();
        znajdzLiczbyPierwsze();
        System.out.println();
        policzRozne();
        znajdzPopularnosci();
    }

    private void znajdzPopularnosci() {
        Map<Integer, Long> mapa = liczby.stream()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        long popularnosc2 = mapa.entrySet().stream()
                .filter(l -> l.getValue() == 2)
                .count();
        System.out.println("Popularnosc 2: " + popularnosc2);
        long popularnosc3 = mapa.entrySet().stream()
                .filter(l -> l.getValue() == 3)
                .count();
        System.out.println("Popularnosc 3: " + popularnosc3);
    }

    private void policzRozne() {
        long liczba = liczby.stream()
                .distinct()
                .count();
        System.out.println(liczba + " roznych");
    }

    private void znajdzLiczbyPierwsze() {
        liczby.stream()
                .filter(l -> czyPierwsza(l) && czyPierwsza(odbicie(l)))
                .forEach(System.out::println);
    }

    private boolean czyPierwsza(int liczba){
        for (int i = 2; i <= Math.sqrt(liczba); i++) {
            if (liczba % i == 0) return false;
        }
        return true;
    }

    private void znajdzNajwiekszaRozniceDoOdbicia() {
        int najwiekszaRoznica = liczby.stream()
                .map(l -> Math.abs(l - odbicie(l)))
                .max(Integer::compareTo)
                .orElse(-1);

        int maxDlaLiczby = liczby.stream()
                .filter(l -> Math.abs(l - odbicie(l)) == najwiekszaRoznica)
                .toList()
                .getFirst();

        System.out.println(maxDlaLiczby + " dla " + najwiekszaRoznica);
    }

    private void znajdzOdbiciaPodzielnePrzez17() {
        liczby.stream()
                .map(this::odbicie)
                .filter(l -> l%17 == 0)
                .forEach(System.out::println);
    }

    private int odbicie(int liczba){
        StringBuilder sb = new StringBuilder();
        sb.append(liczba);
        sb.reverse();
        return Integer.parseInt(sb.toString());
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2022czerwiec/liczby.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (s.hasNextInt()){
            int liczba = s.nextInt();
            liczby.add(liczba);
        }
    }
}
