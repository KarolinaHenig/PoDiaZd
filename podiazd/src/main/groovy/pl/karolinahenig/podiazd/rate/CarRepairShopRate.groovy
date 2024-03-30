package pl.karolinahenig.podiazd.rate

import pl.karolinahenig.podiazd.appuser.AppUser
import pl.karolinahenig.podiazd.car.*
import pl.karolinahenig.podiazd.locality.CarRepairShop
import pl.karolinahenig.podiazd.malfunction.Malfunction

import javax.persistence.*

@Entity
class CarRepairShopRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id

    @ManyToOne
    private AppUser appUser

    @ManyToOne
    private CarRepairShop carRepairShop

    @ManyToOne
    private Brand brand

    @ManyToOne
    private Model model

    @ManyToOne
    private Generation generation

    @ManyToOne
    private BodyType bodyType

    @ManyToOne
    private Modification modification

    @ManyToOne
    private Malfunction malfunction

    private Float rate

    private String opinion

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    AppUser getAppUser() {
        return appUser
    }

    void setAppUser(AppUser appUser) {
        this.appUser = appUser
    }

    CarRepairShop getCarRepairShop() {
        return carRepairShop
    }

    void setCarRepairShop(CarRepairShop carRepairShop) {
        this.carRepairShop = carRepairShop
    }

    Brand getBrand() {
        return brand
    }

    void setBrand(Brand brand) {
        this.brand = brand
    }

    Model getModel() {
        return model
    }

    void setModel(Model model) {
        this.model = model
    }

    Generation getGeneration() {
        return generation
    }

    void setGeneration(Generation generation) {
        this.generation = generation
    }

    BodyType getBodyType() {
        return bodyType
    }

    void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType
    }

    Modification getModification() {
        return modification
    }

    void setModification(Modification modification) {
        this.modification = modification
    }

    Malfunction getMalfunction() {
        return malfunction
    }

    void setMalfunction(Malfunction malfunction) {
        this.malfunction = malfunction
    }

    Float getRate() {
        return rate
    }

    void setRate(Float rate) {
        this.rate = rate
    }

    String getOpinion() {
        return opinion
    }

    void setOpinion(String opinion) {
        this.opinion = opinion
    }
}
