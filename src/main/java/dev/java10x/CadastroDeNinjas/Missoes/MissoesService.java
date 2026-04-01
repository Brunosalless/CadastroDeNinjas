package dev.java10x.CadastroDeNinjas.Missoes;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {
    private final MissoesRepositoty missoesRepositoty;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepositoty missoesRepositoty, MissoesMapper missoesMapper) {
        this.missoesRepositoty = missoesRepositoty;
        this.missoesMapper = missoesMapper;
    }

    //listar missoes
    public List<MissoesDTO> ListarMissoes(){
        List<MissoesModel> missoes = missoesRepositoty.findAll();
        return missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    // listar missoes por id
    public MissoesDTO ListarMissoesPorID(Long id){
        Optional<MissoesModel> MissoesID = missoesRepositoty.findById(id); // - Pode ter um valor… ou pode não ter nada.
        return MissoesID.map(missoesMapper::map).orElse(null); // realizar a verificação caso nao aja nenhuma missao cadastrada
    }

    // criar uma missao
    public MissoesDTO CriarMissao(MissoesDTO missoesdto){
        MissoesModel missoesmodel = missoesMapper.map(missoesdto);
        missoesmodel = missoesRepositoty.save(missoesmodel);
        return missoesMapper.map(missoesmodel);
    }

    // deletar uma missao
    public void DeletarMissaoID(Long id){
        missoesRepositoty.deleteById(id);
    }

    //atualizar missoes
    public MissoesDTO AtualizarMissaoPorID(Long id, MissoesDTO missoesDTO){
        Optional<MissoesModel> missaoExistente = missoesRepositoty.findById(id);

        if (missaoExistente.isPresent()){
            MissoesModel missoesAtualizado = missoesMapper.map(missoesDTO);
            missoesAtualizado.setId(id);
            MissoesModel missaoSalva = missoesRepositoty.save(missoesAtualizado);
            return missoesMapper.map(missaoSalva);
        }
        return null;
    }
}
