package controleur;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Gaulois;
import utils.TestUtil;
import villagegaulois.Village;

class ControlVerifierIdentiteTest {

	private ControlVerifierIdentite controlVerifierIdentite;
	private Village village;
	private Gaulois habitant;
	
	//	
	
	@BeforeEach
	public void init() {
		TestUtil.wrapBeforeEach(() -> {
			this.village = new Village("VillageTest", 1, 0);
			this.controlVerifierIdentite = new ControlVerifierIdentite(village);
			
			this.habitant = new Gaulois("Gaulouis", 1);
			this.village.ajouterHabitant(habitant);
		});
	}
	
	@Test
	void testVerifierIdentiteExistantVillager() {
		TestUtil.wrapTest(() -> {
			assertTrue(this.controlVerifierIdentite.verifierIdentite(this.habitant.getNom()));
		});
	}
	
	@Test
	void testVerifierIdentiteNonExistantVillager() {
		TestUtil.wrapTest(() -> {
			assertFalse(this.controlVerifierIdentite.verifierIdentite(TestUtil.getRandomString()));
		});
	}
	
	@Test
	void testVerifierIdentiteNullVillager() {
		TestUtil.wrapTest(() -> {
			assertFalse(this.controlVerifierIdentite.verifierIdentite(null));
		});
	}

}
