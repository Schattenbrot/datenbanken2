package de.hda.fbi.db2.stud.entity;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.*;

/**
 * A Category.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cId;
    private String nameOfCategory;
    @OneToMany
    @JoinTable(
            name = "OneToMany",
            joinColumns = {@JoinColumn(name = "fk_Cid")},
            inverseJoinColumns = {@JoinColumn(name = "fk_Qid")},
            uniqueConstraints = {@UniqueConstraint(name = "c_oneToMany", columnNames = {"fk_Qid"})}
    )
    private Collection<Question> cquestion;

    //Constructor
    public Category() {
    }

    public Category(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }


    //Getter & Setter
    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public void setNameOfCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public int getCategoryIdId() {
        return cId;
    }

    //Equals & hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Category category = (Category) o;
        return cId == category.cId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cId);
    }

    //String representation
    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + cId +
                ", nameOfCategory='" + nameOfCategory + '\'' +
                '}';
    }
}
