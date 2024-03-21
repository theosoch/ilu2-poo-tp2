package frontiere;

import controleur.ControlAfficherMarche;

public class BoundaryAfficherMarche {
	private ControlAfficherMarche controlAfficherMarche;

	public BoundaryAfficherMarche(ControlAfficherMarche controlAfficherMarche) {
		this.controlAfficherMarche = controlAfficherMarche;
	}

	public void afficherMarche(String nomAcheteur) {
		String[] infosMarche = this.controlAfficherMarche.donnerInfosMarche();
		
		if(infosMarche.length == 0) {
			System.out.println("Le marché est vide, revenez plus tard.");
		}
		else {
			StringBuilder messageBuilder = new StringBuilder();
			
			messageBuilder.append(nomAcheteur + ", vous trouverez au marché :\n");
			
			for(int i = 0; i < infosMarche.length; i++) {
				String vendeur = infosMarche[i];
				i++;
				String quantite = infosMarche[i];
				i++;
				String produit = infosMarche[i];
				
				//
				
				messageBuilder.append(" - " + vendeur + " qui vend " + quantite + " " + produit + "\n");
			}
			
			System.out.println(messageBuilder.toString());
		}
	}
}
