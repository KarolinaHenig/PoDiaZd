package pl.karolinahenig.podiazd.malfunction

import com.fasterxml.jackson.annotation.JsonIgnore
import pl.karolinahenig.podiazd.car.Brand

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Malfunction {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id

    private  String malfunctionName

    @ManyToOne
    @JsonIgnore
    private Category category

    private String engineType
    private String gearboxType
    private String gearboxSubtype
    private String suspension

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getMalfunctionName() {
        return malfunctionName
    }

    void setMalfunctionName(String malfunctionName) {
        this.malfunctionName = malfunctionName
    }

    Category getCategory() {
        return category
    }

    void setCategory(Category category) {
        this.category = category
    }

    String getEngineType() {
        return engineType
    }

    void setEngineType(String engineType) {
        this.engineType = engineType
    }

    String getGearboxType() {
        return gearboxType
    }

    void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType
    }

    String getGearboxSubtype() {
        return gearboxSubtype
    }

    void setGearboxSubtype(String gearboxSubtype) {
        this.gearboxSubtype = gearboxSubtype
    }

    String getSuspension() {
        return suspension
    }

    void setSuspension(String suspension) {
        this.suspension = suspension
    }

}
