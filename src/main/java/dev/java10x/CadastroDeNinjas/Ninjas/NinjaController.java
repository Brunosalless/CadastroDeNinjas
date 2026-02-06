package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController // um controlador para API rest - ira mapear o arquivo
@RequestMapping // anda junto com a RestController -serve para colocar todas as outras rodas no mesmo lugar
public class NinjaController {


    @GetMapping("/boasVindas") // pega informações
    public String boasVindas(){
        return "Essa e minha primeira mensagem nessa rota";
    }
    // todos que acessarem a minha rota ira ver esta mensagem

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado";
    }

    // Mostrar todos os Ninja (READ)
    @GetMapping("/TodosNinjas")
    public String MostrarTodosNinjas(){
        return "Mostrar todos os Ninjas";
    }

    // Mostrar Ninjas por ID (READ)
    @GetMapping("/TodosID")
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
