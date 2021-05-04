package com.health.controller.api.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class GenericService<T, ID extends Serializable> {

    @Autowired
    private EntityManager entityManager;

    private JpaRepository<T, ID> jpaRepository;

    public GenericService(JpaRepository<T, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public T save(T t) {
        return jpaRepository.save(t);
    }

    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    public T findById(ID id) {
        return jpaRepository.getOne(id);
    }

    public void delete(T t) {
        jpaRepository.delete(t);
    }

    public List<T> findTableData() {
        return jpaRepository.findAll();
    }

    @SuppressWarnings("unchecked")
	public List<T> search(T t, Date startDate, Date endDate){
        StringBuilder query = new StringBuilder("Select e from ");
        query.append(t.getClass().getSimpleName());
        query.append(" e where e.createdDate BETWEEN :startDate AND :endDate");
        return entityManager.createQuery(query.toString())
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate).getResultList();
    }

    public Long findNewId(Class<T> entityClass){
        StringBuilder query = new StringBuilder("Select max(e.id) from ");
        query.append(entityClass.getSimpleName());
        query.append(" e");
        List<Long> newId = entityManager.createQuery(query.toString(),Long.class).getResultList();
        if(newId!=null && !newId.isEmpty()){
            return newId.get(0)!=null ? newId.get(0) + 1L : 1L;
        }else{
            return 1L;
        }
    }

    public List<T> search(T t){
        return jpaRepository.findAll();
    }
}
