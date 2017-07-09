package cn.com.entity;

public class ProductCategory {
	private int epcId;
	private String epcName;
	private int epcParentId;

	public ProductCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductCategory(int epcId, String epcName, int epcParentId) {
		super();
		this.epcId = epcId;
		this.epcName = epcName;
		this.epcParentId = epcParentId;
	}

	public int getEpcId() {
		return epcId;
	}

	public void setEpcId(int epcId) {
		this.epcId = epcId;
	}

	public String getEpcName() {
		return epcName;
	}

	public void setEpcName(String epcName) {
		this.epcName = epcName;
	}

	public int getEpcParentId() {
		return epcParentId;
	}

	public void setEpcParentId(int epcParentId) {
		this.epcParentId = epcParentId;
	}

}
