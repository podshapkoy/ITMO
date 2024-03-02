package org.example.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ResultManager{

    @PersistenceContext
    private EntityManager entityManager;

    public void saveHitResult(ResultData resultData) {
        entityManager.persist(resultData);
    }

    public List<ResultData> getAllResults() {
        return entityManager.createQuery("SELECT h FROM ResultData h ORDER BY h.id DESC", ResultData.class).getResultList();
    }

    public void clearDatabase() {
        entityManager.createQuery("DELETE FROM ResultData").executeUpdate();
    }
}
