package objets;
import pokemon.Pokemon;

/**
 * Classe décrivant une potion.
 * 
 * @author Younes Zitouni
 *
 */
public class Potion extends Objet {

	public Potion(int efficacite, String nom) {
		super(efficacite, nom);
	}

	public void utiliser(Pokemon poke) {
		poke.modifPvActuel(this.efficacite);
	}

	@Override
	public void utiliser(Pokemon poke, int auxiliaire) {
		poke.modifPvActuel(this.efficacite);

	}

	@Override
	public String toString() {
		return nom + "(+" + efficacite + "PV)";
	}
}
