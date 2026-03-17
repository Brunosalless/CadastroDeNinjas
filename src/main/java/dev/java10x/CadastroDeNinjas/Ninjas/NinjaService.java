package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // por ser uma camada de serviço
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Logica para listar todos os ninjas
    public List<NinjasModel> ListarNinjas(){
        return ninjaRepository.findAll(); // lista todos os dados que tem no db
    }

    // Listas todos os ninjas por ID
    public NinjasModel ListarNinjasID(Long id){
        Optional<NinjasModel> ninjaPorID = ninjaRepository.findById(id);
        return ninjaPorID.orElse(null); // caso o ninjas nao exista
    }

    // criar um novo ninja
    public NinjasDTO CriarNinja(NinjasDTO ninjasDTO){
        NinjasModel ninja = ninjaMapper.map(ninjasDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    // deletar um ninja - tem que ser um metodo void
    public void DeletarNinjaPorID(Long id){
         ninjaRepository.deleteById(id);
    }

    // atualizar um ninja
    public NinjasModel AtualizarNinja(Long id, NinjasModel ninjaAtualizado){
        if (ninjaRepository.existsById(id)){
            ninjaAtualizado.setId(id);
            return ninjaRepository.save(ninjaAtualizado);
        }
        return null;
    }



}
