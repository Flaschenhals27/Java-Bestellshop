/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_bestellshop;

import java.util.ArrayList;

/**
 *
 * @author fz-246461
 */
public class Buch extends Produkt{
    private String ISBN;
    private String autor;
    private static double gewicht=0.15;

    
    public Buch(String ISBN, String autor, String name, double preis, int lagerbestand) {
        super(name, preis, lagerbestand);
        this.ISBN = ISBN;
        this.autor = autor;
    }
    
    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String getObjectDetails() {
        return "  ISBN: " + this.ISBN + "  Autor: " + this.autor + "  Name: " + this.name + "  Preis: " + this.preis + "  Lagerbestand: " + this.lagerbestand;
    }
    
    @Override
    public boolean matches(String criteria) {
        return this.name.toLowerCase().replace(" ", "").equals(criteria.toLowerCase().replace(" ", ""));
    }
    
    public String getISBN() {
        return ISBN;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public int getId() {
        return this.id;
    }
}
