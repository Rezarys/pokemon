package objets;

import pokemon.Pokemon;

/**
 * Classe décrivant une huile.
 * 
 * @author Younes Zitouni
 *
 */
public class Huile extends Objet {

	public Huile(int efficacite, String nom) {
		super(efficacite, nom);
	}

	public void utiliser(Pokemon poke, int numeroCapacite) {
		poke.getCapacites().get(numeroCapacite).modifPP_actuel(efficacite);
	}

	@Override
	public String toString() {
		return nom + "(+" + efficacite + "PP)";
	}

}
