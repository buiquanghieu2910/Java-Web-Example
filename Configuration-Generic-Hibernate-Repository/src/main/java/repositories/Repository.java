/**
 * BUI_QUANG_HIEU, 2023
 * Repository.java
 */
package repositories;

import java.io.Serializable;
import java.util.List;

/**
 * @author BUI_QUANG_HIEU
 */
public interface Repository<T, ID extends Serializable> {
    public T save(T t);

    public T delete(T t);

    public T update(T t);

    public List<T> findAll();

    public T findById(ID id);

    public List<T> where(String query);
}
