package pl.karolinahenig.podiazd.car

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.*

@Entity
class Modification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id
    private String modificationName

    @ManyToOne
    @JsonIgnore
    private Generation generation

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

    String getModificationName() {
        return modificationName
    }

    void setModificationName(String modificationName) {
        this.modificationName = modificationName
    }

    Generation getGeneration() {
        return generation
    }

    void setGeneration(Generation generation) {
        this.generation = generation
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
