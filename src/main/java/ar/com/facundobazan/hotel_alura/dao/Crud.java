package ar.com.facundobazan.hotel_alura.dao;

import java.util.List;

public interface Crud<T> {

    T getOne(Long id);
    List<T> getAll();
    void create(T t);
    void update(T t);
    void delete(Long id);
}
