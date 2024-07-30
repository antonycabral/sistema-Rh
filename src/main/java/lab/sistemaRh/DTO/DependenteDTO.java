package lab.sistemaRh.DTO;

import java.time.LocalDate;

public class DependenteDTO {
    private Long id;
    private String nome;
    private String parentesco;
    private String cpf;
    private LocalDate dataNascimento;
    public DependenteDTO() {
    }
    public DependenteDTO(Long id, String nome, String parentesco, String cpf, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.parentesco = parentesco;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
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
    public String getParentesco() {
        return parentesco;
    }
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
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

    
}