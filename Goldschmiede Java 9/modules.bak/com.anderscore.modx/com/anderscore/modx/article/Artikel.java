package com.anderscore.modx.article;
/**
 * Eine einfache Artikel-Klasse für ein Bestandsfuehrungssystem.
 * 
 * @author Roland Daidone, Tobias Schirmer
 * @version 1.5
 */
public class Artikel
{
    private int Nummer = 0;
    private String Bezeichnung = "";
    private int Bestand = 0;
    private static final int MIN_ARTIKELNR = 1000;
    private static final int MAX_ARTIKELNR = 9999;
    
    /** 
     *  Methoden bzw. Operatoren - Definition des Verhaltens der Bestandsfuehrung
     *  @param Die Artikelnummer muss eine 4-stellige positive Zahl sein
     *  @param Die Bezeichnung darf nicht null sein
     *  @param Der Bestand darf nie kleiner als 0 werden
     */
    
    public Artikel(int artikelnummer, String artikelbezeichnung, int artikelbestand)
    {
        if (artikelnummer <MIN_ARTIKELNR || artikelnummer >MAX_ARTIKELNR) // Bedingung einer positiven vierstelligen Artikelnummer
            throw new IllegalArgumentException ("Nur vierstellige Artikelnummer angeben");
        if (artikelbezeichnung.trim().isEmpty())   // Bedingung für eine ausgefüllte Artikelbezeichnung
            throw new IllegalArgumentException ("Artikelbezeichnung muss angeben werden");
        if (artikelbestand <0)                     // Bedingung das der Bestand nie unter 0 fällt
            throw new IllegalArgumentException ("Artikel kann nicht weniger als 0 sein");
            
        this.Nummer = artikelnummer;
        this.Bezeichnung = artikelbezeichnung;
        setartikelbezeichnung(artikelbezeichnung);
        this.Bestand = artikelbestand;
    }
    
    public String toString()
    {
        return "Artikel: "        + Nummer
             + ", Bezeichnung: "  + Bezeichnung
             + ", Bestand: "      + Bestand;
    }
    
     /**
     *  @param  Menge - ist die zuzubuchende oder abzubuchende Menge
     */
    public void zugangsbuchung(int menge)
    {
        if (menge <0)                                                               // Zugang von Artikeln mit rein positiven Zahlen
            menge =0;
        Bestand = Bestand + menge;
    }
    
    public void zugangsabbuchung(int menge)
    {
        if (menge >=0) 
             Bestand = Bestand - menge;
        else Bestand = Bestand + menge;
        if (Bestand <0)                                                             // Abgang von Artikeln mit Regel, dass der Artikelbestand nie unter 0 fällt
            Bestand =0;
    }
    
    public void uebertragen(Artikel ziellager, int menge)
    {
        this.zugangsabbuchung(menge);
        ziellager.zugangsbuchung(menge);
    } 
    
    /**
     * Gibt Artikelnummer wieder
     */
    public int getartikelnummer()
    {
        return this.Nummer;
    }
    
    /**
     * Gibt Artikelbezeichnung wieder
     */
    public String getartikelbezeichnung()
    {
        if (Bezeichnung.trim().isEmpty())
            throw new IllegalArgumentException ("Artikelbezeichnung muss angeben werden");
        return this.Bezeichnung;
    }
    
    /**
     * Gibt Artikelbestand wieder
     */
   public int getartikelbestand()
    {
        if (Bestand <0)
            throw new IllegalArgumentException ("Artikel kann nicht weniger als 0 sein");
        return this.Bestand;
    }
    
    /**
     * Setze Artikelnummer fest
     * @param artikelnummer - neue Artikelnummer
     */
    public void setartikelnummer(int artikelnummer)
    {
        if (artikelnummer <MIN_ARTIKELNR || artikelnummer >MAX_ARTIKELNR)
            throw new IllegalArgumentException ("Nur vierstellige Artikelnummer angeben");
        this.Nummer = artikelnummer;
    }
    
    /**
     * Setze Artikelbezeichnung fest
     * @param artikelbezeichnung - neue Artikelbezeichnung
     */
    public void setartikelbezeichnung (String artikelbezeichnung)
    {
        if (artikelbezeichnung.trim().isEmpty())
            throw new IllegalArgumentException ("Artikelbezeichnung muss angeben werden");
        this.Bezeichnung = artikelbezeichnung;
    }
    
}
