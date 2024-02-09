package org.example.beans;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Named
@ApplicationScoped
public class ResultManager implements Serializable {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private List<ResultData> resultDataList;

    @PostConstruct
    public void init() {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
            entityManager = entityManagerFactory.createEntityManager();

            resultDataList = entityManager.createQuery("SELECT r FROM ResultData r", ResultData.class).getResultList();
        } catch (Exception e) {
            // Обработка ошибок и логирование
            e.printStackTrace();
        }
    }

    public void saveResult(BigDecimal x, BigDecimal y, BigDecimal r, boolean inside) {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            ResultData resultData = new ResultData();
            resultData.setX(x);
            resultData.setY(y);
            resultData.setR(r);
            resultData.setInside(inside);
            resultData.setLocalDateTime(LocalDateTime.now());

            entityManager.persist(resultData);
            resultDataList.add(resultData);

            transaction.commit();
        } catch (Exception e) {
            // Обработка ошибок и логирование
            e.printStackTrace();
        }
    }

    public List<ResultData> getResultDataList() {
        return resultDataList;
    }
}
