package com.medicare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "role_id")
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "description")
        private String description;

        public RoleEntity() {
        }

        public RoleEntity(Long id, String roleName, String description) {
                this.id = id;
                this.name = roleName;
                this.description = description;
            }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        // Getter for name
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
