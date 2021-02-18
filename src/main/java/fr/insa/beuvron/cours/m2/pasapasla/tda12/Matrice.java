/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.beuvron.cours.m2.pasapasla.tda12;

import java.util.Arrays;

/**
 *
 * @author francois
 */
public class Matrice {
    
    private int nbrLig;
    private int nbrCol;
    private double[][] coeffs;
    
    public Matrice(int nl,int nc) {
        this.nbrLig = nl;
        this.nbrCol = nc;
        this.coeffs = new double[nl][nc];
        for(int i = 0 ; i < this.nbrLig ; i ++) {
            for(int j = 0; j < this.nbrCol ; j ++) {
                this.coeffs[i][j] = 0;
            }
        }
    }
    
    public String toString() {
        String s;
        s= "";
        for(int i = 0 ; i < this.nbrLig ; i ++) {
            s = s + "[";
            for (int j = 0 ; j < this.nbrCol ; j++) {
                s = s + String.format("%+4.2E",this.coeffs[i][j]);
                if (j != this.nbrCol - 1) {
                    s =s  + " ";
                }
                        
            }
            s = s + "]\n";
            
        }
        return s;
    }
    
    public static void main(String[] args) {
        int i = 3;
        Matrice m = new Matrice(2,3);
        m.coeffs[0][0] = 3.223456;
        System.out.println("coeffs de m : " + 
                Arrays.deepToString(m.coeffs));
        System.out.println("m = \n" + m);
    }
    
}
