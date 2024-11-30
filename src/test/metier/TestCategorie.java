package test.metier;

import modele.metier.Categorie;

/**
 *Classe de test unitaire de la classe Categorie
 * @author Stefen R
 */
public class TestCategorie {
    
    public static void main(String[] args) {
        
        System.out.println("TestCategorie");
        
        Categorie uneCategorie = new Categorie("C1", "cadre moyen", 2500, "AGIRC",1);        
        System.out.println(uneCategorie.toString());
    }
}
