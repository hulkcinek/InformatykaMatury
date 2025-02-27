package inf2018maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadanie {

    List<String> sygnaly = new ArrayList<>();

    public void start(){
        odczytaj();
        znajdzUkrytePrzeslanie();
        znajdzSlowoZNajwiekszaLiczbaRoznychLiter();
        znajdzSlowaZMaloOdleglymiLiterami();
    }

    private void znajdzSlowaZMaloOdleglymiLiterami() {
        sygnaly.stream()
                .filter(this::czyMaloOdlegle)
                .forEach(System.out::println);
    }

    private boolean czyMaloOdlegle(String s) {
        char[] litery = s.toCharArray();
        for (int i = 0; i < litery.length; i++) {
            for (int j = 0; j < litery.length; j++) {
                if (i == j) continue;
                if (Math.abs(litery[i] - litery[j]) > 10) return false;
            }
        }
        return true;
    }

    private void znajdzSlowoZNajwiekszaLiczbaRoznychLiter() {
        long max = sygnaly.stream()
                .map(s -> s.chars()
                        .distinct()
                        .count())
                .max(Long::compareTo)
                .orElse(-1L);
        String najroznorodniejszy = sygnaly.stream()
                .filter(s -> s.chars()
                        .distinct()
                        .count() == max)
                .toList()
                .getFirst();
        System.out.println(najroznorodniejszy + ": " + max);
    }

    private void znajdzUkrytePrzeslanie() {
        StringBuilder sb = new StringBuilder();
        for (int i = 39; i < sygnaly.size(); i+=40) {
            sb.append(sygnaly.get(i).charAt(9));
        }
        System.out.println(sb);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2018maj/sygnaly.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (s.hasNextLine()){
            String line = s.nextLine();
            if (!line.isEmpty())
                sygnaly.add(line);
        }
    }
}
