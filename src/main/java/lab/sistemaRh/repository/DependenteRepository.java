package lab.sistemaRh.repository;

import lab.sistemaRh.models.Dependente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Long> {
    void deleteByFuncionarioId(Long funcionarioId);
}