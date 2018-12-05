package DAOs;

import Entidades.Aluno;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOAluno extends DAOGenerico<Aluno> {

    public DAOAluno() {
        super(Aluno.class);
    }

    public int autoIdAluno() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.cpfAluno) FROM Aluno e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Aluno> listByCpfAluno(int cpfAluno) {
        return em.createQuery("SELECT e FROM Aluno e WHERE e.cpfAluno = :cpfAluno").setParameter("cpfAluno", cpfAluno).getResultList();
    }

    public List<Aluno> listByNomeAluno(String nomeAluno) {
        return em.createQuery("SELECT e FROM Aluno e WHERE e.nomeAluno LIKE :nomeAluno").setParameter("nomeAluno", "%" + nomeAluno + "%").getResultList();
    }

    public List<Aluno> listInOrderCpfAluno() {
        return em.createQuery("SELECT e FROM Aluno e ORDER BY e.cpfAluno").getResultList();
    }

    public List<Aluno> listInOrderNomeAluno() {
        return em.createQuery("SELECT e FROM Aluno e ORDER BY e.nomeAluno").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Aluno> lf;
        if (qualOrdem.equals("cpfAluno")) {
            lf = listInOrderCpfAluno();
        } else {
            lf = listInOrderNomeAluno();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getCpfAluno() + ";" + lf.get(i).getNomeAluno() + ";" + sdf.format(lf.get(i).getDataNascimento()) + ";" + lf.get(i).getRgAluno() + ";" + lf.get(i).getRgEmissorAluno() + ";" + lf.get(i).getEnderecoAluno() + ";" + lf.get(i).getCidadeAluno() + ";" + lf.get(i).getBairroAluno() + ";" + lf.get(i).getCepTipoCarteira() + ";" + lf.get(i).getTelefoneAluno() + ";" + lf.get(i).getCelularAluno() + ";" + lf.get(i).getTipoCarteiraIdTipoCarteira() + ";");
        }
        return ls;
    }
    public static void main(String[] args) {
        DAOAluno daoAluno = new DAOAluno();
        daoAluno.listInOrderNomeAluno();
        
    }
}

