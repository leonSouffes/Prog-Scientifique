package AlgLin;

public class SysTriangSupUnite extends SysLin {

    protected SysTriangSupUnite(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
        super(matriceSystem, secondMembre);
    }

    @Override
    Vecteur resolution() {
        Vecteur retVec = new Vecteur(this.getOrdre());

        Matrice matrice = this.getMatriceSystem();
        Vecteur vecteur = this.getSecondMembre();

        for(int i=this.getOrdre()-1; i>=0; i--){
            retVec.remplaceCoef( i, vecteur.getCoef(i) );

            for(int y=this.getOrdre()-1; y>i; y--)
                retVec.remplaceCoef(i, retVec.getCoef(i) - (retVec.getCoef(y) * matrice.getCoef(i, y)));

            retVec.remplaceCoef( i, retVec.getCoef(i) / 1 );
        }

        return retVec;
    }

    public static void main(String[] args) {
        double[][] tabA =  {{2,3,1},
                {0,2,4},
                {0,0,2}};

        double[] tabB = {5,4,7};

        Matrice A = new Matrice(tabA);
        Vecteur b = new Vecteur(tabB);

        try {
            SysTriangSupUnite sys = new SysTriangSupUnite(A, b);
            Vecteur res = sys.resolution();

            System.out.println(res);
        }catch(Exception e){
            System.out.println("Erreur");
        }
    }
}
