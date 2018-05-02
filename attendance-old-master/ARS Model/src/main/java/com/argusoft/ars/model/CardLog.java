/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author harshit
 */
@Entity
@Table(name = "card_log")
@NamedQueries({
    @NamedQuery(name = "CardLog.findAll", query = "SELECT c FROM CardLog c")})
public class CardLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "card_enroll_number")
    private long cardEnrollNumber;
    @Basic(optional = false)
    @Column(name = "card_punching_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cardPunchingTime;
    @Column(name = "card_entry_reason")
    private String cardEntryReason;
    @Id
    @Basic(optional = false)
    @Column(name = "card_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardLogId;
    @Basic(optional = false)
    @Column(name = "card_entry_type")
    private short cardEntryType;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "verify_code")
    private Integer verifyCode;
    @Column(name = "entry_turn")
    private Integer entruTurn;

    public CardLog() {
    }

    public CardLog(Long cardLogId) {
        this.cardLogId = cardLogId;
    }

    public CardLog(long cardEnrollNumber, Date cardPunchingTime, String cardEntryReason, Long cardLogId, Short cardEntryType, Long userId) {
        this.cardEnrollNumber = cardEnrollNumber;
        this.cardPunchingTime = cardPunchingTime;
        this.cardEntryReason = cardEntryReason;
        this.cardLogId = cardLogId;
        this.cardEntryType = cardEntryType;
        this.userId = userId;
    }

    public long getCardEnrollNumber() {
        return cardEnrollNumber;
    }

    public void setCardEnrollNumber(long cardEnrollNumber) {
        this.cardEnrollNumber = cardEnrollNumber;
    }

    public Date getCardPunchingTime() {
        return cardPunchingTime;
    }

    public void setCardPunchingTime(Date cardPunchingTime) {
        this.cardPunchingTime = cardPunchingTime;
    }

    public String getCardEntryReason() {
        return cardEntryReason;
    }

    public void setCardEntryReason(String cardEntryReason) {
        this.cardEntryReason = cardEntryReason;
    }

    public Long getCardLogId() {
        return cardLogId;
    }

    public void setCardLogId(Long cardLogId) {
        this.cardLogId = cardLogId;
    }

    public short getCardEntryType() {
        return cardEntryType;
    }

    public void setCardEntryType(short cardEntryType) {
        this.cardEntryType = cardEntryType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getEntruTurn() {
        return entruTurn;
    }

    public void setEntruTurn(Integer entruTurn) {
        this.entruTurn = entruTurn;
    }

    public Integer getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(Integer verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardLogId != null ? cardLogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CardLog)) {
            return false;
        }
        CardLog other = (CardLog) object;
        if ((this.cardLogId == null && other.cardLogId != null) || (this.cardLogId != null && !this.cardLogId.equals(other.cardLogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CardLog{" + "cardEnrollNumber=" + cardEnrollNumber + ", cardPunchingTime=" + cardPunchingTime + ", cardEntryReason=" + cardEntryReason + ", cardLogId=" + cardLogId + ", cardEntryType=" + cardEntryType + ", userId=" + userId + '}';
    }
}
