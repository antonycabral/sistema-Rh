package lab.sistemaRh.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "candidato")
public class CandidatoModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@Column(unique = true)
	private String cpf;
	
	@NotEmpty
	private String nomeCandidato;
	
	@NotEmpty
	private String email;
	
	@ManyToOne
	private VagaModel vaga;

	

	public CandidatoModel() {
	}

	public CandidatoModel(Long id, String cpf, @NotEmpty String nomeCandidato, @NotEmpty String email, VagaModel vaga) {
		Id = id;
		this.cpf = cpf;
		this.nomeCandidato = nomeCandidato;
		this.email = email;
		this.vaga = vaga;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCandidato() {
		return nomeCandidato;
	}

	public void setNomeCandidato(String nomeCandidato) {
		this.nomeCandidato = nomeCandidato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public VagaModel getVaga() {
		return vaga;
	}

	public void setVaga(VagaModel vaga) {
		this.vaga = vaga;
	}



	
	
}
