/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.persistence;

import ve.org.bcv.fts.bean.FtsInstAutProMod;
import ve.org.bcv.fts.bean.FtsInstAutProModPK;
import ve.org.bcv.fts.exception.ExceptionTreatment;

/**
 *
 * @author furibe
 */
public class FtsInstAutProModPersistence extends JpaDao<FtsInstAutProModPK, FtsInstAutProMod> {

    public FtsInstAutProModPersistence() {
        super();
    }

    public Integer isInstAut(String nuRifInst, int idPropMod) {
        Integer count = 0;
        try {
            count = ((Number) em.createNamedQuery("FtsInstAutProMod.findAllCount").getSingleResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            new ExceptionTreatment().treat(e);
        }
        return count;
    }

}
