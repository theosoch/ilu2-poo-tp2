package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Gaulois;
import utils.TestUtil;
import villagegaulois.Village;

class ControlAfficherMarcheTest {

	private ControlAfficherMarche controlAfficherMarche;
	private Village village;
	private Gaulois marchand1;
	private Gaulois marchand2;
	
	//	
	
	@BeforeEach
	public void init() {
		TestUtil.wrapBeforeEach(() -> {
			this.village = new Village("VillageTest", 2, 2);
			this.controlAfficherMarche = new ControlAfficherMarche(this.village);
			
			this.marchand1 = new Gaulois("Gaulouis", 1);
			this.village.ajouterHabitant(marchand1);
			this.village.installerVendeur(marchand1, "produitX", 3);
	
			this.marchand2 = new Gaulois("Lugauvik", 2);
			this.village.ajouterHabitant(marchand2);
			this.village.installerVendeur(marchand2, "produitY", 7);
		});
	}
	
	@Test
	void testDonnerInfosMarche() {
		TestUtil.wrapTest(() -> {
			String[] result = this.controlAfficherMarche.donnerInfosMarche();
			String[] expected = new String[] { "Gaulouis", "3", "produitX", "Lugauvik", "7", "produitY" };
			
			assertNotNull(result);
			assertEquals(result.length, expected.length);
			
			for(int i = 0; i < expected.length; i++) {
				assertTrue(expected[i].equals(result[i]));
			}
		});
	}

}
