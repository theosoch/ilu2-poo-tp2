package controleur;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Gaulois;
import utils.TestUtil;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlPrendreEtalTest {

	private ControlVerifierIdentite controlVerifierIdentite;
	private ControlPrendreEtal controlPrendreEtal;
	private Village village;
	private Gaulois vendeur;
	
	//	
	
	@BeforeEach
	public void init() {
		TestUtil.wrapBeforeEach(() -> {
			this.village = new Village("VillageTest", 1, 1);
			this.controlVerifierIdentite = new ControlVerifierIdentite(village);
			this.controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
			
			this.vendeur = new Gaulois("Gaulouis", 1);
			this.village.ajouterHabitant(vendeur);
		});
	}
	
	@Test
	void testResteEtals() {
		TestUtil.wrapTest(() -> {
			assertEquals(this.village.rechercherEtalVide(), controlPrendreEtal.resteEtals());
		});
	}
	
	@Test
	void testPrendreEtalWithExistantSeller() {
		TestUtil.wrapTest(() -> {
			this.controlPrendreEtal.prendreEtal(this.vendeur.getNom(), "produitX", 1);
			
			Etal etal = this.village.rechercherEtal(this.vendeur);
			assertNotNull(etal);
			assertEquals(etal.getVendeur(), this.vendeur);
			assertEquals(etal.getProduit(), "produitX");
			assertEquals(etal.getQuantite(), 1);
		});
	}
	
	@Test
	void testPrendreEtalWithNonExistantSeller() {
		TestUtil.wrapTest(() -> {
			assertEquals(-1, this.controlPrendreEtal.prendreEtal(TestUtil.getRandomString(), "produitX", 1));
		});
	}
	
	@Test
	void testPrendreEtalWithNullSeller() {
		TestUtil.wrapTest(() -> {
			assertEquals(-1, this.controlPrendreEtal.prendreEtal(null, "produitX", 1));
		});
	}
	
	@Test
	void testVerifierIdentiteWithExistantVillager() {
		TestUtil.wrapTest(() -> {
			assertTrue(this.controlPrendreEtal.verifierIdentite(vendeur.getNom()));
		});
	}
	
	@Test
	void testVerifierIdentiteWithNonExistantVillager() {
		TestUtil.wrapTest(() -> {
			assertFalse(this.controlPrendreEtal.verifierIdentite(TestUtil.getRandomString()));
		});
	}
	
	@Test
	void testVerifierIdentiteWithNullVillager() {
		TestUtil.wrapTest(() -> {
			assertFalse(this.controlPrendreEtal.verifierIdentite(null));
		});
	}

}
