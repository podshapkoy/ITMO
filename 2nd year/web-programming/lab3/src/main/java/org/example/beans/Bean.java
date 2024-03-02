package org.example.beans;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@ViewScoped
public class Bean implements Serializable {

    @Inject
    private ResultManager resultManager;
    @Inject
    @Push(channel = "notify")
    private PushContext pushContext;
    private double x;
    private BigDecimal y;
    private BigDecimal r;
    private List<ResultData> hits = new ArrayList<>();

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getR() {
        return r;
    }

    public void setR(BigDecimal r) {
        this.r = r;
    }

    public List<ResultData> getHits() {
        return hits;
    }

    @PostConstruct
    public void init() {
        hits = resultManager.getAllResults();
    }

    public void sendData() {
        pushContext.send("update");
    }

    public void submitForm() {
        Result hit = new Result(x, y, r);
        hit.isValid();
        hit.checkPoint();

        ResultsData hitResult = new ResultsData();
        hitResult.setX(x);
        hitResult.setY(y);
        hitResult.setR(r);
        hitResult.setInside(hit.getInside());
        hitResult.setLocalDateTime(LocalDateTime.now());
        try {
            resultManager.saveHitResult(hitResult);
            hits.add(0, hitResult);
        } catch (OutOfMemoryError e) {
            resultManager.clearDatabase();

            resultManager.saveHitResult(hitResult);
            hits.add(0, hitResult);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Data saved successfully"));
        sendData();
    }

    public void cleanDataBase() {
        resultManager.clearDatabase();
        hits.clear();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Database cleaned successfully"));
        sendData();
    }
}
