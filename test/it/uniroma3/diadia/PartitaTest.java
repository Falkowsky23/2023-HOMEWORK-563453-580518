package it.uniroma3.diadia;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	private Partita terminata;
	private Partita vinta;
	private Partita persa;
	private Partita nonTerminata;
	private IOConsole io;

	@Before
	public void setUp() throws FormatoFileNonValidoException{
		this.io = new IOConsole();
		this.terminata = new Partita(new Labirinto() ,io);
		this.vinta = new Partita(new Labirinto(),io);
		this.persa = new Partita(new Labirinto(),io);
		this.nonTerminata = new Partita(new Labirinto(),io);
		this.terminata.setFinita();
		Stanza stanza = vinta.getLabirinto().getStanzaVincente();
		this.vinta.getLabirinto().setStanzaCorrente(stanza);
		this.persa.getGiocatore().setCfu(0);
	}

	//INIZIO TEST: isFinita()
	@Test
	public void testIsFinitaNonTerminata() {
		assertFalse(nonTerminata.isFinita());
	}
	
	@Test
	public void testIsFinitaTerminata() {
		assertTrue(terminata.isFinita());
	}

	@Test
	public void testIsFinitaVinta() {
		assertTrue(vinta.isFinita());
	}

	@Test
	public void testIsFinitaPersa() {
		assertTrue(persa.isFinita());
	}

	//FINE TEST

}
