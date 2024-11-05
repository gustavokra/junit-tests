# O básico para testar

Adicionar a dependência junit jupiter engine

> https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine

## Assertions

Assertions.assertTrue()
Assertions.assertFalse()

Assertions.assertNull()
Assertions.assertNotNull()

Assertions.assertEquals(valor, valorTestar)
Assertions.assertNotEquals(valor, valorTestar)

Assertions.assertArrayEquals(valor, valorTestar)
Assertions.assertArrayNotEquals(valor, valorTestar)

Pode ser importado estaticamente para não precisar repetir *Assertions*: 
```
import static org.junit.jupiter.api.Assertions.assertArrayEquals;  
import static org.junit.jupiter.api.Assertions.assertEquals;  
import static org.junit.jupiter.api.Assertions.assertNull;  
import static org.junit.jupiter.api.Assertions.assertNotNull;
```

ou somente importar tudo:
```
import static org.junit.jupiter.api.Assertions.*;
```


## BeforeAll e AfterAll
Executam uma vez antes do inicio dos testes e uma no final dos testes.
Pode ser usado para inciar e finalizar uma conexão com banco de dados.

## BeforeEach e AfterEacho
Executam antes e depois de cada teste escrito com @Test.
Pode ser usado para inserir e remover dados no incio e no fim dos testes, respectivamente.

## Assumptions
Assumptions valida algo de acordo com uma suposição.
Exemplo: Só validar se o usuário for gustavo.
```
@Test  
void validarAlgoSOmenteNoUsuarioGustavo() {  
    Assumptions.assumeTrue("gustavo-kraemer".equals(System.getenv("USER")));  
    Assertions.assertEquals(10, 5 + 5);  
}
```

## Testes condicionais
Desabilita ou abilita um teste dado uma condição.
Exemplo:
```
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
@EnabledOnJre({JRE.JAVA_10, JRE.JAVA_22})  
void testeEmUmaJREEscolhida() {  
    Assertions.assertEquals(10, 5 + 5);  
}  
  
@Test  
@EnabledForJreRange(min = JRE.JAVA_10, max = JRE.JAVA_17)  
void testeEmUmRangeDeJREs() {  
    Assertions.assertEquals(10, 5 + 5);  
}
```

## Testando Exceptions
Para testar um caso onde deva ocorrer uma exception, deve ser usado o Assertions.assertThrows(Exeception.class, () -> metodoParaAcontecerExeptions);

Exemplo: 
```
@Test  
void validarExecessaoTransferencia() {  
    Conta contaOrigem = new Conta("1", 0);  
    Conta contaDestino = new Conta("2", 100);  
  
    TransferenciaEntreContas transferenciaEntreContas = new  TransferenciaEntreContas();  
    Assertions.assertThrows(IllegalArgumentException.class, () ->  
            transferenciaEntreContas.transfere(contaOrigem, contaDestino, -1));  
}
```

Podemos testar também se NÃO será lançada uma exceção:
```
@Test  
void validarNaoExecessaoTransferencia() {  
    Conta contaOrigem = new Conta("1", 0);  
    Conta contaDestino = new Conta("2", 100);  
  
    TransferenciaEntreContas transferenciaEntreContas = new TransferenciaEntreContas();  
    Assertions.assertDoesNotThrow(() ->  
            transferenciaEntreContas.transfere(contaOrigem, contaDestino, 1));  
}
```


## Ordenando Testes
Por padrão(*default*), os testes são executados na ordem em que foram escritos, independente de seu nome. Porém, é possível escolher a ordem que os testes serão executados, usando uma das seguintes anotações acima da declaração da classe: 
```
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
```
```
@TestMethodOrder(MethodOrderer.MethodName.class)
```
```
@TestMethodOrder(MethodOrderer.Random.class)
```
```
@TestMethodOrder(MethodOrderer.DisplayName.class)
```

No primeiro caso, devemos escolher a ordem dos testes pela anotação @Order(*numero*)
Exemplo: 
```
import org.junit.jupiter.api.*;  
  
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  
public class EscolhendoOrdemTest {  
  
    @Order(4)  
    @Test  
    void validaFluxoA() {  
        Assertions.assertEquals(10, 5 + 5);  
    }  
  
    @Order(2)  
    @Test  
    void validaFluxoB() {  
        Assertions.assertEquals(10, 5 + 5);  
    }  
  
    @Order(3)  
    @Test  
    void validaFluxoC() {  
        Assertions.assertEquals(10, 5 + 5);  
    }  
  
    @Order(1)  
    @Test  
    void validaFluxoD() {  
        Assertions.assertEquals(10, 5 + 5);  
    }  
}
```
No segundo caso, *MethodName*, será executado os testes em ordem alfabética de sua assinatura, sendo desnecessário a anotação *@Order()*.

No terceiro caso, *Random*, será executado de forma aleatória.

No quarto caso, *DisplayName*, será executado na ordem alfabética do display name escolhido:
```
@DisplayName("B")  
@Test  
void validaFluxoA() {  
    Assertions.assertEquals(10, 5 + 5);  
}  
  
@DisplayName("A")  
@Test  
void validaFluxoB() {  
    Assertions.assertEquals(10, 5 + 5);  
}  
  
@DisplayName("C")  
@Test  
void validaFluxoC() {  
    Assertions.assertEquals(10, 5 + 5);  
}  
  
@DisplayName("D")  
@Test  
void validaFluxoD() {  
    Assertions.assertEquals(10, 5 + 5);  
}
```
Ou seja, executará primeiro o teste com *@DisplayName("A")*, depois o *@DisplayName("B")*. 
