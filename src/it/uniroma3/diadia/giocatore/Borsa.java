package it.uniroma3.diadia.giocatore;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String,Attrezzo> attrezzi;
	private int pesoMax;
	private int peso;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.peso = 0;
		this.attrezzi = new HashMap<>(); 
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		this.peso += attrezzo.getPeso();
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		return this.peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public boolean removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = this.attrezzi.remove(nomeAttrezzo);
		if(a == null)
			return false;
		else {
			this.peso = this.peso - a.getPeso();
			return true;
		}
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (this.isEmpty())
			s.append("Borsa vuota");
		else {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			s.append(this.attrezzi.values().toString());
		}
		return s.toString();
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		ComparatorePerPeso comparatore = new ComparatorePerPeso();
		List<Attrezzo> attrezziOrdinati = new LinkedList<Attrezzo>(this.attrezzi.values());
		Collections.sort(attrezziOrdinati,comparatore);
		return attrezziOrdinati;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		ComparatorePerNome comparatore = new ComparatorePerNome();
		SortedSet<Attrezzo> attrezziSet = new TreeSet<Attrezzo>(comparatore);
		attrezziSet.addAll(this.attrezzi.values());
		return attrezziSet;
	}

	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>> mappa = new HashMap<>();
		for(Attrezzo a : this.attrezzi.values()){
			Set<Attrezzo> setAttrezzi = mappa.get(a.getPeso());
			if(setAttrezzi == null) {
				setAttrezzi = new HashSet<Attrezzo>();
				mappa.put(a.getPeso(), setAttrezzi);
			}
			setAttrezzi.add(a);
		}
		return mappa;
	}

	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		ComparatorePerPeso comparatore = new ComparatorePerPeso();
		SortedSet<Attrezzo> attrezziSet = new TreeSet<Attrezzo>(comparatore);
		attrezziSet.addAll(this.attrezzi.values());
		return attrezziSet;
	}
}