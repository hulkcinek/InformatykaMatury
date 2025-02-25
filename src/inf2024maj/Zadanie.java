package inf2024maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadanie {

    List<Integer> liczbyPierwsze = new ArrayList<>();
    List<Long> duzeLiczby = new ArrayList<>();

    public void start(){
        odczytaj();
        policzDzielniki();
        znajdz101liczbe();
        zadanie3();
        znajdzNajwiekszySpojnyFragment();
    }

    private void znajdzNajwiekszySpojnyFragment() {
        System.out.println();
        double maxSrednia = 0;
        int indexMaxPoczetek = 0;
        int dlugoscMaxa = 0;

        int n = liczbyPierwsze.size();

        int[] prefixSuma = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSuma[i+1] = prefixSuma[i] + liczbyPierwsze.get(i);
        }

        for (int dlugosc = 50; dlugosc < n; dlugosc++) {
            for (int start = 0; start < n - dlugosc; start++) {
                int obecnaSuma = prefixSuma[start + dlugosc] - prefixSuma[start];
                double obecnaSrednia = 1.0*obecnaSuma / dlugosc;
                if (obecnaSrednia > maxSrednia){
                    maxSrednia = obecnaSrednia;
                    indexMaxPoczetek = start;
                    dlugoscMaxa = dlugosc;
                }
            }
        }

        System.out.println(maxSrednia + " " + dlugoscMaxa + " " + liczbyPierwsze.get(indexMaxPoczetek));

    }

    private void zadanie3(){
        System.out.println();
        duzeLiczby.stream()
                .filter(this::czyMoznaRozbic)
                .forEach(System.out::println);
    }

    private boolean czyMoznaRozbic(long liczba){
        for (int dzielnik : liczbyPierwsze){
            if (liczba == 1) return true;
            if(liczba % dzielnik == 0){
                liczba /=dzielnik;
            }
        }
        return false;
    }

    private void znajdz101liczbe() {
        int liczba = liczbyPierwsze.stream()
                .sorted()
                .toList()
                .reversed()
                .get(101);
        System.out.println(liczba);
    }

    private void policzDzielniki() {
        long wynik = liczbyPierwsze.stream()
                .filter(this::czyJestDzielnikiem)
                .count();
        System.out.println(wynik);
    }

    private boolean czyJestDzielnikiem(int i){
        return duzeLiczby.stream()
                .anyMatch(l -> l % i == 0);

    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("Dane-NF-2405/liczby.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line1 = s.nextLine();
        String line2 = s.nextLine();

        s = new Scanner(line1);
        while (s.hasNextInt()){
            liczbyPierwsze.add(s.nextInt());
        }
        s = new Scanner(line2);
        while (s.hasNextLong()){
            duzeLiczby.add(s.nextLong());
        }
    }
}
