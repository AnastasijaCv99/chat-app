/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domeni.Korisnik;
import domeni.Poruka;
import forme.LoginForma;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroler.KontrolerKlijent;
import transfer.Odgovor;
import transfer.Receiver;
import transfer.Sender;

/**
 *
 * @author Korisnik
 */
public class ObradaServerskihOdgovora extends Thread{
    
    //ovo je pozvano u login fomru samo na pocetak u konstruktor ja msm

    public ObradaServerskihOdgovora() {
    }

    @Override
    public void run() {
        while (true) {            
            Odgovor so = (Odgovor) Komunikacija.getInstance().getReceiver().primi();
            switch (so.getOperacija()) {
                case 0: //login
                    Korisnik k = (Korisnik) so.getOdgovor();
                    String ishod = so.getIshod();
                    KontrolerKlijent.getInstance().obradiLogin(k, ishod);
                    break;
                case 10: //logout od strane klijenta
                    boolean uspesno = (boolean) so.getOdgovor();
                    KontrolerKlijent.getInstance().obradiLogout(uspesno);
                    break;
                case 11: //ugasen server
                    String poruka = so.getIshod();
                    KontrolerKlijent.getInstance().obradiUgasenServer(poruka);
                    break;
                case 1: //vracanje ulogovanih korisnika
                    ArrayList<Korisnik> listaUlogovanihKorisnika = (ArrayList<Korisnik>) so.getOdgovor();
                    KontrolerKlijent.getInstance().obradiListuUlogovanih(listaUlogovanihKorisnika);
                    break;
                case 3: //posalji odnosno ovde primi poruku
                    Poruka p = (Poruka) so.getOdgovor();
                    KontrolerKlijent.getInstance().obradiPoruku(p);
            }
        }
    }
    
    
}
