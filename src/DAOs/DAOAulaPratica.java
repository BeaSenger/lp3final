package DAOs;

import Entidades.AulaPratica;
import Entidades.AulaPraticaPK;
import java.util.ArrayList;
import java.util.List;

public class DAOAulaPratica extends DAOGenerico<AulaPratica> {

    private List<AulaPratica> lista = new ArrayList<>();

    public DAOAulaPratica() {
        super(AulaPratica.class);
    }

    public int autoIdAulaPratica() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idAulaPratica) FROM AulaPratica e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<AulaPratica> listByNome(String nome) {
        return em.createQuery("SELECT e FROM AulaPratica e WHERE e.idAulaPratica LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<AulaPratica> listById(int id) {
        return em.createQuery("SELECT e FROM AulaPratica + e WHERE e.clienteIdCliente= :id").setParameter("id", id).getResultList();
    }

    public List<AulaPratica> listInOrderNome() {
        return em.createQuery("SELECT e FROM AulaPratica e ORDER BY e.clienteIdCliente").getResultList();
    }

    public List<AulaPratica> listInOrderId() {
        return em.createQuery("SELECT e FROM AulaPratica e ORDER BY e.idAulaPratica").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<AulaPratica> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getAluno()+ "-" + lf.get(i).getProfessor());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOAulaPratica daoAulaPratica = new DAOAulaPratica();
        List<AulaPratica> listaAulaPratica = daoAulaPratica.list();
        for (AulaPratica pedido : listaAulaPratica) {
            System.out.println(pedido.getAluno()+ "-" + pedido.getProfessor());
        }
    }
}
