package net.segabank.dao;

import net.segabank.bo.Compte;
import net.segabank.bo.CompteSimple;
import net.segabank.bo.CompteType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompteDAO implements IDAO<Integer, Compte> {

    private static final String CREATE_COMPTE = "INSERT INTO contacts (solde, tauxInteret, id_agence, interetBanque, decouvert, type_banque) VALUES (?,?,?,?,?)";

    @Override
    public Compte create(Compte object, CompteType compteType) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        if(connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(CREATE_COMPTE)) {
                if (compteType.equals(CompteType.SIMPLE)) {
                    Compte compte = (CompteSimple) object;
                    this.createCompteWithArgument(ps, compte.getSolde(), 1, 1, 1, CompteType.SIMPLE);
                }
            }
        }
        return null;
    }

    @Override
    public void modify(Compte object) {

    }

    @Override
    public void delete(Compte object) {

    }

    @Override
    public void findById(Integer integer) {

    }

    @Override
    public void findAll() {

    }

    private void createCompteWithArgument(PreparedStatement ps, int solde, int tauxInteret, int id_agence, int interetBanque, CompteType compteType) throws SQLException {
        ps.setInt(1, solde);
        ps.setInt(2, tauxInteret);
        ps.setInt(3, id_agence);
        ps.setInt(4, interetBanque);
        ps.setString(5, compteType.name());
    }
}
