package AlgLin;

public class Vecteur extends Matrice {
    /**
     * Crée un nouveau vecteur avec le nombre de coefficients indiqué.
     * @param nbligne Nombre de coefficients.
     */
    public Vecteur(int nbligne) {
        super(nbligne, 1);
    }

    /**
     * Crée un nouveau vecteur avec les coefficients passés en paramètres.
     * @param coefficients Coefficients à utiliser.
     */
    public Vecteur(double[] coefficients) {
        this(coefficients.length);

        for (int i = 0; i < coefficients.length; ++i) {
            this.coefficient[i][0] = coefficients[i];
        }
    }

    /**
     * Crée un nouveau vecteur en fonction du nom du fichier passé en paramètres.
     * @param fichier Nom du fichier à lire.
     */
    public Vecteur(String fichier) {
        super(fichier);
    }

    /**
     * Retourne le nombre de coefficients du vecteur.
     * @return nombre de coefficients.
     */
    @Override
    public int nbLigne() {
        return super.nbLigne();
    }

    /**
     * Retourne le coefficient à la ligne donnée en paramètres.
     * @param ligne Ligne à laquelle retourner le coefficient.
     * @return coefficient de la ligne.
     */
    public double getCoef(int ligne) {
        return getCoef(ligne, 0);
    }

    /**
     * Remplace le coefficient à la ligne donnée en paramètres.
     * @param ligne Ligne à laquelle remplacer le coefficient.
     * @param valeur Valeur à remplacer.
     */
    public void remplaceCoef(int ligne, double valeur) {
        remplacecoef(ligne, 0, valeur);
    }

    /**
     * Retourne notre vecteur sous une forme textuelle lisible.
     * @return vecteur en tant que string.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < this.coefficient.length; ++i) {
            builder.append(this.coefficient[i][0]).append(" ");
        }
        builder.append("\n");

        return builder.toString();
    }

    /**
     * Effectue le produit scalaire de deux vecteurs.
     * @param a AlgLin.Vecteur de gauche.
     * @param b AlgLin.Vecteur de droite.
     * @return Scalaire des deux vecteurs a et b.
     * @throws Exception Exception jetée lorsque les deux vecteurs n'ont pas la même longueur.
     */
    public static double produitScalaire(Vecteur a, Vecteur b) throws Exception {
        if (a.nbLigne() != b.nbLigne()) {
            throw new Exception("Les deux vecteurs n'ont pas la même longueur.");
        }

        double scalaire = 0;
        for (int i = 0; i < a.nbLigne(); ++i) {
            scalaire += a.getCoef(i) * b.getCoef(i);
        }

        return scalaire;
    }

    /**
     * Calcule la norme L1 de ce vecteur.
     * @return Norme L1
     */
    public double normeL1() {
        double total = 0;
        double coeff;

        for (int i = 0; i < nbLigne(); ++i) {
            coeff = getCoef(i);
            total += Math.abs(coeff);
        }
        return total;
    }

    /**
     * Calcule la norme L2 de ce vecteur.
     * @return Norme L2
     */
    public double normeL2() {
        double total = 0;
        double coeff;

        for (int i = 0; i < nbLigne(); ++i) {
            coeff = getCoef(i);
            total += Math.pow(Math.abs(coeff), 2);
        }
        return Math.sqrt(total);
    }

    /**
     * Calcule la norme LInf de ce vecteur.
     * @return Norme L infinie
     */
    public double normeLI() {
        double total = 0;
        double coeff;
        double normeMax;

        for (int i = 0; i < nbLigne(); ++i) {
            coeff = getCoef(i);
            normeMax = Math.abs(coeff);
            total = Math.max(normeMax, total);
        }
        return Math.sqrt(total);
    }

    public static void main(String... args) throws Exception {
        Vecteur v = new Vecteur(new double[] { 3, 2, 6, 2 });
        System.out.println(v);
        System.out.println(v.nbLigne());
        System.out.println(v.getCoef(2));

        Vecteur v2 = new Vecteur(new double[] { 2, 1, 2, 1 });
        System.out.println(produitScalaire(v, v2));
    }
}