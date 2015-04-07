package scripts.woodcutter.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import scripts.woodcutter.api.types.bottom.progression.ProgressionFactory;
import scripts.woodcutter.api.types.bottom.progression.ProgressionMove;
import scripts.woodcutter.api.types.bottom.progression.ProgressionMoveType;
import scripts.woodcutter.api.types.ingame.Tree;

public class ProgressionMoveCreator extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	public ProgressionMoveCreator(final ProgressionFactory factory,
			final ProgressionGUI gui) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 459, 135);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JComboBox<Tree> comboBoxTrees = new JComboBox<Tree>(Tree.values());
		comboBoxTrees.setBounds(10, 33, 115, 20);
		contentPane.add(comboBoxTrees);

		JLabel lblMoveTo = new JLabel("Move to");
		lblMoveTo.setBounds(10, 11, 95, 11);
		contentPane.add(lblMoveTo);

		final JComboBox<ProgressionMoveType> comboBoxConditions = new JComboBox<ProgressionMoveType>(
				ProgressionMoveType.values());
		comboBoxConditions.setBounds(149, 33, 115, 20);
		contentPane.add(comboBoxConditions);

		JLabel lblCondition = new JLabel("Condition");
		lblCondition.setBounds(149, 11, 95, 11);
		contentPane.add(lblCondition);

		final JLabel lblValue = new JLabel("Value");
		lblValue.setBounds(288, 11, 151, 11);
		contentPane.add(lblValue);

		textField = new JTextField();
		textField.setBounds(288, 33, 145, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		comboBoxConditions.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED)
					lblValue.setText(((ProgressionMoveType) comboBoxConditions
							.getSelectedItem()).getLabelText());
			}
		});

		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProgressionMove fin = factory.create((Tree) comboBoxTrees
						.getSelectedItem(),
						(ProgressionMoveType) comboBoxConditions
								.getSelectedItem(), textField.getText());
				gui.addRow(fin);
				setVisible(false);
				dispose();
			}
		});
		btnDone.setBounds(344, 62, 89, 23);
		contentPane.add(btnDone);
	}
}
