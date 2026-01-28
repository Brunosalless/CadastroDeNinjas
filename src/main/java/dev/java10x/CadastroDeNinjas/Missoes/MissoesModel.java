package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjasModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity // rudo o que esta abaixo ate ter um colchete ou ponto e virgula e uma entidade
@Table (name = "tb_Missoes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MissoesModel {

    // relacionamento entre tabelas uma missao pode ter varios ninjas mais um ninja pode ter apenas uma missao por vez
    // como atribui a um unico ninja a uma missao e como atribui uma missao a varios ninjas

    // tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String difuldade;

   // @OneToMany - uma missao pode ter varios ninjas
    @OneToMany(mappedBy = "missoes") // toda vez que realizar um relacionamento entre duas tabelas e necessario mappear e conectar atraves de uma chave estrangeira
    private List<NinjasModel> ninjas;

}
