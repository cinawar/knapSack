package com.mobiquityinc.models;

public class Item {
	public Integer index;
	public Integer price;
	public Double weight;

	public Item(Integer index, Double weight, Integer price) {
		this.setWeight(weight);
		this.setIndex(index);
		this.setPrice(price);
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(Integer value) {
		this.price = value;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [index=");
		builder.append(index);
		builder.append(", price=");
		builder.append(price);
		builder.append(", weight=");
		builder.append(weight);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

}
