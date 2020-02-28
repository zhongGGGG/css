package com.t248.zjl.thymeleaf.Respository;


import com.t248.zjl.thymeleaf.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserRespository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    public User findByUsrId(Long userId);
    public User findByUsrName(String usrName);
//    public List<User> findAll();

   public Page<User> findAll(Specification<User> specification, Pageable pageable);

   public User findUserByUsrName(String usrName);

}
