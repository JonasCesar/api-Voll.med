package med.voll.api.domain.consulta;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		Medico medico = medicoRepository.findById(dados.idMedico()).get();
		Paciente paciente = pacienteRepository.findById(dados.idPaciente()).get();
		
		Consulta consulta = new Consulta(null, medico, paciente, dados.data());
		consultaRepository.save(consulta);
		
	}

}
