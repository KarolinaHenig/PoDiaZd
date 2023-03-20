package pl.karolinahenig.podiazd.car

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductionYearRepository extends CrudRepository<ProductionYear, Long> {
    List<ProductionYear> findAllByEngineCapacityId(long engineCapacityId)
}