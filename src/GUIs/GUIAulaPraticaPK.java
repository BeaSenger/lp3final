package GUIs;

import java.awt.event.*;
import javax.swing.*;
import DAOs.*;
import Auxiliar.ManipulaArquivo;
import Auxiliar.SearchableComboBox;
import DAOs.DAOAluno;
import Entidades.AulaPratica;
import Entidades.AulaPraticaPK;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.text.JTextComponent;

public class GUIAulaPraticaPK extends JFrame {

    public static void main(String[] args) {
        new GUIAulaPraticaPK();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelCpfAluno = new JLabel("Cpf Aluno: ");
    private JTextField fdCpfAluno = new JTextField(15);

    private JLabel labelCpfProf = new JLabel("Cpf Prof: ");
    private JTextField fdCpfProf = new JTextField(15);

    private JLabel labelHoraInicio = new JLabel("Hora Início ");
    private JTextField fdHoraInicio = new JTextField(15);

    private JLabel labelHoraFim = new JLabel("Hora Fim ");
    private JTextField fdHoraFim = new JTextField(15);

    private JLabel labelObs = new JLabel("Observação: ");
    private JTextField fdObs = new JTextField(15);

    private JLabel labelComboCodVeiculo = new JLabel("Codigo Veiculo");
    JComboBox comboCodVeiculo = new JComboBox();
    private List<String> stringCodigoVeiculo = new ArrayList<>();

    private JSpinner spinnerDataAula = new JSpinner(new SpinnerDateModel());
    private final JSpinner.DateEditor spinnerEditorDataAula = new JSpinner.DateEditor(spinnerDataAula, "dd/MM/yyyy");
    private JLabel lbDataAula = new JLabel("Data Aula");

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
//    JButton btAtualizar = new JButton(new ImageIcon(getClass().getResource("/icones/att.png")));
    JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/icones/search.png")));
    JButton btCancelar = new JButton(new ImageIcon(getClass().getResource("/icones/cancelar.png")));
    JButton btListar = new JButton(new ImageIcon(getClass().getResource("/icones/listar.png")));

    DAOAulaPraticaPK daoControlePK = new DAOAulaPraticaPK();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JTextComponent editor = (JTextComponent) comboCodVeiculo.getEditor().getEditorComponent();

    DAOAluno daoAluno = new DAOAluno();
    DAOProfessor daoProfessor = new DAOProfessor();
    AulaPratica aulaPratica = new AulaPratica();
    AulaPraticaPK aulaPraticaPK = new AulaPraticaPK();

    public GUIAulaPraticaPK() {
        setSize(725, 420);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Aluno Aula");

        painelCentral.setLayout(new GridLayout(4, 2));

        List<String> combo = new ArrayList<>();
        combo = new ManipulaArquivo().abrirArquivo("CodigoVeiculo.txt");
        for (int x = 0; x < combo.size(); x++) {
            stringCodigoVeiculo.add(combo.get(x).split(";")[0]);
        }
        comboCodVeiculo = new JComboBox(stringCodigoVeiculo.toArray());
        painelCentral.add(labelComboCodVeiculo);
        painelCentral.add(comboCodVeiculo);
        comboCodVeiculo.setEnabled(false);
        editor.setDocument(new SearchableComboBox(comboCodVeiculo));

        painelCentral.add(labelHoraInicio);
        painelCentral.add(fdHoraInicio);
        painelCentral.add(labelHoraFim);
        painelCentral.add(fdHoraFim);
        painelCentral.add(labelComboCodVeiculo);
        painelCentral.add(comboCodVeiculo);

        cp.setBackground(Color.white);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelCpfAluno);
        painelNorteCima.add(fdCpfAluno);
        painelNorteCima.add(labelCpfProf);
        painelNorteCima.add(fdCpfProf);
        painelNorteCima.add(lbDataAula);
        painelNorteCima.add(spinnerDataAula);
        spinnerDataAula.setEditor(spinnerEditorDataAula);
        spinnerDataAula.setEnabled(true);
        fdCpfProf.setEditable(false);
        fdCpfAluno.setEditable(false);
        painelNorteBaixo.add(btBuscar);
        painelNorteBaixo.add(btInserir);
//        painelNorteBaixo.add(btAtualizar);
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
//        btAtualizar.setBackground(Color.WHITE);
        btBuscar.setBackground(Color.WHITE);
        btCancelar.setBackground(Color.WHITE);
        btListar.setBackground(Color.WHITE);

        labelCpfAluno.setFont(new Font("Courier New", Font.BOLD, 20));
        fdCpfAluno.setFont(new Font("Courier New", Font.PLAIN, 20));
        labelCpfProf.setFont(new Font("Courier New", Font.BOLD, 20));
        fdCpfProf.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbDataAula.setFont(new Font("Courier New", Font.BOLD, 17));
        fdCpfAluno.setFont(new Font("Courier New", Font.PLAIN, 17));
        spinnerDataAula.setFont(new Font("Courier New", Font.PLAIN, 17));

        labelHoraInicio.setFont(new Font("Courier New", Font.BOLD, 20));
        labelHoraFim.setFont(new Font("Courier New", Font.BOLD, 20));
        labelObs.setFont(new Font("Courier New", Font.BOLD, 20));
        labelComboCodVeiculo.setFont(new Font("Courier New", Font.BOLD, 20));

        labelAviso.setFont(new Font("Courier New", Font.BOLD, 20));
        btInserir.setVisible(false);
//        btAtualizar.setVisible(false);
        btRemover.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        painelSul.add(labelAviso);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    aulaPraticaPK.setAlunoCpfAluno(fdCpfAluno.getText());
                    aulaPraticaPK.setProfessorCpfProfessor(fdCpfProf.getText());
                    aulaPraticaPK.setDataAula((Date) spinnerDataAula.getValue());
//                    aulaPratica = daoControlePK.obter(aulaPraticaPK);
                    
                    if (aulaPratica != null) {
                        System.out.println("Passou aqui");
                        fdCpfAluno.setText(aulaPraticaPK.getAlunoCpfAluno() + "");
                        spinnerDataAula.setValue(aulaPraticaPK.getDataAula());
                        fdCpfProf.setText(aulaPraticaPK.getProfessorCpfProfessor() + "");
//                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btListar.setVisible(false);
                        btInserir.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        System.out.println(aulaPratica);
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdCpfAluno.setEditable(false);
                        fdCpfAluno.setEnabled(false);
                        fdCpfProf.setEnabled(false);
                        fdCpfProf.setEditable(false);
                        spinnerDataAula.setEnabled(false);
                        spinnerDataAula.setValue(new Date());
                        labelAviso.setText("Não encontrado!");
                        labelAviso.setBackground(Color.red);
                        btInserir.setVisible(true);
//                        btAtualizar.setVisible(false);
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
                fdCpfAluno.requestFocus();
                fdCpfAluno.setEditable(true);
                fdCpfProf.setEditable(true);
                spinnerDataAula.setEnabled(true);
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
                    System.out.println("entrou no if");
                    try {
                        AulaPraticaPK aulaPraticaPK1 = new AulaPraticaPK();
                        aulaPraticaPK.setAlunoCpfAluno(fdCpfAluno.getText());
                        aulaPraticaPK.setProfessorCpfProfessor(String.valueOf(fdCpfProf.getText()));
                        aulaPraticaPK.setDataAula((Date) spinnerDataAula.getValue());

                        aulaPratica.setHoraInicio((Time.valueOf(fdHoraInicio.getText())));
                        aulaPratica.setHoraFim(Time.valueOf(fdHoraFim.getText()));
                        aulaPratica.setObservacao(fdObs.getText());

                        System.out.println("PASSOU AQUI");
                        daoControlePK.inserir(aulaPraticaPK);

                        labelAviso.setText("Registro inserido com sucesso!");
                        fdCpfAluno.setEnabled(true);
                        fdCpfAluno.requestFocus();
                        fdCpfAluno.setEnabled(false);
                        spinnerDataAula.setEnabled(false);
                        fdCpfProf.setEnabled(false);
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
                fdCpfAluno.setEnabled(false);
                fdCpfAluno.setEditable(false);
                fdCpfProf.setEditable(false);
                fdCpfAluno.setText("");
                fdCpfAluno.setEnabled(false);
                fdCpfAluno.setText("");
                spinnerDataAula.setEnabled(false);
                spinnerDataAula.setValue(new Date());
                fdCpfProf.setEnabled(false);
                fdCpfProf.setText("");
                fdCpfAluno.setEnabled(true);
                fdCpfAluno.requestFocus();
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
            }
        }
        );
//
//        btAtualizar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                acao = false;
//                fdAluno.setEnabled(true);
//                spinnerDataAula.setEnabled(true);
//                fdCpfProf.setEnabled(true);
//                fdAluno.requestFocus();
//                fdCpfAluno.setEnabled(false);
//                btSalvar.setVisible(true);
//                btCancelar.setVisible(true);
//                btBuscar.setVisible(false);
//                btRemover.setVisible(false);
//                btAtualizar.setVisible(false);
//                btListar.setVisible(false);
//            }
//        }
//        );
//
        btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btRemover.setVisible(false);
//                btAtualizar.setVisible(false);
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + aulaPraticaPK.getAlunoCpfAluno() + aulaPraticaPK.getProfessorCpfProfessor()+ aulaPraticaPK.getDataAula() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    daoControlePK.remover(aulaPraticaPK);
                    labelAviso.setText("Removido!");
                    fdCpfAluno.setText("");
                    fdCpfAluno.setText("");
                    fdCpfAluno.setEnabled(false);
                    spinnerDataAula.setEnabled(false);
                    spinnerDataAula.setValue(new Date());
                    fdCpfProf.setText("");
                    fdCpfProf.setEnabled(false);
                } else {
                    labelAviso.setText("Remoção cancelada!");
//                    btAtualizar.setVisible(true);
                    btRemover.setVisible(true);
                }
            }
        }
        );

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AlunoGUIListagem(daoControlePK.listInOrderNomeStrings("tanto faz"), cp);

            }

        }
        );

        fdCpfAluno.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    List<String> listaAuxiliar = daoAluno.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split(";");
                            fdCpfAluno.setText(aux[0]);
                            fdCpfProf.requestFocus();
//                        btBuscar.doClick();//aciona o botao buscar
                        } else {
                            fdCpfAluno.requestFocus();
                            fdCpfAluno.selectAll();
                        }
                    }
                }
            }
        });

        fdCpfProf.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    List<String> listaAuxiliar = daoProfessor.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split(";");
                            fdCpfProf.setText(aux[0]);
//                        btBuscar.doClick();//aciona o botao buscar
                        } else {
                            fdCpfProf.requestFocus();
                            fdCpfProf.selectAll();
                        }
                    }
                }
            }
        });

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
