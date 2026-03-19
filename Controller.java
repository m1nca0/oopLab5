import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;


public class Controller {
    private Daw daw;
    private DawInOut inOut;
    private MyTable myTableModel;

    public Controller(){
        this.daw = new Daw();
        this.inOut = new DawInOut();
        myTableModel = new MyTable(this.daw, this.inOut);
    }

    public void saveProjectToFile(String way) {
        if (!way.toLowerCase().endsWith(".txt")) {
            way += ".txt";
        }
        myTableModel.saveSamples(way);
    }

    public void showFileList(File file) {
        myTableModel.openSamples(file.getAbsolutePath());
    }

    public void showDeleteMenu(String name, String type) {
            if (name.isEmpty() ||
                    type.isEmpty()) {
                // JOptionPane.showMessageDialog(this, "Для удаления заполните все поля!");
                return;
            }
            myTableModel.deleteSamples(name, type);
        }


    public void showKickDialog(String nameKick, String lenKick,String volumeKick,String lowFrequencyKick,String highFrequencyKick,String bassLevelKick) {
            if (nameKick.isEmpty() ||
                    lenKick.isEmpty() ||
                    volumeKick.isEmpty() ||
                    lowFrequencyKick.isEmpty() ||
                    highFrequencyKick.isEmpty() ||
                    bassLevelKick.isEmpty()) {

                // JOptionPane.showMessageDialog(this, "Для добавления сэмпла заполните все поля!");
                return;
            }
            try {
                String name = nameKick.trim();
                int len = Integer.parseInt(lenKick.trim());
                double volume = Double.parseDouble(volumeKick.trim());
                int lowFrequency = Integer.parseInt(lowFrequencyKick.trim());
                int highFrequency = Integer.parseInt(highFrequencyKick.trim());
                int bassLevel = Integer.parseInt(bassLevelKick.trim());

                Kick kick = new Kick(name, len, volume, lowFrequency, highFrequency, bassLevel);
                myTableModel.addSamples(kick);
                // JOptionPane.showMessageDialog(this, "Kick - " + name + " успешно добавлен!");
            } catch (Exception e) {
                // JOptionPane.showMessageDialog(this, "Введите корректные значения в поля \n Если поле подразумевает числовое значение, не вводите текст \n Для дробных значений используйте '.'");
            }
    }

    public void showSnareDialog() {
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

    public void showHatDialog() {
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
