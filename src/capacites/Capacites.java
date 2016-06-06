package capacites;

/**
 * Classe gérant les différentes capacités (dégats, pp, précision..)
 * 
 * @author Younes Zitouni
 *
 */
public class Capacites {
	private String nom;
	private int PP_max;
	private int PP_actuel;
	private int puissance;
	private int precision;

	public Capacites() {
		this.nom = "--";
		this.PP_max = 0;
		this.PP_actuel = 0;
		this.puissance = 0;
		this.precision = 0;
	}

	/**
	 * Constructeur classique
	 * 
	 * @param nom
	 * @param PP
	 * @param puissance
	 * @param precision
	 */
	public Capacites(String nom, int PP, int puissance, int precision) {
		this.nom = nom;
		this.PP_max = PP;
		this.PP_actuel = PP;
		this.puissance = puissance;
		this.precision = precision;
	}

	/**
	 * Constructeur utilisé par la factory pour le renvoi d'objet
	 * 
	 * @param c
	 */
	public Capacites(Capacites c) {
		this.nom = c.getNom();
		this.PP_max = c.getPP_max();
		this.PP_actuel = c.getPP_actuel();
		this.puissance = c.getPuissance();
		this.precision = c.getPrecision();
	}

	public int getPP_actuel() {
		return PP_actuel;
	}

	public void modifPP_actuel(int modifPP) {
		if (modifPP + this.PP_actuel > this.PP_max)
			this.PP_actuel = this.PP_max;
		else if (this.PP_actuel - modifPP <= 0)
			this.PP_actuel = 0;
		else
			this.PP_actuel += modifPP;
	}

	public String getNom() {
		return nom;
	}

	public int getPP_max() {
		return PP_max;
	}

	public int getPuissance() {
		return puissance;
	}

	public int getPrecision() {
		return precision;
	}

	@Override
	public String toString() {
		return nom + " (" + PP_actuel + "/" + PP_max + ")";
	}

}
