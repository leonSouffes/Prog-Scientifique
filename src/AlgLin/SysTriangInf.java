package AlgLin;

public class SysTriangInf extends SysLin {
    protected SysTriangInf(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
        super(matriceSystem, secondMembre);
    }

    @Override
    Vecteur resolution() {
        Vecteur retVec = new Vecteur(this.getOrdre());

        Matrice matrice = this.getMatriceSystem();
        Vecteur vecteur = this.getSecondMembre();

        for(int i=0; i<this.getOrdre(); i++){
            retVec.remplaceCoef( i, vecteur.getCoef(i) );

            for(int y=0; y<i; y++)
                retVec.remplaceCoef( i, retVec.getCoef(i) - ( retVec.getCoef(y) * matrice.getCoef(i, y)) );

            retVec.remplaceCoef( i, retVec.getCoef(i) / matrice.getCoef(i, i) );
        }

        return retVec;
    }

    public static void main(String[] args) {
        double[][] tabA =  {{1,0,0},
                            {4,1,0},
                            {1,3,1}};

        double[] tabB = {5,4,7};

        Matrice A = new Matrice(tabA);
        Vecteur b = new Vecteur(tabB);

        try {
            SysTriangInf sys = new SysTriangInf(A, b);
            Vecteur res = sys.resolution();

            System.out.println(res);
        }catch(Exception e){
            System.out.println("Erreur");
        }
    }
}
