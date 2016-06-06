package pokemon;
import java.util.ArrayList;

import capacites.Capacites;

/**
 * Classe décrivant un pokémon (et ses nombreux attributs)
 * 
 * @author Younes Zitouni
 *
 */

public class Pokemon {

	protected ArrayList<Capacites> capacites = new ArrayList<Capacites>();
	protected int niveau; // 0
	protected int pvMax; // 1
	protected int atk; // 2
	protected int def; // 3
	protected int atkSpe; // 4
	protected int defSpe; // 5
	protected int vitesse; // 6

	protected int pvActuel;
	protected int atkActuelle;
	protected int defActuelle;
	protected int atkSpeActuelle;
	protected int defSpeActuelle;
	protected int vitesseActuelle;

	protected String nom;
	protected Espece espece;

	public Pokemon() {
		this.pvMax = 30;
		this.niveau = 2;
		this.atk = 20;
		this.def = 20;
		this.atkSpe = 20;
		this.defSpe = 20;
		this.vitesse = 20;
		this.nom = "Aspifouette";
		this.espece = Espece.Pikachu;
		this.pvActuel = this.pvMax;
		this.atkActuelle = this.atk;
		this.defActuelle = this.def;
		this.atkSpeActuelle = this.atkSpe;
		this.defSpeActuelle = this.defSpe;
		this.vitesseActuelle = this.vitesse;
		this.capacites.add(new Capacites());
	}

	public Pokemon(ArrayList<Capacites> list, String nom, Espece esp, int[] stats) {

		this.capacites.addAll(list);
		this.nom = nom;
		this.espece = esp;
		this.niveau = stats[0];
		this.pvMax = stats[1];
		this.atk = stats[2];
		this.def = stats[3];
		this.atkSpe = stats[4];
		this.defSpe = stats[5];
		this.vitesse = stats[6];
		this.pvActuel = this.pvMax;
		this.atkActuelle = this.atk;
		this.defActuelle = this.def;
		this.atkSpeActuelle = this.atkSpe;
		this.defSpeActuelle = this.defSpe;
		this.vitesseActuelle = this.vitesse;
	}

	public Pokemon(Pokemon poke) {
		this.capacites.addAll(poke.getCapacites());
		this.nom = poke.getNom();
		this.espece = poke.getEspece();
		this.niveau = poke.getNiveau();
		this.pvMax = poke.getPvMax();
		this.atk = poke.getAtkActuelle();
		this.def = poke.getDefActuelle();
		this.atkSpe = poke.getAtkSpeActuelle();
		this.defSpe = poke.getDefActuelle();
		this.vitesse = poke.getVitesseActuelle();
		this.pvActuel = this.pvMax;
		this.atkActuelle = this.atk;
		this.defActuelle = this.def;
		this.atkSpeActuelle = this.atkSpe;
		this.defSpeActuelle = this.defSpe;
		this.vitesseActuelle = this.vitesse;
	}

	/**
	 * Attaque d'un pokemon sur un autre
	 * 
	 * @param poke
	 * @param attaque
	 */
	public void attaque(Pokemon poke, int attaque) {

		int degats = (int) Math
				.floor(((((this.niveau * 0.4) + 2) * this.atkActuelle * this.capacites.get(attaque).getPuissance())
						/ ((poke.getDefActuelle() * 50)) + 2));
		poke.modifPvActuel(-degats);
		this.capacites.get(attaque).modifPP_actuel(-1);

	}

	// GETTERS & SETTERS //
	// Attention les setters sont plus exactement des modifiers
	public int getPvMax() {
		return pvMax;
	}

	public int getPvActuel() {
		return pvActuel;
	}

	public void modifPvActuel(int modifPv) {
		if (modifPv + this.pvActuel > this.pvMax)
			this.pvActuel = this.pvMax;
		else if (this.pvActuel + modifPv <= 0)
			this.pvActuel = 0;
		else
			this.pvActuel += modifPv;
	}

	public int getAtkActuelle() {
		return atkActuelle;
	}

	public void modifAtkActuelle(int modifAtk) {
		if (this.atkActuelle + modifAtk <= 0)
			this.atkActuelle = 0;
		else
			this.atkActuelle += modifAtk;
	}

	public int getDefActuelle() {
		return defActuelle;
	}

	public void modifDefActuelle(int modifDef) {
		if (this.defActuelle + modifDef <= 0)
			this.defActuelle = 0;
		else
			this.defActuelle += modifDef;
	}

	public int getAtkSpeActuelle() {
		return atkSpeActuelle;
	}

	public void modifAtkSpeActuelle(int modifAtkSpe) {
		if (this.atkSpeActuelle + modifAtkSpe <= 0)
			this.atkSpeActuelle = 0;
		else
			this.atkSpeActuelle += modifAtkSpe;
	}

	public int getDefSpeActuelle() {
		return defSpeActuelle;
	}

	public void modifDefSpeActuelle(int modifDefSpe) {
		if (this.defSpeActuelle + modifDefSpe <= 0)
			this.defSpeActuelle = 0;
		else
			this.defSpeActuelle += modifDefSpe;
	}

	public int getVitesseActuelle() {
		return vitesseActuelle;
	}

	public void modifVitesseActuelle(int modifVitesse) {
		if (this.vitesseActuelle + modifVitesse <= 0)
			this.vitesseActuelle = 0;
		else
			this.vitesseActuelle += modifVitesse;
	}

	public ArrayList<Capacites> getCapacites() {
		return capacites;
	}

	public int getNiveau() {
		return niveau;
	}

	public String getNom() {
		return nom;
	}

	public Espece getEspece() {
		return espece;
	}

	@Override
	public String toString() {
		return this.getNom() + " lvl." + this.getNiveau() + " (" + this.getEspece().toString() + ")" + " HP : "
				+ this.getPvActuel() + "/" + this.getPvMax();
	}

}
