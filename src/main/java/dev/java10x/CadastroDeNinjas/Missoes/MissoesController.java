package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {
    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // get-- manda um requisiçao para mostrar as missoes (READ)
    @GetMapping("/listar")
    public List<MissoesModel> ListarMissoes(){
        return missoesService.ListarMissoes();
    }

    // (READ)
    @GetMapping("/listar/{id}")
    public MissoesModel ListarMissoesID(@PathVariable Long id){
        return missoesService.ListarMissoesPorID(id);
    }

    // post-- mandar uma requisiçao para criar as missoes (CREATE)
    @PostMapping("/criar")
    public MissoesModel criarMisooes(@RequestBody MissoesModel missoes){
        return missoesService.CriarMissao(missoes);
    }

    // put-- manda uma requisiçao para alterar a missao
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada com sucesso";
    }

    //delete-- serve para deletar uma missao (DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable Long id){
        missoesService.DeletarMissaoID(id);
    }


}
