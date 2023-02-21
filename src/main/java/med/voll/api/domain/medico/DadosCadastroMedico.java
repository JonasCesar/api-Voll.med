package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(
		@NotBlank
		String nome,
		@NotBlank
		@Email
		String email,
		@NotBlank
		String telefone,
		@NotBlank
		@Pattern(regexp = "\\d{4,6}", message = "CRM deve possuir de 4 a seis dígitos")
		String crm, 
		@NotNull
		Especialidade especialidade,
		@Valid
		DadosEndereco endereco) {

}