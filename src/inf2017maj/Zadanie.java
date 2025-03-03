package inf2017maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Zadanie {

    int[][] piksele;

    public void start() {
        odczytaj();
        znajdzNajjasniejszyINajciemniejszy();
        policzIleUsunacDlaSymetrii();
        policzKontrastujace();
        znajdzNajdluzszaLinie();
    }

    private void znajdzNajdluzszaLinie() {
//        int[] dy = {-1, 0, 0, 1};
//        int[] dx = {0, -1, 1, 0};

        int[] dy = {-1, 1};
        int[] dx = {0, 0};
        int maxDlugosc = -1;

        for (int y = 0; y < piksele.length; y++) {
            for (int x = 0; x < piksele[0].length; x++) {
                for (int i = 0; i < 2; i++){
                    int obecnaDlugosc = 1;
                    int nextY = y + dy[i];
                    int nextX = x + dx[i];
                    while (isValid(nextY, nextX) && piksele[y][x] == piksele[nextY][nextX]){
                        obecnaDlugosc++;
                        nextY += dy[i];
                        nextX += dx[i];
                    }
                    if (maxDlugosc < obecnaDlugosc)
                        maxDlugosc = obecnaDlugosc;
                }
            }
        }
        System.out.println(maxDlugosc);
    }

    private void policzKontrastujace() {
        int wynik = 0;
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};
        for (int y = 0; y < piksele.length; y++) {
            for (int x = 0; x < piksele[0].length; x++) {
                for (int i = 0; i < 4; i++) {
                    int nextY = y + dy[i];
                    int nextX = x + dx[i];
                    if (isValid(nextY, nextX)){
                        if (Math.abs(piksele[y][x] - piksele[nextY][nextX]) > 128){
                            wynik++;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(wynik);
    }

    private boolean isValid(int y, int x){
        return 0 <= y && 0 <= x && y < piksele.length && x < piksele[0].length;
    }

    private void policzIleUsunacDlaSymetrii() {
        long wynik = Arrays.stream(piksele)
                .filter(a -> !czySymetryczny(a))
                .count();
        System.out.println(wynik);
    }

    private boolean czySymetryczny(int[] line){
        int[] odwrocona = Arrays.stream(line)
                .boxed()
                .toList()
                .reversed()
                .stream()
                .mapToInt(i -> i)
                .toArray();
        return Arrays.equals(odwrocona, line);
    }

    private void znajdzNajjasniejszyINajciemniejszy() {
        int max = Arrays.stream(piksele)
                .map(p -> Arrays.stream(p)
                        .boxed()
                        .max(Integer::compareTo)
                        .orElse(Integer.MIN_VALUE))
                .max(Integer::compareTo)
                .orElse(Integer.MIN_VALUE);
        System.out.println("Max: " + max);

        int min = Arrays.stream(piksele)
                .map(p -> Arrays.stream(p)
                        .boxed()
                        .min(Integer::compareTo)
                        .orElse(Integer.MAX_VALUE))
                .min(Integer::compareTo)
                .orElse(Integer.MAX_VALUE);
        System.out.println("Min: " + min);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2017maj/dane.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<int[]> temp = new ArrayList<>();
        while (s.hasNextLine()){
            String[] line = s.nextLine().split(" ");
            if (line.length == 0) continue;

            temp.add(Arrays.stream(line)
                    .mapToInt(Integer::parseInt)
                    .toArray());
        }
        piksele = temp.toArray(new int[0][]);
    }


}
