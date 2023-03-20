package pl.karolinahenig.podiazd.locality

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class County {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id
    @Column
    private String countyName

    @ManyToOne
    @JsonIgnore
    private Voivodeship voivodeship

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getCountyName() {
        return countyName
    }

    void setCountyName(String countyName) {
        this.countyName = countyName
    }

    Voivodeship getVoivodeship() {
        return voivodeship
    }

    void setVoivodeship(Voivodeship voivodeship) {
        this.voivodeship = voivodeship
    }
}
