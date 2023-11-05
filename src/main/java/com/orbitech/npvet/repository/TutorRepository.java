package com.orbitech.npvet.repository;

import com.orbitech.npvet.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    /**
     * Retorna Lista de Tutores com nomes semelhantes ao informado
     * */
    @Query("from Tutor where nome like: nome")
    public List<Tutor> findAllByNomeLike(@Param("nome") String nome);

    /**
     * Retorna Lista de Tutores com o nome exato ao informado
     * */
    @Query("from Tutor where nome = :nome")
    public List<Tutor> findAllByNome(@Param("nome") String nome);

    /**
    * Retorna Tutor com o CPF informado
    * */
    @Query("from Tutor where cpf = :cpf")
    public Tutor findByCpf(@Param("cpf") String cpf);

    /**
    * Retorna Tutor com o RG informado
    * */
    @Query("from Tutor where rg = :rg")
    public Tutor findByRg(@Param("rg") String rg);

    /**
     * Retorna Lista de Tutor com que têm e-mail informado
     * */
    @Query("from Tutor where email = :email")
    public List<Tutor> findAllByEmailLike(@Param("email") String email);

    /**
     * Retorna Lista de Tutor ativos no banco
     * */
    @Query("from Tutor where deletedAt is null")
    public List<Tutor> getAllAtivados();

    /**
     * Retorna Lista de Tutor que sofreram soft delete no banco
     * */
    @Query("from Tutor where deletedAt is not null")
    public List<Tutor> getAllDesativados();

}
