
import static org.junit.Assert.*;
import org.junit.Test;

public class DTestTest {


	@Test
	public void test_realiserDTest0(){
		DTest monTest = new DTest(32, DTest.Sexe.Homme ,92, true, false, false, 1.90, 80, false, DTest.Frequence.Tt_Jours);
		assertEquals(monTest.realiserDTest(), 0);
	}
	
	@Test
	public void test_realiserDTestMax(){
		DTest monTest = new DTest(67, DTest.Sexe.Homme ,108, false, true, true, 1.90, 220, true, DTest.Frequence.Jamais);
		assertEquals(monTest.realiserDTest(), 25);
	}
	
	@Test
	public void calculIMC(){
		DTest monTest = new DTest(1.84, 78);
		assertEquals(Math.round(monTest.calculIMC()), 23,0);
	}
	
	public void calculIMC_0(){
		DTest monTest = new DTest(1.84, 0);
		assertEquals(Math.round(monTest.calculIMC()), 0);
	}

}
