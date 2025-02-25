package inf2022maj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Liczba {
    int liczba;
    List<Integer> czynnikiPierwsze = new ArrayList<>();
    Set<Integer> unikalneCzynnikiPierwsze;

    public Liczba(int liczba) {
        this.liczba = liczba;
        znajdzCzynniki();
        unikalneCzynnikiPierwsze = new HashSet<>(czynnikiPierwsze);
    }

    private void znajdzCzynniki() {
        int temp = liczba;
        for (int i = 2; i <= liczba; i++) {
//            if (!czyPierwsza(i)) continue;
            while (temp % i == 0){
                czynnikiPierwsze.add(i);
                temp /= i;
            }
        }
    }

    @Override
    public String toString() {
        return liczba + "";
    }
}
