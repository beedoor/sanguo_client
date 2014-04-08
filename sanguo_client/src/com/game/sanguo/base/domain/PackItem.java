package com.game.sanguo.base.domain;

public class PackItem {
	private Long id=0L;
	private String props_type;
	private String props_name;
	private String p_descrip;
	private Long price=0L;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProps_type() {
		return props_type;
	}
	public void setProps_type(String props_type) {
		this.props_type = props_type;
	}
	public String getProps_name() {
		return props_name;
	}
	public void setProps_name(String props_name) {
		this.props_name = props_name;
	}
	public String getP_descrip() {
		return p_descrip;
	}
	public void setP_descrip(String p_descrip) {
		this.p_descrip = p_descrip;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "PackItem [id=" + id + ", props_type=" + props_type + ", props_name=" + props_name + ", p_descrip=" + p_descrip + ", price=" + price + "]";
	}
}
