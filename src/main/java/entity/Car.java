package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mich5
 */
@Entity
@Table(name = "car", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"regno"})})
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c")
    , @NamedQuery(name = "Car.findByRegno", query = "SELECT c FROM Car c WHERE c.regno = :regno")
    , @NamedQuery(name = "Car.findByPrice", query = "SELECT c FROM Car c WHERE c.price = :price")
    , @NamedQuery(name = "Car.findByManufactor", query = "SELECT c FROM Car c WHERE c.manufactor = :manufactor")
    , @NamedQuery(name = "Car.findByModel", query = "SELECT c FROM Car c WHERE c.model = :model")
    , @NamedQuery(name = "Car.findByType", query = "SELECT c FROM Car c WHERE c.type = :type")
    , @NamedQuery(name = "Car.findByReleaseYear", query = "SELECT c FROM Car c WHERE c.releaseYear = :releaseYear")
    , @NamedQuery(name = "Car.findByDrivingDist", query = "SELECT c FROM Car c WHERE c.drivingDist = :drivingDist")
    , @NamedQuery(name = "Car.findBySeats", query = "SELECT c FROM Car c WHERE c.seats = :seats")
    , @NamedQuery(name = "Car.findByDrive", query = "SELECT c FROM Car c WHERE c.drive = :drive")
    , @NamedQuery(name = "Car.findByFuelType", query = "SELECT c FROM Car c WHERE c.fuelType = :fuelType")
    , @NamedQuery(name = "Car.findByLongitude", query = "SELECT c FROM Car c WHERE c.longitude = :longitude")
    , @NamedQuery(name = "Car.findByLatitude", query = "SELECT c FROM Car c WHERE c.latitude = :latitude")
    , @NamedQuery(name = "Car.findByAddress", query = "SELECT c FROM Car c WHERE c.address = :address")})
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "regno", nullable = false, length = 45)
    private String regno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price", nullable = false)
    private double price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "manufactor", nullable = false, length = 45)
    private String manufactor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "model", nullable = false, length = 45)
    private String model;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type", nullable = false, length = 45)
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "releaseYear", nullable = false)
    private int releaseYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "drivingDist", nullable = false)
    private int drivingDist;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seats", nullable = false)
    private int seats;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "drive", nullable = false, length = 45)
    private String drive;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fuelType", nullable = false, length = 45)
    private String fuelType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "longitude", nullable = false, length = 150)
    private String longitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "latitude", nullable = false, length = 150)
    private String latitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "address", nullable = false, length = 45)
    private String address;
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Country country;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car")
    private Collection<BookingInformation> bookingInformationCollection;

    public Car() {
    }

    public Car(String regno) {
        this.regno = regno;
    }

    public Car(String regno, double price, String manufactor, String model, String type, int releaseYear, int drivingDist, int seats, String drive, String fuelType, String longitude, String latitude, String address) {
        this.regno = regno;
        this.price = price;
        this.manufactor = manufactor;
        this.model = model;
        this.type = type;
        this.releaseYear = releaseYear;
        this.drivingDist = drivingDist;
        this.seats = seats;
        this.drive = drive;
        this.fuelType = fuelType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDrivingDist() {
        return drivingDist;
    }

    public void setDrivingDist(int drivingDist) {
        this.drivingDist = drivingDist;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Collection<BookingInformation> getBookingInformationCollection() {
        if (bookingInformationCollection == null) {
            bookingInformationCollection = new ArrayList();
        }
        return bookingInformationCollection;
    }

    public void setBookingInformationCollection(Collection<BookingInformation> bookingInformationCollection) {
        this.bookingInformationCollection = bookingInformationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (regno != null ? regno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.regno == null && other.regno != null) || (this.regno != null && !this.regno.equals(other.regno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Car[ regno=" + regno + " ]";
    }

}
