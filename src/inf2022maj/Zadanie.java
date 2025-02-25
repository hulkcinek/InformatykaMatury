package inf2022maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Zadanie {

    List<Liczba> liczby = new ArrayList<>();

    public void start() {
        odczytaj();
//        zadanie1();
        zadanie2();
    }

    private void zadanie2() {
        Optional<Integer> max = liczby.stream()
                .map(l -> l.czynnikiPierwsze.size())
                .max(Integer::compareTo);
        Optional<Integer> maxUnikalne = liczby.stream()
                .map(l -> l.unikalneCzynnikiPierwsze.size())
                .max(Integer::compareTo);
        if (max.isPresent()){
            System.out.println(liczby.stream().filter(l -> l.czynnikiPierwsze.size() == max.get()).toList() + " " + max.get());
            System.out.println(liczby.stream().filter(l -> l.unikalneCzynnikiPierwsze.size() == maxUnikalne.get()).toList()  + " " + maxUnikalne.get());
        }
    }

    private void zadanie1() {
        List<Integer> wyciagniete = new ArrayList<>();
        wyciagniete = liczby.stream()
                .map(liczba -> liczba.liczba)
                .toList();
        long ilosc = wyciagniete.stream()
                .filter(l -> String.valueOf(l).charAt(0) == String.valueOf(l%10).charAt(0))
                .count();
        System.out.println(ilosc);
        for(int l : wyciagniete){
            if (String.valueOf(l).charAt(0) == String.valueOf(l%10).charAt(0)) {
                System.out.println(l);
                break;
            }
        }
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("Dane_2205/liczby.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextInt()){
            liczby.add(new Liczba(s.nextInt()));
        }
    }

}
