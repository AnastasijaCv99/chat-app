/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domeni.Korisnik;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleServer extends AbstractTableModel{
    ArrayList<Korisnik> listaKorisnika;
    //nek budu dve liste, jedna iz lista aktivnih iz kontrolora
    //a druga ce da bude samo lista svih korisnika iz baze
    //pa ce pri svakom pozivu ovde da se proverava u koju listu su koji
    String[] kolona = {"Ime", "Prezime","Korisnicko Ime", "Online"};

    public ModelTabeleServer() {
        listaKorisnika = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return listaKorisnika.size();
    }

    @Override
    public String getColumnName(int column) {
        return kolona[column];
    }

    @Override
    public int getColumnCount() {
        return kolona.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 3) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

 

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik k = listaKorisnika.get(rowIndex);
        switch (columnIndex) {
            case 0: return k.getIme();
            case 1: return k.getPrezime();
            case 2: return k.getUsername();
            //case 3 ce da vraca ako je ulogovan korisnik
            case 3: return k.isUlogovan();
            default:
                return "ret";
        }
    }

    public void dodajUTabelu(ArrayList<Korisnik> listaUlogovanih, ArrayList<Korisnik> listaSvihIzBaze) {
      
        listaKorisnika = listaSvihIzBaze;
        
        for (Korisnik korisnik1 : listaKorisnika) {
            for (Korisnik korisnik2 : listaUlogovanih) {
                if (korisnik1.getKorisnikID() == korisnik2.getKorisnikID()) {
                    listaKorisnika.set(listaKorisnika.indexOf(korisnik1), korisnik2);
                }
            }
        }
        fireTableDataChanged();
    }
    
    
}
