package motor

import datos.RepositorioFrases
import modelo.Frase
import modelo.JugadorIA
import modelo.ResultadoRuleta
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

class MotorTest {

    private lateinit var motor: MotorJuego
    
    // Repositorio falso para pruebas
    private val repoTest = object : RepositorioFrases {
        override fun obtenerFraseAleatoria() = Frase("Tests", "TEST")
        override fun obtenerTodas() = listOf(Frase("Tests", "TEST"))
    }

    @BeforeEach
    fun setup() {
        // Usamos JugadorIA para no depender de interacción humana
        val jugadores = listOf(JugadorIA("BotTest"))
        motor = MotorJuego(repoTest, jugadores)
        motor.iniciarRonda() 
    }

    @Test
    fun `girarRuleta debe devolver un resultado valido`() {
        val resultado = motor.girarRuleta()
        assertNotNull(resultado)
        assertTrue(
            resultado is ResultadoRuleta.Premio || 
            resultado is ResultadoRuleta.Quiebra || 
            resultado is ResultadoRuleta.PierdeTurno
        )
    }

    /* DESCOMENTAR CUANDO SE IMPLEMENTE LA LÓGICA
    @Test
    fun `probarLetra acierta correctamente y suma dinero`() {
        // Mockear resultado ruleta si fuera necesario o asumir estado inicial
        val acierto = motor.probarLetra('T') // Frase es "TEST"
        assertTrue(acierto, "La T debería estar en TEST")
        
        // Verificar que sumó dinero si veníamos de una tirada con premio
        // Esto requeriría exponer estado del motor o del jugador
    }

    @Test
    fun `probarLetra falla correctamente`() {
        val acierto = motor.probarLetra('Z') // Frase es "TEST"
        assertFalse(acierto, "La Z NO debería estar en TEST")
    }
    
    @Test
    fun `resolverPanel funciona`() {
        assertTrue(motor.resolverPanel("TEST"))
        assertFalse(motor.resolverPanel("OTRA COSA"))
    }
    */
}
