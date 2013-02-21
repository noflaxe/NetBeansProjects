/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author noflaxe
 */
public class Instructor {
    private int id;
    private String name;
    private String surname;
    private String speciality;
    private User user;
    
    public Instructor(int id, String name, String surname, String speciality,User user) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
        this.user = user;
    }
    
    public Instructor(){
    }

    public User getUser(){
        return user;
    }
    
    public void setUser(User user){
    this.user = user;    
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 83 * hash + (this.surname != null ? this.surname.hashCode() : 0);
        hash = 83 * hash + (this.speciality != null ? this.speciality.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Instructor other = (Instructor) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.surname == null) ? (other.surname != null) : !this.surname.equals(other.surname)) {
            return false;
        }
        if ((this.speciality == null) ? (other.speciality != null) : !this.speciality.equals(other.speciality)) {
            return false;
        }
        return true;
    }
    
}
