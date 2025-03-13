package inf2024majStara;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Zadanie {

    char[][] plansza;

    public void start() {
        odczytaj();
        znajdzMozliweMiejscaNaJednomasztowce();
        policzSymetryczne();
        policzDwumasztowce();
        policzStatkiNaPrzekatnych();
    }

    private void policzStatkiNaPrzekatnych() {
        int jednomasztowce = 0;
        int dwumasztowce = 0;
        for (int i = 0; i < plansza.length; i++) {
            if (czyToJednomasztowiec(i, i)) jednomasztowce++;
            else if (czyToDwumasztowiec(i, i)) dwumasztowce++;
        }
        for (int y = 0; y < plansza.length; y++) {
            int x = plansza.length - 1 - y;
            if (czyToJednomasztowiec(y, x)) jednomasztowce++;
            else if (czyToDwumasztowiec(y, x)) dwumasztowce++;
        }

        //TODO edge-case statek na srodku zliczany dwa razy
        System.out.println("Jednomasztowce: " + jednomasztowce);
        System.out.println("Dwumasztowce: " + dwumasztowce);
    }

    private void policzDwumasztowce() {
        int licznik = 0;
        for (int y = 0; y < plansza.length; y++) {
            for (int x = 0; x < plansza[0].length; x++) {
                if (czyToDwumasztowiec(y, x))
                    licznik++;
            }
        }
        licznik /= 2;
        System.out.println(licznik);
    }

    private boolean czyToDwumasztowiec(int y, int x){
        if (plansza[y][x] == '0') return false;

        int[] dY = {-1, 1, 0, 0};
        int[] dX = {0, 0, -1, 1};
        int znalezioneDookola = 0;
        for (int i = 0; i < dY.length; i++) {
            int nextY = y + dY[i];
            int nextX = x + dX[i];
            if (isValid(nextY, nextX) && plansza[nextY][nextX] == '1'){
                znalezioneDookola++;
            }
        }
        return znalezioneDookola == 1;
    }

    private boolean isValid(int nextY, int nextX) {
        return nextY >= 0 && nextX >= 0 && nextY < plansza.length && nextX < plansza[0].length;
    }

    private void policzSymetryczne() {
        int licznik = 0;
        for (int y = 0; y < plansza.length; y++) {
            for (int x = 0; x < y; x++) {
                //noinspection SuspiciousNameCombination
                if (czyToJednomasztowiec(y, x) && czyToJednomasztowiec(x, y))
                    licznik++;
            }
        }
        System.out.println(licznik);
    }

    private boolean czyToJednomasztowiec(int y, int x){
        if (plansza[y][x] == '0') return false;

        for (int dY = -1; dY <= 1; dY++) {
            for (int dX = -1; dX <= 1; dX++) {
                if (dY == 0 && dX == 0) continue;
                int nextY = y + dY;
                int nextX = x + dX;
                if (isValid(nextY, nextX) && plansza[nextY][nextX] == '1') return false;
            }
        }
        return true;
    }

    private void znajdzMozliweMiejscaNaJednomasztowce() {
        int licznik = 0;
        for (int y = 0; y < plansza.length; y++) {
            for (int x = 0; x < plansza[0].length; x++) {
                if (czyMoznaPostawicJednomasztowiec(y, x))
                    licznik++;
            }
        }
        System.out.println(licznik);
    }

    private boolean czyMoznaPostawicJednomasztowiec(int y, int x) {
        for (int dY = -1; dY <= 1; dY++) {
            for (int dX = -1; dX <= 1; dX++) {
                int nextY = y + dY;
                int nextX = x + dX;
                if (isValid(nextY, nextX) && plansza[nextY][nextX] == '1') return false;
            }
        }
        return true;
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2024majStara/plansza.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<char[]> temp = new ArrayList<>();
        while (s.hasNextLine()){
            String line = s.nextLine();
            temp.add(line.replace(" ", "").toCharArray());
        }
        plansza = temp.toArray(new char[0][]);
    }
}
