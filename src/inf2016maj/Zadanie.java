package inf2016maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Zadanie {

    List<Punkt> punkty = new ArrayList<>();

    public void start() {
        odczytaj();
        policzPolozenia(punkty);
//        policzPrzyblizeniaPi(1000);
//        policzPrzyblizeniaPi(5000);
//        policzPrzyblizeniaPi(punkty.size());
        obliczDaneDoWykresu();
    }

    private void obliczDaneDoWykresu() {
        for (int i = 0; i < 1700; i++) {
            System.out.println((i + 1) + " " + policzBlad(policzPrzyblizeniaPi(i)));
        }
    }

    private double policzBlad(double pi){
        return Math.abs(Math.PI - pi);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private double policzPrzyblizeniaPi(int dokladnosc) {
        List<Punkt> subList = punkty.subList(0, dokladnosc);
        Map<Polozenie, Long> mapa = policzPolozenia(subList);

        double nalezaceDoKola = 0.0;
        if (mapa.containsKey(Polozenie.W_KOLE))
            nalezaceDoKola +=  policzPolozenia(subList).get(Polozenie.W_KOLE);

        if (mapa.containsKey(Polozenie.NA_KOLE))
            nalezaceDoKola +=  policzPolozenia(subList).get(Polozenie.NA_KOLE);

        double przyblizenie = 4.0 * nalezaceDoKola / dokladnosc;
//        System.out.println("PI dla pierwszych " + dokladnosc + " elementow wynosi:" + przyblizenie);
        return przyblizenie;
    }

    private Map<Polozenie, Long> policzPolozenia(List<Punkt> p) {
        return p.stream()
                .collect(Collectors.groupingBy(Punkt::getPolozenie, Collectors.counting()));
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2016maj/punkty.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()){
            int x = s.nextInt();
            int y = s.nextInt();
            punkty.add(new Punkt(x, y));
        }
    }
}
