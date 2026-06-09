
package java_bestellshop;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class Java_Bestellshop {

    ArrayList<Produkt> katalog = new ArrayList();
    static String Pfad;
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Pfad = PfadManager.readPath();
        Java_Bestellshop obj = new Java_Bestellshop();
        
        System.out.println("Willkommen im Java-Shop");
        System.out.println("-----------------------");
        System.out.println("Sind Sie zum ersten mal hier?");
        String eingabe = scan.nextLine();
        if (eingabe.equalsIgnoreCase("ja") || eingabe.equalsIgnoreCase("j")) printHilfe();
        
        System.out.println("Schauen Sie zum bestellen gerne in den Katalog");
        System.out.println("Möchten Sie etwas kaufen? (Enter: k)");
        eingabe = scan.nextLine();
        if (eingabe.charAt(0) != 'k' && !eingabe.equals("kaufen") && !eingabe.equals("ja") && !eingabe.equalsIgnoreCase("Pfad ändern")) {
            System.out.println("Schauen Sie gerne wieder vorbei!\n");
            System.exit(0);
        }
        if (eingabe.equalsIgnoreCase("Pfad ändern")) {
            PfadManager.changePath();
            Pfad = PfadManager.readPath();
        }
        obj.initShop();
        obj.printProducts(obj.buildProducts());
        obj.erstelleBestellung();
    }
    
    private static void printHilfe(){
        System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Um den Shop nutzen zu können, müssen Sie folgendes Beachten:");
        System.out.println("1) Ändern Sie den Pfad für die Ausgabe des Kataloges. Geben Sie dafür \"Pfad ändern\" ein nachdem Sie gefragt werden ob Sie etwas kaufen möchten.");
        System.out.println("2) Der Pfad darf keine Leerzeichen enthalten!");
        System.out.println("3) Um eine Bestellung aufzunehmen werden Sie nach Ihren Kundendaten gefragt. Geben Sie diese bitte korrekt ein.");
        System.out.println("4) In einer Bestellung können Sie mehrere Produkte gleichzeitig bestellen. Achten Sie darauf wie viele Produkte noch vorrätig sind.");
        System.out.println("5) Der Name des Produktes bezieht sich immer auf das einzelne Produkt, nicht auf seine Kategorie wie \" Buch \"");
        System.out.println("6) Noch vor Bestellabschluss ist die Bestellung gültig. Sie können nicht mehr zurück kehren!");
        System.out.println("7) Die verfügbaren Artikel entnehmen Sie der Datei katalog.txt in Ihrem Pfadverzeichnis.");
        System.out.println("8) Bei mehreren Produkten mit selbem Namen, werden Ihnen zur Identifikation Artikelnummern erstellt, welche Sie dann eingaben können.");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------\n");
    }
    
    public void initShop() {
        katalog.add(new Elektronik(2,"Iphone 15",849,11));
        katalog.add(new Elektronik(2,"Iphone 15",769,11));
        katalog.add(new Elektronik(2,"Lenovo Thinkpad",1099,3));
        katalog.add(new Elektronik(3,"Bosch Spülmaschine",599,6));
        katalog.add(new Buch("978-3596190539","Dale Carnegie","Wie man Freunde gewinnt",12,4));
        katalog.add(new Buch("978-3440168646","Werner E. Celnik","Astronomie für Einsteiger",20,2));
        katalog.add(new Buch("978-3451360008","Jesus","Die Bibel",666,9));
        katalog.add(new Kleidung('S',"Baumwolle","Gucci T-Shirt",349,1));
        katalog.add(new Kleidung('M',"Baumwolle","Teddy T-Shirt",2,4));
    }
    
    private String buildProducts() {
        StringBuilder str = new StringBuilder();
        Iterator<Produkt> iterator = katalog.iterator();
        while (iterator.hasNext()) {
            Produkt p = iterator.next();
            if (p.getLagerbestand() == 0) {
                iterator.remove(); // Sicheres Entfernen über den Iterator
            }
        }
        
        str.append("<----------Bücher---------->\n");
        for (Produkt p : katalog) {
            if (p instanceof Buch) {
                str.append(p.getObjectDetails()).append("\n");
            }
        }            
        str.append("<----------Elektronik---------->\n");
        for (Produkt p : katalog) {
            if (p instanceof Elektronik) {
                str.append(p.getObjectDetails()).append("\n");
            }
        }           
        str.append("<----------Kleidung---------->\n");
        for (Produkt p : katalog) {
            if (p instanceof Kleidung) {
                str.append(p.getObjectDetails()).append("\n");
            }
        }
        return str.toString();
    }
    
    public void printProducts(String argument) {
        try (FileOutputStream fos = new FileOutputStream(Pfad, false)) {
            fos.write(argument.getBytes());
        } catch (IOException e) {
            System.out.print("Keine Übertragung möglich!");
        }
    }
    
    public void erstelleBestellung(){
        Scanner scan = new Scanner(System.in);
        Kunde k = erstelleKunde();
        Produkt p=null;          
        int anzahl=0;
        List<Produkt> produktlist = new ArrayList<>();
        List<Integer> mengen = new ArrayList<>();

        do {
            printProducts(buildProducts());
            do {
                try {
                    p = getAuftrag();
                } catch(FalschesProduktException e){
                    System.out.println("--------------------\nDas gewünschte Produkt existiert nicht.\nEs erfolgt eine erneute Abfrage des Produktnamens\n--------------------");
                }
            } while(p==null);
            do {
                try {
                    System.out.print("Anzahl Exemplare: ");
                    anzahl = scan.nextInt();
                    Bestellung.checkCorrectOrder(p, anzahl);
                } catch(ZuVielBestelltException e) {
                    System.out.println("-------------------\nSie haben zuviel bestellt, geben Sie eine Bestellmenge von maximal " + p.getLagerbestand() + " an\n-------------------");
                }
            } while(p.getLagerbestand() < anzahl);
            p.setLagerbestand(p.getLagerbestand()-anzahl);
            produktlist.add(p);
            mengen.add(anzahl);            
            boolean abbruch = bestellungfertig();
            if (abbruch) {
                break;
            }
        } while(true);
        Bestellung bestellung = new Bestellung(k,produktlist,mengen);
        printBestellung(bestellung);
    }
    
    private boolean bestellungfertig() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Möchtest du noch mehr Artikel bestellen?");
        String eingabe = scan.next();
        return !eingabe.equals("ja");
    }
    
    private void printBestellung(Bestellung b) {
        System.out.println("----------Bestellung----------");
        System.out.println("Kundenname: " + b.getKunde().getName().toUpperCase() + " " + b.getKunde().getVorname().toUpperCase());
        List<Produkt> objekte = b.getProdukt();
        List<Integer> mengen = b.getMenge();
        int menge;
        int i=0;
        for (Produkt p : objekte) {
            p = objekte.get(i);
            menge = mengen.get(i);
            System.out.println("Produkt: "+ p.getName());
            System.out.println("Menge: " + menge);
            i++;
        }
        
        System.out.println("--------------------------------\n");
        printProducts(buildProducts());
    }
    
    private Kunde erstelleKunde() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Wie lautet dein Vorname?");
        String vorname = scan.next();
        System.out.println("Wie lautet dein Nachname?");
        String name = scan.next();
        System.out.println("Wie lautet deine PLZ?");
        int PLZ = scan.nextInt();
        System.out.println("Wie lautet deine Telefonnummer?");
        int tel = scan.nextInt();
        Kunde k = new Kunde(name,vorname,tel,PLZ);
        return k;
    }
    
    private Produkt getAuftrag() throws FalschesProduktException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Name des Produktes: ");
        String name = scan.nextLine();
        ArrayList<Produkt> treffer = new ArrayList();
        for (Produkt p : katalog) {
            if (p.matches(name)) {
                treffer.add(p);
            }
        }
        if (treffer.isEmpty()) {
            throw new FalschesProduktException("");
        } else if (treffer.size() >= 2) {
            return specifyArticle(treffer);
        } else return treffer.get(0);
    }
    
    private Produkt specifyArticle(ArrayList<Produkt> treffer) {
        Scanner scan = new Scanner(System.in);
        int i=1;
        String name = treffer.get(0).getName();
        for (Produkt p : katalog) {
            if (p.matches(name)) {
                p.setId(i);
                i++;
            }
        }
        System.out.println("Es wurden mehrere Produkte mit dem selben Namen gefunden, welcher Artikel ist gemeint?");
        printSpecifiedArticle();
        System.out.print("Artikelnummer: ");
        int nr = scan.nextInt();
        for (Produkt p : katalog) {
            if (p.getId() == nr) {
                return p;
            }
        }
        return null;
    }
    
    private void printSpecifiedArticle() {
        StringBuilder str = new StringBuilder();
        for (Produkt p : katalog) {
            if (p.getId() != 0) {
                str.append("Nr: ").append(p.getId()).append(" | ").append(p.getObjectDetails()).append("\n");
            }
        }
        printProducts(str.toString());
    }
}

class AuftragsException extends Exception { //Package sichtbarkeit
    public AuftragsException(String Nachricht) {
        super(Nachricht);
    }
}
class FalschesProduktException extends AuftragsException {
    public FalschesProduktException(String Nachricht) {
        super(Nachricht);
    }
}
class ZuVielBestelltException extends AuftragsException {
    public ZuVielBestelltException(String Nachricht) {
        super(Nachricht);
    }
}
/*
Changelogs v4.0:
-Mehrere Artikel können nun in einer Bestellung gekauft werden
-Der Ausgabepfad wird nun über config und PfadManager Klasse dynamisch verwaltet
-Artikel die nicht mehr vorrätig sind werden gelöscht
*/
/*
Aufgaben für v4.1:
-Ankauffunktion
-Eingaben abdichten
-Preis + Lieferkosten berechnen, Gewicht als Member
-Falsche Namenseingaben werden nicht korrekt neu abgefragt
-pfad mit leerzeichen werden nicht angenommen
-...
*/


