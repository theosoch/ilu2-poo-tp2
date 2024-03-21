package frontiere;

import java.util.Scanner;

import controleur.ControlPrendreEtal;

public class BoundaryPrendreEtal {
	private ControlPrendreEtal controlPrendreEtal;
	private Scanner scan = new Scanner(System.in);

	public BoundaryPrendreEtal(ControlPrendreEtal controlChercherEtal) {
		this.controlPrendreEtal = controlChercherEtal;
	}

	public void prendreEtal(String nomVendeur) {
		if(!this.controlPrendreEtal.verifierIdentite(nomVendeur)) {
			System.out.println("Je suis désolé " + nomVendeur + ", mais il faut être un habitant de notre village pour commercer ici.");
		}
		else {
			System.out.println("Bonjour vendeur " + nomVendeur + ", je vais regarder si je peux trouver un étal.");
			
			if(!this.controlPrendreEtal.resteEtals()) {
				System.out.println("Désolé" + nomVendeur + ", je n'ai plus d'étal qui ne soit pas déjà occupée.");
			}
			else {
				this.installerVendeur(nomVendeur);
			}
		}
	}

	private void installerVendeur(String nomVendeur) {
		System.out.println("C'est parfait, il me reste un étal pour vous !\nIl me faudrait quelques renseignements :");
		
		String produit = Clavier.entrerChaine("Quel produit souhaitez-vous vendre ?");
		int nbProduit = Clavier.entrerEntier("Combien souhaitez-vous en vendre ?");
		
		int indiceEtal = this.controlPrendreEtal.prendreEtal(nomVendeur, produit, nbProduit);
		
		if(indiceEtal != -1) {
			System.out.println("Le vendeur " + nomVendeur + " s'est installé à l'étal n°" + (indiceEtal+1));
		}
	}
}
