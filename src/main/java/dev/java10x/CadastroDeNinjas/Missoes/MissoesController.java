package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    // get-- manda um requisiçao para mostrar as missoes
    @GetMapping("/listar")
    public String ListarMissoes(){
        return "Missoes listadas com sucesso";
    }

    // post-- mandar uma requisiçao para criar as missoes
    @PostMapping("/criar")
    public String criarMisooes(){
        return "Ainda nao tem nenhuma missao aqui";
    }

    // put-- manda uma requisiçao para alterar a missao
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada com sucesso";
    }

    //delete-- serve para deletar uma missao
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missao deletada com sucesso";
    }


}
