/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author noflaxe
 */
public enum GradeEnum {

    A,
    B,
    C,
    D,
    E,
    F,
    IN_PROGRESS;
    public static GradeEnum getGradeFromString(String status) {
        for (GradeEnum enumStatus : values()) {
            if (enumStatus.toString().equalsIgnoreCase(status)) {
                return enumStatus;
            }
        }
        return null;
    }
    public String getGradeEnum(){
    return "hey test";
    }
    
}

