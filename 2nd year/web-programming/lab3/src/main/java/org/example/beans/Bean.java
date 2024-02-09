package org.example.beans;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Collections;
import java.util.List;

@Named
@ViewScoped
public class Bean implements Serializable {

    @Inject
    private ResultManager resultManager;

    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal r;
    private boolean inside;

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
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

    public List<ResultData> getResultTable() {
        if (resultManager != null) {
            return resultManager.getResultDataList();
        } else {
            return Collections.emptyList();
        }
    }

    public void submitForm() {
        if (resultManager != null) {
            resultManager.saveResult(x, y, r, inside);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Успех", "Данные успешно сохранены"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Не удалось получить доступ к ResultManager"));
        }
    }
}
