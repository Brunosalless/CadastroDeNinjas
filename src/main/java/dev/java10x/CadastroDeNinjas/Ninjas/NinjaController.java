package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // um controlador para API rest - ira mapear o arquivo
@RequestMapping("/ninjas") // anda junto com a RestController -serve para colocar todas as outras rodas no mesmo lugar
public class NinjaController {

    private final NinjaService ninjaService;


    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para o usuario")
    public String boasvindas(){
        return "Essa e minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar") // o post faz uma serialização inversa de Json para o banco e salva os dados
    @Operation(summary = "Cria um novo Ninja", description = "Rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = { // resposta do servidor
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criacao do ninja")
    })
    public ResponseEntity<String> criarNinjas(@RequestBody NinjasDTO ninjas){
        NinjasDTO novoNinja = ninjaService.CriarNinja(ninjas);

        return ResponseEntity.status(HttpStatus.CREATED) // pro servidor ele so esta vendo que ele foi criado(CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId()); // esta mensagem e para o usuario vizualizer o que esta acontecendo

    }



    // Mostrar todos os Ninja (READ)
    @GetMapping("/listar") // puxa do banco de dados e joga em forma de Json
    @Operation(summary = "Lista os Ninja", description = "Rota lista todos os ninjas do banco de dados")
    @ApiResponses(value = { // resposta do servidor
            @ApiResponse(responseCode = "201", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro ninja nao encontrado")
    })
    public ResponseEntity<List<NinjasDTO>> listarNinjas(){
        List<NinjasDTO> ninjas = ninjaService.ListarNinjas();
        return ResponseEntity.ok(ninjas); // passa ninjas para mostrar para o usuario
    }



    // Mostrar Ninjas por ID (READ)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista os Ninja por ID", description = "Rota lista os ninjas por ID do banco de dados")
    @ApiResponses(value = { // resposta do servidor
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro ninja nao encontrado")
    })
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
    @Operation(summary = "Altera os Ninja por id", description = "Rota Altera os ninjas por id no banco de dados")
    @ApiResponses(value = { // resposta do servidor
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro ninja nao encontrado, nao foi possivel alterar")
    })
    public ResponseEntity<?> AtualizarNinjasPorID(
            @Parameter(description = "Usuario manda o id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do ninja atualizado no corpo da requisição")
            @RequestBody NinjasDTO ninjaAtualizado){

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
    @Operation(summary = "Deleta o Ninja por id", description = "Rota deleta os ninjas por id do banco de dados")
    @ApiResponses(value = { // resposta do servidor
            @ApiResponse(responseCode = "201", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro ninja nao deletado")
    })
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
