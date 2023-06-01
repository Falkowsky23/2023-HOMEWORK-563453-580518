package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {

	private Comando comandoVai;
	private Partita partita;
	private Stanza partenza;
	private Stanza destinazione;
	private Direzione direzioneSbagliata;
	private Direzione direzione;
	private IO io;
	private Labirinto labirinto;

	@Before
	public void setUp() {
		this.io = new IOConsole();
		this.labirinto = Labirinto.newBuilder().getLabirinto();
		this.partita = new Partita(labirinto,io);
		this.direzione = Direzione.NORD;
		this.direzioneSbagliata = Direzione.SUD;
		this.comandoVai = new ComandoVai();
		this.comandoVai.setParametro("nord");
		this.partenza = new Stanza("Partenza");
		this.destinazione = new Stanza("Destinazione");
		this.partita.getLabirinto().setStanzaCorrente(partenza);
	}

	@Test
	public void testVaiStanzaAssente() {
		comandoVai.esegui(partita);
		assertEquals("Partenza", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testVaiStanzaPresente() {
		partenza.impostaStanzaAdiacente(direzione, destinazione);
		comandoVai.esegui(partita);
		assertEquals("Destinazione", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiStanzaPresenteDirezioneSbagliata() {
		partenza.impostaStanzaAdiacente(direzioneSbagliata, destinazione);
		comandoVai.esegui(partita);
		assertEquals("Partenza", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testPartitaAutonoma() {
		String[] comandiDaEseguire = {"vai est","fine"};
		IOSimulator io = new IOSimulator(comandiDaEseguire);
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("inizio")
				.addStanza("fine")
				.addAttrezzo("fine","attrezzo", 2)
				.addAdiacenza("inizio","fine",Direzione.EST)
				.getLabirinto();
		DiaDia diadia = new DiaDia(labirinto,io);
		diadia.gioca();
		assertTrue(io.hasMessaggio());
		assertEquals(io.nextMessaggio(), DiaDia.MESSAGGIO_BENVENUTO);
		assertTrue(io.hasMessaggio());
		assertEquals(io.nextMessaggio(), diadia.partita.getLabirinto().getStanzaCorrente().getDescrizione());
		assertTrue(io.hasMessaggio());
		assertEquals(io.nextMessaggio(), "Cfu: " + diadia.partita.getCfu());
		assertTrue(io.hasMessaggio());
		assertEquals(io.nextMessaggio(), "Grazie di aver giocato!");
	}
}
