/**
 * MIT License
 * <p>
 * Copyright (c) 2020 Dhanusha Perera
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @since : 29/01/2021
 * @since : 29/01/2021
 * @since : 29/01/2021
 **/
/**
 * @author : Dhanusha Perera
 * @since : 29/01/2021
 **/
package lk.ijse.dep.web.dao;

import lk.ijse.dep.web.entity.SuperEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/*
 * sir's repor link : https://github.com/sura-boy-playground/servlet-pos
 * */
public abstract class CrudDAOImpl<T extends SuperEntity, K extends Serializable> implements CrudDAO<T, K> {

    private EntityManager entityManager = null;
    private Class<T> entityClass;

    public CrudDAOImpl() {
        entityClass = (Class<T>) ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
    }

    public CrudDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /** This method will share the session with its subclasses */
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) throws Exception {
        this.entityManager = entityManager;
    }

    @Override
    public void save(T entity) throws Exception {
        this.entityManager.persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        this.entityManager.merge(entity);
    }

    @Override
    public void delete(K key) throws Exception {
        this.entityManager.remove(this.entityManager.getReference(entityClass, key));
    }

    @Override
    public List<T> getAll() throws Exception {
        /* HQL ekak gahala SELECT * karagannawa */
        return this.entityManager.createQuery("FROM " + entityClass.getName()).getResultList();
    }

    @Override
    public T get(K key) throws Exception {
        return this.entityManager.find(entityClass, key);
    }


}
