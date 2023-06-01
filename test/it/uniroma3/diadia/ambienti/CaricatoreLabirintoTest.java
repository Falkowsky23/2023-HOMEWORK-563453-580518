package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import org.junit.Test;

public class CaricatoreLabirintoTest {
	
	private String DESCRIZIONE_LABIRINTO 
			= "Stanze: Biblioteca, N10 \n"
			+ "Stanze Bloccate: \n"
			+ "Stanze Buie: \n"
			+ "Stanze Magiche: \n"
			+ "Inizio: N10\n"
			+ "Vincente: Biblioteca \n"
			+ "Attrezzi: Osso 5 N10 \n"
			+ "Uscite: N10 nord Biblioteca \n"
			+ "Cani: \n"
			+ "Maghi: \n"
			+ "Streghe: \n";

	@Test
	public void testCarica() throws FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(DESCRIZIONE_LABIRINTO));
		caricatore.carica();
		Labirinto labirinto = caricatore.getLabirinto();
		assertEquals("N10", labirinto.getStanzaCorrente().getNome());
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
		assertEquals("Osso", labirinto.getStanzaCorrente().getAttrezzo("Osso").getNome());
		assertEquals(5, labirinto.getStanzaCorrente().getAttrezzo("Osso").getPeso());
	}
}