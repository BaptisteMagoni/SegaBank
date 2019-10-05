package net.segabank.dao;

import net.segabank.bo.CompteType;

import java.io.IOException;
import java.sql.SQLException;

public interface IDAO<ID, E> {

    E create(E object, CompteType compteType) throws SQLException, IOException, ClassNotFoundException;
    void modify(E object);
    void delete(E object);
    void findById(ID id);
    void findAll();


}
