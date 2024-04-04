package controleur;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Gaulois;
import utils.TestUtil;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlTrouverEtalVendeurTest {

	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private Village village;
	private Gaulois vendeur;
	
	//	
	
	@BeforeEach
	public void init() {
		TestUtil.wrapBeforeEach(() -> {
			this.village = new Village("VillageTest", 1, 1);
			this.controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
			
			this.vendeur = new Gaulois("Gaulouis", 1);
			this.village.ajouterHabitant(vendeur);
			this.village.installerVendeur(vendeur, "produitX", 1);
		});
	}
	
	@Test
	void trouverEtalVendeurParameters() {
		TestUtil.wrapTest(() -> {
			assertNull(this.controlTrouverEtalVendeur.trouverEtalVendeur(null));
		});
	}
	
	@Test
	void trouverEtalVendeurUnexistantSeller() {
		TestUtil.wrapTest(() -> {
			assertNull(this.controlTrouverEtalVendeur.trouverEtalVendeur(TestUtil.getRandomString()));
		});
	}
	
	@Test
	void trouverEtalVendeurExistantSeller() {
		TestUtil.wrapTest(() -> {
			Etal result = this.controlTrouverEtalVendeur.trouverEtalVendeur(this.vendeur.getNom());
			
			assertNotNull(result);
			assertTrue(result.isEtalOccupe());
			assertEquals(result.getVendeur(), vendeur);
			assertEquals(result.getProduit(), "produitX");
			assertEquals(result.getQuantite(), 1);
		});
	}

}
