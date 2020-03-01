package AlgLin;

public class Helder extends SysLin {
    public Helder(Matrice m, Vecteur v) throws MauvaiseTaillesException, IrregularSysLinException {
        super(m, v);
    }

    public void factorLDR() {
        Matrice m = this.matriceSystem;

        int n = this.getOrdre();

        double calc;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {

                calc = 0;
                for (int k = 0; k < j; k++) {
                    calc += m.getCoef(i, k) * m.getCoef(k, k) * m.getCoef(k, j);
                }
                calc = m.getCoef(i, j) - calc;
                m.remplacecoef(i, j, calc / m.getCoef(j, j));
            }

            calc = 0;
            for (int k = 0; k < i; k++) {
                calc += m.getCoef(i, k) * m.getCoef(k, k) * m.getCoef(k, i);
            }
            m.remplacecoef(i, i, m.getCoef(i, i) - calc);

            for (int j = i + 1; j < n; j++) {
                calc = 0;
                for (int k = 0; k < i; k++) {
                    calc += m.getCoef(i, k) * m.getCoef(k, k) * m.getCoef(k, j);
                }
                calc = m.getCoef(i, j) - calc;
                m.remplacecoef(i, j, calc / m.getCoef(i, i));
            }
        }
    }

    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        this.factorLDR();

        return resolutionPartielle();
    }

    public Vecteur resolutionPartielle() throws IrregularSysLinException {
        int n = this.getOrdre();
        Vecteur ret = new Vecteur(n);

        Vecteur y, z;

        //Ly = b
        SysTriangInfUnite sysInf = new SysTriangInfUnite(matriceSystem, secondMembre);
        y = sysInf.resolution();

        //Dz = y
        SysDiagonal sysDia = new SysDiagonal(matriceSystem, y);
        z = sysDia.resolution();

        //Rx = z avec ret = x
        SysTriangSupUnite sysSup = new SysTriangSupUnite(matriceSystem, z);
        ret = sysSup.resolution();

        return ret;
    }

    public void setSecondMembre(Vecteur newSecondMembre) {
        this.secondMembre = newSecondMembre;
    }

    public static void main(String[] args) {
        try {
            Matrice m = new Matrice("C:\\Users\\Admin\\IdeaProjects\\ProgScientifique\\src\\Matrice.txt");
            Vecteur v1 = new Vecteur("C:\\Users\\Admin\\IdeaProjects\\ProgScientifique\\src\\Vecteur1.txt");
            Vecteur v2 = new Vecteur("C:\\Users\\Admin\\IdeaProjects\\ProgScientifique\\src\\Vecteur2.txt");

            m = Matrice.verif_produit(m,m);

            Helder helder = new Helder(m, v1);

            System.out.println(helder.resolution());

            helder.setSecondMembre(v2);
            System.out.println(helder.resolutionPartielle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
