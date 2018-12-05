package DAOs;

import Entidades.TipoCarteira;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOTipoCarteira extends DAOGenerico<TipoCarteira> {

    public DAOTipoCarteira() {
        super(TipoCarteira.class);
    }

    public int autoIdTipoCarteira() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTipoCarteira) FROM TipoCarteira e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<TipoCarteira> listByIdTipoCarteira(int idTipoCarteira) {
        return em.createQuery("SELECT e FROM TipoCarteira e WHERE e.idTipoCarteira = :idTipoCarteira").setParameter("idTipoCarteira", idTipoCarteira).getResultList();
    }

    public List<TipoCarteira> listByNomeTipoCarteira(String nomeTipoCarteira) {
        return em.createQuery("SELECT e FROM TipoCarteira e WHERE e.nomeTipoCarteira LIKE :nomeTipoCarteira").setParameter("nomeTipoCarteira", "%" + nomeTipoCarteira + "%").getResultList();
    }

    public List<TipoCarteira> listInOrderIdTipoCarteira() {
        return em.createQuery("SELECT e FROM TipoCarteira e ORDER BY e.idTipoCarteira").getResultList();
    }

    public List<TipoCarteira> listInOrderNomeTipoCarteira() {
        return em.createQuery("SELECT e FROM TipoCarteira e ORDER BY e.nomeTipoCarteira").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<TipoCarteira> lf;
        if (qualOrdem.equals("idTipoCarteira")) {
            lf = listInOrderIdTipoCarteira();
        } else {
            lf = listInOrderNomeTipoCarteira();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdTipoCarteira() + ";" + lf.get(i).getNomeTipoCarteira() + ";");
        }
        return ls;
    }
}

