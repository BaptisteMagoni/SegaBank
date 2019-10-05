package net.segabank.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<TYPE, E, ID> {

    E create(E object, TYPE type) throws SQLException, IOException, ClassNotFoundException;
    void modify(E object, TYPE type) throws SQLException, IOException, ClassNotFoundException;
    void delete(E object) throws SQLException, IOException, ClassNotFoundException;
    List<E> findByTypeCompte(TYPE type) throws SQLException, IOException, ClassNotFoundException;
    List<E> findAll() throws SQLException, IOException, ClassNotFoundException;
    E findCompteById(ID id);


}
