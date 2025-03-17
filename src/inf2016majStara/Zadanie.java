package inf2016majStara;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Zadanie {

    char[][] plansza;
    List<char[][]> generacje = new ArrayList<>();

    public void start() {
        odczytaj();
        wygenerujNastepnePokolenia();

        zadanie1();
        System.out.println(zliczZyweWPokoleniu(1));
        znajdzStanUstabilizowany();
    }

    private void znajdzStanUstabilizowany() {
        List<Long> ilosciZywych = new ArrayList<>();
        for (int i = 0; i < generacje.size(); i++) {
            ilosciZywych.add(zliczZyweWPokoleniu(i));
        }
        for (int i = 0; i < ilosciZywych.size(); i++) {
            if (ilosciZywych.subList(i, ilosciZywych.size()).stream()
                    .distinct()
                    .count() == 1L){
                System.out.println(i+2);
                System.out.println(ilosciZywych.get(i));
                break;
            }
        }
    }

    private long zliczZyweWPokoleniu(int index) {
        long wynik = Arrays.stream(generacje.get(index))
                .flatMapToInt(l -> new String(l).chars())
                .filter(c -> c == 'X')
                .count();
        return wynik;
    }

    private void zadanie1() {
        System.out.println(policzZywe(1, 18, generacje.get(36)));
    }

    private void wygenerujNastepnePokolenia() {
        generacje.add(plansza);
        int height = plansza.length;
        int width = plansza[0].length;

        for (int i = 1; i < 100; i++) {
            char[][] nastepna = new char[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int zyweDookola = policzZywe(y, x, generacje.getLast());
                    char obecna = generacje.getLast()[y][x];
                    if (obecna == 'X' && (zyweDookola == 2 || zyweDookola == 3)){
                        nastepna[y][x] = 'X';
                    }else if (obecna == '.' && zyweDookola == 3){
                        nastepna[y][x] = 'X';
                    }else {
                        nastepna[y][x] = '.';
                    }
                }
            }
            generacje.add(nastepna);
        }
    }

    private void wypisz(char[][] nastepna) {
        for (char[] chars : nastepna) {
            for (char c : chars) {
                System.out.print(chars[c] + " ");
            }
            System.out.println();
        }
        System.out.println("--".repeat(10));
    }

    private int policzZywe(int y, int x, char[][] last) {
        int ilosc = 0;
        for (int dY = -1; dY <= 1; dY++) {
            for (int dX = -1; dX <= 1; dX++) {
                if (dY == 0 && dX == 0) continue;
                int nextY = y + dY;
                int nextX = x + dX;

                int height = last.length;
                int width = last[0].length;

                if (nextY < 0) nextY += height;
                if (nextX < 0) nextX += width;
                if (nextY >= height) nextY -= height;
                if (nextX >= width) nextX -= width;

                if (last[nextY][nextX] == 'X')
                    ilosc++;
            }
        }
        return ilosc;
    }

    private boolean isOutOfBounds(int y, int x) {
        return (0 > y) || (0 > x) || (y >= plansza.length) || (x >= plansza[0].length);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2016majStara/gra.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<char[]> temp = new ArrayList<>();
        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            temp.add(line.toCharArray());
        }
        plansza = temp.toArray(new char[0][]);
    }
}
