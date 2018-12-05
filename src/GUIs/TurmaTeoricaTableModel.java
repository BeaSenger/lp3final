package GUIs;

import Entidades.TurmaTeorica;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import Entidades.Professor;
import DAOs.DAOProfessor;

public class TurmaTeoricaTableModel extends AbstractTableModel {

//  ------------------------------------------------------------------------------------------------------ 
    private final Class classes[] = new Class[]{Integer.class, Date.class, Professor.class};
    private final String colunas[] = new String[]{"idTurmaTeorica", "dataTurmaTeorica", "Professor"};
    private List<TurmaTeorica> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
    public TurmaTeoricaTableModel(List<TurmaTeorica> dados) {
        this.dados = dados;
    }

    public void setDados(List<TurmaTeorica> dados) {
        this.dados = dados;
    }

    public List<TurmaTeorica> getDados() {
        return this.dados;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        TurmaTeorica turmaTeorica = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return turmaTeorica.getCodigoTurma();
            case 1:
                return turmaTeorica.getPeriodoTurma();
            case 2:
                return turmaTeorica.getDataInicio();
            case 3:
                return turmaTeorica.getProfessorCpfProfessor().getNomeProfessor();
            case 4:
                return turmaTeorica.getQuantidadeHoras();
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        }
        return true;
    }

// Para impedir que exista duplicidade na chave primaria
    public boolean chaveExiste(String chave) {
        for (TurmaTeorica x : dados) {
            if (x.getCodigoTurma().equals(chave)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String aaa[];
        TurmaTeorica turmaTeorica = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (!chaveExiste((String) aValue)) {
                    turmaTeorica.setCodigoTurma((Integer) aValue);
                }
                break;
            case 1:
                if (!chaveExiste((String) aValue)) {
                    turmaTeorica.setPeriodoTurma((String) aValue);
                }
                break;
            case 2:
                turmaTeorica.setDataInicio((Date) aValue);
                break;
            case 3:
                aaa = String.valueOf(aValue).split("-");
                Professor vProfessor = new DAOProfessor().obter(Integer.valueOf(aaa[0].trim()));
                turmaTeorica.setProfessorCpfProfessor(vProfessor);
                break;
            case 4:
                turmaTeorica.setQuantidadeHoras((Integer) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");

        }
        fireTableDataChanged();
    }

    public TurmaTeorica getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(TurmaTeorica turmaTeorica) {
        return dados.indexOf(turmaTeorica);
    }

    public void onAdd(TurmaTeorica turmaTeorica) {
        dados.add(turmaTeorica);
        fireTableRowsInserted(indexOf(turmaTeorica), indexOf(turmaTeorica));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}
