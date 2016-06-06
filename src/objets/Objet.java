package objets;
import pokemon.Pokemon;

public abstract class Objet {
	protected String nom;
	protected int efficacite;

	public Objet(int efficacite, String nom) {
		this.efficacite = efficacite;
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public int getEfficacite() {
		return efficacite;
	}

	public abstract void utiliser(Pokemon poke, int auxiliaire);

	@Override
	public String toString() {
		return nom + "(Eff : " + efficacite + ")";
	}

}
