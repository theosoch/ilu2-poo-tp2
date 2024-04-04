package controleur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import personnages.Gaulois;
import utils.TestUtil;
import villagegaulois.Village;

class ControlLibererEtalTest {

	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlLibererEtal controlLibererEtal;
	private Village village;
	private Gaulois vendeur;
	
	//	
	
	@BeforeEach
	public void init() {
		TestUtil.wrapBeforeEach(() -> {
			this.village = new Village("VillageTest", 1, 1);
			this.controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
			this.controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
			
			this.vendeur = new Gaulois("Gaulouis", 1);
			this.village.ajouterHabitant(vendeur);
			this.village.installerVendeur(vendeur, "produitX", 4);
		});
	}
	
	//
	
	@Test
	void testIsVendeurWithExistantSeller() {
		TestUtil.wrapTest(() -> {
			assertTrue(this.controlLibererEtal.isVendeur(this.vendeur.getNom()));
		});
	}
	
	@Test
	void testIsVendeurWithNonExistantSeller() {
		TestUtil.wrapTest(() -> {
			assertFalse(this.controlLibererEtal.isVendeur(TestUtil.getRandomString()));
		});
	}
	
	@Test
	void testIsVendeurWithNullSeller() {
		TestUtil.wrapTest(() -> {
			assertFalse(this.controlLibererEtal.isVendeur(null));
		});
	}
	
	//
	
	@Test
	void testLibererEtalWithExistantSeller() {
		TestUtil.wrapTest(() -> {
			String[] expected = new String[] { "true", this.vendeur.getNom(), "produitX", "4", "0" };
			String[] result = this.controlLibererEtal.libererEtal(this.vendeur.getNom());
			
			assertNotNull(result);
			assertEquals(expected.length, result.length);
			
			for(int i = 0; i < expected.length; i++) {
				assertEquals(expected[i], result[i]);
			}
		});
	}
	
	@Test
	void testLibererEtalWithNonExistantSeller() {
		TestUtil.wrapTest(() -> {
			String[] expected = new String[] { null, null, null, null, null };
			String[] result = this.controlLibererEtal.libererEtal(TestUtil.getRandomString());
			
			assertNotNull(result);
			assertEquals(expected.length, result.length);
			
			for(int i = 0; i < expected.length; i++) {
				assertEquals(expected[i], result[i]);
			}
		});
	}
	
	@Test
	void testLibererEtalWithNullSeller() {
		TestUtil.wrapTest(() -> {
			String[] expected = new String[] { null, null, null, null, null };
			String[] result = this.controlLibererEtal.libererEtal(null);
			
			assertNotNull(result);
			assertEquals(expected.length, result.length);
			
			for(int i = 0; i < expected.length; i++) {
				assertEquals(expected[i], result[i]);
			}
		});
	}

}
