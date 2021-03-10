/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.beuvron.cours.m2.pasapasla.dessin;

import java.awt.Color;

/**
 *
 * @author francois
 */
public abstract class FigureSimple implements Figure{
    
    private Color couleurContenu;
    
    public FigureSimple(Color couleurContenu) {
        this.couleurContenu = couleurContenu;
    }

    /**
     * @return the couleurContenu
     */
    public Color getCouleurContenu() {
        return couleurContenu;
    }

    /**
     * @param couleurContenu the couleurContenu to set
     */
    public void setCouleurContenu(Color couleurContenu) {
        this.couleurContenu = couleurContenu;
    }
    
    
}
