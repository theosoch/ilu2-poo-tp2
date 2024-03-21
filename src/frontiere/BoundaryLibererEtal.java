package frontiere;

import controleur.ControlLibererEtal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		if(!this.controlLibererEtal.isVendeur(nomVendeur)) {
			System.out.println("Mais vous n'êtes pas inscrit sur notre marché aujourd'hui !");
		}
		else {
			String[] donneesEtal = this.controlLibererEtal.libererEtal(nomVendeur);
			
			boolean etalOccupee = Boolean.valueOf(donneesEtal[0]);
			if(etalOccupee) {
				String quantiteVendue = donneesEtal[4];
				String quantiteInitiale = donneesEtal[3];
				String produit = donneesEtal[2];
				
				System.out.println("Vous avez vendu " + quantiteVendue + " sur " + quantiteInitiale + " " + produit + ". Au revoir " + nomVendeur + ", passez une bonne journée.");
			}
		}
	}

}
