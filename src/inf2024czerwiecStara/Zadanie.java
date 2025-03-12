package inf2024czerwiecStara;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Zadanie {

    List<String> slowa = new ArrayList<>();

    public void start() {
        odczytaj();
        znajdzTrojliteroweFragmenty();
        znajdzPiecioliteroweFragmenty();
        znajdzSlowaZCiekawaWlasnoscia();
        znajdzLiteryWystepujaceWPrzynajmniejPolowie();
    }

    private void znajdzLiteryWystepujaceWPrzynajmniejPolowie() {
        slowa.stream()
                .filter(slowo -> {
                    Map<Character, Long> mapa = slowo.chars()
                            .mapToObj(c -> (char) c)
                            .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

                    Long max = mapa.values().stream()
                            .max(Long::compareTo)
                            .orElse(-1L);

                    return (max * 1.0) > (slowo.length() / 2.0);
                })
                .forEach(System.out::println);
    }

    private void znajdzSlowaZCiekawaWlasnoscia() {
        List<String> spelniajaceWarunek = slowa.stream()
                .filter(slowo -> {
                    String zakodowaneIOdwrocone = odwroc(zakodujROT13(slowo));
                    return slowo.equals(zakodowaneIOdwrocone);
                })
                .toList();
        int maxDlugosc = spelniajaceWarunek.stream()
                .map(String::length)
                .max(Integer::compareTo)
                .orElse(-1);
        String najdluzsze = spelniajaceWarunek.stream()
                .filter(slowo -> slowo.length() == maxDlugosc)
                .toList()
                .getFirst();
        System.out.println("Ilosc spelniajacych: " + spelniajaceWarunek.size());
        System.out.println("Najdluzsze spelniajace: " + najdluzsze);
    }

    private String odwroc(String s){
         StringBuilder sb = new StringBuilder(s);
         return sb.reverse().toString();
    }

    private String zakodujROT13(String s){
        char[] litery = s.toCharArray();
        for (int i = 0; i < litery.length; i++) {
            litery[i] += 13;
            if (litery[i] > 'z') litery[i] -= ('z' - 'a' + 1);
        }
        return String.valueOf(litery);
    }

    private void znajdzPiecioliteroweFragmenty() {
        List<String> fragmenty = new ArrayList<>();
        for (String slowo : slowa) {
            for (int i = 0; i < slowo.length()-4; i++) {
                String fragment = slowo.substring(i, i + 5);
                if (fragment.charAt(0) == 'e' && fragment.charAt(4) == 'e'){
                    fragmenty.add(fragment);
                }
            }
        }
        System.out.println(fragmenty.size());
        fragmenty.forEach(System.out::println);
    }

    private void znajdzTrojliteroweFragmenty() {
        long wynik = slowa.stream()
                .filter(slowo -> slowo.matches(".*k.t.*"))
                .count();
        System.out.println(wynik);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2024czerwiecStara/slowa.txt"));
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
