package de.hda.fbi.db2.stud.entity;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;

/**
 * Question Class.
 * @version 0.1.1
 * @since 0.1.0
 * @author A. Machill
 * @author M. Stuber
 */

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int qId;
    private String question;
    private int solution;
    @ManyToOne
    private Category qcategory;
    @ManyToMany(mappedBy = "aquestion")
    private Collection<Answer> qanswer;

    // Constructors
    public Question() {

    }
    public Question(String question, int solution) {
        this.question = question;
        this.solution = solution;
    }

    // Getter & Setter
    public int getqId() {
        return qId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getSolution() {
        return solution;
    }

    public void setSolution(int solution) {
        this.solution = solution;
    }

    // Equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question = (Question) o;
        return qId == question.qId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(qId);
    }

    // String representation
    @Override
    public String toString() {
        return "Question{" +
                "qId=" + qId +
                ", question='" + question + '\'' +
                ", solution=" + solution +
                '}';
    }
}
