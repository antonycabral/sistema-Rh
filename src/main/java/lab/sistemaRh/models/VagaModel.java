package lab.sistemaRh.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "vaga")
public class VagaModel implements Serializable{

    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String descricao;
	
	@NotEmpty
	private String data;
	
	@NotEmpty
	private String salario;
	
	@OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE)
	private List<CandidatoModel> candidatos;

	

	public VagaModel() {
	}

	

	public VagaModel(long codigo, @NotEmpty String nome, @NotEmpty String descricao, @NotEmpty String data,
			@NotEmpty String salario, List<CandidatoModel> candidatos) {
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
		this.salario = salario;
		this.candidatos = candidatos;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public List<CandidatoModel> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<CandidatoModel> candidatos) {
		this.candidatos = candidatos;
	}

    public Long getID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getID'");
    }

}
