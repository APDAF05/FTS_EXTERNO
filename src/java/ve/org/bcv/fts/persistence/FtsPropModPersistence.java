/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.persistence;

import javax.persistence.Query;
import ve.org.bcv.fts.bean.FtsPropMod;
import ve.org.bcv.fts.exception.ExceptionTreatment;

/**
 *
 * @author furibe
 */
public class FtsPropModPersistence extends JpaDao<Integer, FtsPropMod> {

    public FtsPropModPersistence() {
        super();
    }

    public FtsPropMod findByCoPropMod(String coPropMod) {

        FtsPropMod ftsPropMod = null;

        try {
            Query query = em.createNamedQuery("FtsPropMod.findByCoPropMod").setHint("eclipselink.refresh", "true");
            query.setParameter("coPropMod", coPropMod);
            ftsPropMod = (FtsPropMod) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            new ExceptionTreatment().treat(e);
        }
        return ftsPropMod;

    }

}
