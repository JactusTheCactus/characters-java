package app;

import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import app.utils.Char.Character;

public class App {
	public static void main(String[] args) {
		var chars = new TreeSet<Character>();
		chars.add(new Character(
				new String[] {
						"Morrigan",
						"Heffernan"
				}));
		chars.add(new Character(
				new String[] {
						"Hound",
						"NcNamara"
				}));
		JFrame window = new JFrame("Characters");
		window.setSize(1980, 1080);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		var main = new JPanel();
		for (Character i : chars) {
			String name;
			Character c = i.clone();
			if (c.name.size() == 1) {
				name = c.name.getLast();
			} else {
				String last = c.name.getLast();
				c.name.removeLast();
				String names = String.join(" ", c.name);
				name = String.format("%s, %s", last, names);
			}
			var button = new JButton(name);
			main.add(button);
		}
		window.add(main);
		window.setVisible(true);
	}
}