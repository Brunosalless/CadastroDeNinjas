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
    public NinjasDTO criarNinjas(@RequestBody NinjasDTO ninjas){
        return ninjaService.CriarNinja(ninjas);
    }

    // Mostrar todos os Ninja (READ)
    @GetMapping("/listar") // puxa do banco de dados e joga em forma de Json
    public List<NinjasDTO> listarNinjas(){
        return ninjaService.ListarNinjas();
    }

    // Mostrar Ninjas por ID (READ)
    @GetMapping("/listar/{id}")
    public NinjasDTO ListarNinjasPorId(@PathVariable Long id){
        return ninjaService.ListarNinjasID(id);
    }

    // Atualizar dados dos ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    public NinjasDTO AtualizarNinjasPorID(@PathVariable Long id, @RequestBody NinjasDTO ninjaAtualizado){
        return ninjaService.AtualizarNinja(id, ninjaAtualizado);
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}") // toda vez que tiver que passar algo como o id e necessario passar na url ultilizando parte desta url como variavel
    public void deletarNinjasPorID(@PathVariable Long id){
        ninjaService.DeletarNinjaPorID(id);
    }




}
