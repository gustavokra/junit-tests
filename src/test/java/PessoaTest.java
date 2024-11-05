import org.example.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PessoaTest {

    @Test
    void deveCalcularIdadeCorretamente() {
        Pessoa pessoa = new Pessoa("julia", LocalDate.of(2020, 1, 1));
        Assertions.assertEquals(4, pessoa.getIdade());
    }

    @Test
    void deveVerificarSeMaiorDeIdade() {
        Pessoa pessoaMenor = new Pessoa("julia", LocalDate.of(2020, 1, 1));
        Assertions.assertFalse(pessoaMenor.isMaiorDeIdade());

        Pessoa pessoa18Anos = new Pessoa("julia", LocalDate.of(2006, 1, 1));
        Assertions.assertTrue(pessoa18Anos.isMaiorDeIdade());

        Pessoa maior18Anos = new Pessoa("julia", LocalDate.of(2005, 1, 1));
        Assertions.assertTrue(maior18Anos.isMaiorDeIdade());
    }
}
