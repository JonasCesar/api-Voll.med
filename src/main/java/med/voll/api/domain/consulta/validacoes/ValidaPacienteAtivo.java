package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

@Component
public class ValidaPacienteAtivo implements ValidadorAgendamentoDeConsulta{
	
	@Autowired
	private PacienteRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		Boolean pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
		if(!pacienteEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser realizada com paciente excluído");
		}
	}

}
