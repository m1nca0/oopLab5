import javax.swing.table.AbstractTableModel;

public class MyTable extends AbstractTableModel {
    private Daw daw;
    private DawInOut inOut;

    public MyTable(Daw daw, DawInOut inOut) {
        this.daw = daw;
        this.inOut = inOut;
    }

    @Override
    public int getRowCount() {
        return daw.getSamplesCount();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sample sample = daw.getSample(rowIndex);
        
        if (sample == null) return "";

        switch (columnIndex) {
            case 0: return rowIndex + 1;
            case 1: return sample.getType();
            case 2: return sample.getName();
            case 3: return sample.getLengthMs();
            case 4: return sample.getVolume();
            case 5: return sample.getLowFrequency();
            case 6: return sample.getHighFrequency();
            case 7: 
                return (sample instanceof Kick) ? ((Kick) sample).getBassLevel() : "-";
            case 8: 
                return (sample instanceof Snare) ? ((Snare) sample).getResonance() : "-";
            case 9: 
                return (sample instanceof Snare) ? ((Snare) sample).getPunch() : "-";
            case 10: 
                return (sample instanceof Hat) ? ((Hat) sample).getTailLength() : "-";
            case 11: 
                return (sample instanceof Hat) ? (((Hat) sample).isClosed() ? "Закрыт" : "Открыт") : "-";
            case 12: 
                return sample.getName();
            default: 
                return "";
        }
    }
    public void addSamples(Sample sample) {
        this.daw.CreateSample(sample);
        this.fireTableDataChanged();
    }
    public void deleteSamples(String name, String type) {
        this.daw.findSample(name,type,false);
        this.fireTableDataChanged();
    }
    public void openSamples(String way){
        this.daw = inOut.loadProject(way);
        this.fireTableDataChanged();
    }
}

