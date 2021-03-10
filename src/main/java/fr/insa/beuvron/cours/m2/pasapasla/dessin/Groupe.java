/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.beuvron.cours.m2.pasapasla.dessin;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author francois
 */
public class Groupe implements Figure {
    
    private List<Figure> contient;
    
    public Groupe() {
        this.contient = new ArrayList<Figure>();
    }
    
    public List<Figure> getContient() {
        return this.contient;
    }
    
    public boolean addFig(Figure fig) {
        if (this.contient.contains(fig)) {
            return false;
        } else {
            this.contient.add(fig);
            return true;
        }
    }

    @Override
    public double minX() {
        // TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
