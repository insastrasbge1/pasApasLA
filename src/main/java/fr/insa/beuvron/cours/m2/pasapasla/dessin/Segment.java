/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.beuvron.cours.m2.pasapasla.dessin;

/**
 *
 * @author francois
 */
public class Segment {
    
    private Point debut;
    private Point fin;
    
    public Segment(Point debut,Point fin) {
        this.debut = debut;
        this.fin = fin;
    }
    
    public double longueur() {
        Segment s2;
        return this.debut.distance(this.fin);
    }
    
    public String toString() {
        return "[" + this.debut +
                "," + this.fin.toString() + "]";
    }
    
    public static void main(String[] args) {
        Segment s1;
        s1 = new Segment(new Point(1,2),new Point(3,4));
        System.out.println("s1 = " + s1);
        System.out.println("longueur de s1 : " + s1.longueur());
        Point mil = s1.debut.milieu(s1.fin);
        double abmil = mil.getPx();
        s1.debut.setPx(-3);
        System.out.println("s1 nouveau = " + s1);
    }
    
}
