package com.anderscore.modx.article;

import java.util.Scanner;
/**
 * Beschreiben Sie hier die Klasse ArtikelDialog.
 * 
 * @author Roland Daidone, Tobias Schirmer
 * @version 1.7
 */
public class ArtikelDialog 
{
    private Artikel artikel;
    private Artikel ziellager;
    private Scanner input = new Scanner(System.in);

    // Klassenmenue - Auswahlmoeglichkeiten
    private static final int ArtikelnummerAnlegen = 1;
    private static final int ArtikelHinzufuegen = 2;
    private static final int ArtikelEntfernen = 3;
    private static final int ArtikelBezeichnung = 4;
    private static final int Ende = 0;

    /**
     * Main-Methode zum Erzeugen des ArtikelDialog-Objekts
     * Start der Testschleife
     */
    public static void main(String[] args) {
        new ArtikelDialog().start();
    }

    /**
     * Hauptschleife des Programms
     */
    public void start() {
        artikel = null;
        int funktion = -1;

        while (funktion !=Ende) {
            try {
                funktion = einlesenFunktion();
                ausfuehrenFunktion(funktion);
            } catch (AssertionError e) {
                System.out.println(e);
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                input.next();
            } catch (Exception e) {
                System.out.println("Ausnahme: " + e);
                e.printStackTrace(System.out);
            }
        }
    }

    /**
     * Anzeige des Menues und Verarbeitung der Eingabefunktion.
     * 
     * @param eingegebene Funktion ist ganzzahliger Wert
     */
    private int einlesenFunktion() {
        System.out.print (ArtikelnummerAnlegen   + ": Artikelnummer anlegen; "
            + ArtikelHinzufuegen    + ": Artikel hinzufuegen; "
            + ArtikelEntfernen      + ": Artikel entfernen; "
            + ArtikelBezeichnung    + ": Artikel bezeichnen; "
            + Ende                  + ": Programm beenden -> ");
        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Ungültige Eingabeparameter. Bitte wählen Sie einen Menüpunkt");
        }
        return input.nextInt();
    }

    /**
     * Fuehrt ausgewaehlte Funktion aus
     * 
     * @param funktion auszufuehrende Funktion als Eingabe durch ganze Zahl
     */
    private void ausfuehrenFunktion(int funktion) {
        String bezeichnung;

        // Abbruchbedingungen pruefen 
        if (funktion == Ende) {
            System.out.println("Programm beendet");
            return; // that's it - nothing to do anymore
        }

        // Pruefen ob einer der gueltigen Parameter eingegeben wurde
        if (funktion != ArtikelnummerAnlegen && 
        funktion != ArtikelHinzufuegen && 
        funktion != ArtikelEntfernen &&
        funktion != ArtikelBezeichnung) {
            System.out.println("Ungültige Eingabeparameter. Bitte wählen Sie einen Menüpunkt");
            return; // ungueltige Eingabe - we are done here
        }

        // Gueltiger Menuepunkt wurde gewaehlt, Abarbeitung der einzelnen Menuepunkte
        if (funktion == ArtikelnummerAnlegen) {
            artikel = erfasseArtikel();
        }

        if (artikel != null) { 
            if (funktion == ArtikelHinzufuegen) {
                artikel.zugangsbuchung(erfasseBestand());

            }           
            if (funktion == ArtikelEntfernen) {
                artikel.zugangsabbuchung(erfasseBestand());

            }
            if (funktion == ArtikelBezeichnung) {
                System.out.println("Neue Bezeichnung: ");
                input.nextLine();                          //Workaround für Zeile 90-Problematik mit Eingabe 
                bezeichnung = input.nextLine();
                artikel.setartikelbezeichnung(bezeichnung);   

            } 

            // wenn ein nicht-null Artikel angegeben ist, immer auswerfen:
            System.out.println(artikel);

        } else { // Artikel ist null -> dem User den Hinweis geben was er zu tun hat
            System.out.println("Bitte zuerst einen Artikel anlegen");        
        }
    }

    private Artikel erfasseArtikel() {
        int artikelnummer;
        String artikelbezeichnung;
        int artikelbestand;

        System.out.print("Artikelnummer: ");
        artikelnummer = skipToNextInt(); 
        System.out.print("Artikelbezeichnung: ");
        artikelbezeichnung = input.next();
        System.out.print("Artikelbestand: ");
        artikelbestand = skipToNextInt();
        return new Artikel(artikelnummer, artikelbezeichnung, artikelbestand);
    }

    private int skipToNextInt() {
        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Bitte ganze Zahlen eingeben!");
        }
        return input.nextInt();
    }

    private int erfasseBestand() {
        System.out.print("Bestand ");
        return skipToNextInt();
    }
}

