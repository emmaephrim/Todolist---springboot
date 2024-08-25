package com.todolist.Todo_List.model;

import java.util.Date; // Add this import statement

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Incomplete'")
    private String status;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isSoftDeleted;

    public Todo() {
        this.status = "Incomplete";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSoftDeleted() {
        return isSoftDeleted;
    }

    public void setSoftDeleted(boolean isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }

}
