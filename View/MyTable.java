package View;
import javax.swing.table.AbstractTableModel;

import Model.Daw;
import Model.Hat;
import Model.Kick;
import Model.Sample;
import Model.Snare;

public class MyTable extends AbstractTableModel {
    private Daw daw;

    public MyTable(Daw daw) {
        this.daw = daw;
    }

    @Override
    public int getRowCount() {
        return daw.getSamplesCount();
    }

    @Override
    public int getColumnCount() {
        return 12;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class;
            case 1: return String.class; 
            case 2: return String.class; 
            case 3: return Integer.class;
            case 4: return Double.class; 
            case 5: return Integer.class;
            case 6: return Integer.class;
            case 7: return Double.class; 
            case 8: return Double.class; 
            case 9: return Double.class;
            case 10: return Double.class; 
            case 11: return String.class;
            default: return Object.class;
        }
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
                return (sample instanceof Kick) ? ((Kick) sample).getBassLevel() : null;
            case 8: 
                return (sample instanceof Snare) ? ((Snare) sample).getResonance() : null;
            case 9: 
                return (sample instanceof Snare) ? ((Snare) sample).getPunch() : null;
            case 10: 
                return (sample instanceof Hat) ? ((Hat) sample).getTailLength() : null;
            case 11: 
                return (sample instanceof Hat) ? (((Hat) sample).isClosed() ? "Закрыт" : "Открыт") : null;
            default: 
                return "";
        }
    }
    public void setDaw(Daw daw) {
        this.daw = daw;
    }
}

