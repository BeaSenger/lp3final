/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import DAOs.DAOAluno;
import DAOs.DAOTurmaTeorica;
import java.util.List;

/**
 *
 * @author Asus
 */
public class TurmaTeoricaHasAluno {
    
    int retorno = 0;

    public TurmaTeoricaHasAluno(int codigoTurma, String cpfAluno) {
        TurmaTeorica p = new TurmaTeorica();
        Aluno i = new Aluno();
        DAOTurmaTeorica daoTurmaTeorica = new DAOTurmaTeorica();
        DAOAluno daoAluno = new DAOAluno();
        i = daoAluno.obter(cpfAluno);
        List<TurmaTeorica> lp = i.getTurmaTeoricaList();
        if (lp.indexOf(daoTurmaTeorica.obter(codigoTurma)) < 0) {
            lp.add(daoTurmaTeorica.obter(codigoTurma));
            retorno = 0;
        } else {
            System.out.println("ja existe!");
            retorno = 1;
        }

        i.setTurmaTeoricaList(lp);
        daoAluno.atualizar(i);
    }

    public int encontradoNaLista() {
        if (retorno == 1) {
            return 1;
        } else{
            return 0;
        }
    }

}

    

