package br.fipppay.Model.repository;

import br.fipppay.Model.entities.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SystemUserRepository extends JpaRepository<SystemUser,Long>
{
    @Query(value="SELECT * FROM system_user WHERE user_email = :email",nativeQuery = true)
    public SystemUser getByEmail (@Param("email") String email);
}
