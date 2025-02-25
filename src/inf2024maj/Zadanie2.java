package inf2024maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadanie2 {

    List<Integer> liczby = new ArrayList<>();

    public void start() {
        odczytaj();
        policzLiczbyBezSkrotu();
    }

    private void policzLiczbyBezSkrotu() {
        long wynik = liczby.stream()
                .filter(l -> {
                    while (l>0){
                        int cyfra = l%10;
                        l/=10;
                        if (cyfra % 2 == 1){
                            return false;
                        }
                    }
                    return true;
                })
                .count();
        int max = liczby.stream()
                .filter(l -> {
                    while (l>0){
                        int cyfra = l%10;
                        l/=10;
                        if (cyfra % 2 == 1){
                            return false;
                        }
                    }
                    return true;
                })
                .max(Integer::compareTo)
                .orElse(-1);
        System.out.println(wynik + " " + max);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("Dane-NF-2405/skrot.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(s.hasNextInt()){
            liczby.add(s.nextInt());
        }


    }
}
