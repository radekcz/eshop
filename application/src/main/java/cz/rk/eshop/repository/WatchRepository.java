package cz.rk.eshop.repository;

import cz.rk.eshop.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Watch repository
 */
public interface WatchRepository extends JpaRepository<Watch, Long> {
}
