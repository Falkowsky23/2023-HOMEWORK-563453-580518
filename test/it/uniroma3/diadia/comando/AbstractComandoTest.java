package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.AbstractComando;

public class AbstractComandoTest {
	
	FakeAbstractComando comando;


    @Before
    public void setUp() {
        comando = new FakeAbstractComando();
    }

    @Test
    public void testGetNome() {
        assertNull(comando.getNome());
    }

    @Test
    public void testGetSetParametro() {
        comando.setParametro("parametro");
        assertEquals("parametro", comando.getParametro());
    }
    
    @Test
    public void testSetNome() {
        comando.setNome("nome");
        assertEquals("nome", comando.getNome());
    }

    class FakeAbstractComando extends AbstractComando {
        @Override
        public void esegui(Partita partita) {}
    }
}
