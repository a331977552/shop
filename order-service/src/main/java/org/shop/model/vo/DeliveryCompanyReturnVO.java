package org.shop.model.vo;

public class DeliveryCompanyReturnVO {

	private Integer id;

	private String name;

	private String info;

	@Override
	public String toString() {
		return "DeliveryCompanyReturnVO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", info='" + info + '\'' +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}