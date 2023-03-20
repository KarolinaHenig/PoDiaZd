package pl.karolinahenig.podiazd.locality


import javax.persistence.*

@Entity
class Voivodeship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id
    @Column(unique = true)
    private String voivodeshipName

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getVoivodeshipName() {
        return voivodeshipName
    }

    void setVoivodeshipName(String voivodeshipName) {
        this.voivodeshipName = voivodeshipName
    }
}
