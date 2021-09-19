package ru.chuchelin.avg_project.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Chuchelin Aleksey
 */
public class Report {
    private int id;

    private String fio;

    private int avg_value;

    private String dep;

    public Report() {

    }

    public Report(int id, String fio, int avg_value, String dep) {
        this.id = id;
        this.fio = fio;
        this.avg_value = avg_value;
        this.dep = dep;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setAvg_value(int avg_value) {
        this.avg_value = avg_value;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getDep() {
        return dep;
    }

    public int getAvg_value() {
        return avg_value;
    }

}
