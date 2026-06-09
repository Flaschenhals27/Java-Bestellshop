
package java_bestellshop;

import java.util.ArrayList;

public class Elektronik extends Produkt{
    private int garantiezeit;
    private static double gewicht = 0.5;
    
    public Elektronik(int garantiezeit, String name, double preis, int lagerbestand) {
        super(name,preis,lagerbestand);
        this.garantiezeit = garantiezeit;
    }
    
    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    public int getGarantiezeit() {
        return garantiezeit;
    }
    
    @Override
    public boolean matches(String criteria) {
        return this.name.toLowerCase().replace(" ", "").equals(criteria.toLowerCase().replace(" ", ""));
    }
    
    public int getGarantieZeit() {
        return garantiezeit;
    }
    
    @Override
    public String getObjectDetails() {
        return "  Name: " + this.name + "  Preis: " + this.preis + "  Garantiezeit: " + this.garantiezeit + "  Lagerbestand: " + this.lagerbestand;
    }
}
