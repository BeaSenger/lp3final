package DAOs;

import Entidades.AulaPratica;
import Entidades.AulaPraticaPK;
import java.util.ArrayList;
import java.util.List;

public class DAOAulaPraticaPK extends DAOGenerico<AulaPraticaPK> {

    private List<AulaPraticaPK> lista = new ArrayList<>();

    public DAOAulaPraticaPK() {
        super(AulaPraticaPK.class);
    }

    public int autoProdutoIdProduto() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.produtoIdProduto) FROM AulaPraticaPK e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<AulaPraticaPK> listByNome(String nome) {
        return em.createQuery("SELECT e FROM AulaPraticaPK e WHERE e.produtoIdProduto LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<AulaPraticaPK> listById(int id) {
        return em.createQuery("SELECT e FROM AulaPraticaPK + e WHERE e.dataPrecoProduto= :id").setParameter("id", id).getResultList();
    }

    public List<AulaPraticaPK> listInOrderNome() {
        return em.createQuery("SELECT e FROM AulaPraticaPK e ORDER BY e.dataPrecoProduto").getResultList();
    }

    public List<AulaPraticaPK> listInOrderId() {
        return em.createQuery("SELECT e FROM AulaPraticaPK e ORDER BY e.produtoIdProduto").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<AulaPraticaPK> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getAlunoCpfAluno()+ "-" + lf.get(i).getProfessorCpfProfessor());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOAulaPraticaPK daoAulaPraticaPK = new DAOAulaPraticaPK();
        List<AulaPraticaPK> listaAulaPraticaPK = daoAulaPraticaPK.list();
        for (AulaPraticaPK aulaPraticaPK : listaAulaPraticaPK) {
            System.out.println(aulaPraticaPK.getAlunoCpfAluno()+ "-" + aulaPraticaPK.getProfessorCpfProfessor());
        }
    }

}
