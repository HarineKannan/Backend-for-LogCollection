package com.example.Testing.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "logDetails")
public class LogDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "neededLog")
    private String neededLog;

    @Column(name = "field")
    private List<String> fieldsNeeded;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setNeededLog(){
        this.neededLog=neededLog;
    }

    public String getNeededLog() {
        return neededLog;
    }

    public List<String> getFieldsNeeded() {
        return fieldsNeeded;
    }

    public void setFieldsNeeded(List<String> fieldsNeeded) {
        this.fieldsNeeded = fieldsNeeded;
    }
}
