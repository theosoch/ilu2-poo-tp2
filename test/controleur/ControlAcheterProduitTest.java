package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Gaulois;
import utils.TestUtil;
import villagegaulois.Village;

class ControlAcheterProduitTest {

	private ControlVerifierIdentite controleVerifierIdentite;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlAcheterProduit controleAcheterProduit;
	private Village village;
	private Gaulois vendeur;
	private Gaulois acheteur;
	
	//	
	
	@BeforeEach
	public void init() {
		TestUtil.wrapBeforeEach(() -> {
			this.village = new Village("VillageTest", 2, 2);
			this.controleVerifierIdentite = new ControlVerifierIdentite(village);
			this.controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
			this.controleAcheterProduit = new ControlAcheterProduit(this.controleVerifierIdentite, this.controlTrouverEtalVendeur, village);
			
			this.vendeur = new Gaulois("Gaulouis", 1);
			this.village.ajouterHabitant(vendeur);
			this.village.installerVendeur(vendeur, "produitX", 3);

			this.acheteur = new Gaulois("Lugauvik", 2);
			this.village.ajouterHabitant(acheteur);
		});
	}
	
	//	
	
	@Test
	void testVerifierIdentiteExistantVillager() {
		TestUtil.wrapTest(() -> {
			assertTrue(this.controleAcheterProduit.verifierIdentite(this.vendeur.getNom()));
			assertTrue(this.controleAcheterProduit.verifierIdentite(this.acheteur.getNom()));
		});
	}
	
	@Test
	void testVerifierIdentiteNonExistantVillager() {
		TestUtil.wrapTest(() -> {
			assertFalse(this.controleAcheterProduit.verifierIdentite(TestUtil.getRandomString()));
		});
	}
	
	@Test
	void testVerifierIdentiteNullVillager() {
		TestUtil.wrapTest(() -> {
			assertFalse(this.controleAcheterProduit.verifierIdentite(null));
		});
	}
	
	//	
	
	@Test
	void testGetVendeursParProduitExistantProduct() {
		TestUtil.wrapTest(() -> {
			Gaulois[] expected = { this.vendeur };
			Gaulois[] result = this.controleAcheterProduit.getVendeursParProduit("produitX");
			
			assertNotNull(result);
			assertEquals(expected.length, result.length);
			
			for(int i = 0; i < expected.length; i++) {
				assertEquals(expected[i], result[i]);
			}
		});
	}
	
	@Test
	void testGetVendeursParProduitNullExistantProduct() {
		TestUtil.wrapTest(() -> {
			assertNull(this.controleAcheterProduit.getVendeursParProduit(TestUtil.getRandomString()));
		});
	}
	
	@Test
	void testGetVendeursParProduitNullProduct() {
		TestUtil.wrapTest(() -> {
			assertNull(this.controleAcheterProduit.getVendeursParProduit(null));
		});
	}
	
	//	
	
	@Test
	void testAcheterProduitBuyMoreThanAvailable() {
		TestUtil.wrapTest(() -> {
			int expected = 3;
			int result = this.controleAcheterProduit.acheterProduit(this.vendeur, 4);
			assertEquals(expected, result);
		});
	}
	
	@Test
	void testAcheterProduitBuyExactlyTheAvailableQuantity() {
		TestUtil.wrapTest(() -> {
			int expected = 3;
			int result = this.controleAcheterProduit.acheterProduit(this.vendeur, 3);
			assertEquals(expected, result);
		});
	}
	
	@Test
	void testAcheterProduitBuyLessThanAvailable() {
		TestUtil.wrapTest(() -> {
			int expected = 2;
			int result = this.controleAcheterProduit.acheterProduit(this.vendeur, 2);
			assertEquals(expected, result);
		});
	}
	
	@Test
	void testAcheterProduitBuy0() {
		TestUtil.wrapTest(() -> {
			int expected = 0;
			int result = this.controleAcheterProduit.acheterProduit(this.vendeur, 0);
			assertEquals(expected, result);
		});
	}

}
