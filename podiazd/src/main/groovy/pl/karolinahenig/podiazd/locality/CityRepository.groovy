package pl.karolinahenig.podiazd.locality

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CityRepository extends CrudRepository<City, Long> {
    List<City> findAllByCountyId(long countyId)
}