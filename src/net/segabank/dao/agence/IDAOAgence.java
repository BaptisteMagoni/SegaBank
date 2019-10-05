package net.segabank.dao.agence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IDAOAgence<E, ID> {

    Map<ID, E> findAll() throws SQLException, IOException, ClassNotFoundException;
    E findById(ID id) throws SQLException, IOException, ClassNotFoundException;
}
