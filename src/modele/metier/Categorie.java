package modele.metier;

/**
 * Classe métier Catégorie
 * @author Stefen R
 */
public class Categorie {
    private String code;
    private String libelle;
    private double salaireMini;
    private String caisseDeRetraite;
    private int primeResultat;

    /**
     * Constructeur complet
     * @param code
     * @param libelle
     * @param salaireMini
     * @param caisseDeRetraite
     * @param primeResultat
     */
    public Categorie(String code, String libelle, double salaireMini, String caisseDeRetraite, int primeResultat) {
        this.code = code;
        this.libelle = libelle;
        this.salaireMini = salaireMini;
        this.caisseDeRetraite = caisseDeRetraite;
        this.primeResultat = primeResultat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getSalaireMini() {
        return salaireMini;
    }

    public void setSalaireMini(double salaireMini) {
        this.salaireMini = salaireMini;
    }

    public String getCaisseDeRetraite() {
        return caisseDeRetraite;
    }

    public void setCaisseDeRetraite(String caisseRetraite) {
        this.caisseDeRetraite = caisseRetraite;
    }

    public int getprimeResultat() {
        return primeResultat;
    }

    public void setprimeResultat(int prime) {
        this.primeResultat = prime;
    }
    
    @Override
    /**
     * toString complet (tous les attributs)
     * @return état complet de l'objet
     */
    public String toString() {
        return "Categorie{" + "code=" + code + ", libelle=" + libelle + ", salaireMini=" + salaireMini + ", caisseRetraite=" + caisseDeRetraite + ", prime=" + primeResultat + '}';
    }
}
