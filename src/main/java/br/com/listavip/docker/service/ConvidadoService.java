package br.com.listavip.docker.service;

import br.com.listavip.docker.model.Convidado;
import br.com.listavip.docker.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConvidadoService {

    @Autowired
    private ConvidadoRepository repository;

    public Iterable<Convidado> obterTodos() {
        Iterable<Convidado> convidados = repository.findAll();
        return convidados;
    }

    public Optional<Convidado> obterPorId(Long id) {
        return repository.findById(id);
    }

    public void salvar(Convidado convidado) {
        repository.save(convidado);
    }

    public List<Convidado> obterPorNome(String nome){
        return repository.findByNomeIgnoreCase(nome);
    }

}
