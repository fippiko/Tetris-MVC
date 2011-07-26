package game.view.configuration;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

public class ConfigurationTable extends JTable {
	private JScrollPane scrollPane;

	private final static int ROWHEIGHT = 40;

	public ConfigurationTable() {
		this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		this.setAutoResizeMode(AUTO_RESIZE_LAST_COLUMN);
		this.setRowHeight(ROWHEIGHT);
	}

	public JScrollPane generateScrollPane() {
		this.scrollPane = new JScrollPane(this);

		this.scrollPane.setBounds(this.getBounds());

		return this.scrollPane;
	}

	@Override
	public void setModel(TableModel dataModel) {
		super.setModel(dataModel);

		if (this.scrollPane != null) {
			this.scrollPane.setBounds(this.getBounds());
		}
	}

	@Override
	public TableCellEditor getCellEditor(int row, int col) {
		TableCellEditor tmpEditor = ((ConfigurationTableModel)this.getModel()).getCellEditor(row, col);
		if (tmpEditor != null){
			return tmpEditor;
		}
		return super.getCellEditor(row, col);
	}

	public void updateUI() {
		super.updateUI();

		LookAndFeel.installColorsAndFont(this, "TableHeader.background", "TableHeader.foreground", "TableHeader.font");
	}
}
