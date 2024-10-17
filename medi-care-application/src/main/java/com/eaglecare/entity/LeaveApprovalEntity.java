package com.eaglecare.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "leave_approvals")
public class LeaveApprovalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "leave_application_id", nullable = false)
    private Long leaveApplicationId;

    @Column(name = "approval_date")
    @Temporal(TemporalType.DATE)
    private Date approvalDate;

    public enum StatusEnum {
        PENDING,
        APPROVED,
        REJECTED;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEnum status;

    @Column(name = "comments")
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLeaveApplicationId() {
        return leaveApplicationId;
    }

    public void setLeaveApplicationId(Long leaveApplicationId) {
        this.leaveApplicationId = leaveApplicationId;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leaveApplicationId, approvalDate, status, comments);
    }

    @Override
    public String toString() {
        return "LeaveApprovalEntity{" +
                "id=" + id +
                ", leaveApplicationId=" + leaveApplicationId +
                ", approvalDate=" + approvalDate +
                ", status=" + status +
                ", comments='" + comments + '\'' +
                '}';
    }
}
