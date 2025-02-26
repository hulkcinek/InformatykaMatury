package inf2021czerwiec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Zadanie {

    List<String> napisy = new ArrayList<>();

    public void start(){
        odczytaj();
        policzCyfry();
        odczytajSekretneHaslo();
        znajdzPalindromy();
        znajdzHasloZCyfr();
    }

    private void znajdzHasloZCyfr() {
        List<String> cyfry = napisy.stream()
                .map(s -> s.chars()
                    .filter(c -> '0' <= c && c <= '9')
                    .mapToObj(l -> String.valueOf((char) l))
                    .collect(Collectors.joining()))
                .toList();

        List<Integer> wydzielone = new ArrayList<>();

        for (String line : cyfry){
            for (int i = 1; i < line.length(); i+=2) {
                wydzielone.add(Integer.parseInt(String.valueOf(line.charAt(i-1))+ line.charAt(i)));
            }
        }

        String wynik = wydzielone.stream()
                .filter(l -> 65 <= l && l <= 90)
                .map(l -> (char) l.intValue())
                .map(String::valueOf)
                .collect(Collectors.joining());

        System.out.println(wynik);
    }

    private void znajdzPalindromy() {
        String wynik = napisy.stream()
                .map(this::przeksztalcWPalindrom)
                .filter(l -> !l.isEmpty())
                .map(l -> l.charAt(l.length()/2))
                .map(String::valueOf)
                .collect(Collectors.joining());

        System.out.println(wynik);
    }

    private String przeksztalcWPalindrom(String tekst){
        char pierwsza = tekst.charAt(0);
        if (czyJestPalindromem(tekst + pierwsza)){
            return tekst + pierwsza;
        }
        char ostatnia = tekst.charAt(tekst.length()-1);
        if (czyJestPalindromem(ostatnia + tekst)){
            return ostatnia + tekst;
        }
        return "";
    }

    private boolean czyJestPalindromem(String tekst){
        StringBuilder sb = new StringBuilder();
        sb.append(tekst);
        sb.reverse();
        return sb.toString().equals(tekst);
    }

    private void odczytajSekretneHaslo() {
        StringBuilder wynik = new StringBuilder();
        for (int i = 20; i <= napisy.size(); i+=20) {
            wynik.append(napisy.get(i-1).charAt(i/20-1));
        }
        System.out.println(wynik);
    }

    private void policzCyfry() {
        long wynik = napisy.stream()
                .map(s -> s.chars()
                        .filter(c -> '0' <= c && c <= '9')
                        .count())
                .mapToLong(i -> i)
                .sum();
        System.out.println(wynik);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2021czerwiec/napisy.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()){
            napisy.add(s.nextLine());
        }
    }
}
