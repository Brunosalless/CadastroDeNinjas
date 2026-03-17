package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // por ser uma camada de serviço
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Logica para, listar todos os ninjas
    public List<NinjasDTO> ListarNinjas(){
         List<NinjasModel> ninjas = ninjaRepository.findAll(); //lista todos os dados que tem no db
        return ninjas.stream()   //stream() = transforma sua lista em uma sequência processável, permitindo usar operações como map, filter, collect para transformar dados de forma elegante.
                .map(ninjaMapper::map)   // converte o NinjasModel para Ninjas DTO
                .collect(Collectors.toList());  // pega o resultado que está passando pelo Stream e transforma de volta em uma Lista (List).
    }

    // Listas todos os ninjas por ID
    public NinjasDTO ListarNinjasID(Long id){
        Optional<NinjasModel> ninjaPorID = ninjaRepository.findById(id);
        return  ninjaPorID.map(ninjaMapper::map).orElse(null);   // caso o ninjas nao exista
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

    public NinjasDTO AtualizarNinja(Long id, NinjasDTO ninjasDTO){
        Optional<NinjasModel> ninjaexistente = ninjaRepository.findById(id); // procura pelo id
        if (ninjaexistente.isPresent()) { // se o id existir entao
            NinjasModel ninjaAtualizado =ninjaMapper.map(ninjasDTO); // realiza o mapper do DTO
            ninjaAtualizado.setId(id); // sobreescreve o id
            NinjasModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado); // salva os dados atulizado do ninja
            return ninjaMapper.map(ninjaSalvo); // retorna o ninja salvo
        }
        return null; // caso nao aja nenhum id
    }



    /*
    metodos sem o dto(antigo)
    public NinjasModel AtualizarNinja(Long id, NinjasModel ninjaAtualizado){
        if (ninjaRepository.existsById(id)){
            ninjaAtualizado.setId(id);
            return ninjaRepository.save(ninjaAtualizado);
        }
        return null;
    }
    */


}
