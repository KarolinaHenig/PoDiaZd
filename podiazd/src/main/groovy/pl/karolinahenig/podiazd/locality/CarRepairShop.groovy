package pl.karolinahenig.podiazd.locality

import javax.persistence.*

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

    @Column
    private String email

    @Column
    private String averageRate

    @Column
    private String averageRateCar

    @Column
    private String averageRateMalfunction

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

    String getAverageRate() {
        return averageRate
    }

    void setAverageRate(String averageRate) {
        this.averageRate = averageRate
    }

    String getAverageRateCar() {
        return averageRateCar
    }

    void setAverageRateCar(String averageRateCar) {
        this.averageRateCar = averageRateCar
    }

    String getAverageRateMalfunction() {
        return averageRateMalfunction
    }

    void setAverageRateMalfunction(String averageRateMalfunction) {
        this.averageRateMalfunction = averageRateMalfunction
    }

}
