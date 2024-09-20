package straatrakker.leukedingetjes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import straatrakker.leukedingetjes.domain.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
