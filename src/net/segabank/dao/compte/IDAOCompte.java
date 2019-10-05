package net.segabank.dao.compte;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IDAOCompte<TYPE, E, ID, A> {

    E create(E object, TYPE type, A agence) throws SQLException, IOException, ClassNotFoundException;
    void modify(E object, TYPE type, A agence) throws SQLException, IOException, ClassNotFoundException;
    void delete(E object) throws SQLException, IOException, ClassNotFoundException;
    List<E> findByTypeCompte(TYPE type) throws SQLException, IOException, ClassNotFoundException;
    List<E> findAll() throws SQLException, IOException, ClassNotFoundException;
    List<E> findCompteByIdAgence(A agence) throws SQLException, IOException, ClassNotFoundException;
    E findCompteById(ID id) throws SQLException, IOException, ClassNotFoundException;


}
