/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domeni.Poruka;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Operacije;
import transfer.Receiver;
import transfer.Sender;
import transfer.Zahtev;

/**
 *
 * @author Korisnik
 */
public class Komunikacija {
    private static Komunikacija instance;
    private Socket s;
    
    Sender sender;
    Receiver receiver;

    public Komunikacija() {
        try {
            s = new Socket("localhost", 9000);
            sender = new Sender(s);
            receiver = new Receiver(s);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }
    //ovde ce da budu sve metode i formi

    public Sender getSender() {
        return sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void posaljiLogin(HashMap<String, String> mapa) {
        Zahtev kz = new Zahtev(Operacije.LOGIN, mapa);
        sender.posalji(kz);
    }

    public void posaljiLogout(int korisnikID) {
        Zahtev kz = new Zahtev(Operacije.LOGOUT, korisnikID);
        sender.posalji(kz);
        //hocu da mi se vrati bool da li je uspeo logout
    }

    public void posaljiZahtevZaKomboAktivneKorisnike() {
        Zahtev kz = new Zahtev();
        kz.setOperacija(Operacije.VRATI_ULOGOVANE);
        
        sender.posalji(kz);
        //vratice mi listu ili objekta korisnik ili stringova
    }

    public void posaljiPoruku(Poruka p) {
        
        Zahtev kz = new Zahtev(Operacije.POSALJI_PORUKU, p); //case 2
        sender.posalji(kz);
        //ovo nece imati povratnu vrednost, moze doduse da ima bool pa da bude sout
    }
 
    
    
}
