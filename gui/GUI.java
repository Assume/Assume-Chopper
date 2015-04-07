package scripts.woodcutter.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import scripts.woodcutter.api.types.bottom.progression.Progression;
import scripts.woodcutter.api.types.ingame.Tree;
import scripts.woodcutter.api.types.variables.Vars;

public class GUI extends JFrame {

	private JPanel contentPane;
	private ProgressionGUI progression_gui;
	private GUI main_gui;
	private final JComboBox<Progression> progression_method;
	private final DefaultComboBoxModel<Progression> progression_model;

	public GUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 183, 266);
		this.main_gui = this;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnStart = new JButton("Start");

		btnStart.setBounds(10, 189, 147, 23);
		contentPane.add(btnStart);

		final JCheckBox chckbxProgressiveMode = new JCheckBox(
				"Tree Progression");
		chckbxProgressiveMode.setBounds(10, 44, 120, 23);
		contentPane.add(chckbxProgressiveMode);

		final JCheckBox chckbxAxeProgression = new JCheckBox("Axe progression");
		chckbxAxeProgression.setBounds(10, 101, 120, 23);
		contentPane.add(chckbxAxeProgression);
		final JComboBox<Tree> location_box = new JComboBox<Tree>(Tree.values());
		location_box.setBounds(10, 11, 147, 20);
		contentPane.add(location_box);

		final JCheckBox drop_logs_box = new JCheckBox("Drop logs");
		drop_logs_box.setBounds(10, 125, 97, 23);
		contentPane.add(drop_logs_box);

		progression_method = new JComboBox<Progression>();
		progression_method.setEnabled(false);
		progression_method.setBounds(10, 74, 147, 20);
		progression_model = new DefaultComboBoxModel<Progression>(
				Vars.get().PROGRESSION_TYPES.toArray(new Progression[Vars.get().PROGRESSION_TYPES
						.size()]));
		progression_method.setModel(progression_model);
		contentPane.add(progression_method);

		JButton btnNewButton = new JButton("New Progression");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (progression_gui != null && progression_gui.isVisible()) {
					JOptionPane.showMessageDialog(null,
							"Finishing making your current progression first!");
					return;
				}
				progression_gui = new ProgressionGUI(main_gui);
				progression_gui.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 155, 147, 23);
		contentPane.add(btnNewButton);

		chckbxProgressiveMode.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				progression_method.setEnabled(chckbxProgressiveMode
						.isSelected());
			}
		});

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (progression_gui != null && progression_gui.isVisible()) {
					JOptionPane.showMessageDialog(null,
							"Finishing making your current progression first!");
					return;
				}
				Vars.get().progressive_mode = chckbxProgressiveMode
						.isSelected();
				Vars.get().axe_upgrades = chckbxAxeProgression.isSelected();
				Vars.get().current_location = (Tree) location_box
						.getSelectedItem();
				Vars.get().banking = !drop_logs_box.isSelected();
				Vars.get().progression_type = (Progression) progression_method
						.getSelectedItem();
				setVisible(false);
			}
		});
	}

	public void updateProgressions() {
		if (progression_model.getSize() != Vars.get().PROGRESSION_TYPES.size())
			progression_model.addElement(Vars.get().PROGRESSION_TYPES.get(Vars
					.get().PROGRESSION_TYPES.size() - 1));

	}
}
