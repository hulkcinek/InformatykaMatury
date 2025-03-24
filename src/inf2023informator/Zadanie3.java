package inf2023informator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Zadanie3 {

    List<Przedzial> przedzialy = new ArrayList<>();

    public void start() {
        odczytaj();
        znajdzNajkrotszePrzedzialy();
        znajdzNajczestszaDlugosc();
        znajdzNajdluzszyLancuch();
    }

    private void znajdzNajdluzszyLancuch() {
        int maxDlugosc = szukaj(0, new ArrayList<>());
        System.out.println(maxDlugosc);
    }

    private int szukaj(int obecnaDlugosc, ArrayList<Przedzial> uzyte) {
        List<Integer> dlugosci = new ArrayList<>();
        for (Przedzial przedzial : przedzialy) {
            if (uzyte.contains(przedzial)) continue;
            
            if (uzyte.isEmpty() || uzyte.getLast().czyZawiera(przedzial)) {
                uzyte.add(przedzial);
                dlugosci.add(szukaj(obecnaDlugosc + 1, uzyte));
                uzyte.removeLast();
            }
        }
        return dlugosci.stream()
                .max(Integer::compareTo)
                .orElse(-1);
    }

    private void znajdzNajczestszaDlugosc() {
        Map<Integer, Long> mapa = przedzialy.stream()
                .map(Przedzial::getDlugosc)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        long max = mapa.values().stream()
                .max(Long::compareTo)
                .orElse(-1L);
        Map.Entry<Integer, Long> entry = mapa.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .max(Map.Entry.comparingByKey())
                .orElse(null);

        assert entry != null;
        System.out.println("Dlugosc: " + entry.getKey());
        System.out.println("Krotnosc: " + entry.getValue());
    }

    private void znajdzNajkrotszePrzedzialy() {
        int najmniejszy = Integer.MAX_VALUE;
        int mniejszy = Integer.MAX_VALUE;
        for (int dlugosc : przedzialy.stream().map(Przedzial::getDlugosc).toList()){
            if (dlugosc < najmniejszy){
                mniejszy = najmniejszy;
                najmniejszy = dlugosc;
            }else if (dlugosc < mniejszy){
                mniejszy = dlugosc;
            }
        }
        System.out.println(mniejszy + " " + najmniejszy);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2023informator/dane3.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();

        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            sb.append(line).append("\n");
        }

        Pattern pattern = Pattern.compile("(-?\\d+) (-?\\d+)");
        Matcher matcher = pattern.matcher(sb.toString());

        while (matcher.find()){
            int poczatek = Integer.parseInt(matcher.group(1));
            int koniec = Integer.parseInt(matcher.group(2));
            przedzialy.add(new Przedzial(poczatek, koniec));
        }

    }


}

class Przedzial{
    private final int poczatek;
    private final int koniec;

    public Przedzial(int poczatek, int koniec) {
        this.poczatek = poczatek;
        this.koniec = koniec;
    }

    public int getPoczatek() {
        return poczatek;
    }

    public int getKoniec() {
        return koniec;
    }

    public int getDlugosc(){
        return koniec - poczatek + 1;
    }

    public boolean czyZawiera(Przedzial p){
        return p.getPoczatek() <= poczatek && koniec <= p.getKoniec();
    }

    @Override
    public String toString() {
        return "[" + poczatek + ", " + koniec + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Przedzial przedzial = (Przedzial) o;
        return poczatek == przedzial.poczatek && koniec == przedzial.koniec;
    }

    @Override
    public int hashCode() {
        return Objects.hash(poczatek, koniec);
    }
}