/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import Main.CaixaDeFerramentas;


/**
 *
 * @author Asus
 */
public class GUIMenu extends JFrame {

    Container cp;

    JPanel pnTotal = new JPanel();

    JMenuBar menubarMenu = new JMenuBar();
    JMenu menu = new JMenu("Cadastros");//aqui o que vai aparecer no nome do menu
    JMenuItem aluno = new JMenuItem("Aluno");//as partes do menu
    JMenuItem professor = new JMenuItem("Professor");
    JMenuItem veiculo = new JMenuItem("Veículo");
    JMenuItem tipoVeiculo = new JMenuItem("Tipo Veículo");
    JMenuItem tipoCarteira = new JMenuItem("Tipo Carteira");
    JMenuItem turmaTeorica = new JMenuItem("Turma Teórica");
    JMenuItem PK = new JMenuItem("PK");
    

    CaixaDeFerramentas caixaDeFerramentas = new CaixaDeFerramentas();

    public GUIMenu() {
        setTitle("Auto Escola Nacional");
        setSize(500, 320);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();

        setJMenuBar(menubarMenu);
        menubarMenu.add(menu);
        menu.add(aluno);//só ta adicionando essas porra
        menu.addSeparator();
        menu.add(professor);
        menu.addSeparator();
        menu.add(veiculo);
        menu.addSeparator();
        menu.add(tipoVeiculo);
        menu.addSeparator();
        menu.add(tipoCarteira);
        menu.addSeparator();
        menu.add(turmaTeorica);
        menu.addSeparator();
        menu.add(PK);
        menu.addSeparator();

        cp.add(pnTotal);

        aluno.addActionListener(new ActionListener() {//faz listener de todos os componentes do menu e só vai
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIAddAluno guiAluno = new GUIAddAluno();
            }
        });

        professor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIAddProfessor guiProfessor = new GUIAddProfessor();
            }
        });

        veiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIVeiculo guiVeiculo = new GUIVeiculo();
            }
        });

        tipoCarteira.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIAddTipoCarteira guiTipoCarteira = new GUIAddTipoCarteira();
            }
        });

        tipoVeiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUITipoVeiculo guiTipoVeiculo = new GUITipoVeiculo();
            }
        });

        turmaTeorica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUITurmaTeorica guiTurmaTeorica = new GUITurmaTeorica();
            }
        });
        
        PK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUITurmaTeoricaHasAluno guiAlunoTurma = new GUITurmaTeoricaHasAluno();
            }
        });
        
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
