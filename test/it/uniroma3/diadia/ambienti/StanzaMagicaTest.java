package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
    private Stanza stanzaMagica;
    private Attrezzo attrezzo;
    private int soglia = 1;
    private int peso = 5;
    private String nomeAttrezzo = "attrezzo";
    private String nomeAttrezzoInvertito = "ozzertta";
    Attrezzo attrezzoAppoggio;

    @Before
    public void setUp() {
        stanzaMagica = new StanzaMagica("Magica",soglia);
        attrezzo = new Attrezzo(nomeAttrezzo , peso);
        attrezzoAppoggio = new Attrezzo("attrezzo1", 3);
    }

    @Test
    public void testPrimaVoltaAttrezzoAggiunto() {
        stanzaMagica.addAttrezzo(attrezzo);
        assertEquals(attrezzo, stanzaMagica.getAttrezzo(nomeAttrezzo));
    }

    @Test
    public void testSecondaVoltaAttrezzoAggiunto() {
        stanzaMagica.addAttrezzo(attrezzoAppoggio);
        stanzaMagica.addAttrezzo(attrezzo);
        assertNotNull(stanzaMagica.getAttrezzo(nomeAttrezzoInvertito));
        assertEquals(peso*2, stanzaMagica.getAttrezzo(nomeAttrezzoInvertito).getPeso());
    }

}