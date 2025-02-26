package inf2020lipiec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadanie {

    List<Identyfikator> identyfikatory = new ArrayList<>();

    public void start() {
        odczytaj();
        znajdzNajwiekszaSumeCyfr();
        znajdzPalindromy();
        System.out.println("niepoprawne");
        znajdzNiewlasciwe();
    }

    private void znajdzNiewlasciwe() {
        identyfikatory.stream()
                .filter(l -> !l.czyPoprawny())
                .forEach(System.out::println);
    }

    private void znajdzPalindromy() {
        List<Identyfikator> palindromy = identyfikatory.stream()
                .filter(l -> czyToPalindrom(l.seria) || czyToPalindrom(l.numer))
                .toList();
        palindromy.forEach(System.out::println);
    }

    private boolean czyToPalindrom(String s){
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return sb.toString().equals(s);
    }

    private void znajdzNajwiekszaSumeCyfr() {
        int max = identyfikatory.stream()
                .map(Zadanie::sumaCyfr)
                .max(Integer::compareTo)
                .orElse(-1);

        List<Identyfikator> maxIdentyfikator = identyfikatory.stream()
                .filter(l -> sumaCyfr(l) == max)
                .toList();
        System.out.println(maxIdentyfikator);
    }

    private static int sumaCyfr(Identyfikator l) {
        int suma = 0;
        int cyfry = l.numerInt;
        while (cyfry > 0){
            suma += cyfry % 10;
            cyfry /= 10;
        }
        return suma;
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2020lipiec/identyfikator.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()){
            identyfikatory.add(new Identyfikator(s.nextLine()));
        }
    }
}
