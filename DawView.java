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
        toolBar.addSeparator();
        add(toolBar, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> showAddMenu(addButton));
        setVisible(true);
    }
    private void showAddMenu(JButton button)
    {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem kickItem = new JMenuItem("Добавить KICK");
        kickItem.addActionListener(e -> showKickDialog());

        JMenuItem snareItem = new JMenuItem("Добавить SNARE");
        // snareItem.addActionListener(e -> showSnareDialog());

        JMenuItem hatItem = new JMenuItem("Добавить HAT");
        // hatItem.addActionListener(e -> showHatDialog());


        menu.add(kickItem);
        menu.add(snareItem);
        menu.add(hatItem);
        menu.show(button, 0, button.getHeight());
    }
    private void showKickDialog(){
        JTextField nameKick = new JTextField(15);
        JTextField lenKick = new JTextField(15);
        JTextField volumeKick = new JTextField(15);
        JTextField lowFrequencyKick = new JTextField(15);
        JTextField highFrequencyKick = new JTextField(15);
        JTextField bassLevelKick = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.add(new JLabel("Название: "));
        panel.add(nameKick);
        panel.add(new JLabel("Длина (в милисекундах, 0-10000): "));
        panel.add(lenKick);
        panel.add(new JLabel("Громкость: "));
        panel.add(volumeKick);
        panel.add(new JLabel("Низ: "));
        panel.add(lowFrequencyKick);
        panel.add(new JLabel("Вверх: "));
        panel.add(highFrequencyKick);
        panel.add(new JLabel("Басс: "));
        panel.add(bassLevelKick);

        int result = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Добавление kick'а",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
        if (result == JOptionPane.OK_OPTION) {
            if (nameKick.getText().trim().isEmpty() ||
                lenKick.getText().trim().isEmpty() ||
                volumeKick.getText().trim().isEmpty() ||
                lowFrequencyKick.getText().trim().isEmpty() ||
                highFrequencyKick.getText().trim().isEmpty() ||
                bassLevelKick.getText().trim().isEmpty()) {
                
                JOptionPane.showMessageDialog(this, "Для добавления сэмпла заполните все поля!");

            }
            String name = nameKick.getText().trim();
            int len = Integer.parseInt(lenKick.getText().trim());
            double volume = Double.parseDouble(volumeKick.getText().trim());
            int lowFrequency = Integer.parseInt(lowFrequencyKick.getText().trim());
            int highFrequency = Integer.parseInt(highFrequencyKick.getText().trim());
            int bassLevel = Integer.parseInt(bassLevelKick.getText().trim());

            Kick kick = new Kick(name, len, volume, lowFrequency, highFrequency, bassLevel);
            myTableModel.addSamples(kick);
            JOptionPane.showMessageDialog(this, "Kick - " + name + " успешно добавлен!");
        }
    }
}