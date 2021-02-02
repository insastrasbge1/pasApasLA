/*
 Copyright 2000-2011 Francois de Bertrand de Beuvron

 This file is part of CoursBeuvron.

 CoursBeuvron is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 CoursBeuvron is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with CoursBeuvron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.insa.beuvron.cours.m2.pasapasla;

import fr.insa.beuvron.utils.Console;

// des commentaires ont été ajouté pour comprendre l'utilisation des classes.
// la documentation normale du programme aparait entre /** ... et ... */
// les commentaires supplémentaires sont , comme celui-ci introduit par //
// La définition d'une classe permet de créer un nouveau type de données et de
// définir les opérations que l'on souhaite réaliser sur ces objets
// Pour cela :
//   . on doit définir les caractéristiques d'un objet. On parlera plutôt
//     d'attribut en informatique. Pour les rationnels, il est facile de voir que
//     la connaissance du numérateur et du dénominateur permet de définir
//     un rationnel de façon unique, et de réaliser toutes les opérations
//     usuelles. L'ensemble de caractéristiques {numérateur,dénominateur}
//     est donc utilisé pour représenter un rationnel.
//
//   . on doit définir les opérations possibles sur ces objets. On parlera
//     plutôt de méthodes dans le cas d'un langage objet comme java.
/**
 * calcul dans le corps des rationnels Le but de ce programme est simplement
 * d'illustrer la définition de classes simple en Java, avec des méthodes static
 * et non static. Une implantation moins limitée utiliserai sans doute des
 * entier de taille arbitraire définis dans java.math.BigInteger. Ici, on se
 * contente d'utiliser des long
 *
 * @author F. de Beuvron (INSA Strasbourg)
 *
 */
public class Rationnel {

//=============================== définition des attributs normaux
// les définitions ci-dessous indiquent que "chaque objet de type Rationnel"
// (on dira plutôt pour un langage objet comme java "chaque objet instance de
// la classe Rationnel) possède une caractéristique (un attribut) nommé "num"
// qui est de type entier, et un attribut nommé denom, également de type entier.
//
// . la convention java est que les nom d'attribut commencent par une minuscule
//
// . il est possible de restreindre l'utilisation d'un attribut :
//    private : utilisation seulement dans la classe elle-même
//    protected : utilisation dans la classe ou dans ses sous-classes
//    public : utilisation partout
//    aucun des trois mots clés précédents : utilisation dans les classes du même package
    /**
     * numérateur
     */
    private long num;

    /**
     * dénominateur
     */
    private long denom;

//============================= attributs statiques
// on a parfois besoin de définir des valeurs qui sont communes à tous les
// objet d'une classe
// on parle alors d'attribut statique, et on les définis avec le mot clé "static"
// attention ce mot clé change complètement la signification : la valeur d'un
// attribut statique est associée à la classe et non à un objet particulier. On 
// parlerait de "variable globale" dans d'autres langages. Il faut autant que 
// possible limiter l'utilisation de ces attributs statique (sauf si leur valeur
// est en fait une constante comme ci-dessous)
// le mot clé "final" indique que la valeur ne peut pas être modifiée (c'est
// une constante)
// convention de nom : les constantes sont écrites tout en majuscule
    /**
     * chaine utilisée pour séparer le numérateur du dénominateur dans la forme
     * textuelle d'un rationnel. Note : c'est un peu inutile, mais c'était pour
     * montrer un attribut static
     */
    public static final String SEPARATEUR = "/";

// il n'est pas rare de définir comme constantes des objets particuliers
    public static final Rationnel ZERO = new Rationnel(0, 1);
    public static final Rationnel UN = new Rationnel(1, 1);

//============================= encapsulation
// Pour une bonne structuration des programme, on restreint en général l'accès
// aux attribut en "private" : il ne sont donc plus directement accessibles hors
// de la classe. Si l'on veut néanmoins avoir accès à la valeur de ces attributs
// en lecture et/ou en écriture, on définie des méthodes get/set qui elles 
// sont en général d'accès "public"
    public long getNum() {
        return this.num;
    }

    public long getDenom() {
        return this.denom;
    }

// pour les rationnels, il n'y a pas de raison de modifier directement la
// valeur du numérateur ou du dénominateur hors de la classe Rationnel. On
// ne défini donc pas de methode set. Je les donne tout de même ci-dessous,
// mais en commentaire
/*
     public void setNum(long num) {
     this.num = num;
     }

     // Note : pour que cette méthode soit correcte, elle devrait générer une exception
     // dans le cas ou l'on tente de fixer le dénominateur à 0
     public void setDenom(long denom) {
     this.denom = denom;
     }
     */
//============================== constructeurs
// lors de la creation d'un objet (opérateur new), il est possible
// d'indiquer des paramètres qui permettent en général d'initialiser les
// valeurs des attributs. La méthode d'initialisation correspondant
// aux paramètres fournis lors de l'appel est automatiquement appelée

    // cette méthode sera appelé si l'on crée un rationnel avec "new Rationnel(3,4)"
    // Note : cette méthode génére une exception (Error)
    // dans le cas ou l'on tente de fixer le dénominateur à 0
    /**
     * initialise un nouveau rationnel avec les valeurs spécifiée pour le
     * numérateur et le dénominateur
     */
    public Rationnel(long num, long denom) {
        if (denom == 0) {
            throw new Error("denominateur nul invalide");
        }
        this.num = num;
        this.denom = denom;
    }

    /**
     * par defaut, on crée le rationnel nul
     */
    // cette méthode sera appelé si l'on crée un rationnel avec "new Rationnel()"
    public Rationnel() {
        // Dans les constructeurs particuliers, il est souvent utilise
        // d'appeler un constructeur plus général de la même classe avec
        // justement des valeurs particulières.
        // c'est la construction this(.. paramètres ..) qui doit être en
        // première ligne qui le permet
        this(0,1);
    }

//============================ méthodes normales
// Le but est de définir de nouvelles opérations sur le type d'objet représenté
// par la classe. Dans les langages algorithmiques classiques, on parlerai
// de définition de fonctions. Dans les langages objets, on parle de
// définition de méthodes. (Nous allons présenter les méthodes comme s'il
// s'agissait de fonctions, sans nous préocuper des diférences liées à la
// la hiérarchie classe/sous-classe)
//
// Avant de définir une méthode (fonction), il faut se demander :
//   . de quoi ai-je besoin pour pouvoir calculer la fonction : on parlera
//     des paramètres de la méthode. Par exemple, pour calculer une somme,
//     j'ai clairement besoin de deux rationnels, pour calculer un inverse,
//     je n'ai besoin que d'un rationnel.
//
//   . quel est le type du résultat obtenu. Par exemple, pour la somme,
//     j'obtient un Rationnel. Si le but de ma fonction est de tester si
//     un rationnel est nul, j'obtient un "boolean". Si je veux une
//     approximation decimale de mon rationnel, j'obtient un "double"...
//
// Si au moins un de mes paramètres est un objet de la classe en cours de
// définition (ici un Rationnel), on dira que la méthode est normale. Sinon,
// on dira que la méthode est "static". Voir ci-dessous pour la définition
// de méthodes normale. Voir plus loin pour la définition de méthodes static.
//
// une méthode normale (non static) dans la classe Rationnel réalise une
// opération sur au moins un rationnel. La syntaxe est particulière pour
// désigner ce paramètre :
//  - lors de la définition de la méthode, ce paramètre n'a pas besoin
//    d'apparaitre explicitement car :
//     . son type est toujours Rationnel (la classe dans laquelle la méthode
//       est définie)
//     . son nom est (par convention) toujours "this"
//
//  - lors de l'appel, ce paramètre n'apparait pas dans la liste des
//    paramètres, mais devant le nom de la méthode (séparé par un ".")
//    Ainsi si l'on veut faire la somme de r2 et r3, et placer le résultat dans
//    r1, l'écriture fonctionnelle serait :
//         r1 = add(r2,r3)   (écriture fonctionnelle, pas du Java)
//    En java il faut écrire :
//         r1 = r2.add(r3)
//    Cette écriture est un peu déroutante au début, mais on s'y habitue vite.
//
// la première ligne de la définition d'une méthode, que l'on appelle aussi
// la déclaration indique
//    . quel est le nom de la méthode : ici "add"
//    . quels sont les paramètres de la méthode, ici :
//        - "this" de type Rationnel  (ne pas oublier ce paramètre implicite)
//        - "arg2" de type Rationnel
//    . quel est le type du résultat : ici "Rationnel"
    /**
     * somme de 2 rationnels
     *
     * @return un nouveau rationnel somme de arg2 et this
     */
    public Rationnel add(Rationnel arg2) {
        // la classe Rationnel que je suis en train de créer est un nouveau type
        // de donnée. Je peux donc définir des variables de type Rationnel.
        Rationnel res;
        // Attention : contrairement aux types prédéfinis (long,double,boolean ..),
        // les instances d'une classe (ici les objets de type Rationnel) doivent
        // être explicitement créés. On utilise pour cela l'operateur "new".
        // Ici, la somme de deux rationnels est un nouveau rationnel. on crée ce
        // nouveau rationnel par "new Rationnel()"
        res = new Rationnel();

        // appel d'une méthode statique (voir plus bas) :
        // <nom de la classe> . <nom de la méthode> ( <liste des parametres> )
        // Exemple de méthodes statiques prédéfinies : Math.abs(x), Math.sqrt(x) ..
        long p = Rationnel.pgcd(this.denom, arg2.denom);

        // pour utiliser l'attribut d'un objet, on précise l'objet et l'attribut
        // séparés par un ".". Ici, "res.num" fait référence au numérateur
        // de l'objet "res". "arg2.denom" fait référence au dénominateur de
        // l'objet "arg2"
        res.num = this.num * (arg2.denom / p) + arg2.num * (this.denom / p);
        res.denom = this.denom * (arg2.denom / p);

        // appel d'une méthode normale :
        // <parametre implicite> . <nom méthode> ( <paramètres explicites> )
        res.reduire();

        // l'instruction "return" permet de sortir de la méthode en indiquant
        // quel est son résultat. (le fait d'appeler la variable
        // "res" n'a aucune importance, j'aurai pu l'appeler "r","truc", ou "toto"
        // et aurait obtenu le même résultat. C'est l'instruction "return" qui
        // fixe la valeur renvoyée par la méthode, ici : la valeur de la
        // variable "res")
        return res;
    }

    /**
     * @return un nouveau rationnel qui est l'opposé de this
     */
    public Rationnel opp() {
        Rationnel res = new Rationnel();
        res.num = -this.num;
        res.denom = this.denom;
        return res;
    }

    /**
     * soustraction de 2 rationnels
     *
     * @return un nouveau rationnel égal a (this - arg2)
     */
    public Rationnel sous(Rationnel arg2) {
        // Note 1 :
        //   pensez a utiliser au maximum le code déjà écrit
        //   ici, on utilise le fait que a-b = a+(-b)
        //   notez la syntaxe de l'appel :
        //     écriture fonctionnelle (pas Java) : res = add(this,opp(arg2))
        //     écriture Java : res = this.add(arg2.opp())
        //
        // Note 2 :
        //  ici, il n'est pas necessaire de créé un nouveau Rationnel, car celui-ci
        //  sera créé dans la méthode add. On déclare donc que res est un
        //  Rationnel, sans créer un nouveau rationnel.
        //
        // Note 3 :
        //   on pourrait écrire tout simplement (à la place des 3 lignes)
        //     return this.add(arg2.opp())
        Rationnel res;
        res = this.add(arg2.opp());
        return res;
    }

    /**
     * produit de 2 rationnels
     *
     * @return un nouveau rationnel produit de arg2 et this
     */
    public Rationnel mult(Rationnel arg2) {
        long p1 = Rationnel.pgcd(this.num, arg2.denom);
        long p2 = Rationnel.pgcd(arg2.num, this.denom);
        Rationnel res = new Rationnel();
        res.num = (this.num / p1) * (arg2.num / p2);
        res.denom = (this.denom / p2) * (arg2.denom / p1);
        res.reduire();
        return res;
    }

    /**
     * @return un nouveau rationnel qui est l'inverse de this
     */
    public Rationnel inv() {
        if (this.num == 0) {
            throw new Error("inverse du rationnel nul impossible");
        }
        Rationnel res = new Rationnel();
        if (this.num < 0) {
            res.num = -this.denom;
            res.denom = -this.num;
        } else {
            res.num = this.denom;
            res.denom = this.num;
        }
        return res;
    }

    /**
     * division de 2 rationnels
     *
     * @return un nouveau rationnel égal a (this / arg2)
     */
    public Rationnel div(Rationnel arg2) {
        return this.mult(arg2.inv());
    }

    /**
     * puissance entiere d'un rationnel
     *
     * @param n un entier positif
     * @return un nouveau rationnel égal a (this ^ n)
     */
    public Rationnel puissance(int n) {
        Rationnel res = Rationnel.UN;
        for (int i = 0; i < n; i++) {
            res = res.mult(this);
        }
        return res;
    }

    /**
     * teste si un rationnel est nul
     *
     * @return true si le numérateur du rationnel est nul, false sinon
     */
    public boolean estNul() {
        return this.num == 0;
    }

    /**
     * @return une approximation du rationnel par un "double" Java
     */
    public double approxime() {
        return ((double) this.num) / this.denom;
    }

    /**
     * met un rationnel sous forme réduite le rationnel est modifié par cette
     * methode
     */
    // cette méthode montre qu'il est possible de modifier les caractéristiques
    // (les attributs) du paramètre implicite (comme des autres paramètres) à
    // l'intérieur d'une méthode.
    // Cette méthode modifie le paramètre implicite, et ne renvoie donc
    // pas de résultat. Une méthode ne renvoyant pas de résultat est signalée
    // par le pseudo-type de retour "void"
    public void reduire() {
        long p;

        if (this.denom < 0) {
            this.num = -this.num;
            this.denom = -this.denom;
        }
        p = Rationnel.pgcd(this.num, this.denom);
        this.num = this.num / p;
        this.denom = this.denom / p;
    }

// Il est presque toujours utile de pouvoir afficher un objet. Pour cela, il
// faut définir sa représentation textuelle. La méthode qui calcule la
// représentation textuelle par défaut d'un objet s'appelle "toString" en Java
    /**
     * @return la forme textuelle d'un rationnel
     */
    public String toString() {
        if (this.denom == 1) {
            return "" + this.num;
        } else {
            return this.num + Rationnel.SEPARATEUR + this.denom;
        }
    }

//============================ méthodes statiques
// lisez d'abord le commentaire sur les méthodes normales, puis ci-dessous
// le cas particulier des méthodes "static"
//
// une méthode n'ayant pas de paramètre de type Rationnel est dites "static"
// en Java. Cela est signalé par le mot clé "static" après "public".
// Exemples de méthodes statiques :
//   pgcd : 2 paramètres de type entier
//     cette méthode devrait logiquement se trouver dans la définition d'une
//     classe entier. Mais comme les entiers sont des types prédéfinis en
//     Java, on ne peut pas ajouter de nouvelles définition de méthodes.
//     On se contente donc d'inclure la définition du pgcd comme une méthode
//     statique dans la classe Rationnel
//
//   creation d'un rationnel : je n'ai pas besoin d'un rationnel au départ,
//     puisque le but est justement d'en créer un nouveau. Les méthodes
//     de création d'objet sont des cas typiques de méthodes statiques.
//
//   test : lors d'un test, je vais demander les valeurs à un utilisateur,
//     et afficher les résultat. Une méthode de test n'a donc en général
//     aucun paramètres (donc static) et ne renvoie pas de résultats
//     (pseudo type de retour "void")
//
// Dans une méthode statique :
//   . pas de paramètre implicite : aucun sens donc d'utiliser "this" à
//     l'intérieur d'une méthode static
//
//   . lors de l'appel, on précède le nom de la méthode par le nom de la
//     classe. Ainsi, il est possible d'utiliser des méthodes static de
//     même nom située dans des classes différentes.
//     Exemples (voir aussi dans méthode reduire) :
//       long p = Rationnel.pgcd(u,v)
//       Rationnel r = Rationnel.parse("-7/2")
    /**
     * calcul du PGCD de deux entiers. propriétés d'Euclide : pgcd(u,v) =
     * pgcd(v,u modulo v) pgcd(u,0) = | u |
     *
     * @return le pgcd de u et v
     */
    public static long pgcd(long u, long v) {
        long poub;
        while (v != 0) {
            poub = u;
            u = v;
            v = poub % v;
        }
        return Math.abs(u);
    }

    /**
     * retourne un rationnel à partir de sa représentation textuelle un
     * rationnel peut s'écrire comme: . un entier signé . un entier signé suivi
     * d'un "/" suivi d'un entier signé
     *
     * @param s la représentation textuelle du rationnel. Exemple : "-132" ou
     * "12/-15"
     * @return un nouveau rationnel (normalisé) correspondant à s
     */
    public static Rationnel parse(String s) {
        Rationnel res = new Rationnel();
        int slashPos = s.indexOf(Rationnel.SEPARATEUR);
        if (slashPos == -1) {
            res.num = Long.parseLong(s);
            res.denom = 1;
        } else {
            res.num = Long.parseLong(s.substring(0, slashPos));
            res.denom = Long.parseLong(s.substring(slashPos + Rationnel.SEPARATEUR.length()));
        }
        res.reduire();
        return res;
    }

    public static Rationnel entreeRationnel(String message) {
        String s = Console.entreeString(message);
        return Rationnel.parse(s);
    }

    public static void testAdd() {
        Rationnel r1 = Rationnel.entreeRationnel("entrez un rationnel : ");
        Rationnel r2 = Rationnel.parse("-7/4");
        Console.println("la somme de " + r1 + " et " + r2 + " vaut : " + r1.add(r2));
    }

// comme pour le premier TP, "l'exécution" d'une classe correspond en fait
// à l'execution de la méthode "main" de cette classe
// rem : ne vous préoccupez pas pour l'instant des paramètres de cette méthode
    public static void main(String[] args) {
        Rationnel.testAdd();
    }
}
