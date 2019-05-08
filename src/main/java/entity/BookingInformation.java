/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mich5
 */
@Entity
@Table(name = "bookinginformation")
@NamedQueries({
    @NamedQuery(name = "BookingInformation.findAll", query = "SELECT b FROM BookingInformation b")
    , @NamedQuery(name = "BookingInformation.findById", query = "SELECT b FROM BookingInformation b WHERE b.id = :id")
    , @NamedQuery(name = "BookingInformation.findByStartPeriod", query = "SELECT b FROM BookingInformation b WHERE b.startPeriod = :startPeriod")
    , @NamedQuery(name = "BookingInformation.findByEndPeriod", query = "SELECT b FROM BookingInformation b WHERE b.endPeriod = :endPeriod")
    , @NamedQuery(name = "BookingInformation.findByCreated", query = "SELECT b FROM BookingInformation b WHERE b.created = :created")
    , @NamedQuery(name = "BookingInformation.findByPrice", query = "SELECT b FROM BookingInformation b WHERE b.price = :price")})
public class BookingInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startPeriod", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startPeriod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "endPeriod", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endPeriod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date created;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price", nullable = false)
    private double price;
    @JoinColumn(name = "car_regno", referencedColumnName = "regno", nullable = false)
    @ManyToOne(optional = false)
    private Car car;

    public BookingInformation() {
    }

    public BookingInformation(Integer id) {
        this.id = id;
    }

    public BookingInformation(Integer id, Date startPeriod, Date endPeriod, Date created, double price) {
        this.id = id;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.created = created;
        this.price = price;
    }

    public BookingInformation(Date startPeriod, Date endPeriod, Date created, double price) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.created = created;
        this.price = price;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
        car.getBookingInformationCollection().add(this);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookingInformation)) {
            return false;
        }
        BookingInformation other = (BookingInformation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BookingInformation[ id=" + id + " ]";
    }

}
