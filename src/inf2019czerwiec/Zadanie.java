package inf2019czerwiec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadanie {

    List<Integer> liczby = new ArrayList<>();
    List<Integer> pierwsze = new ArrayList<>();

    public void start() {
        odczytaj();
        System.out.println("Zadanie 1:");
        znajdzPierwsze();
        System.out.println("Zadanie 2:");
        znajdzOdKoncaPierwsze();
        System.out.println("Zadanie 3:");
        znajdzWagiRowne1();
    }

    private void znajdzWagiRowne1() {
        long ilosc = pierwsze.stream()
                .map(this::znajdzWage)
                .filter(i -> i == 1)
                .count();
        System.out.println(ilosc);
    }

    private int znajdzWage(int liczba){
        while (String.valueOf(liczba).length() > 1){
            liczba = sumaCyfr(liczba);
        }
        return liczba;
    }

    private int sumaCyfr(int i){
        int suma = 0;
        while (i > 0){
            suma += i % 10;
            i /= 10;
        }
        return suma;
    }

    private void znajdzOdKoncaPierwsze() {
        pierwsze.stream()
                .filter(l -> czyPierwsza(odwrocLiczbe(l)))
                .forEach(System.out::println);
    }

    private int odwrocLiczbe(int liczba){
        StringBuilder sb = new StringBuilder(String.valueOf(liczba));
        sb.reverse();
        return Integer.parseInt(sb.toString());
    }

    private void znajdzPierwsze() {
        liczby.stream()
                .filter(l -> 100 <= l && l <= 5000)
                .filter(this::czyPierwsza)
                .forEach(System.out::println);
    }

    private boolean czyPierwsza(int liczba){
        for (int i = 2; i <= Math.sqrt(liczba); i++) {
            if (liczba % i == 0) return false;
        }
        return true;
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2019czerwiec/liczby.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (s.hasNextInt()){
            liczby.add(s.nextInt());
        }


        try {
            s = new Scanner(new File("src/Dane/dane2019czerwiec/pierwsze.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (s.hasNextInt()){
            pierwsze.add(s.nextInt());
        }
    }
}
