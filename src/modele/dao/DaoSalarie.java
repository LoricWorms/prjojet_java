package modele.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import modele.metier.*;

/**
 * Classe DAO : liaison classe métier Salarie / table SALARIE
 * @author btssio
 */
public class DaoSalarie {

    /**
     * Rechercher un enregistrement dans la table SALARIE d'après son code
     * (String) et en faire un objet de type Salarie
     *
     * @param id code du salarie recherché
     * @return objet de type Salarie si trouvé dans la BDD, null sinon
     * @throws SQLException
     */
    public static Salarie getOneById(String id) throws SQLException, IOException {
        Salarie salarieTrouve = null;
        Connection cnx = ConnexionBDD.getConnexion();
        PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM Salarie WHERE Code = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            salarieTrouve = creerSalarie(rs);
        }
        return salarieTrouve;
    }

    /**
     * Extraire l'ensemble des enregistrements de la table SALARIE
     *
     * @return liste d'objets de type Salarie
     * @throws SQLException
     */
    public static ArrayList<Salarie> getAll() throws SQLException, IOException {
        ArrayList<Salarie> lesSalariesTrouves = new ArrayList<>();
        Connection cnx = ConnexionBDD.getConnexion();
        PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM Salarie");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Salarie unSalarie = creerSalarie(rs);
            lesSalariesTrouves.add(unSalarie);
        }
        return lesSalariesTrouves;
    }

    
    /**
     * Transforme un enregistrement de la table SALARIE en instance de Salarie
     * @param rs jeu d'enregistrements ; l'enregistrement courant est concerné
     * @return instance de Salarie
     * @throws SQLException 
     */
    private static Salarie creerSalarie(ResultSet rs) throws SQLException, IOException {
        Salarie unSalarie = null;
        // Récupération du service du salarié et de sa catégorie
        Service unService = DaoService.getOneById(rs.getInt("CodeServ"));
        Categorie uneCategorie = DaoCategorie.getOneById(rs.getString("NumCat"));
        unSalarie = new Salarie(
                rs.getString("Code"),
                rs.getString("Nom"),
                rs.getString("Prenom"),
                (java.util.Date) rs.getDate("DateNaiss"),
                (java.util.Date) rs.getDate("DateEmbauche"),
                rs.getString("Fonction"),
                rs.getDouble("TauxHoraire"),
                rs.getString("situationFamiliale"),
                rs.getInt("NbrEnfants"),
                unService,
                uneCategorie
                
        );
        return unSalarie;
    }
    
public static void supprimerSalarieEtSuivre(String code) throws SQLException, IOException {
    Connection cnx = ConnexionBDD.getConnexion();
    PreparedStatement pstmtSuivre = cnx.prepareStatement("DELETE FROM Suivre WHERE CodeSal = ?");
    pstmtSuivre.setString(1, code);
    int rowCountSuivre = pstmtSuivre.executeUpdate();
    
    PreparedStatement pstmtSalarie = cnx.prepareStatement("DELETE FROM Salarie WHERE Code = ?");
    pstmtSalarie.setString(1, code);
    int rowCountSalarie = pstmtSalarie.executeUpdate();
    
    if (rowCountSalarie == 0) {
        throw new SQLException("Le salarié avec le code " + code + " n'existe pas dans la base de données.");
    }
    
    if (rowCountSuivre == 0) {
        throw new SQLException("Le suivi avec le code " + code + " n'existe pas dans la base de données.");
    }
}
    
      /**
     * Ajouter un nouveau salarié dans la base de données
     * @param code     
     * @param nom     
     * @param prenom     
     * @param naiss     
     * @param embauche     
     * @param fonction     
     * @param horaire     
     * @param famille     
     * @param enfants     
     * @param service     
     * @param categorie     
     * @throws SQLException
     * @throws IOException
     */
    public static void ajouterSalarie(String code, String nom, String prenom, java.util.Date naiss, java.util.Date embauche, String fonction, Double horaire, String famille, int enfants, String categorie, int service) throws SQLException, IOException {
        Connection cnx = ConnexionBDD.getConnexion();
        PreparedStatement pstmt = cnx.prepareStatement("INSERT INTO Salarie (Code, Nom, Prenom, DateNaiss, DateEmbauche, Fonction, TauxHoraire, SituationFamiliale, NbrEnfants, NumCat, CodeServ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        pstmt.setString(1, code);
        pstmt.setString(2, nom);
        pstmt.setString(3, prenom);
        pstmt.setDate(4, (java.sql.Date) (Date) naiss);
        pstmt.setDate(5, (java.sql.Date) (Date) embauche);
        pstmt.setString(6, fonction);
        pstmt.setDouble(7, horaire);
        pstmt.setString(8, famille);
        pstmt.setInt(9, enfants);
        pstmt.setString(10, categorie);
        pstmt.setInt(11, service);
        
        int rowCount = pstmt.executeUpdate();
        if (rowCount == 0) {
            throw new SQLException("Erreur lors de l'ajout du salarié à la base de données.");
        }
    }
    
     /**
     * Ajouter un nouveau salarié dans la base de données
     * @param code     
     * @param nom     
     * @param prenom     
     * @param naiss     
     * @param embauche     
     * @param fonction     
     * @param horaire     
     * @param famille     
     * @param enfants     
     * @param service     
     * @param categorie     
     * @throws SQLException
     * @throws IOException
     */
    public static void ModifierSalarie(String code, String nom, String prenom, java.util.Date naiss, java.util.Date embauche, String fonction, Double horaire, String famille, int enfants, String categorie, int service) throws SQLException, IOException {
        Connection cnx = ConnexionBDD.getConnexion();
        PreparedStatement pstmt = cnx.prepareStatement("UPDATE Salarie SET Nom = ?, Prenom = ?, DateNaiss = ?, DateEmbauche = ?, Fonction = ?, TauxHoraire = ?, SituationFamiliale = ?, NbrEnfants = ?, NumCat = ?, CodeServ = ? WHERE Code = ?");
        pstmt.setString(1, code);
        pstmt.setString(2, nom);
        pstmt.setString(3, prenom);
        pstmt.setDate(4, (java.sql.Date) naiss);
        pstmt.setDate(5, (java.sql.Date) embauche);
        pstmt.setString(6, fonction);
        pstmt.setDouble(7, horaire);
        pstmt.setString(8, famille);
        pstmt.setInt(9, enfants);
        pstmt.setString(10, categorie);
        pstmt.setInt(11, service);
        
        int rowCount = pstmt.executeUpdate();
        if (rowCount == 0) {
            throw new SQLException("Erreur lors de l'ajout du salarié à la base de données.");
        }
    }
   

}
