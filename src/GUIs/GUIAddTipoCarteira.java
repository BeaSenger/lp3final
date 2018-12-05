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

public class GUIAddTipoCarteira extends JFrame {
    public static void main(String[] args) {
        new GUIAddTipoCarteira();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("ID Tipo Carteira: ");
    private JLabel lbIdTipoCarteira = new JLabel("ID Tipo Carteira");
    private JTextField fdIdTipoCarteira = new JTextField(15);

    private JLabel lbNomeTipoCarteira = new JLabel("Nome Tipo Carteira");
    private JTextField fdNomeTipoCarteira = new JTextField(45);


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

    DAOTipoCarteira controle = new DAOTipoCarteira();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    TipoCarteira tipocarteira = new TipoCarteira();

    public GUIAddTipoCarteira(){
        setSize(725, 340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de TipoCarteiras");

        painelCentral.setLayout(new GridLayout(2, 2));
        painelCentral.add(lbNomeTipoCarteira);
        painelCentral.add(fdNomeTipoCarteira);

        fdNomeTipoCarteira.setEnabled(false);

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
        painelNorteCima.add(fdIdTipoCarteira);
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
        fdIdTipoCarteira.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbIdTipoCarteira.setFont(new Font("Courier New", Font.BOLD, 17));
        lbNomeTipoCarteira.setFont(new Font("Courier New", Font.BOLD, 17));
        fdIdTipoCarteira.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdNomeTipoCarteira.setFont(new Font("Courier New", Font.PLAIN, 17));
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
                    tipocarteira = new TipoCarteira();
                    int idTipoCarteira = Integer.valueOf(fdIdTipoCarteira.getText());
                    tipocarteira.setIdTipoCarteira(Integer.valueOf(fdIdTipoCarteira.getText()));
                    tipocarteira = controle.obter(tipocarteira.getIdTipoCarteira());
                    labelAviso.setBackground(Color.green);
                    if (tipocarteira != null) {
                        fdIdTipoCarteira.setText(tipocarteira.getIdTipoCarteira() + "");
                        fdNomeTipoCarteira.setText(tipocarteira.getNomeTipoCarteira() + "");
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdNomeTipoCarteira.setEnabled(false);
                        fdNomeTipoCarteira.setText(null);
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
                fdIdTipoCarteira.setEnabled(false);
                fdNomeTipoCarteira.setEnabled(true);
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
                    tipocarteira = new TipoCarteira();
                        tipocarteira.setIdTipoCarteira(Integer.valueOf(fdIdTipoCarteira.getText()));
                        tipocarteira.setNomeTipoCarteira(fdNomeTipoCarteira.getText());
                        controle.inserir(tipocarteira);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdIdTipoCarteira.setEnabled(true);
                        fdIdTipoCarteira.requestFocus();
                        fdNomeTipoCarteira.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        tipocarteira = new TipoCarteira();
                        tipocarteira.setIdTipoCarteira(Integer.valueOf(fdIdTipoCarteira.getText()));
                        tipocarteira.setNomeTipoCarteira(fdNomeTipoCarteira.getText());
                        controle.atualizar(tipocarteira);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdIdTipoCarteira.setEnabled(true);
                        fdIdTipoCarteira.requestFocus();
                        fdNomeTipoCarteira.setEnabled(false);
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
                fdIdTipoCarteira.setEnabled(false);
                fdIdTipoCarteira.setText("");
                fdNomeTipoCarteira.setEnabled(false);
                fdNomeTipoCarteira.setText("");
                fdIdTipoCarteira.setEnabled(true);
                fdIdTipoCarteira.requestFocus();
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
                fdNomeTipoCarteira.setEnabled(true);
                fdNomeTipoCarteira.requestFocus();
                fdIdTipoCarteira.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + tipocarteira.getIdTipoCarteira() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(tipocarteira);
                    labelAviso.setText("Removido!");
                    fdIdTipoCarteira.setText("");
                    fdNomeTipoCarteira.setText("");
                    fdNomeTipoCarteira.setEnabled(false);
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
                new TipoCarteiraGUIListagem(controle.listInOrderNomeStrings("tanto faz"), cp);
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
