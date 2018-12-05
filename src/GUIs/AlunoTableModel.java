package GUIs;

import Entidades.Aluno;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

public class AlunoTableModel extends AbstractTableModel {

//  ------------------------------------------------------------------------------------------------------ 
    private final Class classes[] = new Class[]{String.class, String.class};
    private final String colunas[] = new String[]{"cpfAluno", "nomeAluno"};
    private List<Aluno> dados;

    public AlunoTableModel(List<Aluno> dados) {
        this.dados = dados;
    }

    public void setDados(List<Aluno> dados) {
        this.dados = dados;
    }

    public List<Aluno> getDados() {
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

        Aluno pedido = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pedido.getCpfAluno();
            case 1:
                return pedido.getNomeAluno();
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
        for (Aluno x : dados) {
            if (x.getCpfAluno().equals(chave)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String aaa[];
        Aluno pedido = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (!chaveExiste((String) aValue)) {
                    pedido.setCpfAluno((String) aValue);
                }
                break;
            case 1:
                pedido.setNomeAluno((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");

        }
        fireTableDataChanged();
    }

    public Aluno getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(Aluno pedido) {
        return dados.indexOf(pedido);
    }

    public void onAdd(Aluno pedido) {
        dados.add(pedido);
        fireTableRowsInserted(indexOf(pedido), indexOf(pedido));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}
