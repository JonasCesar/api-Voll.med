package med.voll.api.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Página não encontrada");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
		var erros = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosValidacao::new).toList());
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity tratarErro400MessageNotReadable(HttpMessageNotReadableException ex) {
		return ResponseEntity.badRequest().body(new HttpMessageNotReadableRecord(ex));
	}
	
	private record DadosErrosValidacao(String campo, String mensagem) {
		public DadosErrosValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
	
	private record HttpMessageNotReadableRecord(String erro) {
		public HttpMessageNotReadableRecord(HttpMessageNotReadableException erro) {
			this(erro.getMessage());
		}
	}

}
