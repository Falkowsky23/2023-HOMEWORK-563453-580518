package it.uniroma3.diadia.ambienti;

public enum Direzione {
	NORD {
		@Override
		public Direzione direzioneOpposta() {
			return SUD;
		}
	},
	SUD {
		@Override
		public Direzione direzioneOpposta() {
			return NORD;
		}
	},
	EST {
		@Override
		public Direzione direzioneOpposta() {
			return OVEST;
		}
	},
	OVEST {
		@Override
		public Direzione direzioneOpposta() {
			return EST;
		}
	};
	public abstract Direzione direzioneOpposta();
}