package scripts.woodcutter.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import scripts.woodcutter.api.types.bottom.progression.ProgressionFactory;
import scripts.woodcutter.api.types.bottom.progression.ProgressionMove;

public class ProgressionGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ProgressionFactory factory;
	protected DefaultTableModel table_model;
	private ProgressionGUI gui;
	private GUI main_gui;

	public ProgressionGUI(final GUI main_gui) {
		this.factory = new ProgressionFactory();
		this.gui = this;
		this.main_gui = main_gui;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 416, 141);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table_model = new DefaultTableModel(new String[][] { null },
				new String[] { "New Tree", "Condition" });
		table.setModel(table_model);
		table_model.removeRow(0);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ProgressionMoveCreator(factory, gui).setVisible(true);
			}
		});
		btnAdd.setBounds(28, 158, 89, 23);
		contentPane.add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (row == -1)
					return;
				factory.remove(row);
				table_model.removeRow(row);
			}
		});
		btnRemove.setBounds(127, 158, 89, 23);
		contentPane.add(btnRemove);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "NOT CURRENTLY AVAILABLE");
			}
		});
		btnEdit.setBounds(226, 158, 89, 23);
		contentPane.add(btnEdit);

		JButton btnNewButton = new JButton("Done");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = JOptionPane
						.showInputDialog("Enter name for this progression type:");
				factory.close(name);
				main_gui.updateProgressions();
				setVisible(false);
				dispose();
			}
		});
		btnNewButton.setBounds(325, 158, 89, 23);
		contentPane.add(btnNewButton);
	}

	public void addRow(ProgressionMove fin) {
		table_model.addRow(new Object[] { fin.getNextTree(), fin });
	}
}
