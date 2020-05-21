    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.persistence;


import java.util.List;
import ve.org.bcv.fts.exception.ServicesException;

/**
 *
 * @author furibe
 */
public interface Dao<K, E> {

    public E persist(E entity)throws ServicesException;

    void remove(E entity);
    
    public E merge(E entity) throws ServicesException ;

    E findById(K id);

    List<E> findAll();

    public void beginTransaction();

    public void commitTransaction();

    public void close();
    

    
 
}
