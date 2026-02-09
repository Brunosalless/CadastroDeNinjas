package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // um controlador para API rest - ira mapear o arquivo
@RequestMapping("/ninjas") // anda junto com a RestController -serve para colocar todas as outras rodas no mesmo lugar
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado";
    }

    // Mostrar todos os Ninja (READ)
    @GetMapping("/listar")
    public List<NinjasModel> listarNinjas(){
        return ninjaService.ListarNinjas();
    }

    // Mostrar Ninjas por ID (READ)
    @GetMapping("/listarID")
    public String MostrarNinjasPorID(){
        return "Mostrar Ninjas Por ID";
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterarID")
    public String AlterarNinjasPorID(){
        return "Alterar Ninja Por ID";
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinjasPorID(){
        return "Ninja deletado por ID";
    }

}
