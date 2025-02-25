package inf2024czerwiec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@SuppressWarnings("ALL")
public class Zadanie {

    List<Integer> odbiorcy = new ArrayList<>();

    public void start() {
        odczytaj();
        znajdzBrakujacych();
        rozgrywajRundy();
    }

    private void rozgrywajRundy() {
        List<Set<Integer>> komputery = new ArrayList<>();
        int n = odbiorcy.size();

        for (int i = 0; i < n; i++) {
            Set<Integer> poczatkowe = new HashSet<>();
            poczatkowe.add(i+1);
            komputery.add(poczatkowe);
        }

        int runda = 0;
        boolean czyWrocil = false;
        while(!czyWrocil){
            runda++;
            List<Set<Integer>> nastepnaRudna = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                nastepnaRudna.add(new HashSet<>());
            }

            for (int i = 0; i < n; i++) {
                int index = odbiorcy.get(i) - 1;
                nastepnaRudna.get(index).addAll(komputery.get(i));
            }

            /*for (int i = 0; i < n; i++) {
                if (nastepnaRudna.get(i).contains(i+1)){
                    System.out.println("Nr pakietu " + (i+1) + " po rundach: " + runda);
                    czyWrocil = true;
                    break;
                }
            }*/


            if (runda == 1 || runda == 2 || runda == 4 || runda == 8) {
                int max =nastepnaRudna.stream()
                        .map(a -> a.size())
                        .max(Integer::compareTo)
                        .orElse(-1);
                System.out.println(max);
            }

            if (runda == 8) czyWrocil = true;

                komputery = nastepnaRudna;
        }
    }

    private void znajdzBrakujacych() {
        List<Integer> brakujace = new ArrayList<>();
        for (int i = 1; i <= odbiorcy.size(); i++) {
            if (!odbiorcy.contains(i))
                brakujace.add(i);
        }
        System.out.println(brakujace.size());
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("src/Dane/dane2024czerwiec/odbiorcy.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextInt()){
            odbiorcy.add(s.nextInt());
        }
    }
}
