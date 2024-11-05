import org.example.Conta;
import org.example.TransferenciaEntreContas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionsTest {

    @Test
    void validarExecessaoTransferencia() {
        Conta contaOrigem = new Conta("1", 0);
        Conta contaDestino = new Conta("2", 100);

        TransferenciaEntreContas transferenciaEntreContas = new TransferenciaEntreContas();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                transferenciaEntreContas.transfere(contaOrigem, contaDestino, -1));
    }

    @Test
    void validarNaoExecessaoTransferencia() {
        Conta contaOrigem = new Conta("1", 0);
        Conta contaDestino = new Conta("2", 100);

        TransferenciaEntreContas transferenciaEntreContas = new TransferenciaEntreContas();
        Assertions.assertDoesNotThrow(() ->
                transferenciaEntreContas.transfere(contaOrigem, contaDestino, 1));
    }
}
