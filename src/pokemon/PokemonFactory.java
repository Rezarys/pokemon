package pokemon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import capacites.Capacites;
import capacites.CapacitesFactory;

/**
 * Factory de la classe Pokemon, plus de détails dans la classe ObjetFactory
 * 
 * @author Younes Zitouni
 *
 */
public class PokemonFactory {

	ArrayList<Pokemon> listePokemon = new ArrayList<Pokemon>();

	public PokemonFactory(CapacitesFactory cf) {
		BufferedReader lecteurAvecBuffer = null;
		String ligne;

		try {
			lecteurAvecBuffer = new BufferedReader(new FileReader("Pokemons.txt"));
			while ((ligne = lecteurAvecBuffer.readLine()) != null) {
				String valeurs[] = ligne.split(";");
				String nom = valeurs[0];
				ArrayList<Capacites> cap = new ArrayList<Capacites>();
				for (int i = 2; i < 6; i++)
					if (!(valeurs[i].equals("")) && (!(valeurs[i].equals(" "))))
						cap.add(cf.recupererCapacites(valeurs[i]));

				int[] stats = new int[7];
				for (int i = 6; i < 13; i++) {
					System.out.println(valeurs[i]);
					stats[i - 6] = Integer.parseInt(valeurs[i]);
				}

				listePokemon.add(new Pokemon(cap, nom, Espece.valueOf(valeurs[1]), stats));
			}
			lecteurAvecBuffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Pokemon recupererPokemon(String nomPokemon) {
		System.out.println("Nom demande : " + nomPokemon);
		for (Pokemon poke : this.listePokemon) {
			System.out.println("Nom propose : " + poke.getNom());
			if (poke.getNom().equals(nomPokemon))
				return new Pokemon(poke);
		}
		throw new IllegalArgumentException("Ce pokémon n'existe pas !");

	}
}
