package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // um controlador para API rest - ira mapear o arquivo
@RequestMapping // anda junto com a RestController -serve para colocar todas as outras rodas no mesmo lugar
public class NinjaController {


    @GetMapping("/boasVindas") // pega informações
    public String boasVindas(){
        return "Essa e minha primeira mensagem nessa rota";
    }

    // todos que acessarem a minha rota ira ver esta mensagem

}
