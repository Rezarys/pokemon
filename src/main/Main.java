package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import capacites.CapacitesFactory;
import objets.Huile;
import objets.ObjetFactory;
import objets.ObjetQuantifiable;
import objets.Potion;
import pokemon.Pokemon;
import pokemon.PokemonFactory;

public class Main {

	/**
	 * Méthode principale s'occupant de l'affichage, la prise en compte des
	 * saisies utilisateurs et de l'algorithmique générale
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("-----POKEMON------");
		System.out.println("1-Nouvelle partie");
		System.out.println("2-Quitter");

		Scanner sc = new Scanner(System.in);
		int choix = sc.nextInt();

		Dresseur d1 = new Dresseur(), d2 = new Dresseur();
		int joueurActif = 1; // 1 = J1 / -1 = J2

		if (choix == 1) {
			clearConsole(50);
			// Creation des deux équipes
			CapacitesFactory cf = new CapacitesFactory();
			ObjetFactory of = new ObjetFactory();
			PokemonFactory pf = new PokemonFactory(cf);

			BufferedReader lecteurAvecBuffer = null;
			String ligne;

			// Remplissage des dresseurs
			for (int j = 1; j < 3; j++) {
				try {
					lecteurAvecBuffer = new BufferedReader(new FileReader("Joueur" + j + ".txt"));
					ligne = lecteurAvecBuffer.readLine();
					String valeurs[] = ligne.split(";");
					// Création des pokémons
					ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
					for (int i = 1; i < 7; i++)
						if (!valeurs[i].equals(""))
							pokemons.add(pf.recupererPokemon(valeurs[i]));
					// Création des objets
					ArrayList<ObjetQuantifiable> obj = new ArrayList<ObjetQuantifiable>();
					if (!valeurs[7].equals("")) {
						obj.add(of.recupererObjets("Potion"));
						obj.get(0).modifQuantite(Integer.parseInt(valeurs[7]) - 1);
					}
					if (!valeurs[8].equals("")) {
						obj.add(of.recupererObjets("Huile"));
						obj.get(1).modifQuantite(Integer.parseInt(valeurs[8]) - 1);
					}
					// Pour les deux dresseurs
					if (j == 1)
						d1 = new Dresseur(valeurs[0], pokemons, obj);
					else
						d2 = new Dresseur(valeurs[0], pokemons, obj);

					lecteurAvecBuffer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// Debut du combat (on décide qui commence avec la vitesse du
			// pokemon)
			if (d1.getEquipe().get(0).getVitesseActuelle() < d2.getEquipe().get(0).getVitesseActuelle())
				joueurActif = -joueurActif;
			Dresseur dActif, dPassif;
			boolean recommencerTour = false;
			// Tant qu'il y a des pokemons en lice
			while (pasTousKO(d1.getEquipe(), d2.getEquipe())) {
				recommencerTour = false;
				// On passe de d1/d2 à dActif/passif pour n'avoir qu'un code
				// (c'est mieux)
				if (joueurActif == 1) {
					dActif = d1;
					dPassif = d2;
				} else {
					dActif = d2;
					dPassif = d1;
				}
				clearConsole(20);
				// Affichage de l'écran "principal" + attente d'input
				ecranChoix(dActif, dPassif);
				sc.hasNext();
				choix = sc.nextInt();

				// Obligation de changer de pokémon s'il est mort
				if (dActif.getEquipe().get(dActif.getPokemonActif()).getPvActuel() == 0) {
					dActif.setPokemonActif(choixPokemon(dActif, sc, true));
				}
				// S'il veut attaquer
				else if (choix == 1) {
					int choixAttaque = choixAttaque(dActif.getEquipe().get(dActif.getPokemonActif()), sc);

					if (choixAttaque == 4) // si retour
						recommencerTour = true;
					else
						dActif.getEquipe().get(dActif.getPokemonActif())
								.attaque(dPassif.getEquipe().get(dPassif.getPokemonActif()), choixAttaque);
				}
				// changement de pokemon
				else if (choix == 2) {
					int choixPokemon = choixPokemon(dActif, sc, false);

					if (choixPokemon == 6 || choixPokemon == dActif.getPokemonActif())
						// si retour
						recommencerTour = true;
					else
						dActif.setPokemonActif(choixPokemon);
					// Si on veut utiliser un objet
				} else if (choix == 3) {
					int choixObjet = choixObjet(dActif.getSac(), sc);
					if (choixObjet == dActif.getSac().size())
						// si retour sur l'objet
						recommencerTour = true;
					// Si on utilise une huile
					else if (dActif.getSac().get(choixObjet).getObj() instanceof Huile) {
						int choixPokemon = choixPokemon(dActif, sc, false);
						if (choixPokemon == 6)
							// si retour sur choix du pokemon
							recommencerTour = true;
						else {
							int choixAttaque = choixAttaque(dActif.getEquipe().get(choixPokemon), sc);
							if (choixAttaque == 4)
								// si retour sur choix de l'attaque
								recommencerTour = true;
							else if (dActif.getSac().get(choixObjet).getQuantite() == 0) {
								recommencerTour = true;
								System.out.println("Vous n'avez plus de cet objet !");
								System.in.read();
							} else // on utilise l'objet (même si full life/PP)
								dActif.getSac().get(choixObjet).utiliser(dActif.getEquipe().get(choixPokemon),
										choixAttaque);
						}
					}
					// Si on utilise une potion
					else if (dActif.getSac().get(choixObjet).getObj() instanceof Potion) {
						int choixPokemon = choixPokemon(dActif, sc, false);
						if (choixPokemon == 6)
							// si retour sur choix du pokemon
							recommencerTour = true;
						else {
							int choixAttaque = choixAttaque(dActif.getEquipe().get(choixPokemon), sc);
							if (choixAttaque == 4)
								// si retour sur choix de l'attaque
								recommencerTour = true;
							else if (dActif.getSac().get(choixObjet).getQuantite() == 0) {
								recommencerTour = true;
								System.out.println("Vous n'avez plus de cet objet !");
								System.in.read();
							} else
								dActif.getSac().get(choixObjet).utiliser(dActif.getEquipe().get(choixPokemon), 0);
						}
					}
					// Si on veut fuir
				} else if (choix == 4) {
					System.out.println("Vous ne pouvez pas quitter un combat contre un dresseur !");
					System.in.read();
					recommencerTour = true;
					// si le nombre est incorrect
				} else {
					System.out.println("Veuillez entrer un nombre correct.");
					System.in.read();
					recommencerTour = true;
				}
				// si le joueur a fait une action significative
				if (!recommencerTour) {
					// on récupère les valeurs modifiées
					if (joueurActif == 1) {
						d1 = dActif;
						d2 = dPassif;
					} else {
						d2 = dActif;
						d1 = dPassif;
					} // on change le tour
					joueurActif = -joueurActif;
				}

			}
			// victoire
			if (tousKO(d1.getEquipe()))
				System.out.println(d2.getNom() + " a gagné ce combat avec brio ! (C'est le petit nom de son pokémon)");
			else
				System.out.println(d1.getNom() + " a envoyé la team rocket voler vers d'autres cieux !");

			// Si on veut quitter
		} else if (choix == 2) {
			extracted();
		}
		// 3Points plutot stylés
		for (int i = 0; i < 3; i++) {
			System.out.print(".");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// Handle exception
			}
		}
	}

	/**
	 * C'est java qui veut
	 */
	private static void extracted() {
		return;
	}

	/**
	 * Renvoie vrai si tous les pokemons d'une equipe sont morts
	 * 
	 * @param equipe
	 * @return
	 */
	private static boolean tousKO(ArrayList<Pokemon> equipe) {
		for (int i = 0; i < equipe.size(); i++)
			if (equipe.get(i).getPvActuel() != 0)
				return false;
		return true;
	}

	/**
	 * Affiche les différents objets et attend l'input du joueur
	 * 
	 * @param sac
	 * @param sc
	 * @return
	 */
	private static int choixObjet(ArrayList<ObjetQuantifiable> sac, Scanner sc) {
		int i;
		for (i = 0; i < sac.size(); i++)
			System.out.println(i + ". " + sac.get(i).toString());
		System.out.println(i + ". Retour");
		sc.hasNext();
		return sc.nextInt();
	}

	/**
	 * Affiche les différentes attaques du pokemon et attend l'input du joueur
	 * 
	 * @param pokemon
	 * @param sc
	 * @return
	 */
	private static int choixAttaque(Pokemon pokemon, Scanner sc) {

		for (int i = 0; i < pokemon.getCapacites().size(); i++)
			System.out.println(i + ". " + pokemon.getCapacites().get(i).toString());
		System.out.println("4. Retour");
		sc.hasNext();
		return sc.nextInt();
	}

	/**
	 * Affiche les différentes pokémons et attend l'input du joueur
	 * 
	 * @param dActif
	 * @param sc
	 * @param b
	 * @return
	 */
	private static int choixPokemon(Dresseur dActif, Scanner sc, boolean b) {
		System.out.println("Liste de vos Pokemons");
		for (int i = 0; i < dActif.getEquipe().size(); i++)
			System.out.println(i + ". " + dActif.getEquipe().get(i).toString());
		System.out.println("6. Retour");
		sc.hasNext();
		int res = sc.nextInt();
		// si on veut faire retour alors qu'on a pas le droit (pokemon mort)
		if (res == 6 && b) {
			System.out.println("Vous devez choisir un pokemon, le votre ne bouge plus !");
			return choixPokemon(dActif, sc, b);
		}
		return res;
	}

	/**
	 * Fonction déterminant si l'une des équipe est complètement KO
	 * 
	 * @param equipe
	 * @param equipe2
	 * @return
	 */
	private static boolean pasTousKO(ArrayList<Pokemon> equipe, ArrayList<Pokemon> equipe2) {

		if (tousKO(equipe) || tousKO(equipe2))
			return false;
		return true;
	}

	/**
	 * Fonction affichant une console propre
	 * 
	 * @param taille
	 */
	public static void clearConsole(int taille) {
		String clear = "";
		for (int i = 0; i < taille; i++)
			clear += "\r\n";
		System.out.println(clear);
	}

	/**
	 * Affiche l'écran "principal"
	 * 
	 * @param joueurActif
	 * @param autre
	 */
	public static void ecranChoix(Dresseur joueurActif, Dresseur autre) {
		System.out.println(autre.getEquipe().get(autre.getPokemonActif()).toString());
		clearConsole(5);
		System.out.println(joueurActif.getEquipe().get(joueurActif.getPokemonActif()).toString());
		System.out.println("1. Attaques - 2. Pokemons");
		System.out.println("3. Sac      - 4. Fuite");
	}

}
