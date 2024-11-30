package modele.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Categorie;

/**
 * Classe DAO : liaison classe métier Categorie / table CATEGORIE
 * @author btssio
 */
public class DaoCategorie {

    /**
     * Rechercher un enregistrement dans la table CATEGORIE d'après son code (int)
     * et en faire un objet de type Service
     *
     * @param id code de la catégorie recherchée
     * @return objet de type Categorie si trouvé dans la BDD, null sinon
     * @throws SQLException
     */
    public static Categorie getOneById(String id) throws SQLException, IOException {
        Categorie categorieTrouvee = null;
        Connection cnx = ConnexionBDD.getConnexion();
        PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM Categorie WHERE Code = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            categorieTrouvee = new Categorie(
                    id,
                    rs.getString("libelle"),
                    rs.getDouble("salaireMini"),
                    rs.getString("caisseDeRetraite"),
                    rs.getInt("primeResultat")
            );
        }
        return categorieTrouvee;
    }

    /**
     * Extraire l'ensemble des enregistrements de la table CATEGORIE
     * @return liste d'objets de type Service
     * @throws SQLException 
     */
    public static ArrayList<Categorie> getAll() throws SQLException, IOException {
        ArrayList<Categorie> lesCateTrouves = new ArrayList<>();
        Connection cnx = ConnexionBDD.getConnexion();
        PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM Categorie");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Categorie uneCategorie = new Categorie(
                    rs.getString("Code"),
                    rs.getString("libelle"),
                    rs.getDouble("salaireMini"),
                    rs.getString("caisseDeRetraite"),
                    rs.getInt("primeResultat")
            );
            lesCateTrouves.add(uneCategorie);
        }
        return lesCateTrouves;
    }

}
