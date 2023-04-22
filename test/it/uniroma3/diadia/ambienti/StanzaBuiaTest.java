package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	private static final String ATTREZZO_LUCE_TEST = "attrezzoLuceTest";
	private StanzaBuia stanzaBuia;

	private static final String DESCRIZIONE_STANZA = "StanzaBuia\n" + 
			"Uscite: \n" + 
			"Attrezzi nella stanza: attrezzoLuceTest (1kg) ";

	@Before
	public void setUp() {
		this.stanzaBuia = new StanzaBuia("StanzaBuia", ATTREZZO_LUCE_TEST);
	}

	@Test
	public void testGetDescrizioneLuceNonPresente() {
		assertEquals(StanzaBuia.DESCRIZIONE_STANZA_BUIA, this.stanzaBuia.getDescrizione());
	}

	@Test
	public void testGetDescrizioneConLuce() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO_LUCE_TEST,1);
		this.stanzaBuia.addAttrezzo(attrezzo);
		assertEquals(DESCRIZIONE_STANZA, this.stanzaBuia.getDescrizione());
	}

	@Test
	public void testGetAttrezzoInesistente() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO_LUCE_TEST,1);
		this.stanzaBuia.addAttrezzo(attrezzo);
		assertNull(this.stanzaBuia.getAttrezzo("inesistente"));
	}
}