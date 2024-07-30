package lab.sistemaRh.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lab.sistemaRh.DTO.DependenteDTO;
import lab.sistemaRh.DTO.FuncionarioDTO;
import lab.sistemaRh.models.Funcionario;
import lab.sistemaRh.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public String listarFuncionarios(Model model) {
        List<Funcionario> funcionarios = funcionarioService.findAll();
        model.addAttribute("funcionarios", funcionarios);
        return "funcionarios/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("funcionarioDTO", new FuncionarioDTO());
        return "funcionarios/form";
    }

    @PostMapping("/novo")
    public String salvarFuncionario(@ModelAttribute FuncionarioDTO funcionarioDTO) {
        funcionarioService.save(funcionarioDTO);
        return "redirect:/funcionarios";
    }

    @GetMapping("/{id}")
    public String detalhesFuncionario(@PathVariable Long id, Model model) {
        Funcionario funcionario = funcionarioService.findById(id);
        if (funcionario == null) {
            return "redirect:/funcionarios";
        }
        model.addAttribute("funcionario", funcionario);
        return "funcionarios/detalhes";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Funcionario funcionario = funcionarioService.findById(id);
        if (funcionario == null) {
            return "redirect:/funcionarios";
        }
        FuncionarioDTO funcionarioDTO = converterFuncionarioParaDTO(funcionario);
        model.addAttribute("funcionarioDTO", funcionarioDTO);
        return "funcionarios/editar";
    }

    @PostMapping("/{id}/editar")
    public String atualizarFuncionario(@PathVariable Long id, @ModelAttribute FuncionarioDTO funcionarioDTO) {
        funcionarioService.update(id, funcionarioDTO);
        return "redirect:/funcionarios";
    }

    @PostMapping("/{id}/deletar")
    public String deletarFuncionario(@PathVariable Long id) {
        funcionarioService.delete(id);
        return "redirect:/funcionarios";
    }

    private FuncionarioDTO converterFuncionarioParaDTO(Funcionario funcionario) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setCargo(funcionario.getCargo());
        dto.setSalario(funcionario.getSalario());
        dto.setCpf(funcionario.getCpf());
        dto.setDataNascimento(funcionario.getDataNascimento());
        dto.setEmail(funcionario.getEmail());
        dto.setDependentes(funcionario.getDependentes().stream()
            .map(dependente -> new DependenteDTO(dependente.getId(), dependente.getNome(), dependente.getParentesco(), dependente.getCpf(), dependente.getDataNascimento()))
            .collect(Collectors.toList()));
        return dto;
    }
}