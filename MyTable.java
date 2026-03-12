import javax.swing.table.AbstractTableModel;

public class MyTable extends AbstractTableModel {
    private Daw daw;

    public MyTable(Daw daw) {
        this.daw = daw;
    }

    @Override
    public int getRowCount() {
        return daw.getSamples().size();
    }

    @Override
    public int getColumnCount() {
        return 13;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "№";
            case 1:
                return "Тип";
            case 2:
                return "Название";
            case 3:
                return "Длина";
            case 4:
                return "Громкость";
            case 5:
                return "Низ";
            case 6:
                return "Вверх";
            case 7:
                return "Уровень басса";
            case 8:
                return "Резонанс";
            case 9:
                return "Удар";
            case 10:
                return "Длина хвоста";
            case 11:
                return "Закрытый/Открытый";
            case 12:
              return "Звук";
            default:return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return daw.getSamples().get(rowIndex).getType();
            case 2:
                return daw.getSamples().get(rowIndex).getName();
            case 3:
                return daw.getSamples().get(rowIndex).getLengthMs();
            case 4:
                return daw.getSamples().get(rowIndex).getVolume();
            case 5:
                return daw.getSamples().get(rowIndex).getLowFrequency();
            case 6:
                return daw.getSamples().get(rowIndex).getHighFrequency();
            case 7:
                return ((Kick) daw.getSamples().get(rowIndex)).getBassLevel();
            case 8:
                return ((Snare)daw.getSamples().get(rowIndex)).getResonance();
            case 9:
                return ((Snare)daw.getSamples().get(rowIndex)).getPunch();
            case 10:
                return ((Hat)daw.getSamples().get(rowIndex)).getTailLength();
            case 11:
                return ((Hat)daw.getSamples().get(rowIndex)).isClosed();
            case 12:
                return daw.getSamples().get(rowIndex).getName();
            default:
                return "";
        }
    }

    public void addSamples(Sample sample) {
        this.daw.CreateSample(sample);
        this.fireTableDataChanged();
    }
}

