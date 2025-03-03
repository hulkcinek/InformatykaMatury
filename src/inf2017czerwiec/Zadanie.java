package inf2017czerwiec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Zadanie {

    List<Punkt> punkty = new ArrayList<>();

    public void start() {
        odczytaj();
        policzWspolrzednePierwsze();
        policzCyfropodobne();
        znajdzNajwiekszaOdleglosc();
        policzLokalizacjePunktow();
    }

    private void policzLokalizacjePunktow() {
        Map<Punkt.Polozenie, Long> mapa = punkty.stream()
                .collect(Collectors.groupingBy(Punkt::getPolozenie, Collectors.counting()));

        System.out.println(mapa);
    }

    private void znajdzNajwiekszaOdleglosc() {
        int index1 = 0;
        int index2 = 0;
        long maxOdleglosc = 0;
        for (int i = 0; i < punkty.size() - 1; i++) {
            for (int j = i + 1; j < punkty.size(); j++) {
                long odleglosc = punkty.get(i).znajdzOdleglosc(punkty.get(j));
                if (odleglosc > maxOdleglosc){
                    maxOdleglosc = odleglosc;
                    index1 = i;
                    index2 = j;
                }
            }
        }
        System.out.println("Dla punktow: " + punkty.get(index1) + " i " + punkty.get(index2) + " odleglosc wynosi: " + maxOdleglosc);
    }

    private void policzCyfropodobne() {
        long wynik = punkty.stream()
                .filter(Punkt::czyCyfropodobne)
                .count();
        System.out.println(wynik);
    }

    private void policzWspolrzednePierwsze() {
        long wynik = punkty.stream()
                .filter(p -> czyPierwsza(p.getX()) && czyPierwsza(p.getY()))
                .count();
        System.out.println(wynik);
    }

    private boolean czyPierwsza(int a){
        for (int i = 2; i <= Math.sqrt(a); i++) {
            if (a % i == 0)
                return false;
        }
        return true;
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2017czerwiec/punkty.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()){
            int x = s.nextInt();
            int y = s.nextInt();
            punkty.add(new Punkt(x, y));
        }
    }
}
