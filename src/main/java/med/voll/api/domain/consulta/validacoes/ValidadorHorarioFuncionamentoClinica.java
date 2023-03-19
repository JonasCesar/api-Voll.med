package med.voll.api.domain.consulta.validacoes;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorHorarioFuncionamentoClinica {
	
	public void validar(DadosAgendamentoConsulta dados) {
		LocalDateTime dataConsulta = dados.data();
		
		Boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		Boolean antesDaAberturaDaClinica = dataConsulta.getHour() < 8;
		Boolean depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
		
		if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
			throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica!");
		}
	}

}
