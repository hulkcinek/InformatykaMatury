package inf2012maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Zadanie4 {

    List<String> tekstJawny = new ArrayList<>();
    List<String> klucze1 = new ArrayList<>();
    List<String> szyfry = new ArrayList<>();
    List<String> klucze2 = new ArrayList<>();

    public void start() {
        odczytaj();
        zaszyfruj();
        odszyfruj();
    }

    private void odszyfruj() {
        IntStream.range(0, tekstJawny.size())
                .mapToObj(i -> {
                    char[] odszyfrowany = szyfry.get(i).toCharArray();
                    String klucz = klucze2.get(i);
                    for (int j = 0; j < odszyfrowany.length; j++) {
                        int przesuniecie = klucz.charAt(j % klucz.length()) - 'A' + 1;
                        if (odszyfrowany[j] - przesuniecie < 'A')
                            przesuniecie -= 26;
                        odszyfrowany[j] -= (char) przesuniecie;
                    }
                    return String.valueOf(odszyfrowany);
                })
                .forEach(System.out::println);    }

    private void zaszyfruj() {
        IntStream.range(0, tekstJawny.size())
                .mapToObj(i -> {
                    char[] zaszyfrowany = tekstJawny.get(i).toCharArray();
                    String klucz = klucze1.get(i);
                    for (int j = 0; j < zaszyfrowany.length; j++) {
                        int przesuniecie = klucz.charAt(j % klucz.length()) - 'A' + 1;
                        if (zaszyfrowany[j] + przesuniecie > 'Z')
                            przesuniecie -= 26;
                        zaszyfrowany[j] += (char) przesuniecie;
                    }
                    return String.valueOf(zaszyfrowany);
                })
                .forEach(System.out::println);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2012maj/tj.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            tekstJawny.add(line);
        }


        try {
            s = new Scanner(new File("src/Dane/dane2012maj/klucze1.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            klucze1.add(line);
        }

        try {
            s = new Scanner(new File("src/Dane/dane2012maj/sz.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            szyfry.add(line);
        }

        try {
            s = new Scanner(new File("src/Dane/dane2012maj/klucze2.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            klucze2.add(line);
        }
    }
}
