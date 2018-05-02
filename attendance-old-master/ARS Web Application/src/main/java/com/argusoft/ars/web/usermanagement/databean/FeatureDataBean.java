/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author dmoteria
 */
@ManagedBean(name = "featureDataBean")
@RequestScoped
public class FeatureDataBean {

    private Long featureId;
    private String featureName;
    private String description;
    private boolean isCrud;
    private Boolean isActive;

    public FeatureDataBean(Long featureId, String featureName, String description, boolean isCrud, Boolean isActive) {
        this.featureId = featureId;
        this.featureName = featureName;
        this.description = description;
        this.isCrud = isCrud;
        this.isActive = isActive;
    }
    public FeatureDataBean(Long featureId, String featureName) {
        this.featureId = featureId;
        this.featureName = featureName;
    }

    public FeatureDataBean() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsCrud() {
        return isCrud;
    }

    public void setIsCrud(boolean isCrud) {
        this.isCrud = isCrud;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FeatureDataBean other = (FeatureDataBean) obj;
        if (this.featureId != other.featureId && (this.featureId == null || !this.featureId.equals(other.featureId))) {
            return false;
        }
        if ((this.featureName == null) ? (other.featureName != null) : !this.featureName.equals(other.featureName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.featureId != null ? this.featureId.hashCode() : 0);
        hash = 97 * hash + (this.featureName != null ? this.featureName.hashCode() : 0);
        return hash;
    }

    
}


