package main;
import java.util.ArrayList;

import objets.ObjetQuantifiable;
import pokemon.Pokemon;

/**
 * 
 * Classe gérant le dresseur qui possède un sac d'objets, un nom et des pokemons
 * 
 * @author Younes Zitouni
 *
 */
public class Dresseur {

	private ArrayList<Pokemon> equipe = new ArrayList<Pokemon>();
	private String nom;
	private ArrayList<ObjetQuantifiable> sac = new ArrayList<ObjetQuantifiable>();
	private int pokemonActif = 0;

	public Dresseur(String nom, ArrayList<Pokemon> equipe, ArrayList<ObjetQuantifiable> obj) {

		this.equipe.addAll(equipe);
		this.sac.addAll(obj);
		this.nom = nom;
	}

	public Dresseur() {

	}

	public ArrayList<Pokemon> getEquipe() {
		return equipe;
	}

	public int getPokemonActif() {
		return pokemonActif;
	}

	public void setPokemonActif(int pokemonActif) {
		this.pokemonActif = pokemonActif;
	}

	public ArrayList<ObjetQuantifiable> getSac() {
		return sac;
	}

	public String getNom() {
		return nom;
	}

}
