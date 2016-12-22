package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TestCity extends TestBaseEntity<Long> {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String map;

	@ManyToOne
	private TestState state;

	public TestCity() {
	}

	public TestCity(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getMap() {
		return this.map;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public TestState getState() {
		return state;
	}

	public void setState(TestState state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return getName() + "," + getState() ;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		TestCity city = (TestCity) o;

		if (name != null ? !name.equals(city.name) : city.name != null) return false;
		if (state != null ? !state.equals(city.state) : city.state != null) return false;
		return map != null ? map.equals(city.map) : city.map == null;

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (state != null ? state.hashCode() : 0);
		result = 31 * result + (map != null ? map.hashCode() : 0);
		return result;
	}
}
