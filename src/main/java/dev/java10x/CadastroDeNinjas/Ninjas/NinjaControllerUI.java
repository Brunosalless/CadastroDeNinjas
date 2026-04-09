package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/UI")
// serve para renderizar as rotas
public class NinjaControllerUI {

    private final NinjaService ninjaService;

    public NinjaControllerUI(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }


    // mesmo metodo que esta no controller mas com algumas alterações
    // quando for acessado o /ui/listar pegara o json e ira renderizar para o usuario

    @GetMapping("/ListarUI")
    public String listarNinjas(Model model){ // da biblioteca thymelife
        List<NinjasDTO> ninjas = ninjaService.ListarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "ListarNinjas"; // tem que retornar o nome da pagina que renderiza
    }


    @GetMapping("/deletar/{id}")
    public String deletarNinjasPorID(@PathVariable Long id) {
        ninjaService.DeletarNinjaPorID(id);
        return "redirect:/ninjas/UI/ListarUI";
    }

    // ver detalhes
    @GetMapping("/Detalhes/{id}")
    public String ListarNinjasPorId(@PathVariable Long id, Model model){
        NinjasDTO ninjaPorID = ninjaService.ListarNinjasID(id);

        if (ninjaPorID != null){
            model.addAttribute("ninja", ninjaPorID);
            return "DetalhesNinjas";
        } else {
            model.addAttribute("mensagem", "Ninjas nao encontrado");
            return "ListarNinjas";
        }

    }

    // add novo ninja
    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjasDTO());
        return "adicionarNinja";
    }


    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjasDTO ninjas, RedirectAttributes redirectAttributes) {
        NinjasDTO salvarNinja = ninjaService.CriarNinja(ninjas);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja cadastrado com sucesso!");
        return "redirect:/ninjas/UI/ListarUI";
    }

}
