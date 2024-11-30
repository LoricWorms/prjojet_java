package test.dao;

import java.io.IOException;
import modele.dao.ConnexionBDD;
import modele.dao.DaoSalarie;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Salarie;

/**
 * Test unitaire de la classe DaoService
 * @author btssio
 */
public class TestDaoSalarie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Test 1 getOneById
        System.out.println("\n Test 1 : DaoCategorie.getOneById");
        try {
            String idRecherche = "S02";
            Salarie sal = DaoSalarie.getOneById(idRecherche);
            if(sal != null){
                System.out.println("Salarié d'id "+idRecherche+" trouvé : \n"+sal.toString());
            }else{
                System.out.println("Salarié d'id "+idRecherche+" non trouvé : \n");
            }
            
        } catch (SQLException ex) {
            System.out.println("TestDaoCategorie - échec getOneById : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("TestDaoCategorie - échec getOneById : " + ex.getMessage());
        }
        // Test 2 getAll
        System.out.println("\n Test 2 : DaoService.getAll");
        try {
            ArrayList<Salarie> lesSalaries = DaoSalarie.getAll();
            for (Salarie sal : lesSalaries) {
                System.out.println(sal.toString());
            }
            System.out.println(lesSalaries.size()+" services trouvés");
        } catch (SQLException ex) {
            System.out.println("TestDaoSalarie - échec getAll : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("TestDaosalarie - échec getAll : " + ex.getMessage());
        }     
        // Fermeture de la connexion
        try {
            ConnexionBDD.getConnexion().close();
            System.out.println("\nConnexion à la BDD fermée");
        } catch (SQLException ex) {
            System.out.println("TestDaoSalarie - échec de la fermeture de la connexion : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("TestDaoSalarie - échec de la fermeture de la connexion : " + ex.getMessage());
        }
    }

}