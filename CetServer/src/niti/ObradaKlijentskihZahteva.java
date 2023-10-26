/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domeni.Korisnik;
import domeni.Poruka;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.KontrolerServer;
import transfer.Odgovor;
import transfer.Operacije;
import transfer.Receiver;
import transfer.Sender;
import transfer.Zahtev;

/**
 *
 * @author Korisnik
 */
public class ObradaKlijentskihZahteva extends Thread{
    Socket s;
    Sender sender;
    Receiver receiver;
    Korisnik ulogovaniKorisnik;
    
    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
        sender = new Sender(s);
        receiver = new Receiver(s);
    }

    @Override
    public void run() {
        while (true) {            
            Zahtev kz = (Zahtev) receiver.primi();
            Odgovor so = new Odgovor();
            switch (kz.getOperacija()) {
                case 0: //login
                    //ovo za posle za prolaz kroz sve niti
                    //ArrayList<ObradaKlijentskihZahteva> sveNiti = kontroler.Kontroler.getInstance().getNiti();
                    HashMap<String, String> mapa = (HashMap<String, String>) kz.getParametar();
                    String username = mapa.get("username");
                    String pass = mapa.get("pass");
                    
                    Korisnik k = KontrolerServer.getInstance().login(username, pass);
                    
                    if (k == null){
                        so.setIshod("losi kredencijali");  
                    } else {
                        //ne moze ovo ovako zato sto ovaj objekat nije u listi, ofc, on je novi
                        //treba mi neka druga metoda u kontroleru koja ce da proverava
                        //samo usernameove
                        // ako je false odnosno ako ne postoji korisnik onda ce da se loguj
                        if(!KontrolerServer.getInstance().proveriDaLiJeUlogovanKorisnik(k)) {
                            KontrolerServer.getInstance().getNiti().add(this); 
                           
                            setUlogovani(k);
                            so.setIshod("uspesan login!");
                        } else {
                            so.setIshod("vec ste ulogovani!");
                        }
                    }           
                    so.setOperacija(Operacije.LOGIN);
                    so.setOdgovor(k);
                    break;
                case 10: //logout od strane korisnika
                    int korisnikID = (int) kz.getParametar();
                    boolean uspesno = KontrolerServer.getInstance().logout(korisnikID);
                    so.setOdgovor(uspesno);
                    so.setOperacija(Operacije.LOGOUT);
                    break;
                case 1: //vrati ulogovane korisnike
                    ArrayList<Korisnik> listaUlogovanihKorisnika = KontrolerServer.getInstance().vratiListuUlogovanihKorisnika();
                    so.setOdgovor(listaUlogovanihKorisnika);
                    so.setOperacija(Operacije.VRATI_ULOGOVANE);
                    break;
                case 2: //posalji poruku 
                    Poruka p = (Poruka) kz.getParametar();
                    so.setOdgovor(p);
                    so.setOperacija(Operacije.PRIMI_PORUKU);
                    if (p.getZaKoga() == null) { //to sam stavila kao pokazatelj da se salje svima poruka
                        posaljiSvima(so);
                        //ovde ce doci kontroler.sacuvajPoruku(p)
                        KontrolerServer.getInstance().sacuvajPorukuUBazu(p);
                    } else {
                        Korisnik zaKoga = p.getZaKoga();
                        posaljiJednom(so, zaKoga);
                        KontrolerServer.getInstance().sacuvajPorukuUBazu(p);
                        posaljiJednom(so, p.getOdKoga());
                        // i ovde ce doci kontroler.sacuvajPoruku(p)
                        
                    }
                    break;
            }
            if (kz.getOperacija() != Operacije.POSALJI_PORUKU) {
                sender.posalji(so);
            }
            if (kz.getOperacija() == Operacije.LOGIN || kz.getOperacija() == Operacije.LOGOUT) {
                osveziListuAktivnih();
            } if (kz.getOperacija() == Operacije.LOGOUT) try {
                s.close();
            } catch (IOException ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    public void ugasiSveKlijente() {
        try {
            //ovde ce da se salje zahtev klijentu za gasenje
            //takodje ce svi klijenti da se izbace iz liste u kontroleru
            //a i ovaj soket bi mogao da se zatvori onda
            //klijentu se ne salje nista kao parametar, samo je bitan naziv operacije
            //moze da se posalje i poruka kao ono sto sam stavila ishod
            Odgovor so = new Odgovor();
            so.setOperacija(Operacije.UGASEN_SERVER);
            so.setIshod("odjavljeni ste, server je  ugasen");
            
            sender.posalji(so);
            KontrolerServer.getInstance().logout(ulogovaniKorisnik.getKorisnikID());
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUlogovani(Korisnik ulogovaniKorisnik) {
        this.ulogovaniKorisnik = ulogovaniKorisnik;
    }

    public Korisnik getUlogovani() {
        return ulogovaniKorisnik;
    }

    public Socket getS() {
        return s;
    }

    public Sender getSender() {
        return sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    
    private void osveziListuAktivnih() {
        Odgovor so2 = new Odgovor();
        ArrayList<Korisnik> listaUlogovanihKorisnika = KontrolerServer.getInstance().vratiListuUlogovanihKorisnika();
        
        so2.setOdgovor(listaUlogovanihKorisnika);
        so2.setOperacija(Operacije.VRATI_ULOGOVANE);
        //moras svima da posaljes ovaj zahtev!!! znaci ides kontroler.getNiti pa onda fore u tu
        
        posaljiSvima(so2);
        //sender.posalji(so2);
    }

    private void posaljiSvima(Odgovor so2) {
        ArrayList<ObradaKlijentskihZahteva> sveNiti = KontrolerServer.getInstance().getNiti();
        for (ObradaKlijentskihZahteva obradaKlijentskihZahteva : sveNiti) {
            obradaKlijentskihZahteva.getSender().posalji(so2);
        }
    }

    private void posaljiJednom(Odgovor so, Korisnik zaKoga) {
        ArrayList<ObradaKlijentskihZahteva> sveNiti = KontrolerServer.getInstance().getNiti();
        for (ObradaKlijentskihZahteva obradaKlijentskihZahteva : sveNiti) {
            if (obradaKlijentskihZahteva.getUlogovani().getKorisnikID() == zaKoga.getKorisnikID())
            obradaKlijentskihZahteva.getSender().posalji(so);
        }
    }
    
    
    
    
    
}
