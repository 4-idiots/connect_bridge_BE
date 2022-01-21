package com.idiotdb.loginpage.register.repository;

import com.idiotdb.loginpage.register.data.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<RegisterEntity, Long>{
}
