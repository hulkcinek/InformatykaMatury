package inf2023maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Zadanie {

    List<Integer> rozwiniecie = new ArrayList<>();

    public void start() {
        odczytaj();
        znajdzFragmenty();
        policzFragmenty();
    }

    private void policzFragmenty() {
        Map<String, Integer> popularnosc = new HashMap<>();
        List<String> fragmenty = new ArrayList<>();
        for (int i = 0; i < rozwiniecie.size() - 1; i++) {
            fragmenty.add(rozwiniecie.get(i) + "" +rozwiniecie.get(i+1));
        }
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                popularnosc.put(i + "" + j, 0);
            }
        }
        for (String s : fragmenty){
            popularnosc.merge(s, 1,Integer::sum);
        }

        int minPopularnosc = popularnosc.values().stream()
                .min(Integer::compareTo)
                .orElse(-1);
        int maxPopularnosc = popularnosc.values().stream()
                .max(Integer::compareTo)
                .orElse(-1);
        Optional<String> minFragment = popularnosc.keySet().stream()
                .filter(f -> popularnosc.get(f) == minPopularnosc)
                .findFirst();

        Optional<String> maxFragment = popularnosc.keySet().stream()
                .filter(f -> popularnosc.get(f) == maxPopularnosc)
                .findFirst();

        if (minFragment.isPresent() && maxFragment.isPresent()) {
            System.out.println("Min ilosc " + minPopularnosc + " dla " + minFragment.get());
            System.out.println("Max ilosc " + maxPopularnosc + " dla " + maxFragment.get());
        }

    }

    private void znajdzFragmenty() {
        List<Integer> fragmenty = new ArrayList<>();
        for (int i = 0; i < rozwiniecie.size() - 1; i++) {
            fragmenty.add(10 * rozwiniecie.get(i) + rozwiniecie.get(i+1));
        }
        long ilosc = fragmenty.stream()
                .filter(i -> i > 90)
                .count();
        System.out.println(ilosc);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2023maj/pi.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextInt()){
            rozwiniecie.add(s.nextInt());
        }
    }
}
