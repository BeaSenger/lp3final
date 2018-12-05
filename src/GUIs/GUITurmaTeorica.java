package GUIs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import Auxiliar.*;
import DAOs.*;
import Entidades.*;

public class GUITurmaTeorica extends JFrame {
    public static void main(String[] args) {
        new GUITurmaTeorica();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("Codigo Turma: ");
    private JLabel lbCodigoTurma = new JLabel("Codigo Turma");
    private JTextField fdCodigoTurma = new JTextField(15);

    private JLabel lbPeriodoTurma = new JLabel("Período Turma");
    private JTextField fdPeriodoTurma = new JTextField(45);

    private JSpinner spinnerdataInicio = new JSpinner(new SpinnerDateModel());
    private final JSpinner.DateEditor spinnerEditordataInicio = new JSpinner.DateEditor(spinnerdataInicio, "dd/MM/yyyy");
    private JLabel lbDataInicio = new JLabel("Data Inicio");
    private JLabel lbQuantidadeHoras = new JLabel("Qtde Horas");
    private JTextField fdQuantidadeHoras = new JTextField(15);

    private JLabel lbProfessorCpfProfessor = new JLabel("Cpf Professor");
    private List<String> stringprofessorCpfProfessor = new ArrayList<>();
    private JComboBox comboProfessorCpfProfessor = new JComboBox();

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

    DAOTurmaTeorica controle = new DAOTurmaTeorica();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JTextComponent editor = (JTextComponent) comboProfessorCpfProfessor.getEditor().getEditorComponent();

    TurmaTeorica turmateorica = new TurmaTeorica();
    DAOProfessor daoProfessor = new DAOProfessor();

    public GUITurmaTeorica(){
        setSize(725, 420);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de TurmaTeoricas");

        painelCentral.setLayout(new GridLayout(5, 2));
        painelCentral.add(lbPeriodoTurma);
        painelCentral.add(fdPeriodoTurma);
        painelCentral.add(lbQuantidadeHoras);
        painelCentral.add(fdQuantidadeHoras);

        fdPeriodoTurma.setEnabled(false);
        fdQuantidadeHoras.setEnabled(false);

        painelCentral.add(lbDataInicio);
        painelCentral.add(spinnerdataInicio);
        spinnerdataInicio.setEditor(spinnerEditordataInicio);
        spinnerdataInicio.setEnabled(false);
        List<String> combo = new ArrayList<>();
        combo = new ManipulaArquivo().abrirArquivo("TurmaTeorica.txt");
        for(int x = 0; x < combo.size(); x++) {
            stringprofessorCpfProfessor.add(combo.get(x).split(";")[0]);
        }
        comboProfessorCpfProfessor = new JComboBox(stringprofessorCpfProfessor.toArray());
        painelCentral.add(lbProfessorCpfProfessor);
        painelCentral.add(comboProfessorCpfProfessor);
        comboProfessorCpfProfessor.setEnabled(false);
        editor.setDocument(new SearchableComboBox(comboProfessorCpfProfessor));


cp.setBackground(Color.white);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
        painelNorteCima.add(fdCodigoTurma);
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
        fdCodigoTurma.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbCodigoTurma.setFont(new Font("Courier New", Font.BOLD, 17));
        lbPeriodoTurma.setFont(new Font("Courier New", Font.BOLD, 17));
        lbDataInicio.setFont(new Font("Courier New", Font.BOLD, 17));
        lbQuantidadeHoras.setFont(new Font("Courier New", Font.BOLD, 17));
        lbProfessorCpfProfessor.setFont(new Font("Courier New", Font.BOLD, 17));
        fdCodigoTurma.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdPeriodoTurma.setFont(new Font("Courier New", Font.PLAIN, 17));
        spinnerdataInicio.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdQuantidadeHoras.setFont(new Font("Courier New", Font.PLAIN, 17));
        comboProfessorCpfProfessor.setFont(new Font("Courier New", Font.PLAIN, 17));
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
                    turmateorica = new TurmaTeorica();
                    int codigoTurma = Integer.valueOf(fdCodigoTurma.getText());
                    turmateorica.setCodigoTurma(Integer.valueOf(fdCodigoTurma.getText()));
                    turmateorica = controle.obter(turmateorica.getCodigoTurma());
                    labelAviso.setBackground(Color.green);
                    if (turmateorica != null) {
                        fdCodigoTurma.setText(turmateorica.getCodigoTurma() + "");
                        fdPeriodoTurma.setText(turmateorica.getPeriodoTurma() + "");
                        spinnerdataInicio.setValue(turmateorica.getDataInicio());
                        fdQuantidadeHoras.setText(turmateorica.getQuantidadeHoras() + "");
                        comboProfessorCpfProfessor.setSelectedItem(turmateorica.getProfessorCpfProfessor().toString());
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdPeriodoTurma.setEnabled(false);
                        fdPeriodoTurma.setText(null);
                        fdQuantidadeHoras.setEnabled(false);
                        fdQuantidadeHoras.setText(null);
                        spinnerdataInicio.setEnabled(false);
                        spinnerdataInicio.setValue(new Date());
                        comboProfessorCpfProfessor.setEnabled(false);
                        comboProfessorCpfProfessor.setSelectedIndex(0);
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
                fdCodigoTurma.setEnabled(false);
                fdPeriodoTurma.setEnabled(true);
                spinnerdataInicio.setEnabled(true);
                fdQuantidadeHoras.setEnabled(true);
                comboProfessorCpfProfessor.setEnabled(true);
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
                    turmateorica = new TurmaTeorica();
                        turmateorica.setCodigoTurma(Integer.valueOf(fdCodigoTurma.getText()));
                        turmateorica.setPeriodoTurma(fdPeriodoTurma.getText());
                        turmateorica.setDataInicio((Date) spinnerdataInicio.getValue());
                        turmateorica.setQuantidadeHoras(Integer.valueOf(fdQuantidadeHoras.getText()));
                        
                        String x = (String) comboProfessorCpfProfessor.getSelectedItem();
                        String[] aux = x.split(";");
                        Professor professor = daoProfessor.obter(Integer.valueOf(aux[0]));
                        turmateorica.setProfessorCpfProfessor(professor);
                        
                        
                        controle.inserir(turmateorica);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdCodigoTurma.setEnabled(true);
                        fdCodigoTurma.requestFocus();
                        fdPeriodoTurma.setEnabled(false);
                        spinnerdataInicio.setEnabled(false);
                        fdQuantidadeHoras.setEnabled(false);
                        comboProfessorCpfProfessor.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        turmateorica = new TurmaTeorica();
                        turmateorica.setCodigoTurma(Integer.valueOf(fdCodigoTurma.getText()));
                        turmateorica.setPeriodoTurma(fdPeriodoTurma.getText());
                        turmateorica.setDataInicio((Date) spinnerdataInicio.getValue());
                        turmateorica.setQuantidadeHoras(Integer.valueOf(fdQuantidadeHoras.getText()));
                        
                        String x = (String) comboProfessorCpfProfessor.getSelectedItem();
                        String[] aux = x.split(";");
                        Professor professor = daoProfessor.obter(Integer.valueOf(aux[0]));
                        turmateorica.setProfessorCpfProfessor(professor);
                        
                        controle.atualizar(turmateorica);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdCodigoTurma.setEnabled(true);
                        fdCodigoTurma.requestFocus();
                        fdPeriodoTurma.setEnabled(false);
                        spinnerdataInicio.setEnabled(false);
                        fdQuantidadeHoras.setEnabled(false);
                        comboProfessorCpfProfessor.setEnabled(false);
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
                fdCodigoTurma.setEnabled(false);
                fdCodigoTurma.setText("");
                fdPeriodoTurma.setEnabled(false);
                fdPeriodoTurma.setText("");
                spinnerdataInicio.setEnabled(false);
                spinnerdataInicio.setValue(new Date());
                fdQuantidadeHoras.setEnabled(false);
                fdQuantidadeHoras.setText("");
                comboProfessorCpfProfessor.setEnabled(false);
                comboProfessorCpfProfessor.setSelectedIndex(0);
                fdCodigoTurma.setEnabled(true);
                fdCodigoTurma.requestFocus();
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
                fdPeriodoTurma.setEnabled(true);
                spinnerdataInicio.setEnabled(true);
                fdQuantidadeHoras.setEnabled(true);
                comboProfessorCpfProfessor.setEnabled(true);
                fdPeriodoTurma.requestFocus();
                fdCodigoTurma.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + turmateorica.getCodigoTurma() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(turmateorica);
                    labelAviso.setText("Removido!");
                    fdCodigoTurma.setText("");
                    fdPeriodoTurma.setText("");
                    fdPeriodoTurma.setEnabled(false);
                    spinnerdataInicio.setEnabled(false);
                    spinnerdataInicio.setValue(new Date());
                    fdQuantidadeHoras.setText("");
                    fdQuantidadeHoras.setEnabled(false);
                comboProfessorCpfProfessor.setEnabled(false);
                comboProfessorCpfProfessor.setSelectedIndex(0);
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
                new TurmaTeoricaGUIListagem(controle.listInOrderNomeStrings("tanto faz"), cp);
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
