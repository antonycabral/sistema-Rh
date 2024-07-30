package lab.sistemaRh.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lab.sistemaRh.models.CandidatoModel;
import lab.sistemaRh.repository.CandidatoRepository;
import lab.sistemaRh.repository.VagaRepository;
    
@Controller
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @GetMapping
    public String listarCandidatos(Model model) {
        List<CandidatoModel> candidatos = candidatoRepository.findAll();
        model.addAttribute("candidatos", candidatos);
        return "candidato/listar-candidatos";
    }

    @GetMapping("/novo")
    public String novoCandidato(Model model) {
        model.addAttribute("candidato", new CandidatoModel());
        model.addAttribute("vagas", vagaRepository.findAll());
        return "candidato/cadastro-candidato";
    }

    @PostMapping
    public String salvarCandidato(@ModelAttribute CandidatoModel candidato, RedirectAttributes redirectAttributes) {
        if (candidatoRepository.existsByCpf(candidato.getCpf())) {
            redirectAttributes.addFlashAttribute("erro", "Já existe um candidato com este CPF.");
            return "redirect:/candidatos/novo";
        }
        candidatoRepository.save(candidato);
        redirectAttributes.addFlashAttribute("mensagem", "Candidato cadastrado com sucesso!");
        return "redirect:/candidatos";
    }

    @GetMapping("/{id}")
    public String detalharCandidato(@PathVariable Long id, Model model) {
        CandidatoModel candidato = candidatoRepository.findById(id).orElseThrow();
        model.addAttribute("candidato", candidato);
        return "candidato/detalhar-candidato";
    }

    @GetMapping("/{id}/editar")
    public String editarCandidato(@PathVariable Long id, Model model) {
        CandidatoModel candidato = candidatoRepository.findById(id).orElseThrow();
        model.addAttribute("candidato", candidato);
        return "candidato/cadastro-candidato";
    }

    @PostMapping("/{id}/editar")
    public String atualizarCandidato(@PathVariable Long id, @ModelAttribute CandidatoModel candidato, RedirectAttributes redirectAttributes) {
        candidato.setId(id);
        candidatoRepository.save(candidato);
        redirectAttributes.addFlashAttribute("mensagem", "Candidato atualizado com sucesso!");
        return "redirect:/candidatos";
    }

    @GetMapping("/{id}/excluir")
    public String excluirCandidato(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        candidatoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Candidato excluído com sucesso!");
        return "redirect:/candidatos";
    }
}