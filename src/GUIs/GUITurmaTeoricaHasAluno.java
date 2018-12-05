package GUIs;

import java.awt.event.*;
import javax.swing.*;
import DAOs.*;
import Auxiliar.ManipulaArquivo;
import DAOs.DAOAluno;
import DAOs.DAOTurmaTeorica;
import Entidades.Aluno;
import Entidades.TurmaTeorica;
import Entidades.TurmaTeoricaHasAluno;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GUITurmaTeoricaHasAluno extends JFrame {

    public static void main(String[] args) {
        new GUITurmaTeoricaHasAluno();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelCpfAluno = new JLabel("Cpf Aluno: ");
    private JTextField fdCpfAluno = new JTextField(15);
    private JLabel labelCodigoTurma = new JLabel("Código Turma: ");
    private JTextField fdCodigoTurma = new JTextField(15);

    private JPanel painelNortes = new JPanel(new GridLayout(2, 1));
    private JPanel painelNorteCima = new JPanel();
    private JPanel painelNorteBaixo = new JPanel();
    private JPanel painelCentralFora = new JPanel(new BorderLayout());
    private JPanel painelCentral = new JPanel();
    private JPanel painelSul = new JPanel();
    private JLabel labelBranco = new JLabel();

//    JButton btInserir = new JButton(new ImageIcon(getClass().getResource("/icones/add.png")));
//    JButton btSalvar = new JButton(new ImageIcon(getClass().getResource("/icones/confirmar.png")));
//    JButton btRemover = new JButton(new ImageIcon(getClass().getResource("/icones/deletar.png")));
//    JButton btAtualizar = new JButton(new ImageIcon(getClass().getResource("/icones/att.png")));
    JButton btInserir = new JButton("Inserir");
//    JButton btCancelar = new JButton(new ImageIcon(getClass().getResource("/icones/cancelar.png")));
//    JButton btListar = new JButton(new ImageIcon(getClass().getResource("/icones/listar.png")));

//    DAOTurmaTeoricaHasAlunoPK daoControlePK = new DAOTurmaTeoricaHasAlunoPK();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    DAOAluno daoAluno = new DAOAluno();
    DAOTurmaTeorica daoTurmaTeorica = new DAOTurmaTeorica();
//    TurmaTeoricaHasAluno tthaPK = new TurmaTeoricaHasAluno();
//    DAOTurmaTeoricaHasAlunoPK daoTurmaTeoricaHasAlunoPK = new DAOTurmaTeoricaHasAlunoPK();

    public GUITurmaTeoricaHasAluno() {
        setSize(725, 420);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Aluno Aula");

        painelCentral.setLayout(new GridLayout(3, 2));

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
        painelNorteCima.add(labelCodigoTurma);
        painelNorteCima.add(fdCodigoTurma);
        fdCodigoTurma.setEditable(false);
        fdCpfAluno.setEditable(false);
        painelNorteBaixo.add(btInserir);
//        painelNorteBaixo.add(btAtualizar);
//        painelNorteBaixo.add(btRemover);
//        painelNorteBaixo.add(btSalvar);
//        painelNorteBaixo.add(btCancelar);
//        painelNorteBaixo.add(btListar);
        painelNorteCima.setBackground(Color.white);
        painelNorteBaixo.setBackground(Color.white);
        painelCentralFora.setBackground(Color.white);
        painelCentral.setBackground(Color.white);
        painelSul.setBackground(Color.white);
        btInserir.setBackground(Color.WHITE);
//        btSalvar.setBackground(Color.WHITE);
//        btRemover.setBackground(Color.WHITE);
//        btAtualizar.setBackground(Color.WHITE);
        btInserir.setBackground(Color.WHITE);
//        btCancelar.setBackground(Color.WHITE);
//        btListar.setBackground(Color.WHITE);

        labelCpfAluno.setFont(new Font("Courier New", Font.BOLD, 20));
        fdCpfAluno.setFont(new Font("Courier New", Font.PLAIN, 20));
        labelCodigoTurma.setFont(new Font("Courier New", Font.BOLD, 20));
        fdCodigoTurma.setFont(new Font("Courier New", Font.PLAIN, 20));
        fdCpfAluno.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdCodigoTurma.setFont(new Font("Courier New", Font.PLAIN, 17));
        labelAviso.setFont(new Font("Courier New", Font.BOLD, 20));
        btInserir.setVisible(true);
//        btAtualizar.setVisible(false);
//        btRemover.setVisible(false);
//        btSalvar.setVisible(false);
//        btCancelar.setVisible(false);

        painelSul.add(labelAviso);

//        btInserir.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    try {
//                        tthaPK = new TurmaTeoricaHasAluno();
////                        String cpf = fdCpfAluno.getText();
//                        
//                        tthaPK.setAlunoCpfAluno(fdCpfAluno.getText());
//                        tthaPK.setTurmaTeoricaCodigoTurma(Integer.valueOf(fdCodigoTurma.getText()));
//                        TurmaTeoricaHasAlunoPK obter = daoControlePK.obter(tthaPK);
//                    } catch (Exception eee) {
//                        System.out.println("erro nos dados");
//                    }
//                    labelAviso.setBackground(Color.green);
//                    Object obter = daoControlePK.obter(tthaPK);
//                    if (obter != null) {
//                        tthaPK = new TurmaTeoricaHasAlunoPK();
//                        fdCpfAluno.setText(tthaPK.getAlunoCpfAluno() + "");
//                        //spinnerdataInicio.setValue(ttha.getDataAula());
//                        fdCodigoTurma.setText(tthaPK.getTurmaTeoricaCodigoTurma() + "");
//                        System.out.println("1111111111111111111111111111111111");
////                        btAtualizar.setVisible(true);
//                        btRemover.setVisible(true);
//                        btListar.setVisible(false);
//                        btInserir.setVisible(false);
//                        labelAviso.setText("Encontrado na lista!");
//                        labelAviso.setBackground(Color.green);
//                    } else {
//                        System.out.println("222222222222222222222222222222");
//                        fdCpfAluno.setEditable(false);
//                        fdCpfAluno.setEnabled(false);
//                        fdCodigoTurma.setEnabled(false);
//                        fdCodigoTurma.setEditable(false);
//                        labelAviso.setText("Não encontrado!");
//                        labelAviso.setBackground(Color.red);
//                        btInserir.setVisible(true);
////                        btAtualizar.setVisible(false);
//                        btRemover.setVisible(false);
//                        btListar.setVisible(false);
//                    }
//                } catch (Exception erro) {
//                    labelAviso.setText("Preencha os campos corretamente!");
//                    System.out.println(erro.getMessage());
//                    labelAviso.setBackground(Color.red);
//                }
//            }
//        }
//        );

//        btInserir.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                acao = true;
//                fdCpfAluno.requestFocus();
//                fdCpfAluno.setEditable(true);
//                fdCodigoTurma.setEditable(true);
//                btSalvar.setVisible(true);
//                btCancelar.setVisible(true);
//                btInserir.setVisible(false);
//                btInserir.setVisible(false);
//                btListar.setVisible(false);
//            }
//        }
//        );

//        btSalvar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (acao) { //btInserir
//                    try {
//                        tthaPK = new TurmaTeoricaHasAlunoPK();
//                        tthaPK.setAlunoCpfAluno(fdCpfAluno.getText());
//                        tthaPK.setTurmaTeoricaCodigoTurma(Integer.valueOf(fdCodigoTurma.getText()));
//                        daoControlePK.inserir(tthaPK);
//
////                        Date dt = (Date) spinnerdataInicio.getValue();
////                        ttha.setDataAula(dt);
////                        if (cbPresenca.isSelected()) {
////                            ttha.setCompareceu(true);
////                        } else {
////                            ttha.setCompareceu(false);
////                        }
//                        daoControlePK.inserir(tthaPK);
//
//                        labelAviso.setText("Registro inserido com sucesso!");
//                        fdCpfAluno.setEnabled(true);
//                        fdCpfAluno.requestFocus();
//                        fdCpfAluno.setEnabled(false);
//                        fdCodigoTurma.setEnabled(false);
//                        btSalvar.setVisible(false);
//                        btCancelar.setVisible(false);
//                        btInserir.setVisible(true);
//                        btListar.setVisible(true);
//                    } catch (Exception erro) {
//                        labelAviso.setText("Erro nos dados!");
//                        System.out.println(erro.getMessage());
//                        System.out.println(erro.getCause());
//                    }
//                }
//            }
//        }
//        );


        btInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String turmatf = fdCodigoTurma.getText();
                String[] turma = turmatf.split(";");
                int codTurma = Integer.parseInt(turma[0]);
                
                String alunotf = fdCpfAluno.getText();
                String[] aluno = alunotf.split(";");
                String cpfAlu = String.valueOf(aluno[0]);
                
                System.out.println(codTurma + "," + cpfAlu);
                TurmaTeoricaHasAluno turmaTeoricaHasAluno = new TurmaTeoricaHasAluno(codTurma, cpfAlu);

                int retorno = turmaTeoricaHasAluno.encontradoNaLista();
                
                if (retorno == 1) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                            "Já existe este registro! \n"
                            + "Deseja excluir o <" + aluno[1] + "> no professor <" + turma[1] + "> no banco de dados?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {

                        Aluno x = daoAluno.obter(aluno[0]);
                        String xy = String.valueOf(x);
                        List<TurmaTeorica> tt = x.getTurmaTeoricaList();
                        tt.remove(daoTurmaTeorica.obter(Integer.valueOf(turma[0])));
                        daoAluno.atualizar(x);
                        fdCpfAluno.setText("");
                    }
                } else {
                    fdCpfAluno.setText("");

                }
                
            }
        }
        );


//        btCancelar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                labelAviso.setText("Cancelado!");
//                fdCpfAluno.setEnabled(false);
//                fdCpfAluno.setEditable(false);
//                fdCodigoTurma.setEditable(false);
//                fdCpfAluno.setText("");
//                fdCpfAluno.setEnabled(false);
//                fdCpfAluno.setText("");
//                fdCodigoTurma.setEnabled(false);
//                fdCodigoTurma.setText("");
//                fdCpfAluno.setEnabled(true);
//
//                btSalvar.setVisible(false);
//                btCancelar.setVisible(false);
//                btInserir.setVisible(true);
//                btListar.setVisible(true);
//
//                fdCpfAluno.requestFocus();
//
//            }
//        }
//        );
//
//        btAtualizar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                acao = false;
//                fdAluno.setEnabled(true);
//                spinnerdataInicio.setEnabled(true);
//                fdCodigoTurma.setEnabled(true);
//                fdAluno.requestFocus();
//                fdCpfAluno.setEnabled(false);
//                btSalvar.setVisible(true);
//                btCancelar.setVisible(true);
//                btInserir.setVisible(false);
//                btRemover.setVisible(false);
//                btAtualizar.setVisible(false);
//                btListar.setVisible(false);
//            }
//        }
//        );
//
//        btRemover.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                btRemover.setVisible(false);
////                btAtualizar.setVisible(false);
//                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + tthaPK.getAlunoCpfAluno() + tthaPK.getTurmaTeoricaCodigoTurma() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
//                    daoControlePK.remover(tthaPK);
//                    labelAviso.setText("Removido!");
//                    fdCpfAluno.setText("");
//                    fdCpfAluno.setText("");
//                    fdCpfAluno.setEnabled(false);
//                    fdCodigoTurma.setText("");
//                    fdCodigoTurma.setEnabled(false);
//                } else {
//                    labelAviso.setText("Remoção cancelada!");
////                    btAtualizar.setVisible(true);
//                    btRemover.setVisible(true);
//                }
//            }
//        }
//        );

//        btListar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new AlunoGUIListagem(daoControlePK.listInOrderNomeStrings("tanto faz"), cp);
//
//            }
//
//        }
//        );

        fdCpfAluno.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    List<String> listaAuxiliar = daoAluno.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split(";");
                            fdCpfAluno.setText(aux[0]);
                            fdCodigoTurma.requestFocus();
//                        btInserir.doClick();//aciona o botao buscar
                        } else {
                            fdCpfAluno.requestFocus();
                            fdCpfAluno.selectAll();
                        }
                    }
                }
            }
        });

        fdCodigoTurma.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    List<String> listaAuxiliar = daoTurmaTeorica.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split(";");
                            fdCodigoTurma.setText(aux[0]);
//                        btInserir.doClick();//aciona o botao buscar
                        } else {
                            fdCodigoTurma.requestFocus();
                            fdCodigoTurma.selectAll();
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
