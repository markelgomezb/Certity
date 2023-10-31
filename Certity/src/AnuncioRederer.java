import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class AnuncioRederer implements TableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JLabel label = new JLabel();
		
		label.setBackground(table.getBackground());
		
		label.setHorizontalAlignment(JLabel.CENTER);
		
//		0"USUARIO",
//		1"FOTOS",
//		2"DESCRIPCION",
//		3"LOCALIDAD",
//		4"PRECIO"
		
		if(column == 0 || column == 3) {
			label.setText(value.toString());
		}
		
		if(column == 4) {
			label.setText(String.format("%.2f €", value));
			label.setHorizontalAlignment(JLabel.RIGHT);
		}
		
		if(column == 2) {
			label.setText(value.toString());
			label.setHorizontalAlignment(JLabel.LEFT);
		}
		
		if(isSelected) {
			label.setBackground(table.getSelectionBackground());
			label.setForeground(table.getSelectionForeground());
		}
		return null;
	}

}
