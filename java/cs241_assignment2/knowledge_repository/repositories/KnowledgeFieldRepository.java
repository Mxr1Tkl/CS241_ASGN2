/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cs241_assignment2.knowledge_repository.repositories;

import cs241_assignment2.knowledge_repository.models.KnowledgeField;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Issac
 */
public interface KnowledgeFieldRepository extends JpaRepository<KnowledgeField, Long>{
    List<KnowledgeField> findByParentFieldIsNull();
    List<KnowledgeField> findByParentField_FieldId(Long parentId);
}
