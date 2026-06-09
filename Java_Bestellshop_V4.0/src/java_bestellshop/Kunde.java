
package java_bestellshop;

public class Kunde {
    private String name;
    private String vorname;
    private int telefonnummer;
    private int PLZ;
    
    public Kunde(String name, String vorname, int telefonnummer, int PLZ) {
        this.name = name;
        this.vorname = vorname;
        this.telefonnummer = telefonnummer;
        this.PLZ = PLZ;
    }    
    
    public String getName() {
        return name;
    }
    
    public String getVorname() {
        return vorname;
    }
    
    public int getTelefonnummer() {
        return telefonnummer;
    }
    
    public int getPLZ() {
        return PLZ;
    }    
}
