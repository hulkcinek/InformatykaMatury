package inf2020czerwiec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zadanie {

    List<Para> pary = new ArrayList<>();

    public void start() {
        odczytaj();
//        wypiszSkladowe();
//        wypiszFragmenty();
        znajdzNajmniejszaPare();
    }

    private void znajdzNajmniejszaPare() {
        Optional<Para> najmniejszaPara = pary.stream()
                .filter(p -> p.getLiczba() == p.getSlowo().length())
                .min(Para::compareTo);
        najmniejszaPara.ifPresent(para -> System.out.println(para.getLiczba() + " " + para.getSlowo()));
    }

    private void wypiszFragmenty() {
        pary.forEach(para -> System.out.println(para.getNajdluzszyFragment() + " " + para.getNajdluzszyFragment().length()));
    }

    private void wypiszSkladowe() {
        pary.stream()
                .filter(para -> para.getLiczba() % 2 == 0)
                .forEach(para -> System.out.println(para.getLiczba() + ": "+ para.getSkladnikPierwszy() + " " + (para.getLiczba() - para.getSkladnikPierwszy())));
    }

    private void odczytaj() {
        Scanner s;
        try {
            s = new Scanner(new File("Dane_PR2/pary.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        StringBuilder input = new StringBuilder();
        while (s.hasNextLine()){
            input.append(s.nextLine()).append("\n");
        }

        Pattern pattern = Pattern.compile("(\\d+) (\\w+)");
        Matcher matcher = pattern.matcher(input.toString());
        while(matcher.find()){
            pary.add(new Para(Integer.parseInt(matcher.group(1)), matcher.group(2)));
        }
    }
}
