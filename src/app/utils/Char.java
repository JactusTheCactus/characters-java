package app.utils;

import java.util.LinkedList;

public class Char {
	public static class Character implements Comparable<Character> {
		public LinkedList<String> name;
		public LinkedList<String> pron;
		public char sex;
		public LinkedList<String> spec;
		public LinkedList<String> extr;

		public Character(String[] name_, String[] pron_, String sex_, String[] spec_, String[] extr_) {
			name = new LinkedList<String>();
			for (String i : name_) {
				name.add(i);
			}
			while (name.size() > 2) {
				name.removeLast();
			}
			pron = new LinkedList<String>();
			for (String i : pron_) {
				pron.add(i);
			}
			while (pron.size() > 2) {
				pron.removeLast();
			}
			switch (sex_) {
				case "Male":
					sex = (char) 0x2642;
					break;
				case "Female":
					sex = (char) 0x2640;
					break;
				default:
					sex = (char) 0x26A5;
			}
			spec = new LinkedList<String>();
			for (String i : spec_) {
				spec.add(i);
			}
			extr = new LinkedList<String>();
			for (String i : extr_) {
				extr.add(i);
			}
		}

		public Character clone() {
			String[] n = name.toArray(new String[0]);
			String[] p = pron.toArray(new String[0]);
			String sx;
			switch (sex) {
				case (char) 0x2642:
					sx = "Male";
					break;
				case (char) 0x2640:
					sx = "Female";
					break;
				default:
					sx = "Neuter";
			}
			String[] sp = spec.toArray(new String[0]);
			String[] e = extr.toArray(new String[0]);
			return new Character(n, p, sx, sp, e);
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