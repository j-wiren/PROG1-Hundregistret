
/**
 * @author Joakim Wirén jowi1137
 */

import java.util.*;

public class Owner {
	private String name;
	private Dog[] dogs;

	public Owner(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addDog(Dog dog) {

		if (dogs == null) {
			Dog[] updatedDogs = { dog };
			dogs = updatedDogs;
			return;
		}

		Dog[] updatedDogs = Arrays.copyOf(dogs, dogs.length + 1);
		updatedDogs[updatedDogs.length - 1] = dog;
		dogs = updatedDogs;

		// dog.assignOwner(this);
	}

	public String toString() {
		return "Name: " + this.name;
	}
}