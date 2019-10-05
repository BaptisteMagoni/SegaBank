package net.segabank.dao.agence;

import net.segabank.bo.agence.Agence;
import net.segabank.bo.compte.CompteType;
import net.segabank.dao.ConnectionManager;
import net.segabank.dao.compte.IDAOCompte;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgenceDAO implements IDAOAgence<Agence, Integer>{

    private static final String SELECT_ALL = "SELECT * FROM agence";
    private static final String SELECT_BY_ID = "SELECT * FROM agence WHERE id = ?";

    @Override
    public List<Agence> findAll() throws SQLException, IOException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        List<Agence> agences = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                agences.add(new Agence(rs.getInt("id"), rs.getInt("code"), rs.getString("adresse")));
            }
        }
        return agences;
    }

    @Override
    public Agence findById(Integer id) throws SQLException, IOException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        Agence agence = null;
        try(PreparedStatement ps = con.prepareStatement(SELECT_BY_ID);
            ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                agence = new Agence(rs.getInt("id"), rs.getInt("code"), rs.getString("adresse"));
            }
        }
        return agence;
    }
}