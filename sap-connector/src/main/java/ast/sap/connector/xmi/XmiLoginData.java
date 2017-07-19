package ast.sap.connector.xmi;

public class XmiLoginData {
	private String company;
	private String product;
	private String xmiInterface = "XBP";
	private String version = "0.1";

	/**
	 * "XMI logging: company name of external management tool "
	 * 
	 * @return
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * "XMI logging: Program name of external management tool "
	 * 
	 * @return
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * "Interface ID (for example, XBP)"
	 * 
	 * @return
	 */
	public String getXmiInterface() {
		return xmiInterface;
	}

	/**
	 * "Version of XMI interface delivered for check purposes  "
	 * 
	 * @return
	 */
	public String getVersion() {
		return version;
	}

	public XmiLoginData(String company, String product, String xmiInterface, String version) {
		this.company = company;
		this.product = product;
		this.xmiInterface = xmiInterface;
		this.version = version;
	}

	public XmiLoginData(String company, String product, String xmiInterface) {
		this.company = company;
		this.product = product;
		this.xmiInterface = xmiInterface;
	}

	public XmiLoginData(String company, String product) {
		this.company = company;
		this.product = product;
	}
}
