package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(		
		@JsonAlias("id_medico")
		Long idMedico,
		@NotNull
		@JsonAlias("id_paciente")
		Long idPaciente,
		@NotNull
		@Future //para deixar passar datas apenas do futuro
		@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
		LocalDateTime data) {

}
