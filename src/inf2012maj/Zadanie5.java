package inf2012maj;

import java.util.ArrayList;
import java.util.List;

public class Zadanie5 {

    List<List<Integer>> trojkatPaskala = new ArrayList<>();

    public void start() {
        wypelnijTrojkat();
        znajdzNajwieksze();
        policzCyfryWKazdymWierszu();
        policzWierszeNiepodzielnePrzez5();
        narysujTrojkat();
    }

    private void narysujTrojkat() {
        for (List<Integer> rzad : trojkatPaskala) {
            for (int a : rzad) {
                if (a % 3 == 0)
                    System.out.print("X ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }

    private void policzWierszeNiepodzielnePrzez5() {
        long wynik = trojkatPaskala.stream()
                .filter(rzad -> rzad.stream()
                        .allMatch(l -> l % 5 != 0))
                .count();
        System.out.println(wynik);
    }

    private void policzCyfryWKazdymWierszu() {
        for (int i = 0; i < trojkatPaskala.size(); i++) {
            int iloscCyfr = trojkatPaskala.get(i).stream()
                    .mapToInt(this::ileCyfr)
                    .sum();
            System.out.println("Rzad nr " + (i + 1) + " ilosc cyfr: " + iloscCyfr);
        }
    }

    private int ileCyfr(int a){
        return String.valueOf(a).length();
    }

    private void znajdzNajwieksze() {
        System.out.println(trojkatPaskala.get(9).stream().max(Integer::compareTo).orElse(-1));
        System.out.println(trojkatPaskala.get(19).stream().max(Integer::compareTo).orElse(-1));
        System.out.println(trojkatPaskala.get(29).stream().max(Integer::compareTo).orElse(-1));
    }

    private void wypelnijTrojkat() {
        trojkatPaskala.add(List.of(1));
        trojkatPaskala.add(List.of(1, 1));
        for (int i = 2; i < 30; i++) {
            List<Integer> rzad = new ArrayList<>();
            rzad.add(1);
            List<Integer> poprzedni = trojkatPaskala.get(i - 1);
            for (int j = 0; j < poprzedni.size() - 1; j++) {
                rzad.add(poprzedni.get(j) + poprzedni.get(j + 1));
            }
            rzad.add(1);
            trojkatPaskala.add(rzad);
        }
    }
}
