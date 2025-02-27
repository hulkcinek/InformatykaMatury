package inf2019maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadanie {

    List<Integer> liczby = new ArrayList<>();

    public void start(){
        odczytaj();
        policzPotegi3();
        System.out.println();
        znajdzRowneSumieSilniCyfr();
        System.out.println();
        znajdzNajdluzszyCiagZeWspolnymDzielnikiem();
    }

    private void znajdzNajdluzszyCiagZeWspolnymDzielnikiem() {
        int maxDlugosc = 0;
        int indexPoczatkuMax = -1;
        int dzielnikMax = 0;

        for (int i = 0; i < liczby.size(); i++) {
            int obecnyDzielnik = liczby.get(i);
            for (int j = i; j < liczby.size(); j++) {
                obecnyDzielnik = nwd(obecnyDzielnik, liczby.get(j));
                if (obecnyDzielnik == 1) break;

                int obecnaDlugosc = j - i + 1;
                if (obecnaDlugosc > maxDlugosc){
                    maxDlugosc = obecnaDlugosc;
                    indexPoczatkuMax = i;
                    dzielnikMax = obecnyDzielnik;
                }
            }
        }
        System.out.println("Pierwsza liczba w ciągu: " + liczby.get(indexPoczatkuMax));
        System.out.println("Długość ciągu: " + maxDlugosc);
        System.out.println("Największy wspólny dzielnik: " + dzielnikMax);
    }

    private int nwd(int a, int b){
        if (b == 0)
            return a;
        return nwd(b, a%b);
    }

    private void znajdzRowneSumieSilniCyfr() {
        liczby.stream()
                .filter(l -> l == sumaSilniCyfr(l))
                .forEach(System.out::println);
    }

    private int sumaSilniCyfr(int i){
        int suma = 0;
        while (i>0){
            suma += silnia(i % 10);
            i /= 10;
        }
        return suma;
    }

    private int silnia(int i){
        if (i == 0)
            return 1;
        return i * silnia(i-1);
    }

    private void policzPotegi3() {
        long wynik = liczby.stream()
                .filter(this::czyToPotega3)
                .count();
        System.out.println(wynik);
    }

    private boolean czyToPotega3(int i){
        while (i != 1){
            if (i % 3 == 0)
                i /= 3;
            else
                return false;
        }
        return true;
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2019maj/liczby.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(s.hasNextInt()){
            liczby.add(s.nextInt());
        }

    }
}
