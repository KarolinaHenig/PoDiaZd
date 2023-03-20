package pl.karolinahenig.podiazd.locality

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CountyRepository extends CrudRepository<County, Long> {
    List<County> findAllByVoivodeshipId(long voivodeshipId)
}