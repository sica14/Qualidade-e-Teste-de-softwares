import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraTest {
    private final Calculadora calculadora = new Calculadora();

    @Test
    void deveSomarDoisNumeros() {
        assertEquals(5, calculadora.somar(2, 3));
    }

    @Test
    void deveMultiplicarDoisNumeros() {
        assertEquals(12, calculadora.multiplicar(3, 4));
    }
}
