
package java_bestellshop;

import java.util.ArrayList;

public class Kleidung extends Produkt{
    private char groesse;
    private String material;
    private static double gewicht=0.35;
    
    public Kleidung(char groesse, String material, String name, double preis, int lagerbestand) {
        super(name, preis, lagerbestand);
        this.groesse = groesse;
        this.material = material;
    }
    
    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public boolean matches(String criteria) {
        return this.name.toLowerCase().replace(" ", "").equals(criteria.toLowerCase().replace(" ", ""));
    }
    
    public char getGroesse() {
        return groesse;
    }
    
    public String getMaterial() {
        return material;
    }
    
    @Override
    public String getObjectDetails() {
        return "  Groesse: " + this.groesse + "  Material: " + this.material + "  Name: " + this.name + "  Preis: " + this.preis + "  Lagerbestand: " + this.lagerbestand;
    }
}
