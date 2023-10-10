package dev.tomas.repositories.alumno;

import dev.tomas.models.Alumno;
import dev.tomas.repositories.crud.Crud;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface  AlumnosRepository extends Crud<Alumno, Long> {

    Flux<Alumno> findByNombre(String nombre);

    Mono<Alumno> findByUuid(UUID uuid);
}
