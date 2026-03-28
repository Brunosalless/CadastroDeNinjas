package dev.java10x.CadastroDeNinjas.Ninjas;

import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> criarNinjas(@RequestBody NinjasDTO ninjas){
        NinjasDTO novoNinja = ninjaService.CriarNinja(ninjas);

        return ResponseEntity.status(HttpStatus.CREATED) // pro servidor ele so esta vendo que ele foi criado(CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId()); // esta mensagem e para o usuario vizualizer o que esta acontecendo

    }



    // Mostrar todos os Ninja (READ)
    @GetMapping("/listar") // puxa do banco de dados e joga em forma de Json
    public ResponseEntity<List<NinjasDTO>> listarNinjas(){
        List<NinjasDTO> ninjas = ninjaService.ListarNinjas();
        return ResponseEntity.ok(ninjas); // passa ninjas para mostrar para o usuario
    }



    // Mostrar Ninjas por ID (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> ListarNinjasPorId(@PathVariable Long id){ // ao inves de passar apenas uma string eu passo uma generics "?"
        NinjasDTO ninjaPorID = ninjaService.ListarNinjasID(id);
        if (ninjaPorID != null){
            return ResponseEntity.ok(ninjaPorID);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o ID " + id + " nao foi encontrado, tente novamente");
        }

    }


    // Atualizar dados dos ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> AtualizarNinjasPorID(@PathVariable Long id, @RequestBody NinjasDTO ninjaAtualizado){

        NinjasDTO NinjaAtualizado = ninjaService.AtualizarNinja(id, ninjaAtualizado);
        if (NinjaAtualizado != null){
            return ResponseEntity.ok(NinjaAtualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja listado com o ID " + id + " nao foi encontrado, tente novamente");
        }
    }


    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}") // toda vez que tiver que passar algo como o id e necessario passar na url ultilizando parte desta url como variavel
    public ResponseEntity<String> deletarNinjasPorID(@PathVariable Long id){

        if (ninjaService.ListarNinjasID(id) != null){
            ninjaService.DeletarNinjaPorID(id);
            return ResponseEntity.ok("Ninja com o id " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) // resposta para o servidor
                    .body("O ninja com o ID " + id + " Nao encontrado"); // mensagem que o usuario vizualiza
        }

    }




}
