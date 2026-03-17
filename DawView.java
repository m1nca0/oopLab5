import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DawView extends JFrame {
    //ghbdtn 
    private Daw daw;
    private DawInOut inOut;
    private MyTable myTableModel;
    private int rowIndex = 0;
    private JFileChooser fileChooser = new JFileChooser();

    public DawView() {
        this.daw = new Daw();
        this.inOut = new DawInOut();
        myTableModel = new MyTable(this.daw, this.inOut);
        JTable table = new JTable(myTableModel);
        table.setAutoCreateRowSorter(true);
        TableRowSorter<MyTable> sorter = new TableRowSorter<>(myTableModel);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);
        JButton openButton = new JButton("Открыть");
        JButton saveButton = new JButton("Сохранить");
        JButton addButton = new JButton("Добавить");
        JButton deleteButton = new JButton("Удалить");
        JLabel searchLabel = new JLabel(" Поиск: ");
        JTextField searchField = new JTextField(15);
        JButton filterButton = new JButton("Параметр поиска");

        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
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
        });

        setSize(700, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(openButton);
        toolBar.addSeparator();
        toolBar.add(saveButton);
        toolBar.addSeparator();
        toolBar.add(addButton);
        toolBar.addSeparator();
        toolBar.add(deleteButton);
        toolBar.addSeparator();
        toolBar.add(filterButton);
        toolBar.addSeparator();
        toolBar.add(searchLabel);
        toolBar.add(searchField);

        add(toolBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddMenu(addButton);
            } 
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              showDeleteMenu();
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              showFilterMenu(filterButton);
            }
        });

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              showFileList();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              saveProjectToFile();
            }
        });

        setVisible(true);
    }

    private void saveProjectToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Сохранить проект");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            String way = fileToSave.getAbsolutePath();

            if (!way.toLowerCase().endsWith(".txt")) {
                way += ".txt";
            }
            System.out.print(daw.getSamplesCount());
            myTableModel.saveSamples(way);
        }
    }

    private void showFileList() {

        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            myTableModel.openSamples(file.getAbsolutePath());
        }
    }

    private void showFilterMenu(JButton button) {
        JPopupMenu filterMenu = new JPopupMenu();

        JMenuItem typeIteme = new JMenuItem("Тип");
        typeIteme.addActionListener(e -> rowIndex = 1);
        JMenuItem nameIteme = new JMenuItem("Название");
        nameIteme.addActionListener(e -> rowIndex = 2);
        JMenuItem lenIteme = new JMenuItem("Длина");
        lenIteme.addActionListener(e -> rowIndex = 3);
        JMenuItem volumeIteme = new JMenuItem("Громкость");
        volumeIteme.addActionListener(e -> rowIndex = 4);
        JMenuItem lowIteme = new JMenuItem("Низ");
        lowIteme.addActionListener(e -> rowIndex = 5);
        JMenuItem highIteme = new JMenuItem("Вверх");
        highIteme.addActionListener(e -> rowIndex = 6);
        JMenuItem bassIteme = new JMenuItem("Басс");
        bassIteme.addActionListener(e -> rowIndex = 7);
        JMenuItem resIteme = new JMenuItem("Резонанс");
        resIteme.addActionListener(e -> rowIndex = 8);
        JMenuItem punchIteme = new JMenuItem("Удар");
        punchIteme.addActionListener(e -> rowIndex = 9);
        JMenuItem tailIteme = new JMenuItem("Длина хвоста");
        tailIteme.addActionListener(e -> rowIndex = 10);
        JMenuItem openCloseIteme = new JMenuItem("Закрытый/Открытый");
        openCloseIteme.addActionListener(e -> rowIndex = 11);

        filterMenu.add(typeIteme);
        filterMenu.add(nameIteme);
        filterMenu.add(lenIteme);
        filterMenu.add(volumeIteme);
        filterMenu.add(lowIteme);
        filterMenu.add(highIteme);
        filterMenu.add(bassIteme);
        filterMenu.add(resIteme);
        filterMenu.add(punchIteme);
        filterMenu.add(tailIteme);
        filterMenu.add(lenIteme);
        filterMenu.add(openCloseIteme);
        filterMenu.show(button, 0, button.getHeight());
    }

    private void showDeleteMenu() {
        JTextField nameSample = new JTextField();
        JTextField typeSample = new JTextField();
        JPanel deletePanel = new JPanel(new GridLayout(2, 2, 5, 5));
        deletePanel.add(new JLabel("Название:"));
        deletePanel.add(nameSample);
        deletePanel.add(new JLabel("Тип:"));
        deletePanel.add(typeSample);

        int result = JOptionPane.showConfirmDialog(
                this,
                deletePanel,
                "Удаление сэмпла",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (nameSample.getText().trim().isEmpty() ||
                    typeSample.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Для удаления заполните все поля!");
                return;
            }
            String name = nameSample.getText().trim();
            String type = typeSample.getText().trim();
            myTableModel.deleteSamples(name, type);
        }
    }

    private void showAddMenu(JButton button) {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem kickItem = new JMenuItem("Добавить KICK");
        kickItem.addActionListener(e -> showKickDialog());

        JMenuItem snareItem = new JMenuItem("Добавить SNARE");
        snareItem.addActionListener(e -> showSnareDialog());

        JMenuItem hatItem = new JMenuItem("Добавить HAT");
        hatItem.addActionListener(e -> showHatDialog());

        menu.add(kickItem);
        menu.add(snareItem);
        menu.add(hatItem);
        menu.show(button, 0, button.getHeight());
    }

    private void showKickDialog() {
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
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (nameKick.getText().trim().isEmpty() ||
                    lenKick.getText().trim().isEmpty() ||
                    volumeKick.getText().trim().isEmpty() ||
                    lowFrequencyKick.getText().trim().isEmpty() ||
                    highFrequencyKick.getText().trim().isEmpty() ||
                    bassLevelKick.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Для добавления сэмпла заполните все поля!");

            }
            try {
                String name = nameKick.getText().trim();
                int len = Integer.parseInt(lenKick.getText().trim());
                double volume = Double.parseDouble(volumeKick.getText().trim());
                int lowFrequency = Integer.parseInt(lowFrequencyKick.getText().trim());
                int highFrequency = Integer.parseInt(highFrequencyKick.getText().trim());
                int bassLevel = Integer.parseInt(bassLevelKick.getText().trim());

                Kick kick = new Kick(name, len, volume, lowFrequency, highFrequency, bassLevel);
                myTableModel.addSamples(kick);
                JOptionPane.showMessageDialog(this, "Kick - " + name + " успешно добавлен!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Введите корректные значения в поля \n Если поле подразумевает числовое значение, не вводите текст \n Для дробных значений используйте '.'");
            }

        }
    }

    private void showSnareDialog() {
        JTextField nameSnare = new JTextField(15);
        JTextField lenSnare = new JTextField(15);
        JTextField volumeSnare = new JTextField(15);
        JTextField lowFrequencySnare = new JTextField(15);
        JTextField highFrequencySnare = new JTextField(15);
        JTextField resonanceSnare = new JTextField(15);
        JTextField punchSnare = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.add(new JLabel("Название: "));
        panel.add(nameSnare);
        panel.add(new JLabel("Длина (в милисекундах, 0-10000): "));
        panel.add(lenSnare);
        panel.add(new JLabel("Громкость: "));
        panel.add(volumeSnare);
        panel.add(new JLabel("Низ: "));
        panel.add(lowFrequencySnare);
        panel.add(new JLabel("Вверх: "));
        panel.add(highFrequencySnare);
        panel.add(new JLabel("Резонанс: "));
        panel.add(resonanceSnare);
        panel.add(new JLabel("Удар: "));
        panel.add(punchSnare);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Добавление snare'а",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (nameSnare.getText().trim().isEmpty() ||
                    lenSnare.getText().trim().isEmpty() ||
                    volumeSnare.getText().trim().isEmpty() ||
                    lowFrequencySnare.getText().trim().isEmpty() ||
                    highFrequencySnare.getText().trim().isEmpty() ||
                    resonanceSnare.getText().trim().isEmpty() ||
                    punchSnare.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Для добавления сэмпла заполните все поля!");

            }
            try {
                String name = nameSnare.getText().trim();
                int len = Integer.parseInt(lenSnare.getText().trim());
                double volume = Double.parseDouble(volumeSnare.getText().trim());
                int lowFrequency = Integer.parseInt(lowFrequencySnare.getText().trim());
                int highFrequency = Integer.parseInt(highFrequencySnare.getText().trim());
                int resonance = Integer.parseInt(resonanceSnare.getText().trim());
                int punch = Integer.parseInt(punchSnare.getText().trim());

                Snare snare = new Snare(name, len, volume, lowFrequency, highFrequency, resonance, punch);
                myTableModel.addSamples(snare);
                JOptionPane.showMessageDialog(this, "Snare - " + name + " успешно добавлен!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Введите корректные значения в поля \n Если поле подразумевает числовое значение, не вводите текст \n Для дробных значений используйте '.'");
            }

        }
    }

    private void showHatDialog() {
        JTextField nameHat = new JTextField(15);
        JTextField lenHat = new JTextField(15);
        JTextField volumeHat = new JTextField(15);
        JTextField lowFrequencyHat = new JTextField(15);
        JTextField highFrequencyHat = new JTextField(15);
        JTextField tailLengthHat = new JTextField(15);
        JTextField closedHat = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.add(new JLabel("Название: "));
        panel.add(nameHat);
        panel.add(new JLabel("Длина (в милисекундах, 0-10000): "));
        panel.add(lenHat);
        panel.add(new JLabel("Громкость: "));
        panel.add(volumeHat);
        panel.add(new JLabel("Низ: "));
        panel.add(lowFrequencyHat);
        panel.add(new JLabel("Вверх: "));
        panel.add(highFrequencyHat);
        panel.add(new JLabel("Длина хвоста: "));
        panel.add(tailLengthHat);
        panel.add(new JLabel("Открытый/Закрытый: "));
        panel.add(closedHat);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Добавление Hat'а",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (nameHat.getText().trim().isEmpty() ||
                    lenHat.getText().trim().isEmpty() ||
                    volumeHat.getText().trim().isEmpty() ||
                    lowFrequencyHat.getText().trim().isEmpty() ||
                    highFrequencyHat.getText().trim().isEmpty() ||
                    tailLengthHat.getText().trim().isEmpty() ||
                    closedHat.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Для добавления сэмпла заполните все поля!");

            }
            try {
                String name = nameHat.getText().trim();
                int len = Integer.parseInt(lenHat.getText().trim());
                double volume = Double.parseDouble(volumeHat.getText().trim());
                int lowFrequency = Integer.parseInt(lowFrequencyHat.getText().trim());
                int highFrequency = Integer.parseInt(highFrequencyHat.getText().trim());
                int tailLength = Integer.parseInt(tailLengthHat.getText().trim());
                boolean closed = tailLengthHat.getText().trim() == "Закрытый" ? false : true;
                Hat hat = new Hat(name, len, volume, lowFrequency, highFrequency, tailLength, closed);
                myTableModel.addSamples(hat);
                JOptionPane.showMessageDialog(this, "Hat - " + name + " успешно добавлен!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Введите корректные значения в поля \n Если поле подразумевает числовое значение, не вводите текст \n Для дробных значений используйте '.'");
            }

        }
    }
}