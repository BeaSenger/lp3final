package DAOs;

import Entidades.TipoVeiculo;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOTipoVeiculo extends DAOGenerico<TipoVeiculo> {

    public DAOTipoVeiculo() {
        super(TipoVeiculo.class);
    }

    public int autoIdTipoVeiculo() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTipoVeiculo) FROM TipoVeiculo e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<TipoVeiculo> listByIdTipoVeiculo(int idTipoVeiculo) {
        return em.createQuery("SELECT e FROM TipoVeiculo e WHERE e.idTipoVeiculo = :idTipoVeiculo").setParameter("idTipoVeiculo", idTipoVeiculo).getResultList();
    }

    public List<TipoVeiculo> listByNomeTipoVeiculo(String nomeTipoVeiculo) {
        return em.createQuery("SELECT e FROM TipoVeiculo e WHERE e.nomeTipoVeiculo LIKE :nomeTipoVeiculo").setParameter("nomeTipoVeiculo", "%" + nomeTipoVeiculo + "%").getResultList();
    }

    public List<TipoVeiculo> listInOrderIdTipoVeiculo() {
        return em.createQuery("SELECT e FROM TipoVeiculo e ORDER BY e.idTipoVeiculo").getResultList();
    }

    public List<TipoVeiculo> listInOrderNomeTipoVeiculo() {
        return em.createQuery("SELECT e FROM TipoVeiculo e ORDER BY e.nomeTipoVeiculo").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<TipoVeiculo> lf;
        if (qualOrdem.equals("idTipoVeiculo")) {
            lf = listInOrderIdTipoVeiculo();
        } else {
            lf = listInOrderNomeTipoVeiculo();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdTipoVeiculo() + ";" + lf.get(i).getNomeTipoVeiculo() + ";");
        }
        return ls;
    }
}

