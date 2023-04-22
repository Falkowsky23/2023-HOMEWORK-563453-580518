package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	private Borsa borsaVuota;
	private Borsa borsa;
	private Borsa borsaPiena;
	private Attrezzo attrezzo;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private Attrezzo attrezzoPesante;

	@Before
	public void setUp() {

		this.borsaVuota = new Borsa();
		this.borsa = new Borsa();
		this.borsaPiena = new Borsa();
		this.attrezzo = new Attrezzo("spada",5);
		this.attrezzo2 = new Attrezzo("lancia",5);
		this.attrezzo3 = new Attrezzo("fucile",2);
		this.attrezzoPesante = new Attrezzo("martello",12);
		this.borsa.addAttrezzo(attrezzo);
		this.borsaPiena.addAttrezzo(attrezzo);
		this.borsaPiena.addAttrezzo(attrezzo2);
	}
	
	//INIZIO TEST: getAttrezzo()
    @Test
    public void testGetAttrezzoBorsaVuota() {
        assertNull(this.borsaVuota.getAttrezzo("spada"));
    }

    @Test
    public void testGetAttrezzoBorsaAttrezzoSbagliato() {
        assertNull(this.borsa.getAttrezzo("martello"));
    }

    @Test
    public void testGetAttrezzoBorsaAttrezzoGiusto() {
        assertNotNull(this.borsa.getAttrezzo("spada"));
    }
    //FINE TEST

    //INIZIO TEST: removeAttrezzo()
    @Test
    public void testRemoveAttrezzoBorsaVuota(){
        assertFalse(this.borsaVuota.removeAttrezzo("spada"));
    }

    @Test
    public void testRemoveAttrezzoBorsaAttrezzoAssente(){
        assertFalse(this.borsa.removeAttrezzo("martello"));
    }

    @Test
    public void testRemoveAttrezzoBorsaAttrezzoPresente(){
        assertTrue(this.borsa.removeAttrezzo("spada"));
    }
    //FINE TEST

    //INIZIO TEST: hasAttrezzo()
    @Test
    public void testHasAttrezzoBorsaVuota(){
        assertFalse(this.borsaVuota.hasAttrezzo("spada"));
    }

    @Test
    public void testHasAttrezzoBorsaAttrezzoAssente(){
        assertFalse(this.borsa.hasAttrezzo("martello"));
    }
    @Test
    public void testHasAttrezzoBorsaAttrezzoPresente(){
        assertTrue(this.borsa.hasAttrezzo("spada"));
    }
    //FINE TEST
    
  //INIZIO TEST: addAttrezzo()
    @Test
    public void testaddAttrezzoBorsaVuota(){
        assertTrue(this.borsaVuota.addAttrezzo(attrezzo));
    }

    @Test
    public void testAddAttrezzoBorsaVuotaAttrezzoPesante(){
        assertFalse(this.borsaVuota.addAttrezzo(attrezzoPesante));
    }
    @Test
    public void testAddAttrezzoBorsaPiena(){
        assertFalse(this.borsaPiena.addAttrezzo(attrezzo3));
    }
    //FINE TEST

    //INIZIO TEST: getPeso()
    @Test
    public void testGetPesoBorsaVuota(){
        assertEquals(0,this.borsaVuota.getPeso());
    }

    @Test
    public void testGetPesoBorsaUnAttrezzo(){
        assertEquals(5,this.borsa.getPeso());
    }

    @Test
    public void testGetPesoBorsaPiena(){
        assertEquals(10,this.borsaPiena.getPeso());
    }
    //FINE TEST
}