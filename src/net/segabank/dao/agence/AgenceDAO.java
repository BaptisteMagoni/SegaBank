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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgenceDAO implements IDAOAgence<Agence, Integer>{

    private static final String SELECT_ALL = "SELECT * FROM agence";
    private static final String SELECT_BY_ID = "SELECT * FROM agence WHERE id = ?";

    @Override
    public Map<Integer, Agence> findAll() throws SQLException, IOException, ClassNotFoundException {
        Connection con = ConnectionManager.getConnection();
        Map<Integer, Agence> agences = new HashMap<>();
        try(PreparedStatement ps = con.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                agences.put(rs.getInt("id") ,new Agence(rs.getInt("id"), rs.getInt("code"), rs.getString("adresse")));
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