package dev.java10x.CadastroDeNinjas.Missoes;


import dev.java10x.CadastroDeNinjas.Ninjas.NinjaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {
    private MissoesRepositoty missoesRepositoty;

    public MissoesService(MissoesRepositoty missoesRepositoty) {
        this.missoesRepositoty = missoesRepositoty;
    }

    //listar missoes
    public List<MissoesModel> ListarMissoes(){
        return missoesRepositoty.findAll();
    }

    // listar missoes por id
    public MissoesModel ListarMissoesPorID(Long id){
        Optional<MissoesModel> MissoesID = missoesRepositoty.findById(id); // - Pode ter um valor… ou pode não ter nada.
        return MissoesID.orElse(null); // realizar a verificação caso nao aja nenhuma missao cadastrada
    }

    // criar um ninja
    public MissoesModel CriarMissao(MissoesModel missoes){
        return missoesRepositoty.save(missoes);
    }

    // deletar um ninja
    public void DeletarMissaoID(Long id){
        missoesRepositoty.deleteById(id);
    }
}
