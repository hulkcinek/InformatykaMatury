package inf2023informator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadanie2 {

    List<String> wyrazenia = new ArrayList<>();

    public void start(){
        odczytaj();

//        policzGlebokosci();
        sprawdzPoprawnosc();
    }

    private void sprawdzPoprawnosc() {
        wyrazenia.stream()
                .map(w -> czyPoprawne(w) ? "tak" : "nie")
                .forEach(System.out::println);
    }

    private boolean czyPoprawne(String wyrazenie) {
        int obecna = 0;
        for (char c : wyrazenie.toCharArray()){
            switch (c) {
                case '[' -> obecna++;
                case ']' -> obecna--;
            }
            if (obecna < 0) return false;
        }
        return true;
    }

    private void policzGlebokosci() {
        wyrazenia.stream()
                .map(this::obliczGlebokosc)
                .forEach(System.out::println);
    }

    private int obliczGlebokosc(String wyrazenie) {
        int max = 0;
        int obecna = 0;
        for (char c : wyrazenie.toCharArray()){
            switch (c) {
                case '[' -> obecna++;
                case ']' -> obecna--;
            }
            max = Integer.max(obecna, max);
        }
        return max;
    }

    private void odczytaj() {
        Scanner s;
        try {
//            s = new Scanner(new File("src/Dane/dane2023informator/dane2_3.txt"));
            s = new Scanner(new File("src/Dane/dane2023informator/dane2_4.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            wyrazenia.add(line);
        }
    }
}
