package app;

import app.utils.Char.Character;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.util.Enumeration;
import java.util.TreeSet;

public class App {
	public static void main(String[] args) {
		char ACUTE = (char) 0x301;
		char GRAVE = (char) 0x300;
		char OMEGA = (char) 0x3C9;
		char SCHWA = (char) 0x259;
		char ASH = (char) 0xE6;
		String E_ = String.format("e%s", (char) 0x323);
		var chars = new TreeSet<Character>();
		chars.add(new Character(
				new String[] {
						"Morrigan",
						"Heffernan"
				},
				new String[] {
						String.format("m%s%sr%sgy%sn", OMEGA, ACUTE, SCHWA, GRAVE),
						String.format("he%sf%srn%s%sn", ACUTE, SCHWA, ASH, GRAVE)
				},
				"Female",
				new String[] {
						"Reaper"
				},
				new String[] {
						"Killing Touch",
						"Wields A Scythe"
				}));
		chars.add(new Character(
				new String[] {
						"Hound",
						"NcNamara"
				},
				new String[] {
						"haund",
						String.format("ny%skn%sm%s%sr%s", ACUTE, SCHWA, E_, GRAVE, SCHWA)
				},
				"Female",
				new String[] {
						"Human",
						"Changeling"
				},
				new String[] {
						"Shapeshifts Into A Large, Black Wolf"
				}));

		UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("SansSerif", Font.PLAIN, 30));
		var win = new JFrame("Characters");
		win.setSize(1980, 1080);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setLayout(new BoxLayout(win, BoxLayout.Y_AXIS));
		var main = new JPanel();
		var bottom = new JPanel();
		for (Character i : chars) {
			String nameOfficial;
			var c = i.clone();
			if (c.name.size() == 1) {
				nameOfficial = c.name.getLast();
			} else {
				var last = c.name.getLast();
				c.name.removeLast();
				var names = String.join(" ", c.name);
				nameOfficial = String.format("%s, %s", last, names);
				c.name.add(last);
			}
			var button = new JButton(nameOfficial);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					main.removeAll();
					var body = new JPanel();
					{
						var nameLabel = new JLabel(String.format("%s%s", c.sex, String.join(" ", c.name)));
						nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));
						body.add(nameLabel);
					}
					{
						var pronLabel = new JLabel(String.format("<%s>", String.join("|", c.pron)));
						pronLabel.setFont(pronLabel.getFont().deriveFont(Font.ITALIC));
						body.add(pronLabel);
					}
					{
						var specLabel = new JLabel("Species:");
						String specList = "";
						for (String i : c.spec) {
							specList += String.format("<li>%s</li>", i);
						}
						body.add(specLabel);
						var specBody = new JLabel(String.format("<html><ul>%s</ul></html>", specList));
						specBody.setLayout(new BoxLayout(specBody, BoxLayout.Y_AXIS));
						body.add(specBody);
					}
					{
						var extrLabel = new JLabel("Extra:");
						String extrList = "";
						for (String i : c.extr) {
							extrList += String.format("<li>%s</li>", i);
						}
						body.add(extrLabel);
						var extrBody = new JLabel(String.format("<html><ul>%s</ul></html>", extrList));
						extrBody.setLayout(new BoxLayout(extrBody, BoxLayout.Y_AXIS));
						body.add(extrBody);
					}
					body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
					main.add(body);
					main.revalidate();
					main.repaint();
				}
			});
			bottom.add(button);
		}
		win.add(main, BorderLayout.WEST);
		win.add(bottom, BorderLayout.SOUTH);
		win.setVisible(true);
	}
}