

public class Controller {
    private Daw daw;
    private DawInOut inOut;
    private MyTable myTableModel;

    public Controller(Daw dawContr, DawInOut inOutContr, MyTable myTableModelContr){
        this.daw = dawContr;
        this.inOut = inOutContr;
        this.myTableModel = myTableModelContr;
    }

    public void saveProjectToFile(String way) {
        if (!way.toLowerCase().endsWith(".txt")) {
            way += ".txt";
        }
        myTableModel.saveSamples(way);
    }

    public void showFileList(String way) {
        myTableModel.openSamples(way);
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

    public void showSnareDialog(String nameSnare, String lenSnare,String volumeSnare,String lowFrequencySnare,String highFrequencySnare,String resonanceSnare, String punchSnare) {
        if (nameSnare.isEmpty() ||
                lenSnare.isEmpty() ||
                volumeSnare.isEmpty() ||
                lowFrequencySnare.isEmpty() ||
                highFrequencySnare.isEmpty() ||
                resonanceSnare.isEmpty() ||
                punchSnare.isEmpty()) {

            // JOptionPane.showMessageDialog(this, "Для добавления сэмпла заполните все поля!");
            return;
        }
        try {
            String name = nameSnare;
            int len = Integer.parseInt(lenSnare);
            double volume = Double.parseDouble(volumeSnare);
            int lowFrequency = Integer.parseInt(lowFrequencySnare);
            int highFrequency = Integer.parseInt(highFrequencySnare);
            int resonance = Integer.parseInt(resonanceSnare);
            int punch = Integer.parseInt(punchSnare);

            Snare snare = new Snare(name, len, volume, lowFrequency, highFrequency, resonance, punch);
            myTableModel.addSamples(snare);
            // JOptionPane.showMessageDialog(this, "Snare - " + name + " успешно добавлен!");
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(this,"Введите корректные значения в поля \n Если поле подразумевает числовое значение, не вводите текст \n Для дробных значений используйте '.'");
        }
    }

    public void showHatDialog(String nameHat, String lenHat,String volumeHat,String lowFrequencyHat,String highFrequencyHat,String tailLengthHat, String closedHat) {
        if (nameHat.isEmpty() ||
                lenHat.isEmpty() ||
                volumeHat.isEmpty() ||
                lowFrequencyHat.isEmpty() ||
                highFrequencyHat.isEmpty() ||
                tailLengthHat.isEmpty() ||
                closedHat.isEmpty()) {
            return;
            // JOptionPane.showMessageDialog(this, "Для добавления сэмпла заполните все поля!");

        }
        try {
            String name = nameHat;
            int len = Integer.parseInt(lenHat);
            double volume = Double.parseDouble(volumeHat);
            int lowFrequency = Integer.parseInt(lowFrequencyHat);
            int highFrequency = Integer.parseInt(highFrequencyHat);
            int tailLength = Integer.parseInt(tailLengthHat);
            boolean closed = closedHat == "Закрытый" ? false : true;
            Hat hat = new Hat(name, len, volume, lowFrequency, highFrequency, tailLength, closed);
            myTableModel.addSamples(hat);
            // JOptionPane.showMessageDialog(this, "Hat - " + name + " успешно добавлен!");
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(this,"Введите корректные значения в поля \n Если поле подразумевает числовое значение, не вводите текст \n Для дробных значений используйте '.'");
        }

    }

}
