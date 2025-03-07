package inf2020kwiecien;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Zadanie {

    List<Integer> liczby = new ArrayList<>();
    List<Integer> luki = new ArrayList<>();

    public void start() {
        odczytaj();
        obliczLuki();
        znajdzMinIMaxLuke();
        znajdzNajdluzszyRegularny();
        znajdzNajczestszaLuke();
    }

    private void znajdzNajczestszaLuke() {
        System.out.println();
        Map<Integer, Long> mapa = luki.stream()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        long max = mapa.values().stream()
                .max(Long::compareTo)
                .orElse(-1L);
        System.out.println("Max krotnosc: " + max);
        System.out.println("Dla wartosci luk:");
        mapa.entrySet().stream()
                .filter(entry -> entry.getValue() == max)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
    }

    private void znajdzNajdluzszyRegularny() {
        System.out.println();
        int maxDlugosc = -1;
        int indexMax = -1;
        int obecnaDlugosc = 1;
        int indexPoczatkuObecnego = 0;
        for (int i = 1; i < luki.size(); i++) {
            if (luki.get(i - 1).equals(luki.get(i))){
                obecnaDlugosc++;
                continue;
            }

            if (maxDlugosc < obecnaDlugosc){
                maxDlugosc = obecnaDlugosc;
                indexMax = indexPoczatkuObecnego;
            }
            obecnaDlugosc = 1;
            indexPoczatkuObecnego = i;
        }
        if (maxDlugosc < obecnaDlugosc){
            maxDlugosc = obecnaDlugosc;
            indexMax = indexPoczatkuObecnego;
        }

        maxDlugosc += 1;

        System.out.println("Poczatkowy: " + liczby.get(indexMax));
        System.out.println("Ostatni: " + liczby.get(indexMax-1 + maxDlugosc));
        System.out.println("Max dlugosc: " + maxDlugosc);
    }

    private void znajdzMinIMaxLuke() {
        int min = luki.stream()
                .min(Integer::compareTo)
                .orElse(-1);
        int max = luki.stream()
                .max(Integer::compareTo)
                .orElse(-1);
        System.out.println("Min luka: " + min);
        System.out.println("Max luka: " + max);
    }

    private void obliczLuki() {
        for (int i = 1; i < liczby.size(); i++) {
            luki.add(Math.abs(liczby.get(i-1) - liczby.get(i)));
        }
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2020kwiecien/dane4.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (s.hasNextInt()){
            liczby.add(s.nextInt());
        }
    }
}
