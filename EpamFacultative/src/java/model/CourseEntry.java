/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author noflaxe
 */
public class CourseEntry {
    private int id;
    private Course course;
    private Student student;
    private GradeEnum grade;

    public CourseEntry(Course course, Student student, GradeEnum grade, int id) {
        this.course = course;
        this.student = student;
        this.grade = grade;
        this.id = id;
    }
    public CourseEntry(){
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public GradeEnum getGrade() {
        return grade;
    }

    public void setGrade(GradeEnum grade) {
        this.grade = grade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.course != null ? this.course.hashCode() : 0);
        hash = 53 * hash + (this.student != null ? this.student.hashCode() : 0);
        hash = 53 * hash + (this.grade != null ? this.grade.hashCode() : 0);
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
        final CourseEntry other = (CourseEntry) obj;
        if (this.course != other.course && (this.course == null || !this.course.equals(other.course))) {
            return false;
        }
        if (this.student != other.student && (this.student == null || !this.student.equals(other.student))) {
            return false;
        }
        if (this.grade != other.grade) {
            return false;
        }
        return true;
    }
    
    
}
