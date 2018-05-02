/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author harshit
 */
@Entity
@Table(name = "user_wise_leader")
@NamedQueries({
    @NamedQuery(name = "UserWiseLeader.findAll", query = "SELECT u FROM UserWiseLeader u")})
public class UserWiseLeader implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;
    @Basic(optional = false)
    @Column(name = "leader_user_id")
    private Long leaderUserId;

    public UserWiseLeader() {
    }

    public UserWiseLeader(Long id) {
        this.id = id;
    }

    public UserWiseLeader(Long id, Long userId, Long leaderUserId) {
        this.id = id;
        this.userId = userId;
        this.leaderUserId = leaderUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLeaderUserId() {
        return leaderUserId;
    }

    public void setLeaderUserId(Long leaderUserId) {
        this.leaderUserId = leaderUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserWiseLeader)) {
            return false;
        }
        UserWiseLeader other = (UserWiseLeader) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.argusoft.ars.model.UserWiseLeader[ id=" + id + " ]";
    }
    
}
