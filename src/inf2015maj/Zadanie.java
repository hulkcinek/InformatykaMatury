package inf2015maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Zadanie {

    List<String> liczby = new ArrayList<>();

    public void start() {
        odczytaj();
        zliczTeZWiekszaLiczbaZerNizJedynek();
        znajdzPodzielnePrzezDwaLubOsiem();
        znajdzMinIMax();
    }

    private void znajdzMinIMax() {
        String minBinarna = liczby.stream()
                .min(this::porownajLiczbyBinarne)
                .orElse("");

        String maxBinarna = liczby.stream()
                .max(this::porownajLiczbyBinarne)
                .orElse("");


        int minIndex = IntStream.range(0, liczby.size())
                .filter(i -> liczby.get(i).equals(minBinarna))
                .boxed()
                .toList()
                .getFirst() + 1;

        int maxIndex = IntStream.range(0, liczby.size())
                .filter(i -> liczby.get(i).equals(maxBinarna))
                .boxed()
                .toList()
                .getFirst() + 1;

        System.out.println("Index min: " + minIndex);
        System.out.println("Index max: " + maxIndex);
    }

    public int porownajLiczbyBinarne(String o1, String o2) {
        if (o1.length() != o2.length())
            return o1.length() - o2.length();

        for (int i = 0; i < o1.length(); i++) {
            if (o1.charAt(i) == o2.charAt(i))
                continue;
            return o1.charAt(i) - o2.charAt(i);
        }
        return 0;
    }

    private void znajdzPodzielnePrzezDwaLubOsiem() {
        long podzielnePrzez2 = liczby.stream()
                .filter(l -> l.charAt(l.length()-1) == '0')
                .count();

        long podzielnePrzez8 = liczby.stream()
                .map(l -> l.substring(l.length()-3))
                .filter(l -> l.equals("000"))
                .count();

        System.out.println("podzielne przez 2: " + podzielnePrzez2);
        System.out.println("podzielne przez 8: " + podzielnePrzez8);
    }

    private void zliczTeZWiekszaLiczbaZerNizJedynek() {
        long wynik = liczby.stream()
                .filter(l -> {
                    long liczbaZer = l.chars().filter(a -> a == '0').count();
                    long liczbaJedynek = l.chars().filter(a -> a == '1').count();
                    return liczbaZer > liczbaJedynek;
                })
                .count();
        System.out.println(wynik);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2015maj/liczby.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(s.hasNextLine()){
            String liczba = s.nextLine();
            if (liczba.isEmpty()) continue;
            liczby.add(liczba);
        }
    }
}
