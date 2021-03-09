package cz.rk.eshop.repository;

import cz.rk.eshop.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Watch repository
 */
@Repository
public interface WatchRepository extends JpaRepository<Watch, Long> {
}
