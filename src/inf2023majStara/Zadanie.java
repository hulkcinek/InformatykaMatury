package inf2023majStara;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Zadanie {

    List<String> slowa = new ArrayList<>();

    public void start() {
        odczytaj();
        znajdzWKSlowa();
        policzIleSlowWakacjeMozePowstac();
        zadanie3();
    }

    private void zadanie3() {
        System.out.println("Zadanie 3:");
        slowa.stream()
                .map(this::policzIleTrzebaUsunacByPowstalyWakacyjneSlowa)
                .forEach(System.out::println);
    }

    private int policzIleTrzebaUsunacByPowstalyWakacyjneSlowa(String slowo) {
        final String wakacje = "wakacje";
        int j = 0;
        int doUsuniecia = 0;
        for (int i = 0; i < slowo.length(); i++) {
            if (slowo.charAt(i) == wakacje.charAt(j % wakacje.length())){
                j++;
            }else {
                doUsuniecia++;
            }
        }
        return doUsuniecia + j % wakacje.length();
    }

    private void policzIleSlowWakacjeMozePowstac() {
        slowa.stream()
                .map(slowo -> {
                    Map<Character, Long> mapa = slowo.chars()
                            .mapToObj(c -> (char) c)
                            .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
                    if (mapa.containsKey('w') &&
                            mapa.containsKey('a') &&
                            mapa.containsKey('k') &&
                            mapa.containsKey('c') &&
                            mapa.containsKey('j') &&
                            mapa.containsKey('e')){
                        return Stream.of(mapa.get('w'),
                                mapa.get('a')/2,
                                mapa.get('k'),
                                mapa.get('c'),
                                mapa.get('j'),
                                mapa.get('e'))
                                .min(Long::compareTo)
                                .orElse(-1L);
                    }else {
                        return 0L;
                    }
                })
                .forEach(System.out::println);
    }

    private void znajdzWKSlowa() {
        slowa.stream()
                .filter(slowo -> {
                    long literyW = slowo.chars().filter(l -> l == 'w').count();
                    long literyK = slowo.chars().filter(l -> l == 'k').count();
                    return literyK == literyW;
                })
                .forEach(System.out::println);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2023majStara/slowa.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            slowa.add(line);
        }
    }
}
