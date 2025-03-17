package inf2018majStara;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Zadanie {

    List<String[]> slowa = new ArrayList<>();

    public void start() {
        odczytaj();
        policzKonczaceSieNaA();
        policzZawierajaceSieWSobie();
        policzAnagramy();
    }

    private void policzAnagramy() {
        slowa.stream()
                .filter(para -> {
                    char[] slowo1 = para[0].toCharArray();
                    char[] slowo2 = para[1].toCharArray();

                    Arrays.sort(slowo1);
                    Arrays.sort(slowo2);
                    return Arrays.equals(slowo1, slowo2);
                })
                .map(para -> para[0] + " " + para[1])
                .forEach(System.out::println);
        long wynik = slowa.stream()
                .filter(para -> {
                    char[] slowo1 = para[0].toCharArray();
                    char[] slowo2 = para[1].toCharArray();

                    Arrays.sort(slowo1);
                    Arrays.sort(slowo2);
                    return Arrays.equals(slowo1, slowo2);
                })
                .count();
        System.out.println(wynik);
    }

    private void policzZawierajaceSieWSobie() {
        long wynik = slowa.stream()
                .filter(para -> {
                    String slowo1 = para[0];
                    String slowo2 = para[1];
                    for (int i = 0; i < slowo2.length() - slowo1.length() + 1; i++) {
                        if (slowo2.startsWith(slowo1, i))
                            return true;
                    }
                    return false;
                })
                .count();
        System.out.println(wynik);
    }

    private void policzKonczaceSieNaA() {
        long wynik = slowa.stream()
                .flatMap(Arrays::stream)
                .filter(slowo -> slowo.charAt(slowo.length() - 1) == 'A')
                .count();
        System.out.println(wynik);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2018majStara/slowa.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            slowa.add(line.split(" "));
        }
    }
}
