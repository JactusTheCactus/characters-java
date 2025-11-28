package app.utils;
import java.util.LinkedList;
public class Char {
	public static class Character implements Comparable<Character> {
		public LinkedList<String> name;
		public Character(String[] names) {
			name = new LinkedList<String>();
			for (String i : names) {
				name.add(i);
			}
			while (name.size() > 2) {
				name.removeLast();
			}
		}
		public Character clone() {
			String[] c = name.toArray(new String[0]);
			return new Character(c);
		}
		@Override
		public int compareTo(Character other) {
			if (this.name.isEmpty() && other.name.isEmpty()) {
				return 0;
			} else if (this.name.isEmpty()) {
				return -1;
			} else if (other.name.isEmpty()) {
				return 1;
			} else {
				return this.name.getLast().compareTo(other.name.getLast());
			}
		}
		@Override
		public String toString() {
			return String.join(" ", name);
		}
	}
}