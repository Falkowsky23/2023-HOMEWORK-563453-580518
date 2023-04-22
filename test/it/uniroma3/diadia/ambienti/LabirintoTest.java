package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {

	private Labirinto labirinto;
	private Stanza stanza;


	@Before
	public void setUp() {
		this.labirinto = new Labirinto();
		this.stanza = new Stanza("N20");
		this.labirinto.setStanzaCorrente(stanza);
	}

	//INIZIO TEST: getStanzaCorrente()
	@Test
	public void testGetStanzaCorrente() {
		assertNotNull(this.labirinto.getStanzaCorrente());
	}
	//FINE TEST
}