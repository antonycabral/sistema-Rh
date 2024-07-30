package lab.sistemaRh.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VagaRecordDto(@NotBlank Long codigo,@NotNull String nome) {

}
