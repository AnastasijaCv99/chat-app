/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import forma.ServerskaForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class OsveziServerskuNit extends Thread{
    
    ServerskaForma sf;

    public OsveziServerskuNit(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        while (true) {     
            try {
                sleep(3000);
                sf.osveziNit();
            } catch (InterruptedException ex) {
                Logger.getLogger(OsveziServerskuNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
            
}
