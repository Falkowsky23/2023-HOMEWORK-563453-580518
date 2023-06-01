
package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";

	private static final String STANZE_BLOCCATE_MARKER = "Stanze Bloccate:"; 

	private static final String STANZE_BUIE_MARKER = "Stanze Buie:"; 

	private static final String STANZE_MAGICHE_MARKER = "Stanze Magiche:"; 

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	private static final String CANE_MARKER = "Cani:";

	private static final String MAGO_MARKER = "Maghi:";

	private static final String STREGA_MARKER = "Streghe:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Stanze Bloccate: N18 nord pinza
		Stanze Buie: AulaCampus lanterna
		Stanze Magiche: Atrio
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10, osso 3 N11, lanterna 4 N18
		Uscite: biblioteca nord N10, biblioteca sud N11, biblioteca est N18, N18 nord AulaCampus, N18 sud Atrio
		Cani: Ugo WOF WOF- osso dente 1 N10
		Maghi: Pippo Ciao sono Pippo il mago!- bacchetta 2 biblioteca
		Streghe: Marcella Ciao sono Marcella la strega!- N18

	 */
	private BufferedReader reader;

	private List<String> stanze;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private LabirintoBuilder labirinto;
	private int numeroRiga; 


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.stanze = new ArrayList<String>();
		this.reader = new BufferedReader(new FileReader(nomeFile));
		this.labirinto = Labirinto.newBuilder();
		this.numeroRiga = 0;
	}

	public CaricatoreLabirinto(StringReader nomeFile){
		this.stanze = new ArrayList<String>();
		this.reader = new BufferedReader(nomeFile);
		this.labirinto = new LabirintoBuilder();
		this.numeroRiga = 0;
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeMagiche();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECreaCani();
			this.leggiECreaMaghi();
			this.leggiECreaStreghe();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			this.numeroRiga++;
			check(riga != null, "Non hai inserito il marker " + marker);
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.labirinto.addStanza(nomeStanza.trim());
			this.stanze.add(nomeStanza.trim());
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException{
		String stanze = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER).trim();
		for(String specificaStanza : separaStringheAlleVirgole(stanze)) {
			String nomeStanza = null;
			String direzioneBloccata = null;
			String nomeAttrezzo = null; 
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza bloccata."));
				nomeStanza = scannerLinea.next().trim();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata di "+nomeStanza+"."));
				direzioneBloccata = scannerLinea.next().trim();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo che sblocca la direzione "+direzioneBloccata+"."));
				nomeAttrezzo = scannerLinea.next().trim();
			}
			Direzione dirBloccata = null;
			try{
				dirBloccata = Direzione.valueOf(direzioneBloccata.toUpperCase());
			}
			catch(Exception e) {
				check(false, msgTerminazionePrecoce("Direzione inesistente, seggliere una delle coordinate geografiche"));
			}
			
			this.labirinto.addStanzaBloccata(nomeStanza, dirBloccata, nomeAttrezzo);
			this.stanze.add(nomeStanza);
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException{
		String stanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER).trim();
		for(String specificaStanza : separaStringheAlleVirgole(stanze)) {
			String nomeStanza = null;
			String nomeAttrezzo = null; 
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza buia."));
				nomeStanza = scannerLinea.next().trim();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo per vedere in "+nomeStanza+"."));
				nomeAttrezzo = scannerLinea.next().trim();
			}
			this.labirinto.addStanzaBuia(nomeStanza, nomeAttrezzo);
			this.stanze.add(nomeStanza);
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException{
		String stanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER).trim();
		for(String nomeStanza : separaStringheAlleVirgole(stanze)) {
			this.labirinto.addStanzaMagica(nomeStanza.trim());
			this.stanze.add(nomeStanza.trim());
		}
	}

	private void leggiECreaCani() throws FormatoFileNonValidoException{
		String cani = this.leggiRigaCheCominciaPer(CANE_MARKER).trim();
		for(String specificheCane : separaStringheAiTrattini(cani)) {
			String nomeCane = null;
			String descrizione = null;
			String nomeCibo = null;
			String nomeRegalo = null;
			String pesoRegalo = null;
			String nomeStanza = null;
			try(Scanner scanner = new Scanner(specificheCane)){
				scanner.useDelimiter(",");
				check(scanner.hasNext(),msgTerminazionePrecoce("il nome di un cane."));
				nomeCane = scanner.next().trim();
				check(scanner.hasNext(),msgTerminazionePrecoce("la presentazione di "+nomeCane+"."));
				descrizione = scanner.next().trim();
				check(scanner.hasNext(),msgTerminazionePrecoce("il cibo preferito di "+nomeCane+"."));
				nomeCibo = scanner.next().trim();
				check(scanner.hasNext(),msgTerminazionePrecoce("l'attrezzo regalato da "+nomeCane+"."));
				nomeRegalo = scanner.next().trim();
				check(scanner.hasNext(),msgTerminazionePrecoce("il peso di "+nomeRegalo+"."));
				pesoRegalo = scanner.next().trim();
				check(scanner.hasNext(),msgTerminazionePrecoce("la stanza di "+nomeCane+"."));
				nomeStanza = scanner.next().trim();
			}
			try {
				Integer peso = Integer.parseInt(pesoRegalo);
				Attrezzo regalo = new Attrezzo(nomeRegalo, peso);
				this.labirinto.addCane(nomeCane, descrizione, nomeCibo, regalo, nomeStanza);
			}catch (NumberFormatException e) {
				check(false, "Peso attrezzo "+nomeRegalo+" non valido");
			}

		}
	}

	private List<String> separaStringheAiTrattini(String string) {
		List<String> result;
		if(string.equals("")) {
			result = Collections.emptyList();
		}
		else {
			result = new LinkedList<>(Arrays.asList(string.split("-")));
		}
		return result;
	}

	private void leggiECreaMaghi() throws FormatoFileNonValidoException{
		String maghi = this.leggiRigaCheCominciaPer(MAGO_MARKER).trim();
		for(String specificheMago : separaStringheAiTrattini(maghi)) {
			String nomeMago = null;
			String descrizione = null;
			String nomeRegalo = null;
			String pesoRegalo = null;
			String nomeStanza = null;
			try(Scanner scanner = new Scanner(specificheMago)){
				scanner.useDelimiter(",");
				check(scanner.hasNext(),msgTerminazionePrecoce("il nome di un mago."));
				nomeMago = scanner.next().trim();
				check(scanner.hasNext(),msgTerminazionePrecoce("la presentazione di "+nomeMago+"."));
				descrizione = scanner.next().trim();
				check(scanner.hasNext(),msgTerminazionePrecoce("l'attrezzo regalato da "+nomeMago+"."));
				nomeRegalo = scanner.next().trim();
				check(scanner.hasNext(),msgTerminazionePrecoce("il peso di "+nomeRegalo+"."));
				pesoRegalo = scanner.next().trim();
				check(scanner.hasNext(),msgTerminazionePrecoce("la stanza di "+nomeMago+"."));
				nomeStanza = scanner.next().trim();
			}
			try {
				Integer peso = Integer.parseInt(pesoRegalo);
				Attrezzo regalo = new Attrezzo(nomeRegalo, peso);
				this.labirinto.addMago(nomeMago, descrizione, regalo, nomeStanza);
			}catch (NumberFormatException e) {
				check(false, "Peso attrezzo "+nomeRegalo+" non valido");
			}

		}
	}

	private void leggiECreaStreghe() throws FormatoFileNonValidoException{
		String streghe = this.leggiRigaCheCominciaPer(STREGA_MARKER).trim();
		for(String specificheStrega : separaStringheAiTrattini(streghe)) {
			String nomeStrega = null;
			String descrizione = null;
			String nomeStanza = null;
			try(Scanner scanner = new Scanner(specificheStrega)){
				scanner.useDelimiter(",");
				check(scanner.hasNext(),msgTerminazionePrecoce("il nome di un cane."));
				nomeStrega = scanner.next().trim();
				check(scanner.hasNext(),msgTerminazionePrecoce("la presentazione di "+nomeStrega+"."));
				descrizione = scanner.next().trim();
				check(scanner.hasNext(),msgTerminazionePrecoce("la stanza di "+nomeStrega+"."));
				nomeStanza = scanner.next().trim();
			}

			this.labirinto.addStrega(nomeStrega, descrizione, nomeStanza);

		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result;
		if(string.equals("")) {
			result = Collections.emptyList();
		}
		else {
			result = new LinkedList<>(Arrays.asList(string.split(",")));
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER).trim();
		check(isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale + msgTerminazionePrecoce(" non definita"));
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER).trim();
		check(isStanzaValida(nomeStanzaVincente), nomeStanzaIniziale + msgTerminazionePrecoce(" non definita"));
		this.labirinto.addStanzaIniziale(nomeStanzaIniziale);
		this.labirinto.addStanzaVincente(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER).trim();

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.labirinto.addAttrezzo(nomeStanza, nomeAttrezzo, peso);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.stanze.contains(nomeStanza.trim());
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String uscite = this.leggiRigaCheCominciaPer(USCITE_MARKER).trim();
		for(String specificheUscite : separaStringheAlleVirgole(uscite) ) {
			try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {			

				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next().trim();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next().trim();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next().trim();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			} 
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Direzione direzione = null;
		try{
			direzione = Direzione.valueOf(dir.toUpperCase());
		}
		catch(Exception e) {
			check(false, msgTerminazionePrecoce("Direzione inesistente, seggliere una delle coordinate geografiche"));
		}
		this.labirinto.addAdiacenza(stanzaDa, nomeA, direzione);
		Direzione direzioneOpposta = direzione.direzioneOpposta();
		this.labirinto.addAdiacenza(nomeA, stanzaDa, direzioneOpposta);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.numeroRiga + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Labirinto getLabirinto() {
		return this.labirinto.getLabirinto();
	}
}