package DAOs;

import Entidades.Veiculo;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOVeiculo extends DAOGenerico<Veiculo> {

    public DAOVeiculo() {
        super(Veiculo.class);
    }

    public int autoIdVeiculo() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.codigoVeiculo) FROM Veiculo e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Veiculo> listByCodigoVeiculo(int codigoVeiculo) {
        return em.createQuery("SELECT e FROM Veiculo e WHERE e.codigoVeiculo = :codigoVeiculo").setParameter("codigoVeiculo", codigoVeiculo).getResultList();
    }

    public List<Veiculo> listByNomeVeiculo(String nomeVeiculo) {
        return em.createQuery("SELECT e FROM Veiculo e WHERE e.nomeVeiculo LIKE :nomeVeiculo").setParameter("nomeVeiculo", "%" + nomeVeiculo + "%").getResultList();
    }

    public List<Veiculo> listInOrderCodigoVeiculo() {
        return em.createQuery("SELECT e FROM Veiculo e ORDER BY e.codigoVeiculo").getResultList();
    }

    public List<Veiculo> listInOrderNomeVeiculo() {
        return em.createQuery("SELECT e FROM Veiculo e ORDER BY e.nomeVeiculo").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Veiculo> lf;
        if (qualOrdem.equals("codigoVeiculo")) {
            lf = listInOrderCodigoVeiculo();
        } else {
            lf = listInOrderNomeVeiculo();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getCodigoVeiculo() + ";" + lf.get(i).getNomeVeiculo() + ";" + lf.get(i).getTipoVeiculoIdTipoVeiculo() + ";");
        }
        return ls;
    }
}

