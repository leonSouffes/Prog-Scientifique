package AlgLin;

abstract class SysLin {
    private int ordre;

    protected Matrice matriceSystem;
    protected Vecteur secondMembre;

    protected SysLin(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
        this.matriceSystem = matriceSystem;
        this.secondMembre = secondMembre;

        if (this.matriceSystem.nbColonne() != this.matriceSystem.nbLigne()) {
            throw new IrregularSysLinException();
        }

        this.ordre = this.matriceSystem.nbLigne();
    }

    public int getOrdre() {
        return this.ordre;
    }

    public Matrice getMatriceSystem() {
        return this.matriceSystem;
    }

    public Vecteur getSecondMembre() {
        return this.secondMembre;
    }

    abstract Vecteur resolution() throws IrregularSysLinException;
}
