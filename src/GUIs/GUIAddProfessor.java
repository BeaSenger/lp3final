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

public class GUIAddProfessor extends JFrame {
    public static void main(String[] args) {
        new GUIAddProfessor();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("CPF Professor: ");
    private JLabel lbCpfProfessor = new JLabel("CPF Professor");
    private JTextField fdCpfProfessor = new JTextField(15);

    private JLabel lbNomeProfessor = new JLabel("Nome Professor");
    private JTextField fdNomeProfessor = new JTextField(45);

    private JLabel lbHabilitacao = new JLabel("Habilitacao");
    private JTextField fdHabilitacao = new JTextField(45);


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

    DAOProfessor controle = new DAOProfessor();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Professor professor = new Professor();

    public GUIAddProfessor(){
        setSize(725, 340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Professors");

        painelCentral.setLayout(new GridLayout(3, 2));
        painelCentral.add(lbNomeProfessor);
        painelCentral.add(fdNomeProfessor);
        painelCentral.add(lbHabilitacao);
        painelCentral.add(fdHabilitacao);

        fdNomeProfessor.setEnabled(false);
        fdHabilitacao.setEnabled(false);

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
        painelNorteCima.add(fdCpfProfessor);
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
        fdCpfProfessor.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbCpfProfessor.setFont(new Font("Courier New", Font.BOLD, 17));
        lbNomeProfessor.setFont(new Font("Courier New", Font.BOLD, 17));
        lbHabilitacao.setFont(new Font("Courier New", Font.BOLD, 17));
        fdCpfProfessor.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdNomeProfessor.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdHabilitacao.setFont(new Font("Courier New", Font.PLAIN, 17));
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
                    professor = new Professor();
                    String cpfProfessor = fdCpfProfessor.getText();
                    professor.setCpfProfessor(fdCpfProfessor.getText());
                    professor = controle.obter(professor.getCpfProfessor());
                    labelAviso.setBackground(Color.green);
                    if (professor != null) {
                        fdCpfProfessor.setText(professor.getCpfProfessor() + "");
                        fdNomeProfessor.setText(professor.getNomeProfessor() + "");
                        fdHabilitacao.setText(professor.getHabilitacao() + "");
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdNomeProfessor.setEnabled(false);
                        fdNomeProfessor.setText(null);
                        fdHabilitacao.setEnabled(false);
                        fdHabilitacao.setText(null);
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
                fdCpfProfessor.setEnabled(false);
                fdNomeProfessor.setEnabled(true);
                fdHabilitacao.setEnabled(true);
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
                    professor = new Professor();
                        professor.setCpfProfessor(fdCpfProfessor.getText());
                        professor.setNomeProfessor(fdNomeProfessor.getText());
                        professor.setHabilitacao(fdHabilitacao.getText());
                        controle.inserir(professor);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdCpfProfessor.setEnabled(true);
                        fdCpfProfessor.requestFocus();
                        fdNomeProfessor.setEnabled(false);
                        fdHabilitacao.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        professor = new Professor();
                        professor.setCpfProfessor(fdCpfProfessor.getText());
                        professor.setNomeProfessor(fdNomeProfessor.getText());
                        professor.setHabilitacao(fdHabilitacao.getText());
                        controle.atualizar(professor);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdCpfProfessor.setEnabled(true);
                        fdCpfProfessor.requestFocus();
                        fdNomeProfessor.setEnabled(false);
                        fdHabilitacao.setEnabled(false);
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
                fdCpfProfessor.setEnabled(false);
                fdCpfProfessor.setText("");
                fdNomeProfessor.setEnabled(false);
                fdNomeProfessor.setText("");
                fdHabilitacao.setEnabled(false);
                fdHabilitacao.setText("");
                fdCpfProfessor.setEnabled(true);
                fdCpfProfessor.requestFocus();
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
                fdNomeProfessor.setEnabled(true);
                fdHabilitacao.setEnabled(true);
                fdNomeProfessor.requestFocus();
                fdCpfProfessor.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + professor.getCpfProfessor() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(professor);
                    labelAviso.setText("Removido!");
                    fdCpfProfessor.setText("");
                    fdNomeProfessor.setText("");
                    fdNomeProfessor.setEnabled(false);
                    fdHabilitacao.setText("");
                    fdHabilitacao.setEnabled(false);
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
                new ProfessorGUIListagem(controle.listInOrderNomeStrings("tanto faz"), cp);
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
