package controleur;

import personnages.Gaulois;
import villagegaulois.Village;

public class ControlPrendreEtal {
	private Village village;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlPrendreEtal(ControlVerifierIdentite controlVerifierIdentite,
			Village village) {
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.village = village;
	}

	public boolean resteEtals() {
		return this.village.rechercherEtalVide();
	}

	public int prendreEtal(String nomVendeur, String produit, int nbProduit) {
		Gaulois vendeur = this.village.trouverHabitant(nomVendeur);
		return vendeur == null ? -1 : this.village.installerVendeur(vendeur, produit, nbProduit);
	}

	public boolean verifierIdentite(String nomVendeur) {
		return this.controlVerifierIdentite.verifierIdentite(nomVendeur);
	}
}
