package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	private Borsa borsaVuota;
	private Borsa borsa;
	private Borsa borsaPiena;
	private Borsa borsaOrdinata;
	private Attrezzo attrezzo;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private Attrezzo attrezzoPesante;
	private Attrezzo attrezzoOrdinato1;
	private Attrezzo attrezzoOrdinato2;

	@Before
	public void setUp() {

		this.borsaVuota = new Borsa();
		this.borsa = new Borsa();
		this.borsaPiena = new Borsa();
		this.borsaOrdinata = new Borsa();
		this.attrezzo = new Attrezzo("spada",5);
		this.attrezzo2 = new Attrezzo("lancia",5);
		this.attrezzo3 = new Attrezzo("fucile",2);
		this.attrezzoPesante = new Attrezzo("martello",12);
		this.borsa.addAttrezzo(attrezzo);
		this.borsaPiena.addAttrezzo(attrezzo);
		this.borsaPiena.addAttrezzo(attrezzo2);
		attrezzoOrdinato1 = new Attrezzo("aaaa", 3 );
		attrezzoOrdinato2 = new Attrezzo("bbbb", 2 );
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

	//INIZIO TEST: getContenutoOrdinatoPerNome;
	@Test
	public void testGetContenutoOrdinatoPerNome(){
		this.borsaOrdinata.addAttrezzo(attrezzoOrdinato1);
		this.borsaOrdinata.addAttrezzo(attrezzoOrdinato2);
		SortedSet<Attrezzo> attrezzi = this.borsaOrdinata.getContenutoOrdinatoPerNome();
		ComparatorePerNome comparatore = new ComparatorePerNome();
		SortedSet<Attrezzo> expected = new TreeSet<Attrezzo>(comparatore);
		expected.addAll(Arrays.asList(attrezzoOrdinato1, attrezzoOrdinato2));
		assertEquals(expected,attrezzi);
	}

	@Test
	public void testGetContenutoOrdinatoPerPeso(){
		this.borsaOrdinata.addAttrezzo(attrezzoOrdinato1);
		this.borsaOrdinata.addAttrezzo(attrezzoOrdinato2);
		List<Attrezzo> attrezzi = this.borsaOrdinata.getContenutoOrdinatoPerPeso();
		List<Attrezzo> expected = new ArrayList<Attrezzo>();
		expected.addAll(Arrays.asList(attrezzoOrdinato2, attrezzoOrdinato1));
		assertEquals(expected, attrezzi);
	}

	@Test
	public void testGetContenutoRagruppatoPerPeso(){
		this.borsaOrdinata.addAttrezzo(attrezzoOrdinato1);
		this.borsaOrdinata.addAttrezzo(attrezzoOrdinato2);
		Map<Integer, Set<Attrezzo>> attrezzi = this.borsaOrdinata.getContenutoRaggruppatoPerPeso();
		Map<Integer, Set<Attrezzo>> expected = new HashMap<>();
		expected.put(2, Collections.singleton(attrezzoOrdinato2));
		expected.put(3, Collections.singleton(attrezzoOrdinato1));
		assertEquals(expected, attrezzi);
	}

	@Test
	public void testGetSortedSetOrdinatoPerPeso(){
		this.borsaOrdinata.addAttrezzo(attrezzoOrdinato1);
		this.borsaOrdinata.addAttrezzo(attrezzoOrdinato2);
		SortedSet<Attrezzo> attrezzi = this.borsaOrdinata.getSortedSetOrdinatoPerPeso();
		ComparatorePerPeso comparatore = new ComparatorePerPeso();
		SortedSet<Attrezzo> expected = new TreeSet<Attrezzo>(comparatore);
		expected.addAll(Arrays.asList(attrezzoOrdinato1, attrezzoOrdinato2));
		assertEquals(expected, attrezzi);
	}
}
