package View;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

public class SearchListener implements DocumentListener {
    private JTable table;
    private TableRowSorter<? extends AbstractTableModel> sorter;
    private JTextField searchField;
    private int rowIndex;

    public SearchListener(JTable table, TableRowSorter<? extends AbstractTableModel> sorter, JTextField searchField, int rowIndex) {
        this.table = table;
        this.sorter = sorter;
        this.searchField = searchField;
        this.rowIndex = rowIndex;
    }

    @Override
    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        applyFilter();
    }

    @Override
    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        applyFilter();
    }

    @Override
    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        applyFilter();
    }

    private void applyFilter() {
        String text = searchField.getText();
        if (text.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, rowIndex));
        }
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;

        applyFilter();
    }
}
