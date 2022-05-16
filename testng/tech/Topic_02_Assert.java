package tech;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {
	
	@Test
	public void TC_01() {
		String addressCity = "Ha Noi";
		
		Assert.assertTrue(addressCity.equals("Ha Noi"));
		Assert.assertTrue(addressCity.contains("Ha"));
		Assert.assertTrue(addressCity.contains("Noi"));
		
		Assert.assertFalse(addressCity.contains("Da Nang"));
		
		Assert.assertEquals(addressCity, "Ha Noi");
		
		Object fullname = null;
		Assert.assertNull(fullname);
		
		fullname = "Automation FC";
		Assert.assertNotNull(fullname);
	}

}
