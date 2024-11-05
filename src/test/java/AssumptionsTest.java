import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class AssumptionsTest {

    @Test
    void validarAlgoAssumindoNomeDoUsuario() {
        Assumptions.assumeTrue("gustavo-kraemer".equals(System.getenv("USER")));
        Assertions.assertEquals(10, 5 + 5);
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "USER", matches = "gustavo-kraemer")
    void testeCondicionalNomeUsuario() {
        Assertions.assertEquals(10, 5 + 5);
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
    void testeSomenteQuandoOsForLinux() {
        Assertions.assertEquals(10, 5 + 5);
    }

    @Test
    @EnabledOnJre({JRE.JAVA_10, JRE.JAVA_21})
    void testeEmUmaJREEscolhida() {
        Assertions.assertEquals(10, 5 + 5);
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_10, max = JRE.JAVA_21)
    void testeEmUmRangeDeJREs() {
        Assertions.assertEquals(10, 5 + 5);
    }

    @Test
    void validarResultadoSoma() {
        Assertions.assertEquals(10, 5 + 5);
    }

}
