package it.uniroma3.diadia;

import java.io.IOException;
import java.util.Properties;

public class Configurazione {
	private static final String DIADIA_PROPERTIES = "diadia.properties";
	private static final String PESO_MAX = "pesoMax";
	private static final String CFU = "cfu";
	private static final String ATTREZZI_STANZA_MAX = "attrezziStanzaMax";
	private static final String SOGLIA_MAGICA = "sogliaMagica";
	private static Properties prop = null;
	

	public static int getCFU() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(CFU));
	}

	public static int getPesoMax() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(PESO_MAX));
	}
	
	public static int getAttrezziStanzaMax() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(ATTREZZI_STANZA_MAX));
	}
	
	public static int getSogliaMagica() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(SOGLIA_MAGICA));
	}

	private static void carica() {
		prop = new Properties();
		try {
			prop.load(Configurazione.class.getClassLoader().getResourceAsStream(DIADIA_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

