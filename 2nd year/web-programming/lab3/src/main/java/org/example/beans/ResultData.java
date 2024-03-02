package org.example.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "result_data")
public class ResultData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double x;
    private BigDecimal y;
    private BigDecimal r;
    private boolean inside;
    private LocalDateTime localDateTime;

    public void setX(double x) {
        this.x = x;
    }
    public void setY(BigDecimal y) {
        this.y = y;
    }
    public void setR(BigDecimal r) {
        this.r = r;
    }
    public void setInside(boolean inside) {
        this.inside = inside;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public double getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    public BigDecimal getR() {
        return r;
    }

    public boolean getInside() {
        return inside;
    }
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}

