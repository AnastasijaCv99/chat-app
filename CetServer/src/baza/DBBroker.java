/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import domeni.Korisnik;
import domeni.Poruka;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class DBBroker {

    public ArrayList<Korisnik> vratiSveKorisnike() {
        String upit = "SELECT * FROM KORISNIK";
        ArrayList<Korisnik> listaSvihKorisnika = new ArrayList<>();
        
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()) {
                Korisnik k = new Korisnik(rs.getInt(1), rs.getString(2), rs.getString(3), 
                        rs.getString(4), rs.getString(5));
                listaSvihKorisnika.add(k);
            }
            return listaSvihKorisnika;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return listaSvihKorisnika;
    }

    public void sacuvajPorukuUBazu(Poruka p, ArrayList<Korisnik> listaAktivnihKorisnika) throws SQLException {
        String upit = "INSERT INTO PORUKA (odKoga, zaKoga, tekst, vreme) VALUES (?,?,?,?)";
        
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, p.getOdKoga().getKorisnikID());
            ps.setString (3, p.getTekst());
            
            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            //LocalDateTime now = LocalDateTime.now();  
            
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            
            /*
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();  
                System.out.println(dtf.format(now));  
            */
            
            
            if (p.getZaKoga() == null) {
                
                for (Korisnik korisnik : listaAktivnihKorisnika) {
                    ps.setInt(2, korisnik.getKorisnikID());
                
                    ps.addBatch();
                } 
                ps.executeBatch();
                Konekcija.getInstance().getConnection().commit();
                System.out.println("poruka za sve sacuvana");
            } else {
                ps.setInt(2, p.getZaKoga().getKorisnikID());
                ps.executeUpdate();
                Konekcija.getInstance().getConnection().commit();
                System.out.println("poruka za jednog sacuvana, za: " + p.getZaKoga().getUsername());
                //ovo se niakd ne desi, poruka za koga se ne sacuvava iz nekog razloga a TAJ RAZLOG JE BIO STO MORA
                //kad se cuva executeUpdate() a ne executeBatch ako nije lista!!!!!!
            }
        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
