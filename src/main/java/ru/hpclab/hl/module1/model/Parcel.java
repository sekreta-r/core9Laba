package ru.hpclab.hl.module1.model;

public class Parcel {
    private Long id;
    private double weight;
    private String dimensions;
    private String destinationAddress;

    public Parcel() {
    }

    public Parcel(Long id, double weight, String dimensions, String destinationAddress) {
        this.id = id;
        this.weight = weight;
        this.dimensions = dimensions;
        this.destinationAddress = destinationAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", weight=" + weight +
                ", dimensions='" + dimensions + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                '}';
    }
}
