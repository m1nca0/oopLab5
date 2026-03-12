import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DawView extends JFrame {
    private Daw daw;
    private Kick kick;
    private Snare snare;
    private Hat hat;
    private MyTable myTableModel;
    private JComboBox<JButton> addSample;

    public DawView() {
        Daw daw = new Daw();
        myTableModel = new MyTable(daw);
        JTable table = new JTable(myTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        JButton addButton = new JButton("Добавить");

        setSize(700, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(addButton);
        add(toolBar, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> showAddMenu(addButton));
        setVisible(true);
    }
    private void showAddMenu(JButton button)
    {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem kickItem = new JMenuItem("Добавить KICK");
        menu.add(kickItem);
        menu.show(button, 0, button.getHeight());
    }
}