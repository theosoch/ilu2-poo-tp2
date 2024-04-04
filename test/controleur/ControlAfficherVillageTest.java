package controleur;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import utils.TestUtil;
import villagegaulois.Village;

class ControlAfficherVillageTest {

	private static final String NOM_VILLAGE = "VillageTest";
	private static final int NB_ETALS = 2;
	
	//
	
	private ControlAfficherVillage controlAfficherVillage;
	private Village village;
	private Chef chef;
	private Gaulois vendeur;
	private Gaulois acheteur;

	//
	
	@BeforeEach
	public void init() {
		TestUtil.wrapBeforeEach(() -> {
			this.village = new Village(NOM_VILLAGE, 2, NB_ETALS);
			this.controlAfficherVillage = new ControlAfficherVillage(this.village);
			
			this.chef = new Chef("Mixchell", 100, this.village);
			this.village.setChef(this.chef);
			
			this.vendeur = new Gaulois("Gaulouis", 1);
			this.village.ajouterHabitant(vendeur);
			this.village.installerVendeur(vendeur, "produitX", 3);

			this.acheteur = new Gaulois("Lugauvik", 2);
			this.village.ajouterHabitant(acheteur);
		});
	}
	
	@Test
	void testDonnerNomsVillageois() {
		TestUtil.wrapTest(() -> {
			String[] expected = { this.chef.getNom(), this.vendeur.getNom(), this.acheteur.getNom() };
			String[] result = this.controlAfficherVillage.donnerNomsVillageois();
			
			assertNotNull(result);
			assertEquals(expected.length, result.length);
			
			for(int i = 0; i < expected.length; i++) {
				assertEquals(expected[i], result[i]);
			}
		});
	}
	
	@Test
	void testDonnerNomVillage() {
		TestUtil.wrapTest(() -> {
			assertEquals(NOM_VILLAGE, this.controlAfficherVillage.donnerNomVillage());
		});
	}
	
	@Test
	void testDonnerNbEtals() {
		TestUtil.wrapTest(() -> {
			assertEquals(NB_ETALS, this.controlAfficherVillage.donnerNbEtals());
		});
	}

}
