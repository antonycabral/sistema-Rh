package lab.sistemaRh.service;

import lab.sistemaRh.models.VagaModel;
import lab.sistemaRh.repository.VagaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    public VagaService(VagaRepository vagaRepository) {
        this.vagaRepository = vagaRepository;
    }

    /**
     * Salva uma vaga no banco de dados.
     *
     * @param vaga a vaga a ser salva
     * @return a vaga salva
     */
    public VagaModel salvar(VagaModel vaga) {
        return vagaRepository.save(vaga);
    }

    /**
     * Busca todas as vagas no banco de dados.
     *
     * @return uma lista de vagas
     */
    public List<VagaModel> buscarTodas() {
        return vagaRepository.findAll();
    }

    /**
     * Busca uma vaga pelo seu ID.
     *
     * @param id o ID da vaga
     * @return a vaga encontrada ou null se não existir
     */
    public VagaModel buscarPorId(Long id) {
        return vagaRepository.findById(id).orElse(null);
    }

    /**
     * Exclui uma vaga do banco de dados.
     *
     * @param id o ID da vaga a ser excluída
     */
    public void excluir(Long id) {
        vagaRepository.deleteById(id);
    }
}