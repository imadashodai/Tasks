package com.grucchi.tasks;

/**
 * Created by imadashoudai on 2019/01/13.
 */

public class Task {

    private String name;
    private Integer status; // 1: not check, 2: check

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
