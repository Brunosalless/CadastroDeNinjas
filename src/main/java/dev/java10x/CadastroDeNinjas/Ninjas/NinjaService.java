package dev.java10x.CadastroDeNinjas.Ninjas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service // por ser uma camada de serviço
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Logica para listar todos os ninjas
    public List<NinjasDTO> ListarNinjas(){
        List<NinjasModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());// lista todos os dados que tem no db
    }


    // Listas todos os ninjas por ID
    public NinjasDTO ListarNinjasID(Long id){
        Optional<NinjasModel> ninjaPorID = ninjaRepository.findById(id);
        return ninjaPorID.map(ninjaMapper::map).orElse(null); // caso o ninjas nao exista
    }

    // criar um novo ninja
    public NinjasDTO CriarNinja(NinjasDTO ninjasDTO){
        NinjasModel ninja = ninjaMapper.map(ninjasDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    // deletar um ninja - tem que ser um metodo void
    public void DeletarNinjaPorID(Long id) {
         ninjaRepository.deleteById(id);
    }

    // atualizar um ninja
    public NinjasDTO AtualizarNinja(Long id, NinjasDTO ninjasDTO){
        Optional<NinjasModel> Ninjaexistente = ninjaRepository.findById(id);

        if (Ninjaexistente.isPresent()){
            NinjasModel NinjaAtualizado = ninjaMapper.map(ninjasDTO);
            NinjaAtualizado.setId(id);
            NinjasModel ninjaSalvo = ninjaRepository.save(NinjaAtualizado);

            return ninjaMapper.map(ninjaSalvo);

        }
        return null;
    }



}
