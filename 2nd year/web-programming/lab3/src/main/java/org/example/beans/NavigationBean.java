package org.example.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class NavigationBean {
    public String redirectToMainPage() {
        return "index.xhtml?faces-redirect=true";
    }

    public String redirectToStartPage() {
        return "start.xhtml?faces-redirect=true";
    }
}

