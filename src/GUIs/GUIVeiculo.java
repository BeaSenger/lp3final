package GUIs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import java.io.File;
import javax.swing.text.JTextComponent;
import Auxiliar.*;
import DAOs.*;
import Entidades.*;

public class GUIVeiculo extends JFrame {

    public static void main(String[] args) {
        new GUIVeiculo();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("Codigo: ");
    private JLabel lbCodigoVeiculo = new JLabel("Codigo");
    private JTextField fdCodigoVeiculo = new JTextField(15);

    private JLabel lbNomeVeiculo = new JLabel("Nome");
    private JTextField fdNomeVeiculo = new JTextField(10);

    private JLabel lbTipoVeiculoIdTipoVeiculo = new JLabel("ID Veiculo");
    private List<String> stringtipoVeiculoIdTipoVeiculo = new ArrayList<>();
    private JComboBox comboTipoVeiculoIdTipoVeiculo = new JComboBox();

    private JPanel painelNortes = new JPanel(new GridLayout(2, 1));
    private JPanel painelNorteCima = new JPanel();
    private JPanel painelNorteBaixo = new JPanel();
    private JPanel painelCentralFora = new JPanel(new BorderLayout());
    private JPanel painelCentral = new JPanel();
    private JPanel painelSul = new JPanel();
    private JLabel labelBranco = new JLabel();

    JButton btInserir = new JButton(new ImageIcon(getClass().getResource("/icones/add.png")));
    JButton btSalvar = new JButton(new ImageIcon(getClass().getResource("/icones/confirmar.png")));
    JButton btRemover = new JButton(new ImageIcon(getClass().getResource("/icones/deletar.png")));
    JButton btAtualizar = new JButton(new ImageIcon(getClass().getResource("/icones/att.png")));
    JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/icones/search.png")));
    JButton btCancelar = new JButton(new ImageIcon(getClass().getResource("/icones/cancelar.png")));
    JButton btListar = new JButton(new ImageIcon(getClass().getResource("/icones/listar.png")));

    DAOVeiculo controle = new DAOVeiculo();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JTextComponent editor = (JTextComponent) comboTipoVeiculoIdTipoVeiculo.getEditor().getEditorComponent();

    Veiculo veiculo = new Veiculo();
    DAOTipoVeiculo daoTipoVeiculo = new DAOTipoVeiculo();

    public GUIVeiculo() {
        setSize(725, 340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Veiculos");

        painelCentral.setLayout(new GridLayout(3, 2));
        painelCentral.add(lbNomeVeiculo);
        painelCentral.add(fdNomeVeiculo);

        fdNomeVeiculo.setEnabled(false);

        List<String> combo = new ArrayList<>();
        combo = new ManipulaArquivo().abrirArquivo("Veiculo");
        for (int x = 0; x < combo.size(); x++) {
            stringtipoVeiculoIdTipoVeiculo.add(combo.get(x).split(";")[0]);
        }
        comboTipoVeiculoIdTipoVeiculo = new JComboBox(stringtipoVeiculoIdTipoVeiculo.toArray());
        painelCentral.add(lbTipoVeiculoIdTipoVeiculo);
        painelCentral.add(comboTipoVeiculoIdTipoVeiculo);
        comboTipoVeiculoIdTipoVeiculo.setEnabled(false);
        editor.setDocument(new SearchableComboBox(comboTipoVeiculoIdTipoVeiculo));

        cp.setBackground(Color.white);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
        painelNorteCima.add(fdCodigoVeiculo);
        painelNorteBaixo.add(btBuscar);
        painelNorteBaixo.add(btInserir);
        painelNorteBaixo.add(btAtualizar);
        painelNorteBaixo.add(btRemover);
        painelNorteBaixo.add(btSalvar);
        painelNorteBaixo.add(btCancelar);
        painelNorteBaixo.add(btListar);
        painelNorteCima.setBackground(Color.white);
        painelNorteBaixo.setBackground(Color.white);
        painelCentralFora.setBackground(Color.white);
        painelCentral.setBackground(Color.white);
        painelSul.setBackground(Color.white);
        btInserir.setBackground(Color.WHITE);
        btSalvar.setBackground(Color.WHITE);
        btRemover.setBackground(Color.WHITE);
        btAtualizar.setBackground(Color.WHITE);
        btBuscar.setBackground(Color.WHITE);
        btCancelar.setBackground(Color.WHITE);
        btListar.setBackground(Color.WHITE);

        labelTitulo.setFont(new Font("Courier New", Font.BOLD, 20));
        fdCodigoVeiculo.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbCodigoVeiculo.setFont(new Font("Courier New", Font.BOLD, 17));
        lbNomeVeiculo.setFont(new Font("Courier New", Font.BOLD, 17));
        lbTipoVeiculoIdTipoVeiculo.setFont(new Font("Courier New", Font.BOLD, 17));
        fdCodigoVeiculo.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdNomeVeiculo.setFont(new Font("Courier New", Font.PLAIN, 17));
        comboTipoVeiculoIdTipoVeiculo.setFont(new Font("Courier New", Font.PLAIN, 17));
        labelAviso.setFont(new Font("Courier New", Font.BOLD, 20));
        btInserir.setVisible(false);
        btAtualizar.setVisible(false);
        btRemover.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        painelSul.add(labelAviso);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    veiculo = new Veiculo();
                    int codigoVeiculo = Integer.valueOf(fdCodigoVeiculo.getText());
                    veiculo.setCodigoVeiculo(Integer.valueOf(fdCodigoVeiculo.getText()));
                    veiculo = controle.obter(veiculo.getCodigoVeiculo());
                    labelAviso.setBackground(Color.green);
                    if (veiculo != null) {
                        fdCodigoVeiculo.setText(veiculo.getCodigoVeiculo() + "");
                        fdNomeVeiculo.setText(veiculo.getNomeVeiculo() + "");
                        comboTipoVeiculoIdTipoVeiculo.setSelectedItem(veiculo.getTipoVeiculoIdTipoVeiculo().toString());
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdNomeVeiculo.setEnabled(false);
                        fdNomeVeiculo.setText(null);
                        comboTipoVeiculoIdTipoVeiculo.setEnabled(false);
                        comboTipoVeiculoIdTipoVeiculo.setSelectedIndex(0);
                        labelAviso.setText("Não encontrado!");
                        labelAviso.setBackground(Color.red);
                        btInserir.setVisible(true);
                        btAtualizar.setVisible(false);
                        btRemover.setVisible(false);
                        btListar.setVisible(false);
                    }
                } catch (Exception erro) {
                    labelAviso.setText("Preencha os campos corretamente!");
                    labelAviso.setBackground(Color.red);
                }
            }
        }
        );

        btInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = true;
                fdCodigoVeiculo.setEnabled(false);
                fdNomeVeiculo.setEnabled(true);
                comboTipoVeiculoIdTipoVeiculo.setEnabled(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btInserir.setVisible(false);
                btListar.setVisible(false);
            }
        }
        );

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao) { //btInserir
                    try {
                        veiculo = new Veiculo();
                        veiculo.setCodigoVeiculo(Integer.valueOf(fdCodigoVeiculo.getText()));
                        veiculo.setNomeVeiculo(fdNomeVeiculo.getText());

                        String x = (String) comboTipoVeiculoIdTipoVeiculo.getSelectedItem();
                        String[] aux = x.split(";");
                        TipoVeiculo tipoVeiculo = daoTipoVeiculo.obter(Integer.valueOf(aux[0]));
                        veiculo.setTipoVeiculoIdTipoVeiculo(tipoVeiculo);

                        controle.inserir(veiculo);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdCodigoVeiculo.setEnabled(true);
                        fdCodigoVeiculo.requestFocus();
                        fdNomeVeiculo.setEnabled(false);
                        comboTipoVeiculoIdTipoVeiculo.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        veiculo = new Veiculo();
                        veiculo.setCodigoVeiculo(Integer.valueOf(fdCodigoVeiculo.getText()));
                        veiculo.setNomeVeiculo(fdNomeVeiculo.getText());
                        
                        String x = (String) comboTipoVeiculoIdTipoVeiculo.getSelectedItem();
                        String[] aux = x.split(";");
                        TipoVeiculo tipoVeiculo = daoTipoVeiculo.obter(Integer.valueOf(aux[0]));
                        veiculo.setTipoVeiculoIdTipoVeiculo(tipoVeiculo);
                        
                        controle.atualizar(veiculo);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdCodigoVeiculo.setEnabled(true);
                        fdCodigoVeiculo.requestFocus();
                        fdNomeVeiculo.setEnabled(false);
                        comboTipoVeiculoIdTipoVeiculo.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                }
            }
        }
        );

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelAviso.setText("Cancelado!");
                fdCodigoVeiculo.setEnabled(false);
                fdCodigoVeiculo.setText("");
                fdNomeVeiculo.setEnabled(false);
                fdNomeVeiculo.setText("");
                comboTipoVeiculoIdTipoVeiculo.setEnabled(false);
                comboTipoVeiculoIdTipoVeiculo.setSelectedIndex(0);
                fdCodigoVeiculo.setEnabled(true);
                fdCodigoVeiculo.requestFocus();
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
            }
        }
        );

        btAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = false;
                fdNomeVeiculo.setEnabled(true);
                comboTipoVeiculoIdTipoVeiculo.setEnabled(true);
                fdNomeVeiculo.requestFocus();
                fdCodigoVeiculo.setEnabled(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btRemover.setVisible(false);
                btAtualizar.setVisible(false);
                btListar.setVisible(false);
            }
        }
        );

        btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btRemover.setVisible(false);
                btListar.setVisible(false);
                btAtualizar.setVisible(false);
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + veiculo.getCodigoVeiculo() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(veiculo);
                    labelAviso.setText("Removido!");
                    fdCodigoVeiculo.setText("");
                    fdNomeVeiculo.setText("");
                    fdNomeVeiculo.setEnabled(false);
                    comboTipoVeiculoIdTipoVeiculo.setEnabled(false);
                    comboTipoVeiculoIdTipoVeiculo.setSelectedIndex(0);
                    btListar.setVisible(true);
                } else {
                    labelAviso.setText("Remoção cancelada!");
                    btAtualizar.setVisible(true);
                    btRemover.setVisible(true);
                }
            }
        }
        );

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VeiculoGUIListagem(controle.listInOrderNomeStrings("tanto faz"), cp);
            }
        }
        );

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        }
        );
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
