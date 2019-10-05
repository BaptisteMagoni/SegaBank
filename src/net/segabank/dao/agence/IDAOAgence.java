package net.segabank.dao.agence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IDAOAgence<E, ID> {

    List<E> findAll() throws SQLException, IOException, ClassNotFoundException;
    E findById(ID id) throws SQLException, IOException, ClassNotFoundException;
}
