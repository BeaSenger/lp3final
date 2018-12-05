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

public class GUIAddAluno extends JFrame {

    public static void main(String[] args) {
        new GUIAddAluno();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("CPF Aluno: ");
    private JLabel lbCpfAluno = new JLabel("CPF Aluno");
    private JTextField fdCpfAluno = new JTextField(15);

    private JLabel lbNomeAluno = new JLabel("Nome ");
    private JTextField fdNomeAluno = new JTextField(45);

    private JSpinner spinnerdataNascimento = new JSpinner(new SpinnerDateModel());
    private final JSpinner.DateEditor spinnerEditordataNascimento = new JSpinner.DateEditor(spinnerdataNascimento, "dd/MM/yyyy");
    private JLabel lbDataNascimento = new JLabel("Data Nascimento");
    private JLabel lbRgAluno = new JLabel("RG");
    private JTextField fdRgAluno = new JTextField(45);

    private JLabel lbRgEmissorAluno = new JLabel("Orgao Emissor");
    private JTextField fdRgEmissorAluno = new JTextField(45);

    private JLabel lbEnderecoAluno = new JLabel("Endereco");
    private JTextField fdEnderecoAluno = new JTextField(45);

    private JLabel lbCidadeAluno = new JLabel("Cidade");
    private JTextField fdCidadeAluno = new JTextField(45);

    private JLabel lbBairroAluno = new JLabel("Bairro");
    private JTextField fdBairroAluno = new JTextField(45);

    private JLabel lbCepTipoCarteira = new JLabel("CEP");
    private JTextField fdCepTipoCarteira = new JTextField(45);

    private JLabel lbTelefoneAluno = new JLabel("Telefone");
    private JTextField fdTelefoneAluno = new JTextField(45);

    private JLabel lbCelularAluno = new JLabel("Celular");
    private JTextField fdCelularAluno = new JTextField(45);

    private JLabel lbTipoCarteiraIdTipoCarteira = new JLabel("ID Tipo Carteira");
    private List<String> stringtipoCarteiraIdTipoCarteira = new ArrayList<>();
    private JComboBox comboTipoCarteiraIdTipoCarteira = new JComboBox();
    JPanel painelImagem = new JPanel(new GridLayout(1, 1));
    Image img;
    Image imagemAux;
    String origem;
    String destino = "src/fotos/";
    String semImagem = "src/fotos/0.png";
    String escolherImagem = "src/fotos/0a.png";
    JLabel labelFoto = new JLabel("");
    Boolean uploadFoto = false;

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

    DAOAluno controle = new DAOAluno();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JTextComponent editor = (JTextComponent) comboTipoCarteiraIdTipoCarteira.getEditor().getEditorComponent();

    Aluno aluno = new Aluno();
    DAOTipoCarteira daoTipoCarteira = new DAOTipoCarteira();
    DAOAluno daoAluno = new DAOAluno();

    public GUIAddAluno() {
        setSize(725, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Alunos");

        try {
            origem = "/fotos/0.png";
            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            labelFoto.setIcon(icone);

        } catch (Exception erro) {
            System.out.println("erro ao carregar a imagem");
        }

        painelCentral.setLayout(new GridLayout(12, 2));
        painelCentral.add(lbNomeAluno);
        painelCentral.add(fdNomeAluno);
        painelCentral.add(lbRgAluno);
        painelCentral.add(fdRgAluno);
        painelCentral.add(lbRgEmissorAluno);
        painelCentral.add(fdRgEmissorAluno);
        painelCentral.add(lbEnderecoAluno);
        painelCentral.add(fdEnderecoAluno);
        painelCentral.add(lbCidadeAluno);
        painelCentral.add(fdCidadeAluno);
        painelCentral.add(lbBairroAluno);
        painelCentral.add(fdBairroAluno);
        painelCentral.add(lbCepTipoCarteira);
        painelCentral.add(fdCepTipoCarteira);
        painelCentral.add(lbTelefoneAluno);
        painelCentral.add(fdTelefoneAluno);
        painelCentral.add(lbCelularAluno);
        painelCentral.add(fdCelularAluno);

        fdNomeAluno.setEnabled(false);
        fdRgAluno.setEnabled(false);
        fdRgEmissorAluno.setEnabled(false);
        fdEnderecoAluno.setEnabled(false);
        fdCidadeAluno.setEnabled(false);
        fdBairroAluno.setEnabled(false);
        fdCepTipoCarteira.setEnabled(false);
        fdTelefoneAluno.setEnabled(false);
        fdCelularAluno.setEnabled(false);

        painelCentral.add(lbDataNascimento);
        painelCentral.add(spinnerdataNascimento);
        spinnerdataNascimento.setEditor(spinnerEditordataNascimento);
        spinnerdataNascimento.setEnabled(false);
        List<String> combo = new ArrayList<>();
        combo = new ManipulaArquivo().abrirArquivo("TipoCarteira.txt");
        for (int x = 0; x < combo.size(); x++) {
            stringtipoCarteiraIdTipoCarteira.add(combo.get(x).split(";")[0]);
        }
        comboTipoCarteiraIdTipoCarteira = new JComboBox(stringtipoCarteiraIdTipoCarteira.toArray());
        painelCentral.add(lbTipoCarteiraIdTipoCarteira);
        painelCentral.add(comboTipoCarteiraIdTipoCarteira);
        comboTipoCarteiraIdTipoCarteira.setEnabled(false);
        editor.setDocument(new SearchableComboBox(comboTipoCarteiraIdTipoCarteira));

        cp.setBackground(Color.white);
        painelImagem.setBackground(Color.white);
        painelImagem.add(labelFoto);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.WEST);
        cp.add(painelImagem, BorderLayout.EAST);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
        painelNorteCima.add(fdCpfAluno);
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
        fdCpfAluno.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbCpfAluno.setFont(new Font("Courier New", Font.BOLD, 17));
        lbNomeAluno.setFont(new Font("Courier New", Font.BOLD, 17));
        lbDataNascimento.setFont(new Font("Courier New", Font.BOLD, 17));
        lbRgAluno.setFont(new Font("Courier New", Font.BOLD, 17));
        lbRgEmissorAluno.setFont(new Font("Courier New", Font.BOLD, 17));
        lbEnderecoAluno.setFont(new Font("Courier New", Font.BOLD, 17));
        lbCidadeAluno.setFont(new Font("Courier New", Font.BOLD, 17));
        lbBairroAluno.setFont(new Font("Courier New", Font.BOLD, 17));
        lbCepTipoCarteira.setFont(new Font("Courier New", Font.BOLD, 17));
        lbTelefoneAluno.setFont(new Font("Courier New", Font.BOLD, 17));
        lbCelularAluno.setFont(new Font("Courier New", Font.BOLD, 17));
        lbTipoCarteiraIdTipoCarteira.setFont(new Font("Courier New", Font.BOLD, 17));
        fdCpfAluno.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdNomeAluno.setFont(new Font("Courier New", Font.PLAIN, 17));
        spinnerdataNascimento.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdRgAluno.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdRgEmissorAluno.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdEnderecoAluno.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdCidadeAluno.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdBairroAluno.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdCepTipoCarteira.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdTelefoneAluno.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdCelularAluno.setFont(new Font("Courier New", Font.PLAIN, 17));
        comboTipoCarteiraIdTipoCarteira.setFont(new Font("Courier New", Font.PLAIN, 17));
        labelAviso.setFont(new Font("Courier New", Font.BOLD, 10));
        btInserir.setVisible(false);
        btAtualizar.setVisible(false);
        btRemover.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        painelSul.add(labelAviso);

        List<String> ltc = daoTipoCarteira.listInOrderNomeStrings("id"); //lista tipo carteira (ltc)
        for (int i = 0; i < ltc.size(); i++) {
            comboTipoCarteiraIdTipoCarteira.addItem(ltc.get(i));
        }

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadFoto = false;
                try {
                    aluno = new Aluno();
                    String cpfAluno = fdCpfAluno.getText();
                    aluno.setCpfAluno((fdCpfAluno.getText()));
                    aluno = controle.obter(cpfAluno);
                    labelAviso.setBackground(Color.green);
                    if (aluno != null) {
                        fdCpfAluno.setText(aluno.getCpfAluno() + "");
                        fdNomeAluno.setText(aluno.getNomeAluno() + "");
                        spinnerdataNascimento.setValue(aluno.getDataNascimento());
                        fdRgAluno.setText(aluno.getRgAluno() + "");
                        fdRgEmissorAluno.setText(aluno.getRgEmissorAluno() + "");
                        fdEnderecoAluno.setText(aluno.getEnderecoAluno() + "");
                        fdCidadeAluno.setText(aluno.getCidadeAluno() + "");
                        fdBairroAluno.setText(aluno.getBairroAluno() + "");
                        fdCepTipoCarteira.setText(aluno.getCepTipoCarteira() + "");
                        fdTelefoneAluno.setText(aluno.getTelefoneAluno() + "");
                        fdCelularAluno.setText(aluno.getCelularAluno() + "");
                        comboTipoCarteiraIdTipoCarteira.setSelectedItem(aluno.getTipoCarteiraIdTipoCarteira().toString());
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                        try {
                            String aux = String.valueOf(aluno.getCpfAluno()).trim();
                            origem = "/fotos/" + aux + ".png";
                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));

                            labelFoto.setIcon(icone);

                        } catch (Exception erro) {
                            origem = "/fotos/0.png";
                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                            labelFoto.setIcon(icone);
                        }
                    } else {
                        try {
                            origem = "/fotos/0.png";
                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                            labelFoto.setIcon(icone);

                        } catch (Exception erro) {
                            System.out.println("erro ao carregar a imagem");
                        }

                        fdNomeAluno.setEnabled(false);
                        fdNomeAluno.setText("");
                        fdRgAluno.setEnabled(false);
                        fdRgAluno.setText("");
                        fdRgEmissorAluno.setEnabled(false);
                        fdRgEmissorAluno.setText(null);
                        fdEnderecoAluno.setEnabled(false);
                        fdEnderecoAluno.setText(null);
                        fdCidadeAluno.setEnabled(false);
                        fdCidadeAluno.setText(null);
                        fdBairroAluno.setEnabled(false);
                        fdBairroAluno.setText(null);
                        fdCepTipoCarteira.setEnabled(false);
                        fdCepTipoCarteira.setText(null);
                        fdTelefoneAluno.setEnabled(false);
                        fdTelefoneAluno.setText(null);
                        fdCelularAluno.setEnabled(false);
                        fdCelularAluno.setText(null);
                        spinnerdataNascimento.setEnabled(false);
                        spinnerdataNascimento.setValue(new Date());
                        comboTipoCarteiraIdTipoCarteira.setEnabled(false);
                        comboTipoCarteiraIdTipoCarteira.setSelectedIndex(0);
                        labelAviso.setText("Não encontrado!");
                        labelAviso.setBackground(Color.red);
                        btInserir.setVisible(true);
                        btAtualizar.setVisible(false);
                        btRemover.setVisible(false);
                        btListar.setVisible(false);
                        try {
                            origem = "/fotos/0.png";
                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                            labelFoto.setIcon(icone);

                        } catch (Exception erro) {
                            System.out.println("erro ao carregar a imagem");
                        }
                    }
                } catch (Exception erro) {
                    labelAviso.setText("Preencha os campos corretamente!" + erro.getMessage());
                    labelAviso.setBackground(Color.red);
                }
            }
        }
        );

        btInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = true;
                try {
                    origem = "/fotos/0a.png";
                    ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                    imagemAux = icone.getImage();
                    icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                    labelFoto.setIcon(icone);

                } catch (Exception erro) {
                    System.out.println("erro ao carregar a imagem");
                }
                fdCpfAluno.setEnabled(false);
                fdNomeAluno.setEnabled(true);
                spinnerdataNascimento.setEnabled(true);
                fdRgAluno.setEnabled(true);
                fdRgEmissorAluno.setEnabled(true);
                fdEnderecoAluno.setEnabled(true);
                fdCidadeAluno.setEnabled(true);
                fdBairroAluno.setEnabled(true);
                fdCepTipoCarteira.setEnabled(true);
                fdTelefoneAluno.setEnabled(true);
                fdCelularAluno.setEnabled(true);
                comboTipoCarteiraIdTipoCarteira.setEnabled(true);
                uploadFoto = true;
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
                uploadFoto = false;
                if (acao) { //btInserir
                    try {
                        aluno = new Aluno();
                        aluno.setCpfAluno(fdCpfAluno.getText());
                        aluno.setNomeAluno(fdNomeAluno.getText());
                        aluno.setDataNascimento((Date) spinnerdataNascimento.getValue());
                        aluno.setRgAluno(fdRgAluno.getText());
                        aluno.setRgEmissorAluno(fdRgEmissorAluno.getText());
                        aluno.setEnderecoAluno(fdEnderecoAluno.getText());
                        aluno.setCidadeAluno(fdCidadeAluno.getText());
                        aluno.setBairroAluno(fdBairroAluno.getText());
                        aluno.setCepTipoCarteira(fdCepTipoCarteira.getText());
                        aluno.setTelefoneAluno(fdTelefoneAluno.getText());
                        aluno.setCelularAluno(fdCelularAluno.getText());
                        
                        String x = (String) comboTipoCarteiraIdTipoCarteira.getSelectedItem();
                        String[] aux = x.split(";");
                        TipoCarteira tipoCarteira = daoTipoCarteira.obter(Integer.valueOf(aux[0]));
                        aluno.setTipoCarteiraIdTipoCarteira(tipoCarteira);
                        
                        controle.inserir(aluno);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdCpfAluno.setEnabled(true);
                        fdCpfAluno.requestFocus();
                        fdNomeAluno.setEnabled(false);
                        spinnerdataNascimento.setEnabled(false);
                        fdRgAluno.setEnabled(false);
                        fdRgEmissorAluno.setEnabled(false);
                        fdEnderecoAluno.setEnabled(false);
                        fdCidadeAluno.setEnabled(false);
                        fdBairroAluno.setEnabled(false);
                        fdCepTipoCarteira.setEnabled(false);
                        fdTelefoneAluno.setEnabled(false);
                        fdCelularAluno.setEnabled(false);
                        comboTipoCarteiraIdTipoCarteira.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        aluno = new Aluno();
                        aluno.setCpfAluno(fdCpfAluno.getText());
                        aluno.setNomeAluno(fdNomeAluno.getText());
                        aluno.setDataNascimento((Date) spinnerdataNascimento.getValue());
                        aluno.setRgAluno(fdRgAluno.getText());
                        aluno.setRgEmissorAluno(fdRgEmissorAluno.getText());
                        aluno.setEnderecoAluno(fdEnderecoAluno.getText());
                        aluno.setCidadeAluno(fdCidadeAluno.getText());
                        aluno.setBairroAluno(fdBairroAluno.getText());
                        aluno.setCepTipoCarteira(fdCepTipoCarteira.getText());
                        aluno.setTelefoneAluno(fdTelefoneAluno.getText());
                        aluno.setCelularAluno(fdCelularAluno.getText());
                        
                        String x = (String) comboTipoCarteiraIdTipoCarteira.getSelectedItem();
                        String[] aux = x.split(";");
                        TipoCarteira tipoCarteira = daoTipoCarteira.obter(Integer.valueOf(aux[0]));
                        aluno.setTipoCarteiraIdTipoCarteira(tipoCarteira);
                        
                        
                        controle.atualizar(aluno);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdCpfAluno.setEnabled(true);
                        fdCpfAluno.requestFocus();
                        fdNomeAluno.setEnabled(false);
                        spinnerdataNascimento.setEnabled(false);
                        fdRgAluno.setEnabled(false);
                        fdRgEmissorAluno.setEnabled(false);
                        fdEnderecoAluno.setEnabled(false);
                        fdCidadeAluno.setEnabled(false);
                        fdBairroAluno.setEnabled(false);
                        fdCepTipoCarteira.setEnabled(false);
                        fdTelefoneAluno.setEnabled(false);
                        fdCelularAluno.setEnabled(false);
                        comboTipoCarteiraIdTipoCarteira.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                }
                destino = destino + aluno.getCpfAluno() + ".png";
                CopiaImagem.copiar(origem, destino);
                destino = "src/fotos/";
            }
        }
        );

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    origem = "/fotos/0.png";
                    ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                    imagemAux = icone.getImage();
                    icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                    labelFoto.setIcon(icone);

                } catch (Exception erro) {
                    System.out.println("erro ao carregar a imagem");
                }
                labelAviso.setText("Cancelado!");
                fdCpfAluno.setEnabled(false);
                fdCpfAluno.setText("");
                fdNomeAluno.setEnabled(false);
                fdNomeAluno.setText("");
                spinnerdataNascimento.setEnabled(false);
                spinnerdataNascimento.setValue(new Date());
                fdRgAluno.setEnabled(false);
                fdRgAluno.setText("");
                fdRgEmissorAluno.setEnabled(false);
                fdRgEmissorAluno.setText("");
                fdEnderecoAluno.setEnabled(false);
                fdEnderecoAluno.setText("");
                fdCidadeAluno.setEnabled(false);
                fdCidadeAluno.setText("");
                fdBairroAluno.setEnabled(false);
                fdBairroAluno.setText("");
                fdCepTipoCarteira.setEnabled(false);
                fdCepTipoCarteira.setText("");
                fdTelefoneAluno.setEnabled(false);
                fdTelefoneAluno.setText("");
                fdCelularAluno.setEnabled(false);
                fdCelularAluno.setText("");
                comboTipoCarteiraIdTipoCarteira.setEnabled(false);
                comboTipoCarteiraIdTipoCarteira.setSelectedIndex(0);
                fdCpfAluno.setEnabled(true);
                fdCpfAluno.requestFocus();
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
                uploadFoto = false;
                acao = false;
                fdNomeAluno.setEnabled(true);
                spinnerdataNascimento.setEnabled(true);
                fdRgAluno.setEnabled(true);
                fdRgEmissorAluno.setEnabled(true);
                fdEnderecoAluno.setEnabled(true);
                fdCidadeAluno.setEnabled(true);
                fdBairroAluno.setEnabled(true);
                fdCepTipoCarteira.setEnabled(true);
                fdTelefoneAluno.setEnabled(true);
                fdCelularAluno.setEnabled(true);
                comboTipoCarteiraIdTipoCarteira.setEnabled(true);
                fdNomeAluno.requestFocus();
                fdCpfAluno.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + aluno.getCpfAluno() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(aluno);
                    labelAviso.setText("Removido!");
                    fdCpfAluno.setText("");
                    fdNomeAluno.setText("");
                    fdNomeAluno.setEnabled(false);
                    spinnerdataNascimento.setEnabled(false);
                    spinnerdataNascimento.setValue(new Date());
                    fdRgAluno.setText("");
                    fdRgAluno.setEnabled(false);
                    fdRgEmissorAluno.setText("");
                    fdRgEmissorAluno.setEnabled(false);
                    fdEnderecoAluno.setText("");
                    fdEnderecoAluno.setEnabled(false);
                    fdCidadeAluno.setText("");
                    fdCidadeAluno.setEnabled(false);
                    fdBairroAluno.setText("");
                    fdBairroAluno.setEnabled(false);
                    fdCepTipoCarteira.setText("");
                    fdCepTipoCarteira.setEnabled(false);
                    fdTelefoneAluno.setText("");
                    fdTelefoneAluno.setEnabled(false);
                    fdCelularAluno.setText("");
                    fdCelularAluno.setEnabled(false);
                    comboTipoCarteiraIdTipoCarteira.setEnabled(false);
                    comboTipoCarteiraIdTipoCarteira.setSelectedIndex(0);
                    String aux = String.valueOf(aluno.getCpfAluno()).trim();
                    origem = "src/fotos/" + aux + ".png";
                    System.out.println(origem);
                    try {
                        File f = new File(origem);
                        if (f.exists()) {
                            f.delete();
                        }
                    } catch (Exception erro) {
                        System.out.println("Erro");
                    }
                    btListar.setVisible(true);
                } else {
                    labelAviso.setText("Remoção cancelada!");
                    btAtualizar.setVisible(true);
                    btRemover.setVisible(true);
                }
            }
        }
        );

        labelFoto.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (uploadFoto) {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    if (fc.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                        File img = fc.getSelectedFile();
                        origem = fc.getSelectedFile().getAbsolutePath();
                        try {
                            ImageIcon icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                            Image imagemAux;
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                            labelFoto.setIcon(icone);

                        } catch (Exception ex) {
                            System.out.println("Erro: " + ex.getMessage());
                        }
                    }

                }

            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AlunoGUIListagem(controle.listInOrderNomeStrings("tanto faz"), cp);
            }
        }
        );
        
        fdCpfAluno.addActionListener(new ActionListener() {//pesquisa incremental no id
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                List<String> listaAuxiliar = daoAluno.listInOrderNomeStrings("id");
                        
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(";");
                        fdCpfAluno.setText(aux[0]);
                        btBuscar.doClick();//aciona o botao buscar
                    } else {
                        fdCpfAluno.requestFocus();
                        fdCpfAluno.selectAll();
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
