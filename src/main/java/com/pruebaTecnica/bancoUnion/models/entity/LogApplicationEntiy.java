package com.pruebaTecnica.bancoUnion.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "logAplicacion_logs")
public class LogApplicationEntiy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "log_id")
    private String id;
    private String machine_name;
    private String level1;
    private String message;
    private String exception;
    private String payload;
    @Column(name = "call_site")
    private String callSite;
    private String action;
    private String username;
    @Column(name = "method_name")
    private String methodName;
    @Column(name = "aplication_name")
    private String aplicationName;


    public LogApplicationEntiy() {
    }

    public LogApplicationEntiy(String id, String machine_name, String level1, String message, String exception, String payload, String callSite, String action, String username, String methodName, String aplicationName) {
        this.id = id;
        this.machine_name = machine_name;
        this.level1 = level1;
        this.message = message;
        this.exception = exception;
        this.payload = payload;
        this.callSite = callSite;
        this.action = action;
        this.username = username;
        this.methodName = methodName;
        this.aplicationName = aplicationName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMachine_name() {
        return machine_name;
    }

    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getCallSite() {
        return callSite;
    }

    public void setCallSite(String callSite) {
        this.callSite = callSite;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getAplicationName() {
        return aplicationName;
    }

    public void setAplicationName(String aplicationName) {
        this.aplicationName = aplicationName;
    }
}
