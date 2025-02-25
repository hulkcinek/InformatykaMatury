package inf2020czerwiec;

public class Para implements Comparable<Para> {

    int liczba;
    String slowo;
    int skladnikPierwszy;
    String najdluzszyFragment = "";

    public Para(int liczba, String tekst) {
        this.liczba = liczba;
        this.slowo = tekst;
        if (liczba % 2 == 0) {
            skladnikPierwszy = znajdzSkladniki();
        }
        znajdzNajdluzszyFragment();
    }

    private void znajdzNajdluzszyFragment() {//TODO jeden ciag wychodzi pusty
        int indexPoczatku = 0;
        for (int i = 1; i < slowo.length(); i++) {
            if (slowo.charAt(i-1) != slowo.charAt(i)){
                String obecnyCiag = slowo.substring(indexPoczatku, i);
                if (najdluzszyFragment.length() < obecnyCiag.length()){
                    najdluzszyFragment = obecnyCiag;
                }
                indexPoczatku = i;
            }
            if (i == slowo.length()-1){
                String obecnyCiag = slowo.substring(indexPoczatku, i);
                if (najdluzszyFragment.length() < obecnyCiag.length()){
                    najdluzszyFragment = obecnyCiag;
                }
            }
        }
    }

    private int znajdzSkladniki() {
        for (int i = 2; i < liczba; i++) {
            if (czyPierwsza(i) && czyPierwsza(liczba-i)) return i;
        }
        return -1;
    }

    private boolean czyPierwsza(int a){
        if (a <= 1) return false;

        for (int i = 2; i <= Math.sqrt(a); i++) {
            if (a % i == 0){
                return false;
            }
        }
        return true;
    }

    public int getLiczba() {
        return liczba;
    }

    public String getSlowo() {
        return slowo;
    }

    public int getSkladnikPierwszy() {
        return skladnikPierwszy;
    }

    public String getNajdluzszyFragment() {
        return najdluzszyFragment;
    }

    @Override
    public int compareTo(Para p) {
        if (p.getLiczba() == this.getLiczba()){
            return this.getSlowo().compareTo(p.getSlowo());
        }else {
            return (this.getLiczba() - p.getLiczba());
        }
    }

    @Override
    public String toString() {
        return "Para: {" + liczba + " " + slowo + "}";
    }
}