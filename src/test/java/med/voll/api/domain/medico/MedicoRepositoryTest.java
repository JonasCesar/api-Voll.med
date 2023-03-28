package med.voll.api.domain.medico;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest //essa anotação é utilizada para testar uma interface Repository (coisas da camada de persistência)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //para dizer ao spring não substituir as configurações de banco e usar o banco de memória (usar o banco de verdade)
@ActiveProfiles("test")
class MedicoRepositoryTest {

	@Test
	void escolherMedicoAleatorioLivreNaData() {
		
	}

}
