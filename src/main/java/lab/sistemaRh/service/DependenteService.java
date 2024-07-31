package lab.sistemaRh.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lab.sistemaRh.models.Dependente;
import lab.sistemaRh.models.Funcionario;
import lab.sistemaRh.repository.DependenteRepository;
import lab.sistemaRh.repository.FuncionarioRepository;

@Service
@Transactional
public class DependenteService {

    @Autowired
    private DependenteRepository dependenteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

     // Criar um dependente e vincular ao funcionário pelo CPF
    public Dependente createDependente(String cpfFuncionario, String nome, String parentesco, String cpfDependente, LocalDate dataNascimento) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByCpf(cpfFuncionario);
        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            Dependente dependente = new Dependente();
            dependente.setNome(nome);
            dependente.setParentesco(parentesco);
            dependente.setCpf(cpfDependente);
            dependente.setDataNascimento(dataNascimento);
            dependente.setFuncionario(funcionario);
            return dependenteRepository.save(dependente);
        }
        return null; // Ou lance uma exceção se preferir
    }

    public List<Dependente> findAll() {
        return dependenteRepository.findAll();
    }

    public Dependente findById(Long id) {
        return dependenteRepository.findById(id).orElse(null);
    }

    // Obter um dependente por ID
    public Dependente getDependenteById(Long id) {
        return dependenteRepository.findById(id).orElse(null);
    }

    // Atualizar um dependente
    public Dependente updateDependente(Long id, String nome, String parentesco, LocalDate dataNascimento) {
        Optional<Dependente> dependenteOpt = dependenteRepository.findById(id);
        if (dependenteOpt.isPresent()) {
            Dependente dependente = dependenteOpt.get();
            dependente.setNome(nome);
            dependente.setParentesco(parentesco);
            dependente.setDataNascimento(dataNascimento);
            return dependenteRepository.save(dependente);
        }
        return null; // Ou lance uma exceção se preferir
    }

    public void deleteDependente(Long id) {
        dependenteRepository.deleteById(id);
    }

    public Dependente saveDependente(Dependente dependente) {
        if (dependente != null) {
            return dependenteRepository.save(dependente);
        } else {
            throw new IllegalArgumentException("Dependente must not be null");
        }
    }
}