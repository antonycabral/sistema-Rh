package lab.sistemaRh.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lab.sistemaRh.DTO.DependenteDTO;
import lab.sistemaRh.DTO.FuncionarioDTO;
import lab.sistemaRh.models.Dependente;
import lab.sistemaRh.models.Funcionario;
import lab.sistemaRh.repository.DependenteRepository;
import lab.sistemaRh.repository.FuncionarioRepository;

@Service
@Transactional
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DependenteRepository dependenteRepository;

    public Funcionario save(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = converterDTOParaFuncionario(funcionarioDTO);
        funcionario = funcionarioRepository.save(funcionario);

        if (funcionarioDTO.getDependentes() != null) {
            for (DependenteDTO dependenteDTO : funcionarioDTO.getDependentes()) {
                Dependente dependente = converterDTOParaDependente(dependenteDTO);
                dependente.setFuncionario(funcionario);
                dependenteRepository.save(dependente);
            }
        }
        return funcionario;
    }

    // Buscar um funcionário por ID
    public Funcionario findById(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.orElse(null);
    }

    // Listar todos os funcionários
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    // Atualizar um funcionário existente
    public Funcionario update(Long id, FuncionarioDTO funcionarioDTO) {
        Funcionario funcionarioExistente = findById(id);
        if (funcionarioExistente != null) {
            Funcionario funcionarioAtualizado = converterDTOParaFuncionario(funcionarioDTO);
            funcionarioAtualizado.setId(id);
            funcionarioAtualizado = funcionarioRepository.save(funcionarioAtualizado);

            // Atualizar dependentes se existirem
            if (funcionarioDTO.getDependentes() != null) {
                dependenteRepository.deleteByFuncionarioId(id); // Remove dependentes antigos
                for (DependenteDTO dependenteDTO : funcionarioDTO.getDependentes()) {
                    Dependente dependente = converterDTOParaDependente(dependenteDTO);
                    dependente.setFuncionario(funcionarioAtualizado);
                    dependenteRepository.save(dependente);
                }
            }
            return funcionarioAtualizado;
        }
        return null;
    }

    private Funcionario converterDTOParaFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCargo(funcionarioDTO.getCargo());
        funcionario.setSalario(funcionarioDTO.getSalario());
        funcionario.setCpf(funcionarioDTO.getCpf());
        funcionario.setDataNascimento(funcionarioDTO.getDataNascimento());
        funcionario.setEmail(funcionarioDTO.getEmail());
        return funcionario;
    }

    private Dependente converterDTOParaDependente(DependenteDTO dependenteDTO) {
        Dependente dependente = new Dependente();
        dependente.setNome(dependenteDTO.getNome());
        dependente.setParentesco(dependenteDTO.getParentesco());
        dependente.setCpf(dependenteDTO.getCpf());
        dependente.setDataNascimento(dependenteDTO.getDataNascimento());
        return dependente;
    }
}