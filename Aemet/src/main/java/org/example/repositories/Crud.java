package org.example.repositories;

import java.util.List;
public interface Crud<T> {
    void create(T t);

    void read(T t);

    void update(T t);

    void delete(T t);
}
