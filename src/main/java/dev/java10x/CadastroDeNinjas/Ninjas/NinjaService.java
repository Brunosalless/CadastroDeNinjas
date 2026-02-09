package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;

@Service // por ser uma camada de serviço
public class NinjaService {

    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    //Logica para listar todos os ninjas
    public List<NinjasModel> ListarNinjas(){
        return ninjaRepository.findAll(); // lista todos os dados que tem no db
    }
}
