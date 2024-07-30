package lab.sistemaRh.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lab.sistemaRh.DTO.DependenteDTO;
import lab.sistemaRh.models.Dependente;
import lab.sistemaRh.service.DependenteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/dependentes")
public class DependenteController {

    @Autowired
    private DependenteService dependenteService;

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("dependentes", dependenteService.findAll());
        return "dependentes/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("dependente", new DependenteDTO());
        return "dependentes/form";
    }

    @PostMapping
    public String save(@ModelAttribute DependenteDTO dependenteDTO) {
        // Conversão DTO para Entidade e salvamento
        Dependente dependente = new Dependente();
        // mapear atributos do DTO para a entidade Dependente
        dependenteService.save(dependente);
        return "redirect:/dependentes";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("dependente", dependenteService.findById(id));
        return "dependentes/details";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Dependente dependente = dependenteService.findById(id);
        DependenteDTO dependenteDTO = new DependenteDTO();
        // mapear atributos da entidade para o DTO
        model.addAttribute("dependente", dependenteDTO);
        return "dependentes/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute DependenteDTO dependenteDTO) {
        Dependente dependente = dependenteService.findById(id);
        // Atualizar atributos do Dependente com dados do DTO
        dependenteService.save(dependente);
        return "redirect:/dependentes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        dependenteService.delete(id);
        return "redirect:/dependentes";
    }
}