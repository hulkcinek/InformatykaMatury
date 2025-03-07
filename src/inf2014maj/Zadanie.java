package inf2014maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Zadanie {

    List<String> napisy = new ArrayList<>();

    public void start() {
        odczytaj();
        zliczNapisyPierwszy();
        zliczNapisyRosnace();
        wypiszPowtarzajaceSie();
    }

    private void wypiszPowtarzajaceSie() {
        Map<String, Long> mapa = napisy.stream()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        mapa.entrySet().stream()
                .filter(l -> l.getValue() >= 2)
                .forEach(l -> System.out.println(l.getKey() + " popularnosc: " + l.getValue()));
    }

    private void zliczNapisyRosnace() {
        System.out.println("Rosnace:");
        napisy.stream()
                .filter(napis -> {
                    char[] litery = napis.toCharArray();
                    for (int i = 1; i < litery.length; i++) {
                        if (litery[i-1] < litery[i]) continue;
                        return false;
                    }
                    return true;
                })
                .forEach(System.out::println);

    }

    private void zliczNapisyPierwszy() {
        long wynik = napisy.stream()
                .map(l -> l.chars().sum())
                .filter(this::czyPierwsza)
                .count();
        System.out.println(wynik);
    }

    private boolean czyPierwsza(int a){
        for (int i = 2; i <= Math.sqrt(a); i++) {
            if (a % i == 0) return false;
        }
        return true;
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2014maj/NAPIS.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(s.hasNextLine()){
            String napis = s.nextLine();
            if (napis.isEmpty()) continue;
            napisy.add(napis);
        }
    }
}
