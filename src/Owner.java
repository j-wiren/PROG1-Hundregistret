
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

	public Dog[] getDogs() {
		Dog[] dogsCopy = dogs;
		return dogsCopy;
	}

	public void addDog(Dog dog) {
		if (dogs == null) {
			Dog[] updatedDogs = { dog };
			dogs = updatedDogs;
		} else {
			Dog[] updatedDogs = Arrays.copyOf(dogs, dogs.length + 1);
			updatedDogs[updatedDogs.length - 1] = dog;
			dogs = updatedDogs;
		}

		// kollar om hunden redan har en ägare
		if (dog.getOwner() == null) {
			dog.assignOwner(this);
		}
	}

	public void removeDog(Dog dog) {
		Dog[] updatedDogs = new Dog[dogs.length - 1];
		for (int i = 0, j = 0; i < dogs.length; i++) {
			if (dogs[i] != dog) {
				updatedDogs[j] = dogs[i];
				j++;
			}
		}
		dogs = updatedDogs;
	}

	public void removeAllDogs() {
		dogs = null;
	}

	public boolean checkIfOwnerOwnsDog(Dog dog) {
		boolean foundDog = false;
		if (dogs != null) {
			for (Dog d : dogs) {
				if (d == dog) {
					foundDog = true;
				}
			}
		}
		return foundDog;
	}

	public String toString() {
		return "Name: " + this.name;
	}
}