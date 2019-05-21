package co.premier.zuul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.premier.zuul.entities.LogEntity;

@Repository
public interface ILogRepository extends JpaRepository<LogEntity, Long>, CrudRepository<LogEntity, Long>{

}
