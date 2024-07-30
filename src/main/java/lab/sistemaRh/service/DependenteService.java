package lab.sistemaRh.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.sistemaRh.models.Dependente;
import lab.sistemaRh.repository.DependenteRepository;

@Service
public class DependenteService {

    @Autowired
    private DependenteRepository dependenteRepository;

    public List<Dependente> findAll() {
        return dependenteRepository.findAll();
    }

    public Dependente findById(Long id) {
        return dependenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Dependente not found"));
    }

    public Dependente save(Dependente dependente) {
        return dependenteRepository.save(dependente);
    }

    public void delete(Long id) {
        dependenteRepository.deleteById(id);
    }
}