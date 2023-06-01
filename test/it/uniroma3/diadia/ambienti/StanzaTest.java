package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	private Stanza stanzaVuota;
	private Attrezzo attrezzo;
	private Stanza stanza;

	@Before
	public void setUp() {
		this.stanzaVuota = new Stanza("vuota");
		this.stanza = new Stanza("stanza");
		this.attrezzo = new Attrezzo("spada",12);
		this.stanza.addAttrezzo(attrezzo);
		this.stanza.impostaStanzaAdiacente(Direzione.SUD, stanza);
	}

	//INIZIO TEST: getAttrezzo()
	@Test
	public void testGetAttrezzoStanzaUnAttrezzoAssente() {
		assertEquals(null, stanza.getAttrezzo("martello"));
	}

	@Test
	public void testGetAttrezzoStanzaVuota() {
		assertEquals(null,stanzaVuota.getAttrezzo("spada"));
	}

	@Test
	public void testGetAttrezzoStanzaUnAttrezzoPresente() {
		assertEquals(attrezzo, stanza.getAttrezzo("spada"));
	}
	//FINE TEST

	//INIZIO TEST: hasAttrezzo()
	@Test
	public void testHasAttrezzoStanzaUnAttrezzoAssente() {
		assertEquals(false, stanza.hasAttrezzo("martello"));
	}

	@Test
	public void testHasAttrezzoStanzaVuota() {
		assertEquals(false,stanzaVuota.hasAttrezzo("spada"));
	}

	@Test
	public void testHasAttrezzoStanzaUnAttrezzoPresente() {
		assertEquals(true, stanza.hasAttrezzo("spada"));
	}
	//FINE TEST


	//INIZIO TEST: removeAttrezzo()
	@Test
	public void testRemoveAttrezzoStanzaVuota() {
		assertEquals(false, stanzaVuota.removeAttrezzo("spada"));
	}

	@Test
	public void testRemoveAttrezzoStanzaUnAttrezzoAssente() {
		assertEquals(false, stanza.removeAttrezzo("martello"));
	}

	@Test
	public void testRemoveAttrezzoStanzaUnAttrezzoPresente() {
		assertEquals(true, stanza.removeAttrezzo("spada"));
	}
	//FINE TEST

	//INIZIO TEST: getStanzaAdiacente()
	@Test
	public void testGetStanzaAdiacenteStanzaVuota() {
		assertEquals(null, stanzaVuota.getStanzaAdiacente(Direzione.NORD));
	}

	@Test
	public void testGetStanzaAdiacenteStanzaUnaAdiacenteSbagliata() {
		assertEquals(null, stanza.getStanzaAdiacente(Direzione.NORD));
	}

	@Test
	public void testGetStanzaAdiacenteStanzaUnaAdiacenteGiusta() {
		assertEquals(stanza, stanza.getStanzaAdiacente(Direzione.SUD));
	}
	//FINE TEST
}
