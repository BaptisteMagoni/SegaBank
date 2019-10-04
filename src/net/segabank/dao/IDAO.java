package net.segabank.dao;

public interface IDAO<CLASS, E> {

    void create(CLASS object);
    void modify(CLASS object);
    void delete(CLASS object);
    void findById(Integer id);
    void findAll();


}
