package pl.karolinahenig.podiazd.locality

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class CarRepairShop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id

    @Column(unique = true)
    private String carRepairShopName

    @ManyToOne
    @JsonIgnore
    private Voivodeship voivodeship

    @ManyToOne
    @JsonIgnore
    private County county

    @ManyToOne
    @JsonIgnore
    private City city

    @Column
    private String street

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
}
