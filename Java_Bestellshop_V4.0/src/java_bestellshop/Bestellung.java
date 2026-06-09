
package java_bestellshop;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Bestellung {
    Kunde kunde;
    List<Produkt> produkt;
    List<Integer> menge;
    
    public Bestellung(Kunde kunde, List<Produkt> produkt, List<Integer> menge) {
        this.kunde = kunde;
        this.produkt = produkt;
        this.menge = menge;
    }
    
    public static void checkCorrectOrder(Produkt p, int menge) throws ZuVielBestelltException {
        if (p.getLagerbestand() < menge) {
            throw new ZuVielBestelltException("Zu viel Bestellt!");
        }
    }
    
    public void export() {
        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\noobs\\Documents\\NetBeansKlausur\\bestellung.txt", true)) {
            String output = ("-Kundendaten-\n" +"Name/Vorname: " +kunde.getName() +" "+ kunde.getVorname() +"\nPLZ:  "+kunde.getPLZ() + "\nTelefon: " + kunde.getTelefonnummer() + "\nhat bestellt");
            fos.write(output.getBytes());
        } catch (IOException e) {
            System.out.print("Keine Übertragung möglich!");
        }
    }
    
    public Kunde getKunde() {
        return this.kunde;
    }
    public List<Produkt> getProdukt() {
        return this.produkt;
    }
    public List<Integer> getMenge() {
        return this.menge;
    }
}
