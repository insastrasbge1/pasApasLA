/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.beuvron.cours.m2.pasapasla.td56;

import java.util.Arrays;

/**
 *
 * @author francois
 */
public class Matrice {

    private int nbrLig;
    private int nbrCol;
    private double[][] coeffs;

    public Matrice(int nl, int nc) {
        this.nbrLig = nl;
        this.nbrCol = nc;
        this.coeffs = new double[nl][nc];
        for (int i = 0; i < this.nbrLig; i++) {
            for (int j = 0; j < this.nbrCol; j++) {
                this.coeffs[i][j] = 0;
            }
        }
    }

    public String toString() {
        String s;
        s = "";
        for (int i = 0; i < this.nbrLig; i++) {
            s = s + "[";
            for (int j = 0; j < this.nbrCol; j++) {
                s = s + String.format("%+4.2E", this.coeffs[i][j]);
                if (j != this.nbrCol - 1) {
                    s = s + " ";
                }

            }
            s = s + "]\n";

        }
        return s;
    }

    public static void testAffiche() {
        int i = 3;
        Matrice m = new Matrice(2, 3);
        m.coeffs[0][0] = 3.223456;
        System.out.println("coeffs de m : "
                + Arrays.deepToString(m.coeffs));
        System.out.println("m = \n" + m);

    }

    public static Matrice identite(int taille) {
        Matrice m = new Matrice(taille, taille);
        for (int i = 0; i < m.nbrLig; i++) {
            m.coeffs[i][i] = 1;
        }
        return m;
    }

    public static void testIdentite() {
        System.out.println("identité  4:");
        System.out.println(Matrice.identite(4).toString());
        System.out.println("identité 5 :");
        System.out.println(Matrice.identite(5).toString());

    }

    public static int aleaUnOuDeux() {
        return (int) (Math.random() * 2 + 1);
    }

    public static Matrice matAleaZeroUnDeux(int nbLig, int nbCol,
            double probaZero) {
        Matrice res;
        res = new Matrice(nbLig, nbCol);
        for (int i = 0; i < res.nbrLig; i++) {
            for (int j = 0; j < res.nbrCol; j++) {
                if (Math.random() > probaZero) {
                    res.coeffs[i][j] = Matrice.aleaUnOuDeux();
                }
            }
        }
        return res;
    }

    public static Matrice creeVecteur(double[] composants) {
        Matrice res = new Matrice(composants.length, 1);
        for (int i = 0; i < res.nbrLig; i++) {
            res.coeffs[i][0] = composants[i];
        }
        return res;
    }

    public int getNbrLig() {
        return this.nbrLig;
    }

    public int getNbrCol() {
        return this.nbrCol;
    }

    public double get(int i, int j) {
        return this.coeffs[i][j];
    }

    public void set(int i, int j, double nv) {
        this.coeffs[i][j] = nv;
    }

    public static void test1() {
        Matrice m = Matrice.matAleaZeroUnDeux(4, 6, 0.33);
        System.out.println("mat alea : \n" + m);
    }

    public static void main(String[] args) {
        // testAffiche();
        // testIdentite();
        test1();
    }

}
