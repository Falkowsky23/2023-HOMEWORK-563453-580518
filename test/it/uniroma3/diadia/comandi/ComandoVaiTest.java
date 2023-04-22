package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {

	private Comando comandoVai;
	private Partita partita;
	private Stanza partenza;
	private Stanza destinazione;
	private String direzioneSbagliata;
	private String direzione;
	private IO io;

	@Before
	public void setUp() {
		this.io = new IOConsole();
		this.partita = new Partita(io);
		this.direzione = "nord";
		this.direzioneSbagliata = "sud";
		this.comandoVai = new ComandoVai(direzione);
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
		DiaDia diadia = new DiaDia(io);
		diadia.gioca();
		assertTrue(io.hasMessaggio());
		assertEquals(io.mostraMessaggio(), DiaDia.MESSAGGIO_BENVENUTO);
		assertTrue(io.hasMessaggio());
		assertEquals(io.mostraMessaggio(), diadia.partita.getLabirinto().getStanzaCorrente().getDescrizione());
		assertTrue(io.hasMessaggio());
		assertEquals(io.mostraMessaggio(), "Cfu: " + diadia.partita.getCfu());
		assertTrue(io.hasMessaggio());
		assertEquals(io.mostraMessaggio(), "Grazie di aver giocato!");
	}
}