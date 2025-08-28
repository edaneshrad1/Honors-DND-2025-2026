import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	//Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescuePugs(ArrayList<Dog> list) {
		for (int i = 0; i < list.size(); i++) {
			Dog firstDog = list.get(i);
			String dogTypeFront = firstDog.getBreed();
			int isGoldFront = dogTypeFront.indexOf("gold");
			if (isGoldFront != -1) {
				for (int j = list.size() - 1; j >= 0; j--) {
					Dog lastDog = list.get(j);
					String dogTypeBack = lastDog.getBreed();
					int isGoldBack = dogTypeBack.indexOf("gold");
					int frontDogPos = i;
					if (isGoldBack == -1) {
						ArrayList<Dog> temp = new ArrayList<>();
						temp.add(lastDog);
						int lastDogPos = list.size() - j - 1;
						list.set(lastDogPos, firstDog);
						list.set(frontDogPos, temp.get(0));
					}
					//Find last dog in list that's not golden
					//Put this dog in a temp array list
					//Set the golden dog to the index of the last dog that's not golden
					//Set the temp to the origional index of the golden dog
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
