package inf2015majStara;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Zadanie {

    List<String> slowa = new ArrayList<>();

    public void start() {
        odczytaj();
        policzMajaceWiecejZer();
        policzSkladajaceSieZBlokow();
        znajdzNajdluzszyBlokZZer();
    }

    private void znajdzNajdluzszyBlokZZer() {
        Integer maxDlugoscBloku = slowa.stream()
                .flatMap(slowo -> Arrays.stream(slowo.split("1")))
                .map(String::length)
                .max(Integer::compareTo)
                .orElse(-1);

        System.out.println(maxDlugoscBloku);
        slowa.stream()
                .filter(slowo -> slowo.contains("0".repeat(maxDlugoscBloku)))
                .forEach(System.out::println);
    }

    private void policzSkladajaceSieZBlokow() {
        long wynik = slowa.stream()
                .filter(slowo -> {
                    if (!slowo.contains("1") || !slowo.contains("0")) return false;

                    boolean znalezionaJedynka = false;
                    for (char c : slowo.toCharArray()) {
                        if (!znalezionaJedynka && c == '1')
                            znalezionaJedynka = true;

                        if (znalezionaJedynka && c == '0')
                            return false;
                    }
                    return true;
                })
                .count();
        System.out.println(wynik);
    }

    private void policzMajaceWiecejZer() {
        long wynik = slowa.stream()
                .filter(slowo -> {
                    int iloscZer = slowo.replace("1", "").length();
                    int iloscJedynek = slowo.replace("0", "").length();
                    return iloscZer > iloscJedynek;
                })
                .count();
        System.out.println(wynik);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2015majStara/slowa.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            slowa.add(line);
        }
    }
}
