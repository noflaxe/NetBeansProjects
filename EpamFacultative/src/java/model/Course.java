/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



/**
 *
 * @author noflaxe
 */
public class Course {
    private int id;
    private String name;
    private int hours;
    private Instructor instructor;

    public Course(int id, String name, int hours, Instructor instructor) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.instructor = instructor;
    }

     public Course(){
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        hash = 83 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 83 * hash + this.hours;
        hash = 83 * hash + (this.instructor != null ? this.instructor.hashCode() : 0);
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
        final Course other = (Course) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.hours != other.hours) {
            return false;
        }
        if (this.instructor != other.instructor && (this.instructor == null || !this.instructor.equals(other.instructor))) {
            return false;
        }
        return true;
    }
    
    
}
