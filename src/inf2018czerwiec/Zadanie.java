package inf2018czerwiec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Zadanie {

    List<List<Integer>> ciagi1 = new ArrayList<>();
    List<List<Integer>> ciagi2 = new ArrayList<>();


    public void start() {
        odczytaj();
        policzIleKonczySieTakSamo();
        znajdzPoRownoParzystychINie();
        znajdzTakieSameCiagi();
        dodajCiagiDoSiebie();
    }

    private void dodajCiagiDoSiebie() {
        IntStream.range(0, ciagi1.size())
                .mapToObj(i -> {
                    List<Integer> ciag = new ArrayList<>(ciagi1.get(i));
                    ciag.addAll(ciagi2.get(i));
                    ciag.sort(Integer::compareTo);
                    return ciag;
                })
                .map(list -> list.stream().map(String::valueOf).toList())
                .map(list -> String.join(" ", list))
                .forEach(System.out::println);
    }

    private void znajdzTakieSameCiagi() {
        List<Integer> indexy = IntStream.range(0, ciagi1.size())
                .filter(i -> {
                    List<Integer> ciag1 = ciagi1.get(i).stream()
                            .distinct()
                            .toList();
                    List<Integer> ciag2 = ciagi2.get(i).stream()
                            .distinct()
                            .toList();
                    return ciag1.equals(ciag2);
                })
                .map(l -> l + 1)
                .boxed()
                .toList();
        System.out.println("Ilosc " + indexy.size() + " \nIndexy: " + indexy);
    }

    private void znajdzPoRownoParzystychINie() {
        long wynik = IntStream.range(0, ciagi1.size())
                .mapToObj(i -> {
                    int parzyste1 = policzParzyste(ciagi1.get(i));
                    int parzyste2 = policzParzyste(ciagi2.get(i));
                    return parzyste1 == 5 && parzyste2 == 5;
                })
                .filter(l -> l)
                .count();
        System.out.println(wynik);
    }

    private int policzParzyste(List<Integer> lista){
        return (int) lista.stream()
                .filter(l -> l % 2 == 0)
                .count();
    }

    private void policzIleKonczySieTakSamo() {
        List<Integer> ostatnie1 = ciagi1.stream()
                .map(List::getLast)
                .toList();
        List<Integer> ostatnie2 = ciagi2.stream()
                .map(List::getLast)
                .toList();
        int ilosc = 0;
        for (int i = 0; i < ostatnie1.size(); i++) {
            if (ostatnie1.get(i).equals(ostatnie2.get(i)))
                ilosc++;
        }
        System.out.println(ilosc);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2018czerwiec/dane1.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            String[] parts = line.split(" ");
            ciagi1.add(Arrays.stream(parts)
                    .map(Integer::parseInt)
                    .toList());
        }
        try {
            s = new Scanner(new File("src/Dane/dane2018czerwiec/dane2.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            String[] parts = line.split(" ");
            ciagi2.add(Arrays.stream(parts)
                    .map(Integer::parseInt)
                    .toList());
        }
    }
}
