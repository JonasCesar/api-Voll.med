package med.voll.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var tokenJWT = recuperarToken(request);		
		
		var subject = tokenService.getSubject(tokenJWT);
		
		System.out.println(subject);
		
		filterChain.doFilter(request, response); //para continuar a execução da aplicação (chamar os próximos filtros ou ir direto pro controller)
	}

	private String recuperarToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader == null)	{
			throw new RuntimeException("Token JWT não enviado no cabeçalho Authorization!");
		}
		
		return authorizationHeader.replace("Bearer ", "");
	}

}
