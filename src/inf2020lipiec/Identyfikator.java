package inf2020lipiec;

public class Identyfikator {
    String seria;
    String numer;
    int numerInt;
    int cyfraKontrolna;

    public Identyfikator(String s) {
        this.seria = s.substring(0, 3);
        this.numer = s.substring(3);
        this.numerInt = Integer.parseInt(this.numer);
        this.cyfraKontrolna = Character.getNumericValue(numer.charAt(0));
    }

    public boolean czyPoprawny(){
        int suma = 0;
        suma += zmienLitereNaWartosc(seria.charAt(0)) * 7;
        suma += zmienLitereNaWartosc(seria.charAt(1)) * 3;
        suma += zmienLitereNaWartosc(seria.charAt(2)); // * 1

        suma += Character.getNumericValue(numer.charAt(1)) * 7;
        suma += Character.getNumericValue(numer.charAt(2)) * 3;
        suma += Character.getNumericValue(numer.charAt(3));// * 1
        suma += Character.getNumericValue(numer.charAt(4)) * 7;
        suma += Character.getNumericValue(numer.charAt(5)) * 3;

        return suma % 10 == cyfraKontrolna;
    }

    private int zmienLitereNaWartosc(char c){
        return c - 'A' + 10;
    }

    @Override
    public String toString() {
        return seria + numer;
    }
}
