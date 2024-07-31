package lab.sistemaRh.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lab.sistemaRh.models.Dependente;
import lab.sistemaRh.service.DependenteService;
import lab.sistemaRh.service.FuncionarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/dependentes")
public class DependenteController {

    @Autowired
    private DependenteService dependenteService;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public String listarDependentes(Model model) {
        List<Dependente> dependentes = dependenteService.findAll();
        model.addAttribute("dependentes", dependentes);
        return "dependentes/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("funcionarioCpf", "");
        model.addAttribute("nome", "");
        model.addAttribute("parentesco", "");
        model.addAttribute("cpfDependente", "");
        model.addAttribute("dataNascimento", "");
        return "dependentes/form";
    }

    @PostMapping("/novo")
    public String salvarDependente(
        @RequestParam String cpfFuncionario,
        @RequestParam String nome,
        @RequestParam String parentesco,
        @RequestParam String cpfDependente,
        @RequestParam String dataNascimento,
        Model model
    ) {
        LocalDate dataNasc = LocalDate.parse(dataNascimento);
        dependenteService.createDependente(cpfFuncionario, nome, parentesco, cpfDependente, dataNasc);
        return "redirect:/dependentes";
    }

    @GetMapping("/{id}")
    public String mostrarDetalhesDependente(@PathVariable Long id, Model model) {
        Dependente dependente = dependenteService.getDependenteById(id);
        if (dependente != null) {
            model.addAttribute("dependente", dependente);
            model.addAttribute("funcionario", dependente.getFuncionario());
        }
        return "dependentes/detalhes";
    }

    @GetMapping("/{id}/editar")
    public String editarDependente(@PathVariable("id") Long id, Model model) {
        Dependente dependente = dependenteService.getDependenteById(id);
        model.addAttribute("dependente", dependente);
        return "dependentes/editar";
    }

    @PostMapping("/{id}/atualizar")
public String atualizarDependente(@PathVariable("id") Long id,
                                @RequestParam("nome") String nome,
                                @RequestParam("parentesco") String parentesco,
                            @RequestParam("dataNascimento") LocalDate dataNascimento) {
    Dependente dependente = dependenteService.getDependenteById(id);
    if (dependente != null) {
        dependente.setNome(nome);
        dependente.setParentesco(parentesco);
        dependente.setDataNascimento(dataNascimento);
        dependenteService.saveDependente(dependente);
    }
    return "redirect:/dependentes";
}

    @GetMapping("/{id}/excluir")
    public String excluirDependente(@PathVariable("id") Long id) {
        dependenteService.deleteDependente(id);
        return "redirect:/dependentes";
    }

}