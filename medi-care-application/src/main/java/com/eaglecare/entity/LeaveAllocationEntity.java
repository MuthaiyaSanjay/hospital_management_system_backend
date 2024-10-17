package com.eaglecare.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "leave_allocation")
public class LeaveAllocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "allocation_type", nullable = false)
    private AllocationTypeEnum allocationType;

    @Column(name = "allocated_days", nullable = false)
    private BigDecimal allocatedDays;

    public LeaveAllocationEntity() {
    }

    public LeaveAllocationEntity(Long userId, AllocationTypeEnum allocationType, BigDecimal allocatedDays) {
        this.userId = userId;
        this.allocationType = allocationType;
        this.allocatedDays = allocatedDays;
    }

    // Getters and Setters
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

    public AllocationTypeEnum getAllocationType() {
        return allocationType;
    }

    public void setAllocationType(AllocationTypeEnum allocationType) {
        this.allocationType = allocationType;
    }

    public BigDecimal getAllocatedDays() {
        return allocatedDays;
    }

    public void setAllocatedDays(BigDecimal allocatedDays) {
        this.allocatedDays = allocatedDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LeaveAllocationEntity)) return false;
        LeaveAllocationEntity that = (LeaveAllocationEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                allocationType == that.allocationType &&
                Objects.equals(allocatedDays, that.allocatedDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, allocationType, allocatedDays);
    }

    @Override
    public String toString() {
        return "LeaveAllocationEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", allocationType=" + allocationType +
                ", allocatedDays=" + allocatedDays +
                '}';
    }

    public enum AllocationTypeEnum {
        EACH_MONTH("EACH_MONTH"),
        EACH_YEAR("EACH_YEAR");

        private final String value;

        AllocationTypeEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}

