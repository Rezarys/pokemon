package objets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe permettant de créer des objets correspondant au fichier d'entrée
 * Objets.txt. On dispose des différents objets existants possibles dans la
 * liste "listeObjets" et le reste du programme demander à créer des objets
 * comme ceux stockés.
 * 
 * @author Younes Zitouni
 *
 */
public class ObjetFactory {

	// Renvoyer des objets quantifiables
	ArrayList<ObjetQuantifiable> listeObjets = new ArrayList<ObjetQuantifiable>();

	/**
	 * Remplissage de la liste des objets "modèles"
	 */
	public ObjetFactory() {
		BufferedReader lecteurAvecBuffer = null;
		String ligne;

		try {
			lecteurAvecBuffer = new BufferedReader(new FileReader("Objets.txt"));
			while ((ligne = lecteurAvecBuffer.readLine()) != null) {
				String valeurs[] = ligne.split(";");
				if (ligne.contains("Huile") || ligne.contains("huile"))
					listeObjets.add(new ObjetQuantifiable(new Huile(Integer.parseInt(valeurs[1]), valeurs[0]), 1));
				else if (ligne.contains("Potion") || ligne.contains("potion"))
					listeObjets.add(new ObjetQuantifiable(new Potion(Integer.parseInt(valeurs[1]), valeurs[0]), 1));
			}
			lecteurAvecBuffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Renvoi des copies d'objets "modèles"
	 * 
	 * @param nomObjet
	 * @return
	 */
	public ObjetQuantifiable recupererObjets(String nomObjet) {
		System.out.println("Objet demande :" + nomObjet);
		for (ObjetQuantifiable obj : this.listeObjets) {
			System.out.println("Objet propose :" + obj.getObj().getNom());
			if (obj.getObj().getNom().equals(nomObjet))
				return new ObjetQuantifiable(obj);
		}
		throw new IllegalArgumentException("Cet objet n'existe pas !");

	}
}
