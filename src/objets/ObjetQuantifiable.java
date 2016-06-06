package objets;
import pokemon.Pokemon;

/**
 * Wrapper d'un objet. Il ajoute une quantité à un objet.
 * 
 * @author Younes Zitouni
 *
 */

public class ObjetQuantifiable {
	protected int quantite;
	protected Objet obj;

	public ObjetQuantifiable(Objet obj, int quantite) {
		this.quantite = quantite;
		this.obj = obj;
	}

	public ObjetQuantifiable(ObjetQuantifiable obj) {
		this.quantite = obj.getQuantite();
		this.obj = obj.getObj();
	}

	public int getQuantite() {
		return quantite;
	}

	public void modifQuantite(int modifQuantite) {
		if (this.quantite + modifQuantite < 0)
			this.quantite = 0;
		else
			this.quantite += modifQuantite;
	}

	/**
	 * Uitlise un objet et réduit sa quantité
	 * 
	 * @param poke
	 * @param auxiliaire
	 */
	public void utiliser(Pokemon poke, int auxiliaire) {
		if (quantite == 0)
			throw new IllegalArgumentException("Il n'y plus d'objet à utiliser.");
		this.obj.utiliser(poke, auxiliaire);
		System.out.println("qte : " + this.quantite);
		this.quantite--;
		System.out.println("qte : " + this.quantite);

	}

	public Objet getObj() {
		return obj;
	}

	public String toString() {
		return this.obj.toString() + " x" + quantite;
	}

}
