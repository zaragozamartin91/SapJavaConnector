package ast.sap.connector.conn.xmi;

public class XmiLoginData {
	private String company;
	private String product;
	private String xmiInterface = "XBP";
	private String version = "0.1";

	public String getCompany() {
		return company;
	}

	public XmiLoginData company(String company) {
		this.company = company;
		return this;
	}

	public String getProduct() {
		return product;
	}

	public XmiLoginData product(String product) {
		this.product = product;
		return this;
	}

	public String getXmiInterface() {
		return xmiInterface;
	}

	public XmiLoginData xmiInterface(String xmiInterface) {
		this.xmiInterface = xmiInterface;
		return this;
	}

	public String getVersion() {
		return version;
	}

	public XmiLoginData version(String version) {
		this.version = version;
		return this;
	}

	private XmiLoginData() {
	}

	public static XmiLoginData newWith() {
		return new XmiLoginData();
	}
}
