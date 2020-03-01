package AlgLin;

public class SysDiagonal extends SysLin {
    public SysDiagonal(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
        super(matriceSystem, secondMembre);
    }

    @Override
    Vecteur resolution() {
        Vecteur retVec = new Vecteur(this.getOrdre());

        Matrice matrice = this.getMatriceSystem();
        Vecteur vecteur = this.getSecondMembre();

        for(int i=0; i<this.getOrdre(); i++){
            retVec.remplaceCoef( i, vecteur.getCoef(i) / matrice.getCoef(i, i) );
        }

        return retVec;
    }
}
