package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("missoes")
public class MissoesController {
    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // get-- manda um requisiçao para mostrar as missoes (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> ListarMissoes(){
        List<MissoesDTO> missoes = missoesService.ListarMissoes();
        return ResponseEntity.ok(missoes);
    }

    // (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> ListarMissoesID(@PathVariable Long id){
        MissoesDTO listarMissaoID = missoesService.ListarMissoesPorID(id);

        if (listarMissaoID != null){
            return ResponseEntity.ok(listarMissaoID);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhuma missao encontrada com o ID " + id + " tente novamente.");
        }

    }

    // post-- mandar uma requisiçao para criar as missoes (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<?> criarMisooes(@RequestBody MissoesDTO missoes){
        MissoesDTO CriarMissao = missoesService.CriarMissao(missoes);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao " + CriarMissao.getNome() + " criada com sucesso");
    }


    // put-- manda uma requisiçao para alterar a missao
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missoesAtualizado){
        MissoesDTO alterarID = missoesService.AtualizarMissaoPorID(id, missoesAtualizado);

        if (alterarID != null) {
            return ResponseEntity.ok(alterarID);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID " + id + " nao encontrado impossivel de dar proceguimento na atualizacao");
        }

    }

    //delete-- serve para deletar uma missao (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id){

        if (missoesService.ListarMissoesPorID(id) != null){
            missoesService.DeletarMissaoID(id);
            return ResponseEntity.ok("Missao com ID " + id + " deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missao com ID " + id + " nao encontrado em nossos registros, tente novamente");
        }



    }


}
