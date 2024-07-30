package lab.sistemaRh.DTO;

import java.time.LocalDate;
import java.util.List;


public class FuncionarioDTO {
    private Long id;
    private String nome;
    private String cargo;
    private Double salario;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private List<DependenteDTO> dependentes;
    
    
    public FuncionarioDTO() {
    }

    
    public FuncionarioDTO(Long id, String nome, String cargo, Double salario, String cpf, LocalDate dataNascimento,
            String email, List<DependenteDTO> dependentes) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.dependentes = dependentes;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public Double getSalario() {
        return salario;
    }
    public void setSalario(Double salario) {
        this.salario = salario;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<DependenteDTO> getDependentes() {
        return dependentes;
    }
    public void setDependentes(List<DependenteDTO> dependentes) {
        this.dependentes = dependentes;
    }

    
}