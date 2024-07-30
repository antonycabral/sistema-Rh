package lab.sistemaRh.Controller;

import lab.sistemaRh.models.VagaModel;
import lab.sistemaRh.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controlador responsável por gerenciar as operações relacionadas às vagas.
 */
@Controller
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaRepository vagaRepository;

    /**
     * Lista todas as vagas cadastradas.
     *
     * @param model O modelo para adicionar atributos
     * @return O nome da view para listar as vagas
     */
    @GetMapping
    public String listarVagas(Model model) {
        List<VagaModel> vagas = vagaRepository.findAll();
        model.addAttribute("vagas", vagas);
        return "listar-vagas";
    }

    /**
     * Exibe o formulário para cadastrar uma nova vaga.
     *
     * @param model O modelo para adicionar atributos
     * @return O nome da view do formulário de cadastro
     */
    @GetMapping("/nova")
    public String novaVaga(Model model) {
        model.addAttribute("vaga", new VagaModel());
        return "cadastro-vaga";
    }

    /**
     * Salva uma nova vaga no banco de dados.
     *
     * @param vaga A vaga a ser salva
     * @return Redirecionamento para a lista de vagas
     */
    @PostMapping
    public String salvarVaga(@ModelAttribute VagaModel vaga) {
        vagaRepository.save(vaga);
        return "redirect:/vagas";
    }

    /**
     * Exibe os detalhes de uma vaga específica.
     *
     * @param id O ID da vaga
     * @param model O modelo para adicionar atributos
     * @return O nome da view de detalhes da vaga
     */
    @GetMapping("/{id}")
    public String detalharVaga(@PathVariable Long id, Model model) {
        VagaModel vaga = vagaRepository.findById(id).orElseThrow();
        model.addAttribute("vaga", vaga);
        return "detalhar-vaga";
    }

    /**
     * Exibe o formulário para editar uma vaga existente.
     *
     * @param id O ID da vaga a ser editada
     * @param model O modelo para adicionar atributos
     * @return O nome da view do formulário de edição
     */
    @GetMapping("/{id}/editar")
    public String editarVaga(@PathVariable Long id, Model model) {
        VagaModel vaga = vagaRepository.findById(id).orElseThrow();
        model.addAttribute("vaga", vaga);
        return "cadastro-vaga";
    }

    /**
     * Atualiza uma vaga existente no banco de dados.
     *
     * @param id O ID da vaga a ser atualizada
     * @param vaga A vaga com os dados atualizados
     * @return Redirecionamento para a lista de vagas
     */
    @PostMapping("/{id}/editar")
    public String atualizarVaga(@PathVariable Long id, @ModelAttribute VagaModel vaga) {
        vaga.setCodigo(id);
        vagaRepository.save(vaga);
        return "redirect:/vagas";
    }

    /**
     * Exclui uma vaga do banco de dados.
     *
     * @param id O ID da vaga a ser excluída
     * @return Redirecionamento para a lista de vagas
     */
    @GetMapping("/{id}/excluir")
    public String excluirVaga(@PathVariable Long id) {
        vagaRepository.deleteById(id);
        return "redirect:/vagas";
    }
}
