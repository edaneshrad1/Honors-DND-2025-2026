import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	// Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescuePugs(ArrayList<Dog> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBreed().indexOf("Gold") != -1) {
				for (int j = list.size() - 1; j > 0; j--) {
					if (list.get(j).getBreed().indexOf("Gold") == -1) {
						if (i < j) {
							Dog tempDog1 = list.get(i);
							Dog tempDog2 = list.get(j);
							list.set(i, tempDog2);
							list.set(j, tempDog1);
						}
					}
				}
			}
		}
	}
}

// Find last dog in list that's not golden
// Put this dog in a temp array list
// Set the golden dog to the index of the last dog that's not golden
// Set the temp to the origional index of the golden dog
