package inf2017majStara;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadanie {

    List<String> binarne = new ArrayList<>();

    public void start() {
        odczytaj();
        policzDwucykliczne();
        znajdzPoprawne();
    }

    private void znajdzPoprawne() {
        List<List<Integer>> niepoprawneLiczby = binarne.stream()
                /*.map(b -> {
                    int reszta = b.length() % 4;
                    int potrzebneZera = (reszta == 0) ? 0 : (4 - reszta);
                    return "0".repeat(potrzebneZera) + b;
                })*/
                .map(b -> {
                    List<Integer> cyfry = new ArrayList<>();
                    for (int i = 0; i < b.length(); i += 4) {
                        cyfry.add(Integer.parseInt(b.substring(i, i + 4), 2));
                    }
                    return cyfry;
                })
                .filter(cyfry -> cyfry.stream().anyMatch(l -> l > 9))
                .toList();
        Integer najmniejszaDlugosc = niepoprawneLiczby.stream()
                .map(List::size)
                .min(Integer::compareTo)
                .orElse(-1);
        najmniejszaDlugosc *= 4;
        System.out.println(niepoprawneLiczby.size());
        System.out.println(najmniejszaDlugosc);
    }

    private void policzDwucykliczne() {
        List<String> dwucykliczne = binarne.stream()
                .filter(binarna -> {
                    String lewa = binarna.substring(0, binarna.length() / 2);
                    String prawa = binarna.substring(binarna.length() / 2);
                    return lewa.equals(prawa);
                })
                .toList();
        long wynik = dwucykliczne.size();

        Integer maxDlugosc = dwucykliczne.stream()
                .map(String::length)
                .max(Integer::compareTo)
                .orElse(-1);

        String najdluzszy = dwucykliczne.stream()
                .filter(s -> s.length() == maxDlugosc)
                .toList()
                .getFirst();


        System.out.println("Ilosc: " + wynik);
        System.out.println("Max dlugosc: " + maxDlugosc);
        System.out.println("Najdluzszy: " + najdluzszy);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2017majStara/binarne.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            binarne.add(line);
        }
    }
}
