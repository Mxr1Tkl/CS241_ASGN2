/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cs241_assignment2.knowledge_repository.repositories;

import cs241_assignment2.knowledge_repository.models.UserRole;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
    @Modifying
    @Query("DELETE FROM UserRole ur WHERE ur.user.id = :uerId")
    void deleteByUserId(@Param("userId") Long userId);
}
