package DAOs;

import Entidades.TurmaTeorica;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import Entidades.TurmaTeoricaHasAluno;
import java.text.SimpleDateFormat;

public class DAOTurmaTeorica extends DAOGenerico<TurmaTeorica> {

    public DAOTurmaTeorica() {
        super(TurmaTeorica.class);
    }

    public int autoIdTurmaTeorica() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.codigoTurma) FROM TurmaTeorica e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<TurmaTeorica> listByCodigoTurma(int codigoTurma) {
        return em.createQuery("SELECT e FROM TurmaTeorica e WHERE e.codigoTurma = :codigoTurma").setParameter("codigoTurma", codigoTurma).getResultList();
    }

    public List<TurmaTeorica> listByPeriodoTurma(String periodoTurma) {
        return em.createQuery("SELECT e FROM TurmaTeorica e WHERE e.periodoTurma LIKE :periodoTurma").setParameter("periodoTurma", "%" + periodoTurma + "%").getResultList();
    }

    public List<TurmaTeorica> listInOrderCodigoTurma() {
        return em.createQuery("SELECT e FROM TurmaTeorica e ORDER BY e.codigoTurma").getResultList();
    }

    public List<TurmaTeorica> listInOrderPeriodoTurma() {
        return em.createQuery("SELECT e FROM TurmaTeorica e ORDER BY e.periodoTurma").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<TurmaTeorica> lf;
        if (qualOrdem.equals("codigoTurma")) {
            lf = listInOrderCodigoTurma();
        } else {
            lf = listInOrderPeriodoTurma();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getCodigoTurma() + ";" + lf.get(i).getPeriodoTurma() + ";" + sdf.format(lf.get(i).getDataInicio()) + ";" + lf.get(i).getQuantidadeHoras() + ";" + lf.get(i).getProfessorCpfProfessor() + ";");
        }
        return ls;
    }
}

