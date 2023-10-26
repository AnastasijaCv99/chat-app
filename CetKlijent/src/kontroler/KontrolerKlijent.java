/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domeni.Korisnik;
import domeni.Poruka;
import forme.KlijentskaForma;
import forme.LoginForma;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class KontrolerKlijent {
    //sadrzace LoginForma lf i KlijentskaForma fm, i settere za to i tjt, ustv vrv i instancu
    
    private static KontrolerKlijent instance;
       
    LoginForma lf;
    KlijentskaForma kf;

    public KontrolerKlijent() {
    }

    public static KontrolerKlijent getInstance() {
        if (instance == null) {
            instance = new KontrolerKlijent();
        }
        return instance;
    }

    public void setLf(LoginForma lf) {
        this.lf = lf;
    }

    public void setKf(KlijentskaForma kf) {
        this.kf = kf;
    }

    
    public void obradiLogin(Korisnik k, String poruka) {
        lf.obradiLogin(k, poruka);
    }

    public void obradiLogout(boolean uspesno) {
        kf.obradiLogout(uspesno);
    }

    public void obradiUgasenServer(String poruka) {
        kf.obradiUgasenServer(poruka);
    }

    public void obradiListuUlogovanih(ArrayList<Korisnik> listaUlogovanihKorisnika) {
        kf.obradiListuPopuniKombo(listaUlogovanihKorisnika);
    }

    public void obradiPoruku(Poruka p) {
        kf.obradiPrikaziPoruku(p);
    }
    
    
}
