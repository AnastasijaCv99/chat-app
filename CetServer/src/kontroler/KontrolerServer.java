/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import baza.DBBroker;
import domeni.Korisnik;
import domeni.Poruka;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author Korisnik
 */
public class KontrolerServer {
    private static KontrolerServer instance;
    private DBBroker dbb;
    
    //sta ako ovdde postoji lista aktivnih obradaKlijentskihZahteva
    //postoji lista ovde prazna, ima za nju getter i setter i mozda mora da se napravi metoda da moze da se dodaje u listu
    //a mozda i ne mora, samo kad se vrati login u Obradu kazes kontroler.getLista.add(this); MOZEE TOO-- VIDECEMO DAL RADI
    ArrayList<ObradaKlijentskihZahteva> niti = new ArrayList<>();
    
    
    public KontrolerServer() {
        dbb = new DBBroker();
    }

    public static KontrolerServer getInstance() {
        if (instance == null) {
            instance = new KontrolerServer();
        }
        return instance;
    }

    public ArrayList<ObradaKlijentskihZahteva> getNiti() {
        return niti;
    }


    public Korisnik login(String username, String pass) {
        ArrayList<Korisnik> listaSvihIzBaze = dbb.vratiSveKorisnike();
        for (Korisnik k : listaSvihIzBaze) {
            if (k.getUsername().equals(username) && k.getPassword().equals(pass)) {
                k.setUlogovan(true);
                return k;
            }
        }
        return null;
    }
    //da imam cisto jer ce trebati u serverskoj formi
    public ArrayList<Korisnik> listaSvihKorisnikaIzBaze() {
        return dbb.vratiSveKorisnike();
    }

    public boolean proveriDaLiJeUlogovanKorisnik(Korisnik k) {
        for (ObradaKlijentskihZahteva obradaKorisnik : niti) {
            if (obradaKorisnik.getUlogovani().getKorisnikID() == k.getKorisnikID()){
                return true;
                //znaci vec je ulogovan
            }
        }
        return false;
    }
        
    public boolean logout(int korisnikID) {
        for (ObradaKlijentskihZahteva obradaKlijentskihZahteva : niti) {
            if (obradaKlijentskihZahteva.getUlogovani().getKorisnikID() == korisnikID) {
                niti.remove(obradaKlijentskihZahteva);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Korisnik> vratiListuUlogovanihKorisnika() {
        ArrayList<Korisnik> listaUlogovanihKorisnika = new ArrayList<>();
        //ovako sam pravila pre ove liste korisnika koju imam, u koju se dodaju pri loginu, mozda sam mogla i ovu metodu da iskoristim
        for (ObradaKlijentskihZahteva obradaKorisnik : niti) {
            listaUlogovanihKorisnika.add(obradaKorisnik.getUlogovani());
        }
        return listaUlogovanihKorisnika;
    }

    public void sacuvajPorukuUBazu(Poruka p) {
        try {
            //ako ne radi sa ovom metodom, imam listu korisnika koji su bas korisnici ulogovani
            //mogu nju da prosledim ali onda moram da prepravim da dodam da se izbace korisnici pri logoutu
            dbb.sacuvajPorukuUBazu(p, vratiListuUlogovanihKorisnika());
        } catch (SQLException ex) {
            Logger.getLogger(KontrolerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    
}
