/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domeni;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Poruka implements Serializable{
    private int porukaID;
    private Korisnik odKoga;
    private Korisnik zaKoga;
    private String tekst;
    private Date vreme;

    public Poruka() {
    }

    public Poruka(int porukaID, Korisnik odKoga, Korisnik zaKoga, String tekst, Date vreme) {
        this.porukaID = porukaID;
        this.odKoga = odKoga;
        this.zaKoga = zaKoga;
        this.tekst = tekst;
        this.vreme = vreme;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public int getPorukaID() {
        return porukaID;
    }

    public void setPorukaID(int porukaID) {
        this.porukaID = porukaID;
    }

    public Korisnik getOdKoga() {
        return odKoga;
    }

    public void setOdKoga(Korisnik odKoga) {
        this.odKoga = odKoga;
    }

    public Korisnik getZaKoga() {
        return zaKoga;
    }

    public void setZaKoga(Korisnik zaKoga) {
        this.zaKoga = zaKoga;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
    
    
}
