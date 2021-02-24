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

    public static Matrice identite(int taille) {
        Matrice m = new Matrice(taille, taille);
        for (int i = 0; i < m.nbrLig; i++) {
            m.coeffs[i][i] = 1;
        }
        return m;
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

    public static void testAffiche() {
        int i = 3;
        Matrice m = new Matrice(2, 3);
        m.coeffs[0][0] = 3.223456;
        System.out.println("coeffs de m : "
                + Arrays.deepToString(m.coeffs));
        System.out.println("m = \n" + m);

    }

    public static void testIdentite() {
        System.out.println("identité  4:");
        System.out.println(Matrice.identite(4).toString());
        System.out.println("identité 5 :");
        System.out.println(Matrice.identite(5).toString());

    }

    public static void test1() {
        Matrice m = Matrice.matAleaZeroUnDeux(4, 6, 0.33);
        System.out.println("mat alea : \n" + m);

    }

    public int getNbrLig() {
        return this.nbrLig;
    }

    public double get(int lig, int col) {
        return this.coeffs[lig][col];
    }

    public Matrice concatLig(Matrice n) {
        if (this.getNbrCol() != n.getNbrCol()) {
            throw new Error("nombre de cols incompatible");
        }
        Matrice res = new Matrice(this.getNbrLig() + n.getNbrLig(),
                this.getNbrCol());
        for (int i = 0; i < this.getNbrLig(); i++) {
            for (int j = 0; j < this.getNbrCol(); j++) {
                res.set(i, j, this.get(i, j));
            }
        }
        for (int i = 0; i < n.getNbrLig(); i++) {
            for (int j = 0; j < n.getNbrCol(); j++) {
                res.set(i + this.getNbrLig(), j, n.get(i, j));
            }
        }
        return res;
    }

    public static void testConcatLig() {
        Matrice m1 = Matrice.identite(3);
        Matrice m2 = Matrice.matAleaZeroUnDeux(2, 3, 0.33);
        Matrice m3 = m1.concatLig(m2);
        System.out.println("M1 : \n" + m1);
        System.out.println("M2 : \n" + m2);
        System.out.println("M3 = M1.concatLig(M2) : \n" + m3);
    }

    public Matrice add(Matrice m2) {
        if (this.nbrLig != m2.nbrLig || this.nbrCol != m2.nbrCol) {
            throw new Error("incompatibles");
        }
        int nl = this.nbrLig;
        int nc = this.nbrCol;
        Matrice res = new Matrice(nl, nc);
        for (int i = 0; i < nl; i++) {
            for (int j = 0; j < nc; j++) {
                res.set(i, j, this.get(i, j) + m2.get(i, j));
                // ou res.coeffs[i][j] = this.coeffs[i][j] + m2.coeffs[i][j];
            }
        }
        return res;
    }

    public Matrice opp() {
        int nl = this.nbrLig;
        int nc = this.nbrCol;
        Matrice res = new Matrice(nl, nc);
        for (int i = 0; i < nl; i++) {
            for (int j = 0; j < nc; j++) {
                res.set(i, j, -this.get(i, j));
            }
        }
        return res;
    }

    public Matrice moins(Matrice m2) {
        return this.add(m2.opp());
    }

    /**
     * modifie un des coeffs de this.
     */
    public void set(int lig, int col, double nVal) {
        this.coeffs[lig][col] = nVal;
    }

    public static void main(String[] args) {
        // testAffiche();
        //testIdentite();
        //test1();
        testConcatLig();

    }

    /**
     * @return the nbrCol
     */
    public int getNbrCol() {
        return nbrCol;
    }

}
