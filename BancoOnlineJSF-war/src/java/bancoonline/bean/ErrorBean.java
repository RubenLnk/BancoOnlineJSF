/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.bean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author ruben
 */
@Named(value = "errorBean")
@RequestScoped
public class ErrorBean {
    
    private String title;
    private String subtitle;
    
    /**
     * Creates a new instance of ErrorBean
     */
    public ErrorBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    
}
