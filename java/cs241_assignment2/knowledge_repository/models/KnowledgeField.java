/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="knowledge_field")
public class KnowledgeField {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldId;
    
    private String fieldName;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "parent_field_id")
    private KnowledgeField parentField;
    
    @OneToMany(mappedBy = "parentField", cascade = CascadeType.ALL)
    private List<KnowledgeField> subFields = new ArrayList<>();

    public Long getFieldId() { return fieldId; }

    public void setFieldId(Long fieldId) { this.fieldId = fieldId; }

    public String getFieldName() { return fieldName; }

    public void setFieldName(String fieldName) { this.fieldName = fieldName; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public KnowledgeField getParentField() { return parentField; }

    public void setParentField(KnowledgeField parentField) { this.parentField = parentField; }

    public List<KnowledgeField> getSubFields() { return subFields; }

    public void setSubFields(List<KnowledgeField> subFields) { this.subFields = subFields; }
    
}
