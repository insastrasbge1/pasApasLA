/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.beuvron.cours.m2.pasapasla.tda12;

/**
 *
 * @author francois
 */
public class TestMat {
    public static void main(String[] args) {
        Matrice m = Matrice.identite(3);
        m.set(1, 2, 3.0);
        System.out.println("mat : \n" + m);
    }
}
