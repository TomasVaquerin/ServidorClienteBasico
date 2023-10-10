package dev.tomas.repositories.crud;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Crud<T, ID> {
    Flux<T> findAll();

    // Buscar por ID
    Mono<T> findById(ID id);

    // Guardar
    Mono<T> save(T t);

    // Actualizar
    Mono<T> update(T t);

    // Borrar por ID
    Mono<Boolean> deleteById(ID id);

    // Borrar todos
    Mono<Void> deleteAll();
}
