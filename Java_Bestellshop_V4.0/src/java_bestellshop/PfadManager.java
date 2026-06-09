
package java_bestellshop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PfadManager {
    
    private static final String ConfigFile = "config.txt";
    private static final String dokumentname = "\\katalog.txt";
    
    public static void changePath() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Geben Sie den neuen Pfad ein:");
        setPath(scan.next());
    }
    
    public static String readPath() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ConfigFile))) {
            String pfad = reader.readLine();
            if (pfad != null && !pfad.isBlank()) {
                return pfad;
            }
        } catch(IOException e) {
            System.out.print("config.txt nicht gefunden - benutze Standardpfad. ");
        }
        return "katalog.txt"; // Standard: Datei im aktuellen Verzeichnis
    }
    
    private static void setPath(String Path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ConfigFile))) {
            writer.write(Path+dokumentname);
        } catch(IOException e) {
            System.out.print("Fehler");
        }
    }
}
