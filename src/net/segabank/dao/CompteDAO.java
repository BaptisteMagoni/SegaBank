package net.segabank.dao;

import net.segabank.bo.compte.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO implements IDAO<CompteType, Compte, Integer> {

    private static final String CREATE_COMPTE = "INSERT INTO contacts (solde, tauxInteret, id_agence, interetBanque, decouvert, type_banque) VALUES (?,?,?,?,?)";
    private static final String SELECT_ALL_COMPTE = "SELECT * FROM compte";
    private static final String SELECT_COMPTE_TYPE = "SELECT * FROM compte WHERE type_compte = ?";
    private static final String DELETE_COMPTE = "DELETE FROM compte WHERE id = ?";
    private static final String UPDATE_COMPTE = "UPDATE compte SET id = ?, solde = ?, tauxInteret = ?, id_agence = ?, fraisCommission = ?, decouvert = ?, type_compte = ? WHERE id = ?";

    @Override
    public Compte create(Compte object, CompteType compteType) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = ConnectionManager.getConnection();
        if(connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(CREATE_COMPTE, Statement.RETURN_GENERATED_KEYS)) {
                return actionArguments(object, compteType, ps);
            }
        }
        return null;
    }

    @Override
    public void modify(Compte object, CompteType compteType) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_COMPTE)) {
            actionArguments(object, compteType, ps);
        }
    }

    @Override
    public void delete(Compte object) throws SQLException, IOException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        try(PreparedStatement ps = con.prepareStatement(DELETE_COMPTE)){
            ps.setInt(1, object.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Compte> findByTypeCompte(CompteType compteType) throws SQLException, IOException, ClassNotFoundException {
        List<Compte> comptes = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        try(PreparedStatement ps = con.prepareStatement(SELECT_COMPTE_TYPE);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()) {
                if(compteType.equals(compteType.SIMPLE))
                    comptes.add(new CompteSimple(rs.getInt("id"), rs.getInt("solde"), rs.getInt("decouvert")));
                if(compteType.equals(compteType.EPARGNE))
                    comptes.add(new CompteEpargne(rs.getInt("id"), rs.getInt("solde"), rs.getInt("tauxInteret")));
                if(compteType.equals(compteType.PAYANT))
                    comptes.add(new ComptePayant(rs.getInt("id"), rs.getInt("solde"), rs.getInt("fraisCommission")));
            }
            ps.execute();
        }
        return comptes;
    }

    @Override
    public List<Compte> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<Compte> comptes = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        try(PreparedStatement ps = con.prepareStatement(SELECT_ALL_COMPTE);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                if(rs.getString("type_compte").equals(CompteType.SIMPLE.name()))
                    comptes.add(new CompteSimple(rs.getInt("id"), rs.getInt("solde"), rs.getInt("decouvert")));
                if(rs.getString("type_compte").equals(CompteType.EPARGNE.name()))
                    comptes.add(new CompteEpargne(rs.getInt("id"), rs.getInt("solde"), rs.getInt("tauxInteret")));
                if(rs.getString("type_compte").equals(CompteType.PAYANT.name()))
                    comptes.add(new ComptePayant(rs.getInt("id"), rs.getInt("solde"), rs.getInt("fraisCommission")));
            }
            ps.execute();
        }
        return comptes;
    }

    @Override
    public Compte findCompteById(Integer integer) {
        return null;
    }

    private Compte actionArguments(Compte object, CompteType compteType, PreparedStatement ps) throws SQLException {
        if (compteType.equals(CompteType.SIMPLE)) {
            CompteSimple compteSimple = (CompteSimple) object;
            this.setArguments(ps, compteSimple.getSolde(), -1, 1, -1, compteSimple.getDecouvert(), compteType);
            return compteSimple;
        }else if(compteType.equals(CompteType.EPARGNE)){
            CompteEpargne compteEpargne = (CompteEpargne) object;
            this.setArguments(ps, compteEpargne.getSolde(), compteEpargne.getTauxInteret(), 1, -1, -1, compteType);
            return compteEpargne;
        }else if(compteType.equals(CompteType.PAYANT)){
            ComptePayant comptePayant = (ComptePayant) object;
            this.setArguments(ps, comptePayant.getSolde(), -1, 1, -1, comptePayant.getFraisCommission(), compteType);
            return comptePayant;
        }
        ps.executeUpdate();
        return null;
    }

    private void setArguments(PreparedStatement ps, int solde, int tauxInteret, int id_agence, int fraisCommission, int decouvert, CompteType compteType) throws SQLException {
        if(solde != -1) ps.setInt(1, solde);
        else ps.setNull(1, Types.INTEGER);

        if(tauxInteret != -1) ps.setInt(2, tauxInteret);
        else ps.setNull(2, Types.INTEGER);

        if(id_agence != -1) ps.setInt(3, id_agence);
        else ps.setNull(3, Types.INTEGER);

        if(fraisCommission != -1) ps.setInt(4, fraisCommission);
        else ps.setNull(4, Types.INTEGER);

        if(decouvert != -1) ps.setInt(5, decouvert);
        else ps.setNull(5, Types.INTEGER);

        ps.setString(6, compteType.name());
    }
}
