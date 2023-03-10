package med.voll.api.domain.consulta;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class AgendaDeConsultasService {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public void agendar(DadosAgendamentoConsulta dados) {
		
		if(!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("Id do paciente informado não existe!");
		}
		
		if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("Id do médico informado não existe!");
		}
		
		Medico medico = escolherMedico(dados);
		Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		
		Consulta consulta = new Consulta(null, medico, paciente, dados.data());
		consultaRepository.save(consulta);
		
	}

	private Medico escolherMedico(DadosAgendamentoConsulta dados) {
		if(dados.idMedico() != null) {
			return medicoRepository.getReferenceById(dados.idMedico());
		}
		
		if(dados.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatória quando nenhum médico for escolhido");
		}
		
		return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
	}

}
