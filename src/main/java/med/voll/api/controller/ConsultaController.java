package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultasService;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/consultas")
public class ConsultaController {
	
	@Autowired
	AgendaDeConsultasService agendaService;
	
	@PostMapping
	public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
		DadosDetalhamentoConsulta dto = agendaService.agendar(dados);
		return ResponseEntity.ok(dto);
	}

}
