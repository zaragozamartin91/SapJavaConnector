package ast.sap.connector.config;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConfiguratorTest {

	@BeforeClass
	public static void before() {
		Configuration.loadConnectorConfig("connectorsap.properties");

	}

	@Test
	public void GetPropertyFromConfigFile() {
		try {
			Assert.assertEquals("mzaragoz", Configuration.getProperty("username"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void GetUsername() {
		try {
			Assert.assertEquals("mzaragoz", Configuration.getUsername());
		} catch (Exception e) {
			Assert.fail();
		}
	}

}
