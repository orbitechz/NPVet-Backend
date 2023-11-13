package com.orbitech.npvet.repository;

import com.orbitech.npvet.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal,Long> {

    @Query("from Animal where nome like:nome")
    public List<Animal> findAllByNomeLike(@Param("nome") String nome);

    @Query("from Animal where raca like:raca")
    public List<Animal> findAllByRacaLike(@Param("raca") String raca);
    @Query("from Animal where tutorId.id = :id")
    public List<Animal> findAllByTutorId(@Param("id") Long id);
    @Query("from Animal where especie like:especie")
    public List<Animal> findAllByEspecieLike(@Param("especie") String especie);

    /**
     * Retorna Lista de Animal ativos no banco
     * */
    @Query("from Animal where deletedAt is null")
    public List<Animal> getAllAtivados();

    /**
     * Retorna Lista de Animal que sofreram soft delete no banco
     * */
    @Query("from Animal where deletedAt is not null")
    public List<Animal> getAllDesativados();

    @Query("SELECT a FROM Animal a JOIN a.tutorId t WHERE t.id = :id")
    List<Animal> getAllByTutor(@Param("id") Long id);

    @Query("SELECT a FROM Animal a JOIN a.tutorId t WHERE t.id = :id AND a.nome = :nome")
    Animal getByTutorAndName(@Param("id") Long id, @Param("nome") String nome);


}
