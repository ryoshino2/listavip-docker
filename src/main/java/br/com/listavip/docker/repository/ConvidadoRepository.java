package br.com.listavip.docker.repository;

import br.com.listavip.docker.model.Convidado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConvidadoRepository extends CrudRepository<Convidado, Long> {

    List<Convidado> findByNomeIgnoreCase(@Param("nome") String nome);

}
