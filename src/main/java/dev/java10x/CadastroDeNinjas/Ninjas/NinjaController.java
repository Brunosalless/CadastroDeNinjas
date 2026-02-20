package dev.java10x.CadastroDeNinjas.Ninjas;

import jdk.jfr.ContentType;
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
    @PostMapping("/criar") // o post faz uma serialização inversa de Json para o banco e salva os dados
    public NinjasModel criarNinjas(@RequestBody NinjasModel ninja){
        return ninjaService.CriarNinja(ninja);
    }

    // Mostrar todos os Ninja (READ)
    @GetMapping("/listar") // puxa do banco de dados e joga em forma de Json
    public List<NinjasModel> listarNinjas(){
        return ninjaService.ListarNinjas();
    }

    // Mostrar Ninjas por ID (READ)
    @GetMapping("/listar/{id}")
    public NinjasModel ListarNinjasPorId(@PathVariable Long id){
        return ninjaService.ListarNinjasID(id);
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
