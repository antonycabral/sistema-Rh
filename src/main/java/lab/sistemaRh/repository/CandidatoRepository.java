package lab.sistemaRh.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import lab.sistemaRh.models.CandidatoModel;


public interface CandidatoRepository extends JpaRepository<CandidatoModel, Long> {

    boolean existsByCpf(String cpf);

}
