/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domeni.Poruka;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleKlijent extends AbstractTableModel{
    ArrayList<Poruka> listaPoruka;
    
    String[] kolone = {"Od koga", "Za koga", "Tekst"};
    
    public ModelTabeleKlijent() {
        listaPoruka = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return listaPoruka.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Poruka p = listaPoruka.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getOdKoga().getUsername();
            case 1: if (p.getZaKoga() == null) {
                return "svima";
            } else return p.getZaKoga().getUsername();
            case 2:  if (p.getTekst().length()>20) {
                return p.getTekst().substring(0, 20);
            } else return p.getTekst();
                    
            default: return "ret";
        }
    }

    public ArrayList<Poruka> getListaPoruka() {
        return listaPoruka;
    }

    public void dodajPoruku(Poruka p) {        
        listaPoruka.add(p);
        fireTableDataChanged();
    }

    public Poruka dajPrvu() {
        return listaPoruka.get(0);
    }
    



}
