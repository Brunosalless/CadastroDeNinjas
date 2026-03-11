package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjasDTO {
    /*
    DTO - Basicamente um clone da minha entidade(Model) porem sem ter acesso ao banco de dados
    não tem a responsabilidade de ser um entidade, ele e tudo que tem no model so que sem responsabilidade nenhuma
    e uma classe qualquer

    como agora eu tenho um clone da minha entidade a minha entidade da migration de rank que nao foi mudada anteriormente
    e como o dto e um clone eu posso passar o rank aqui

    */

    private Long id;
    private String nome;
    private String email;
    private String imgUrl;
    private int idade;
    private MissoesModel missoes;
    private String rank;

}
