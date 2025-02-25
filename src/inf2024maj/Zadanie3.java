package inf2024maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadanie3 {

    List<Integer> liczby = new ArrayList<>();

    public void start() {
        odczytaj();
        wypiszOdpowiedzi();
    }

    private void wypiszOdpowiedzi() {
        liczby.stream()
                .filter(l -> {
                    int skrot = obliczNieparzystySkrot(l);
                    return nwd(l, skrot) == 7;
                })
                .forEach(System.out::println);
    }

    private int nwd(int a, int b){
        if (b == 0) return a;
        return nwd(b, a%b);
    }

    private int obliczNieparzystySkrot(int i){
        int wynikOdwrocony = 0;
        while (i > 0){
            int cyfra = i % 10;
            i /=10;
            if (cyfra % 2 == 1) {
                wynikOdwrocony = wynikOdwrocony*10 + cyfra;
            }
        }
        int wynik = 0;
        while (wynikOdwrocony > 0){
            wynik = wynik*10 + wynikOdwrocony%10;
            wynikOdwrocony /= 10;
        }
        return wynik;
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("Dane-NF-2405/skrot2.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(s.hasNextInt()){
            liczby.add(s.nextInt());
        }
    }
}
