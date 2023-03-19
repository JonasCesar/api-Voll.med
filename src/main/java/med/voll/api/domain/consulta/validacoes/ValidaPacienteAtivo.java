package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidaPacienteAtivo {
	
	private PacienteRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		Boolean pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
		if(!pacienteEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser realizada com paciente excluído");
		}
	}

}
