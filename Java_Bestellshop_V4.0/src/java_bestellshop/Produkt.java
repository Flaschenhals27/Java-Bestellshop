
package java_bestellshop;

import java.util.ArrayList;

public abstract class Produkt{
    protected int id;
    protected String name;
    protected double preis;
    protected int lagerbestand;
    
    public Produkt (String name, double preis, int lagerbestand) {
        this.name = name;
        this.preis = preis;
        this.lagerbestand = lagerbestand;
    }
    
    public abstract void setId(int id);
    public abstract String getObjectDetails();
    public abstract boolean matches(String criteria);
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLagerbestand() {
        return this.lagerbestand;
    }
    public void setLagerbestand(int lagerbestand) {
        this.lagerbestand = lagerbestand;
    }
    public int getId() {
        return this.id;
    }
}
