package inf2023czerwiec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Zadanie {

    List<String> binarne = new ArrayList<>();
    List<Integer> dziesietne = new ArrayList<>();
    List<int[]> cyfry = new ArrayList<>();


    public void start(){
        odczytaj();
        policzZrownowazone();
        System.out.println();
        znajdzMaksimumAnagramow();
        System.out.println();
        znajdzNajwiekszaRoznice();
        System.out.println();
        policzBezZera();
    }

    private void policzBezZera() {
        cyfry = dziesietne.stream()
                .map(String::valueOf)
                .map(s -> s.chars()
                        .map(Character::getNumericValue)
                        .toArray())
                .toList();
        long bezZer = cyfry.stream()
                .filter(a -> Arrays.stream(a)
                        .noneMatch(c -> c == 0))
                .count();
        System.out.println(bezZer);

        long maxSuma = cyfry.stream()
                .map(l -> Arrays.stream(l).asLongStream()
                        .distinct()
                        .sum())
                .max(Long::compareTo)
                .orElse(-1L);

        int[] wynik = cyfry.stream()
                .filter(l -> Arrays.stream(l).asLongStream()
                    .distinct()
                    .sum() == maxSuma)
                .findFirst()
                .orElse(new int[0]);

        Arrays.stream(wynik).forEach(System.out::print);
    }

    private void znajdzNajwiekszaRoznice() {
        dziesietne = binarne.stream()
                .map(b -> Integer.parseInt(b, 2))
                .toList();

        int maxRoznica = 0;
        for (int i = 0; i < dziesietne.size() - 1; i++) {
            int obecnaRoznica = Math.abs(dziesietne.get(i) - dziesietne.get(i+1));
            if (maxRoznica < obecnaRoznica)
                maxRoznica = obecnaRoznica;
        }
        System.out.println(Integer.toBinaryString(maxRoznica));
    }

    private void znajdzMaksimumAnagramow() {
        long maxKombiacje = binarne.stream()
                .filter(l -> l.length() == 8)
                .map(this::policzKombinacje)
                .max(Long::compare)
                .orElse(-1L);

        binarne.stream()
                .filter(l -> l.length() == 8)
                .filter(b -> policzKombinacje(b) == maxKombiacje)
                .forEach(System.out::println);
    }

    private long policzKombinacje(String binarna){
        long iloscJedynek = policzJedynki(binarna) - 1;
        int dlugosc = binarna.length() - 1;
        // -1 bo ignorujemy pierwsza cyfre

        return silnia(dlugosc) / silnia(iloscJedynek) / silnia(dlugosc - iloscJedynek);
    }

    private long silnia(long i){
        if (i == 1){
            return 1;
        }
        return i * silnia(i-1);
    }

    private void policzZrownowazone() {
        long zrownowazone = binarne.stream()
                .filter(l -> policzJedynki(l) == policzZera(l))
                .count();
        System.out.println(zrownowazone);
        long prawieZrownowazone = binarne.stream()
                .filter(l -> Math.abs(policzZera(l) - policzJedynki(l)) == 1)
                .count();
        System.out.println(prawieZrownowazone);
    }

    private long policzZera(String binarna){
        return binarna.chars()
                .filter(l -> l == '0')
                .count();
    }
    private long policzJedynki(String binarna){
        return binarna.chars()
                .filter(l -> l == '1')
                .count();
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("DANE/DANE_M/anagram.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (s.hasNextLine()){
            String binarna = s.nextLine();
            binarne.add(binarna);
        }
    }
}
