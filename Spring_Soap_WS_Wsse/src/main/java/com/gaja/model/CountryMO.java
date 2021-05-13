package com.gaja.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gaja.country.stubs.Currency;

@Entity
@Table(name = "country")
public class CountryMO {

	@Id
	@GeneratedValue
	protected int id;
    protected String name;
    protected int population;
    protected String capital;
    protected Currency currency;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public CountryMO(String name, int population, String capital, Currency currency) {
		this.name = name;
		this.population = population;
		this.capital = capital;
		this.currency = currency;
	}
	public CountryMO() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CountryMO [name=" + name + ", population=" + population + ", capital=" + capital + ", currency="
				+ currency + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capital == null) ? 0 : capital.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + population;
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
		CountryMO other = (CountryMO) obj;
		if (capital == null) {
			if (other.capital != null)
				return false;
		} else if (!capital.equals(other.capital))
			return false;
		if (currency != other.currency)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (population != other.population)
			return false;
		return true;
	}
    
    
}
