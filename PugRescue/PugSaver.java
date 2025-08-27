import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	//Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescuePugs(ArrayList<Dog> list) {
		for (int i = 0; i < list.size(); i++) {
			Dog firstDog = list.get(i);
			String dogTypeFront = firstDog.getBreed();
			int doesExistFront = dogTypeFront.indexOf("gold");
			if (doesExistFront != -1) {
				for (int j = list.size() - 1; j >= 0; j--) {
					Dog lastDog = list.get(j);
					//Find last dog in list that's not golden
					//Put this dog in a temp array list
					//Set the golden dog to the index of the last dog that's not golden
					//Set the index 
				}
				// ArrayList temp = new ArrayList();
				// Dog gold = list.get(i);
				// temp.add(list.get(i));
				// list.set(list.size() - 1 - i, temp.get(i));
				// list.set(li)
			}
		}
	}
}
