/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.beuvron.cours.m2.pasapasla.tda34;

import Jama.Matrix;
import fr.insa.beuvron.Test;

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
        for (int i = 0; i < this.getNbrLig(); i++) {
            s = s + "[";
            for (int j = 0; j < this.getNbrCol(); j++) {
                s = s + String.format("%+4.2E", this.coeffs[i][j]);
                if (j != this.getNbrCol() - 1) {
                    s = s + " ";
                }

            }
            s = s + "]\n";

        }
        return s;
    }

    public static Matrice identite(int taille) {
        Matrice m = new Matrice(taille, taille);
        for (int i = 0; i < m.getNbrLig(); i++) {
            m.coeffs[i][i] = 1;
        }
        return m;
    }

    public static int aleaUnOuDeux() {
        double i;
        i = Math.random();
        if (i <= 0.5) {
            return 1;
        } else {
            return 2;
        }
    }

    public static int aleaUnOuDeuxV2() {
        return (int) (Math.random() * 2 + 1);
    }

    public static Matrice mataleaZeroUnDeux(int nl, int nc,
            double p2) {
        Matrice m = new Matrice(nl, nc);
        double coeff;
        for (int i = 0; i < nl; i++) {
            for (int j = 0; j < nc; j++) {
                coeff = Math.random();
                if (coeff < p2) {
                    m.coeffs[i][j] = 0;
                } else {
                    m.coeffs[i][j] = Matrice.aleaUnOuDeux();
                }
            }
        }
        return m;
    }

    public static void testAlea() {
        Matrice m = Matrice.mataleaZeroUnDeux(4, 5, 0.33);
        System.out.println("mat alea : \n" + m);

    }

    public int getNbrLig() {
        return this.nbrLig;
    }

    public double get(int lig, int col) {
        return this.coeffs[lig][col];
    }

    public void set(int lig, int col, double nouvelleVal) {
        this.coeffs[lig][col] = nouvelleVal;
    }

    /**
     * concatener les lignes de this et m. "This en dessus et m en dessous"
     *
     * @param m seconde matrice
     * @return la concatenation de this et m
     */
    public Matrice concatLig(Matrice m) {
        if (this.nbrCol != m.nbrCol) {
            throw new Error("nbr cols incompatibles");
        }
        Matrice r = new Matrice(this.getNbrLig() + m.getNbrLig(),
                this.getNbrCol());

        for (int i = 0; i < this.getNbrLig(); i++) {
            for (int j = 0; j < this.getNbrCol(); j++) {
                // r.coeffs[i][j] = this.coeffs[i][j];
                r.set(i, j, this.get(i, j));

            }
        }
        for (int i = 0; i < m.getNbrLig(); i++) {
            for (int j = 0; j < m.getNbrCol(); j++) {
                r.set(this.nbrLig + i, j, this.get(i, j));

            }
        }
        return r;

    }

    public Matrice concatCol(Matrice m) {
        if (this.nbrLig != m.nbrLig) {
            throw new Error("nbr cols incompatibles");
        }
        Matrice r = new Matrice(this.getNbrLig(),
                this.getNbrCol() + m.getNbrCol());

        for (int i = 0; i < this.getNbrLig(); i++) {
            for (int j = 0; j < this.getNbrCol(); j++) {
                // r.coeffs[i][j] = this.coeffs[i][j];
                r.set(i, j, this.get(i, j));

            }
        }
        for (int i = 0; i < m.getNbrLig(); i++) {
            for (int j = 0; j < m.getNbrCol(); j++) {
                r.set(i, this.nbrCol + j, m.get(i, j));

            }
        }
        return r;

    }

    public Matrice transposee() {
        Matrice r = new Matrice(this.getNbrCol(), this.getNbrLig());
        for (int i = 0; i < this.getNbrLig(); i++) {
            for (int j = 0; j < this.getNbrCol(); j++) {
                r.set(j, i, this.get(i, j));

            }
        }
        return r;
    }

    public Matrice metAuCarre() {
        return this.concatCol(Matrice.identite(this.nbrLig)).
                concatLig(Matrice.identite(this.nbrCol).
                        concatCol(this.transposee()));
    }

    public static void main(String[] args) {
        //       Matrice.testAlea();
        Matrice.testCarre();
        Test.monTest();
//        Matrix m;

    }

    public static void testCarre() {
        Matrice m = Matrice.mataleaZeroUnDeux(3, 2, 0.33);
        System.out.println("m : \n" + m);
        System.out.println("au carre : \n" + m.metAuCarre());
    }

    /**
     * @return the nbrCol
     */
    public int getNbrCol() {
        return nbrCol;
    }

}
