package ast.sap.connector.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class ReadPropertiesFromFileTest {

	@Test
	public void test() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		FileInputStream inStream = new FileInputStream(new File("connectorsap.properties"));
		properties.load(inStream);
		
		inStream.close();
	}

}
