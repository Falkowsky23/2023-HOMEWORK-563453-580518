package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	
	private Giocatore giocatore;
	
	@Before
	public void setUp(){
		this.giocatore = new Giocatore();
		this.giocatore.setCfu(10);
	}
	
	//INIZIO TEST: getCfu()
	@Test
	public void testGetCfu() {
		assertEquals(10,this.giocatore.getCfu());
	}
}
