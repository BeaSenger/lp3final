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

public class GUITipoVeiculo extends JFrame {
    public static void main(String[] args) {
        new GUITipoVeiculo();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("ID Tipo Veiculo: ");
    private JLabel lbIdTipoVeiculo = new JLabel("ID Tipo Veiculo");
    private JTextField fdIdTipoVeiculo = new JTextField(15);

    private JLabel lbNomeTipoVeiculo = new JLabel("Nome Tipo Veiculo");
    private JTextField fdNomeTipoVeiculo = new JTextField(45);


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

    DAOTipoVeiculo controle = new DAOTipoVeiculo();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    TipoVeiculo tipoveiculo = new TipoVeiculo();

    public GUITipoVeiculo(){
        setSize(725, 340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de TipoVeiculos");

        painelCentral.setLayout(new GridLayout(2, 2));
        painelCentral.add(lbNomeTipoVeiculo);
        painelCentral.add(fdNomeTipoVeiculo);

        fdNomeTipoVeiculo.setEnabled(false);

        List<String> combo = new ArrayList<>();


cp.setBackground(Color.white);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
        painelNorteCima.add(fdIdTipoVeiculo);
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
        fdIdTipoVeiculo.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbIdTipoVeiculo.setFont(new Font("Courier New", Font.BOLD, 17));
        lbNomeTipoVeiculo.setFont(new Font("Courier New", Font.BOLD, 17));
        fdIdTipoVeiculo.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdNomeTipoVeiculo.setFont(new Font("Courier New", Font.PLAIN, 17));
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
                try{
                    tipoveiculo = new TipoVeiculo();
                    int idTipoVeiculo = Integer.valueOf(fdIdTipoVeiculo.getText());
                    tipoveiculo.setIdTipoVeiculo(Integer.valueOf(fdIdTipoVeiculo.getText()));
                    tipoveiculo = controle.obter(tipoveiculo.getIdTipoVeiculo());
                    labelAviso.setBackground(Color.green);
                    if (tipoveiculo != null) {
                        fdIdTipoVeiculo.setText(tipoveiculo.getIdTipoVeiculo() + "");
                        fdNomeTipoVeiculo.setText(tipoveiculo.getNomeTipoVeiculo() + "");
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdNomeTipoVeiculo.setEnabled(false);
                        fdNomeTipoVeiculo.setText(null);
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
                fdIdTipoVeiculo.setEnabled(false);
                fdNomeTipoVeiculo.setEnabled(true);
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
                if(acao){ //btInserir
                    try {
                    tipoveiculo = new TipoVeiculo();
                        tipoveiculo.setIdTipoVeiculo(Integer.valueOf(fdIdTipoVeiculo.getText()));
                        tipoveiculo.setNomeTipoVeiculo(fdNomeTipoVeiculo.getText());
                        controle.inserir(tipoveiculo);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdIdTipoVeiculo.setEnabled(true);
                        fdIdTipoVeiculo.requestFocus();
                        fdNomeTipoVeiculo.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        tipoveiculo = new TipoVeiculo();
                        tipoveiculo.setIdTipoVeiculo(Integer.valueOf(fdIdTipoVeiculo.getText()));
                        tipoveiculo.setNomeTipoVeiculo(fdNomeTipoVeiculo.getText());
                        controle.atualizar(tipoveiculo);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdIdTipoVeiculo.setEnabled(true);
                        fdIdTipoVeiculo.requestFocus();
                        fdNomeTipoVeiculo.setEnabled(false);
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
                fdIdTipoVeiculo.setEnabled(false);
                fdIdTipoVeiculo.setText("");
                fdNomeTipoVeiculo.setEnabled(false);
                fdNomeTipoVeiculo.setText("");
                fdIdTipoVeiculo.setEnabled(true);
                fdIdTipoVeiculo.requestFocus();
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
                fdNomeTipoVeiculo.setEnabled(true);
                fdNomeTipoVeiculo.requestFocus();
                fdIdTipoVeiculo.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + tipoveiculo.getIdTipoVeiculo() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(tipoveiculo);
                    labelAviso.setText("Removido!");
                    fdIdTipoVeiculo.setText("");
                    fdNomeTipoVeiculo.setText("");
                    fdNomeTipoVeiculo.setEnabled(false);
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
                new TipoVeiculoGUIListagem(controle.listInOrderNomeStrings("tanto faz"), cp);
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
