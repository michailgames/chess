package chess.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import chess.controller.LogController;

/**
 * Projekt: Szachy Panel zawieraj¹cy zapis partii Micha³ Rapacz 2015-05-17
 */

public class MovesLogPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTable logsTable;
    private MovesTableModel tableModel;

    private JScrollPane scrollPanel;

    public MovesLogPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());
        addTitle();
        scrollPanel = new JScrollPane();
        add(scrollPanel, BorderLayout.CENTER);
        createLogsTable();
        scrollPanel.setViewportView(logsTable);
    }

    private void createLogsTable() {
        logsTable = new JTable();
        tableModel = new MovesTableModel();
        logsTable.setModel(tableModel);
        logsTable.getTableHeader().setReorderingAllowed(false);
        logsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumn firstColumn = logsTable.getColumn("#");
        firstColumn.setMaxWidth(50);
        firstColumn.setMinWidth(50);
        firstColumn.setCellRenderer(new CellRenderer());
        logsTable.getColumn("White").setCellRenderer(new CellRenderer());
        logsTable.getColumn("Black").setCellRenderer(new CellRenderer());
        logsTable.setFont(logsTable.getFont().deriveFont(16f));
        logsTable.setRowHeight(logsTable.getFontMetrics(logsTable.getFont()).getHeight() + 2);

        tableModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                JScrollBar verticalScrollBar = scrollPanel.getVerticalScrollBar();
                verticalScrollBar.setValue(verticalScrollBar.getMaximum());
            }
        });
    }

    private void addTitle() {
        JPanel titlePanel = new JPanel();
        add(titlePanel, BorderLayout.NORTH);
        titlePanel.add(new JLabel("Game log"));
    }

    private final class MovesTableModel extends AbstractTableModel {

        private static final long serialVersionUID = 1L;

        @Override
        public String getColumnName(int column) {
            switch (column) {
            case 0:
                return "#";
            case 1:
                return "White";
            case 2:
                return "Black";
            }
            return "";
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return Integer.toString(rowIndex + 1);
            }
            return LogController.getInstance().getLog(rowIndex * 2 + columnIndex - 1);
        }

        @Override
        public int getRowCount() {
            int moves = LogController.getInstance().getLogsNumber();
            return moves / 2 + moves % 2 + 1;
        }

        @Override
        public int getColumnCount() {
            return 3;
        }
    }

    private final class CellRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        public CellRenderer() {
            super();
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super
                    .getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == 1) {
                label.setForeground(BoardPanel.WHITE_PIECES_COLOR);
            } else if (column == 2) {
                label.setForeground(BoardPanel.BLACK_PIECES_COLOR);
            }
            return label;
        }
    }

    public void refreshLogs() {
        tableModel.fireTableDataChanged();
    }
}
