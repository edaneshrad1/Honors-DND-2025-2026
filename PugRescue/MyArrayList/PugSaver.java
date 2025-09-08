import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	// Moves every dog whose breed is "Pug" in the list to the back of the list

	//O(n)
	public static void rescuePugs(MyArrayList<Dog> list) {
		for (int i = 0; i < list.size(); i++) {
			if (isGolden(list.get(i))) {
				for (int j = list.size() - 1; j > i; j--) {
					if (!isGolden(list.get(j))) {
						Dog tempDog = list.get(i);
						list.set(i, list.get(j));
						list.set(j, tempDog);
						break;
					}
				}
			}
		}
	}

	//O(1)
	public static boolean isGolden(Dog dog) {
		if(dog == null) {
			return false;
		}
		String breed = dog.getBreed();
		if (breed.toLowerCase().contains("golden")) {
			return true;
		}
		return false;
	}
}

