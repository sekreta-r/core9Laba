package ru.hpclab.hl.module1.model;

import java.util.ArrayList;
import java.util.List;

public class Courier {
    private Long id;
    private String fullName;
    private String transport;
    private String workZone;
    private List<Delivery> deliveries = new ArrayList<>();

    public Courier(Long id, String fullName, String transport, String workZone, List<Delivery> deliveries) {
        this.id = id;
        this.fullName = fullName;
        this.transport = transport;
        this.workZone = workZone;
        this.deliveries = deliveries != null ? deliveries : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getWorkZone() {
        return workZone;
    }

    public void setWorkZone(String workZone) {
        this.workZone = workZone;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries != null ? deliveries : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", transport='" + transport + '\'' +
                ", workZone='" + workZone + '\'' +
                ", deliveries=" + deliveries +
                '}';
    }
}
