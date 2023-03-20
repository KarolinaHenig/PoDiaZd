package pl.karolinahenig.podiazd.car

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GenerationRepository extends CrudRepository<Generation, Long> {
    List<Generation> findAllByModelId(long modelId)
}