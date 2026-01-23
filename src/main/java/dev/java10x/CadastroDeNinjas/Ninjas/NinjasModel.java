package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// para trabalhar com banco de dados e necessario baixar uma dependencia chamada Springjpa no initializr para ultilizar uma anotação chamada @Entity
@Entity // com o Entity minha classe vira uma entidade do BD
@Table(name = "tb_cadastro") // com o @Entity se criou uma tabela com o @Table coloca um nome na tabela
// JPA - java persistence API
@NoArgsConstructor // -> cria um construtor vazio
@AllArgsConstructor // -> cria todos os construtores desta aplicação
@Data // -> cria todos os getters e setters automaticamente
public class NinjasModel {

    @Id // por isso e necessario passar esta anotação
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para complementar dever ser passodo uma, estrategia de como vai ser ultilizado este ID com o @GeneratedValues
    private Long id; // algo que apenas e declarado, pois o java ja coloca o id automaticamente apenas sendo necessario passar como deve ser feito

    private String nome;

    private String email;

    private int idade;

    //@ManyToOne - Um ninja tem uma unica missao
    @ManyToOne
    // Foreing Key ou chave estrangeira - se no @OneToMany e necessario mapear, com o JoinColumn ira juntar as duas colunas e isso ira criar outra coluna no banco de dados
    // toda vez que clicar na chave estrange missoes_id ira pegar informaçoes das duas tabelas
    @JoinColumn(name = "missoes_id")
    private List<MissoesModel> missoes;




}
