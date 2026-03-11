package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    /*
    realizando o mapeamento do ninja model para o dto
    */

    public NinjasModel map(NinjasDTO ninjasDTO) {
        NinjasModel ninjasModel = new NinjasModel();

        ninjasModel.setId(ninjasDTO.getId());
        ninjasModel.setNome(ninjasDTO.getNome());
        ninjasModel.setEmail(ninjasDTO.getEmail());
        ninjasModel.setIdade(ninjasDTO.getIdade());
        ninjasModel.setImgUrl(ninjasDTO.getImgUrl());
        ninjasModel.setRank(ninjasDTO.getRank());
        ninjasModel.setMissoes(ninjasDTO.getMissoes());

        return ninjasModel;

        //mapeando uma entidade para um DTO
    }

    public NinjasDTO map(NinjasModel ninjasModel){
        NinjasDTO ninjasDTO = new NinjasDTO();

        ninjasDTO.setId(ninjasModel.getId());
        ninjasDTO.setNome(ninjasModel.getNome());
        ninjasDTO.setEmail(ninjasModel.getEmail());
        ninjasDTO.setIdade(ninjasModel.getIdade());
        ninjasDTO.setImgUrl(ninjasModel.getImgUrl());
        ninjasDTO.setRank(ninjasModel.getRank());
        ninjasDTO.setMissoes(ninjasModel.getMissoes());

        return ninjasDTO;

        // mapeando um DTO para uma entidade
    }
}
