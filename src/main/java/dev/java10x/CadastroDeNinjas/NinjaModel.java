package dev.java10x.CadastroDeNinjas;

import jakarta.persistence.*;

// para trabalhar com banco de dados e necessario baixar uma dependencia chamada Springjpa no initializr para ultilizar uma anotação chamada @Entity
@Entity // com o Entity minha classe vira uma entidade do BD
@Table(name = "tb_cadastro") // com o @Entity se criou uma tabela com o @Table coloca um nome na tabela
// JPA - java persistence API
public class NinjaModel {

    @Id // por isso e necessario passar esta anotação
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para complementar dever ser passodo uma, estrategia de como vai ser ultilizado este ID com o @GeneratedValues
    Long id; // algo que apenas e declarado, pois o java ja coloca o id automaticamente apenas sendo necessario passar como deve ser feito
    private String nome;
    private String email;
    private int idade;

    public NinjaModel() {
    }

    public NinjaModel(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
