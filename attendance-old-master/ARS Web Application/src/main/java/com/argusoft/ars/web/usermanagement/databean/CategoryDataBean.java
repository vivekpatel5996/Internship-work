/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author sraijada
 * @since Mar 15, 2012
 */
@ManagedBean(name = "categoryDataBean")
@ViewScoped
public class CategoryDataBean {

    private String catName;
    private Boolean isParent;
    private Long parentCategory;
    private List parentCategories;
    private Long catId;

    public CategoryDataBean() {
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public List getParentCategories() {
        return parentCategories;
    }

    public void setParentCategories(List parentCategories) {
        System.out.println("*****************in CategoryDataBean->>setParentCategories********************");
        this.parentCategories = parentCategories;
    }

    public Long getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Long parentCategory) {
        System.out.println("*****************in CategoryDataBean->>setParentCategory********************");
        this.parentCategory = parentCategory;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        System.out.println("*****************in CategoryDataBean->>setIsParent********************");
        this.isParent = isParent;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        System.out.println("*****************in CategoryDataBean cat name set********************");
        this.catName = catName;
    }
}
