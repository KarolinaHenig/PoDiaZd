package pl.karolinahenig.podiazd.locality

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.*

@Entity
class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id
    @Column
    private String cityName

    @ManyToOne
    @JsonIgnore
    private County county

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getCityName() {
        return cityName
    }

    void setCityName(String cityName) {
        this.cityName = cityName
    }

    County getCounty() {
        return county
    }

    void setCounty(County county) {
        this.county = county
    }
}
