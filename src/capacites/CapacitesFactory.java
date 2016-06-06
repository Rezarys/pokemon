package capacites;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Factory de la classe Capacites, plus de détails dans la classe ObjetFactory
 * 
 * @author Younes Zitouni
 *
 */
public class CapacitesFactory {

	ArrayList<Capacites> listeCapacites = new ArrayList<Capacites>();

	public CapacitesFactory() {
		BufferedReader lecteurAvecBuffer = null;
		String ligne;

		try {
			lecteurAvecBuffer = new BufferedReader(new FileReader("Capacites.txt"));
			while ((ligne = lecteurAvecBuffer.readLine()) != null) {
				String valeurs[] = ligne.split(";");
				listeCapacites.add(new Capacites(valeurs[0], Integer.parseInt(valeurs[1]), Integer.parseInt(valeurs[2]),
						Integer.parseInt(valeurs[3])));
			}
			lecteurAvecBuffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Capacites recupererCapacites(String nomCapacite) {

		System.out.println("Capacite demandee : " + nomCapacite);
		for (Capacites cap : this.listeCapacites) {
			System.out.println("Capacite dispo : " + cap.getNom());
			if (cap.getNom().equals(nomCapacite))
				return new Capacites(cap);
		}
		throw new IllegalArgumentException("Cette compétence n'existe pas !");

	}

}
