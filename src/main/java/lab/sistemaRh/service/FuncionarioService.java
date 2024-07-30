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

    public Funcionario findById(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.orElse(null);
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario update(Long id, FuncionarioDTO funcionarioDTO) {
        Funcionario funcionarioExistente = findById(id);
        if (funcionarioExistente != null) {
            // Atualiza os dados do funcionário
            funcionarioExistente.setNome(funcionarioDTO.getNome());
            funcionarioExistente.setCargo(funcionarioDTO.getCargo());
            funcionarioExistente.setSalario(funcionarioDTO.getSalario());
            funcionarioExistente.setCpf(funcionarioDTO.getCpf());
            funcionarioExistente.setDataNascimento(funcionarioDTO.getDataNascimento());
            funcionarioExistente.setEmail(funcionarioDTO.getEmail());
            funcionarioRepository.save(funcionarioExistente);

            // Remove dependentes antigos
            dependenteRepository.deleteByFuncionarioId(id);

            // Adiciona os novos dependentes
            if (funcionarioDTO.getDependentes() != null) {
                for (DependenteDTO dependenteDTO : funcionarioDTO.getDependentes()) {
                    Dependente dependente = converterDTOParaDependente(dependenteDTO);
                    dependente.setFuncionario(funcionarioExistente);
                    dependenteRepository.save(dependente);
                }
            }
        }
        return funcionarioExistente;
    }

    public void delete(Long id) {
        // Remove dependentes associados ao funcionário
        dependenteRepository.deleteByFuncionarioId(id);
        // Remove o funcionário
        funcionarioRepository.deleteById(id);
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