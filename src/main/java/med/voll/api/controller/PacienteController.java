package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.DadosDetalhamentoPaciente;
import med.voll.api.domain.paciente.DadosListagemPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	
	@Autowired
	private PacienteRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
		
		Paciente paciente = new Paciente(dados);
		
		repository.save(paciente);
		
		URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
		
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao){
		Page page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
		return ResponseEntity.ok(page);
	}
	
	

}
