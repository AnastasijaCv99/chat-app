/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class PokreniServer extends Thread{
    
    ServerSocket ss;
    ArrayList<ObradaKlijentskihZahteva> niti = new ArrayList<>();
    
    @Override
    public void run() {
        ss = null;
        try {
            ss = new ServerSocket(9000);
            System.out.println("server je pokkrenut");
            while (true) {                
                Socket s = ss.accept();
                System.out.println("klijent se povezao");   
                System.out.println("Thread iz pokreniServer" + Thread.currentThread().getId() + " is running");
                ObradaKlijentskihZahteva nit = new ObradaKlijentskihZahteva(s);
                nit.start();
                niti.add(nit);
            }
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServerSocket getSs() {
        return ss;
    }

    public ArrayList<ObradaKlijentskihZahteva> getNiti() {
        return niti;
    }
    
    
}
