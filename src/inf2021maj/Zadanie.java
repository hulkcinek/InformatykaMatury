package inf2021maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Zadanie {

    List<Instrukcja> instrukcje = new ArrayList<>();

    public void start() {
        odczytaj();
        wykonajPolecenia();
        znajdzNajdluzszyCiag();
        znajdzNajczesciejDopisywana();
    }

    private void znajdzNajczesciejDopisywana(){ //TODO omówić i prostsza wersja? + Collectors
        Character najpopularniejsza = instrukcje.stream()
                .filter(instrukcja -> instrukcja.polecenie.equals(Polecenie.DOPISZ))
                .map(a -> a.litera)
                .collect(Collectors.groupingBy( c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse('\0');
        int ilosc = (int) instrukcje.stream()
                .filter(instrukcja -> instrukcja.polecenie.equals(Polecenie.DOPISZ))
                .filter(instrukcja -> instrukcja.litera == najpopularniejsza)
                .count();

        System.out.println(najpopularniejsza + " " + ilosc);
    }

    private void znajdzNajdluzszyCiag() {
        int dlugoscNajdluzszegoCiagu = 0;
        int dlugoscObecnegoCiagu = 1;
        Polecenie typNajdluzszego = instrukcje.getFirst().polecenie;
        for (int i = 1; i < instrukcje.size(); i++) {

            Instrukcja poprzednia = instrukcje.get(i - 1);
            Instrukcja teraz = instrukcje.get(i);

            if (poprzednia.polecenie.equals(teraz.polecenie)){
                dlugoscObecnegoCiagu++;
            }else {
                if (dlugoscObecnegoCiagu > dlugoscNajdluzszegoCiagu){
                    dlugoscNajdluzszegoCiagu = dlugoscObecnegoCiagu;
                    typNajdluzszego = poprzednia.polecenie;
                }
                dlugoscObecnegoCiagu = 1;
            }
        }
        System.out.println(typNajdluzszego + " " + dlugoscNajdluzszegoCiagu);
    }

/*    private void test() {
        int length = instrukcje.stream()
                .mapToInt(inst ->
                        switch (inst.polecenie) {
                            case DOPISZ -> 1;
                            case USUN -> -1;
                            default -> 0;
                })
                .sum();
        System.out.println(length);
    }*/

    private void wykonajPolecenia(){
        StringBuilder wynik = new StringBuilder();
        for (Instrukcja instrukcja : instrukcje){
            switch (instrukcja.polecenie){
                case DOPISZ:
                    wynik.append(instrukcja.litera);
                    break;
                case ZMIEN:
                    int dlugosc = wynik.length()-1;
                    wynik.replace(dlugosc, dlugosc+1, instrukcja.litera + "");
                    break;
                case USUN:
                    wynik.deleteCharAt(wynik.length()-1);
                    break;
                case PRZESUN:
                    int index = wynik.indexOf(instrukcja.litera + "");
                    wynik.replace(index, index+1, przesunOJeden(instrukcja.litera) + "");
            }
        }
        System.out.println(wynik.length());
//        System.out.println(wynik);
    }

    private char przesunOJeden(char x){
        if (x == 'Z') return 'A';
        return (char)(x+1);
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("DANE_2105/instrukcje.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        while(s.hasNextLine()){
            sb.append(s.nextLine()).append("\n");
        }

        Pattern pattern = Pattern.compile("(DOPISZ|USUN|ZMIEN|PRZESUN) (1|.)");
        Matcher matcher = pattern.matcher(sb.toString());

        while(matcher.find()){
            String polecenie = matcher.group(1);
            char litera = matcher.group(2).charAt(0);
            for (Polecenie p : Polecenie.values()){
                if (polecenie.equals(p.tresc)){
                    instrukcje.add(new Instrukcja(p, litera));
                }
            }
        }
    }
}
