package pl.karolinahenig.podiazd.locality

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class CarRepairShop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id

    @Column
    private String carRepairShopName

    @ManyToOne
    private Voivodeship voivodeship

    @ManyToOne
    private County county

    @ManyToOne
    private City city

    @Column
    private String street

    @Column
    private String houseNumber

    @Column
    private String phoneNumber

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getCarRepairShopName() {
        return carRepairShopName
    }

    void setCarRepairShopName(String carRepairShopName) {
        this.carRepairShopName = carRepairShopName
    }

    Voivodeship getVoivodeship() {
        return voivodeship
    }

    void setVoivodeship(Voivodeship voivodeship) {
        this.voivodeship = voivodeship
    }

    County getCounty() {
        return county
    }

    void setCounty(County county) {
        this.county = county
    }

    City getCity() {
        return city
    }

    void setCity(City city) {
        this.city = city
    }

    String getStreet() {
        return street
    }

    void setStreet(String street) {
        this.street = street
    }

    String getHouseNumber() {
        return houseNumber
    }

    void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber
    }

    String getPhoneNumber() {
        return phoneNumber
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }
    @Column
    private String email

}
