package dto;

import entity.BookingInformation;
import java.util.Date;

public class BookingInformationDTO {

    private Integer id;
    private Date startPeriod;
    private Date endPeriod;
    private Date created;
    private double price;
    private CarDTO car;

    public BookingInformationDTO(BookingInformation bi) {
        this.startPeriod = bi.getStartPeriod();
        this.endPeriod = bi.getEndPeriod();
        this.created = bi.getCreated();
        this.price = bi.getPrice();
        this.car = new CarDTO(bi.getCar());
    }

    public BookingInformationDTO(Date startPeriod, Date endPeriod, Date created, double price, CarDTO car) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.created = created;
        this.price = price;
        this.car = car;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Date startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Date endPeriod) {
        this.endPeriod = endPeriod;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

}
