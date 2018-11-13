package de.hda.fbi.db2.stud.entity;

import java.util.Objects;
import javax.persistence.*;

/**
 * Player Class.
 * @version 0.1.1
 * @since 0.1.0
 * @author A. Machill
 * @author M. Stuber
 */

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pId;
    private String  pName;

    //Constructos
    public Player() {

    }

    public Player(String pName) {
        this.pName = pName;
    }

    // Getter & Setter
    public int getpId() {
        return pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    // equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return pId == player.pId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pId);
    }

    // String representation
    @Override
    public String toString() {
        return "Player{" +
                "pId=" + pId +
                ", pName='" + pName + '\'' +
                '}';
    }
}
