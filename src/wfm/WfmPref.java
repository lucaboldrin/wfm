/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wfm;

import java.util.ArrayList;

/**
 *
 * @author Luca Boldrin <luca.boldrin@telecomitalia.it>
 */
public class WfmPref {
    
    private String name;
    private final ArrayList<WrPref> jbList;
    
    public WfmPref() {
        jbList = new ArrayList<>();
    }
    
    public void add(String Name, String Tuning) {
        jbList.add(new WrPref(name,Tuning));
    }
    
    public WrPref getWrPref(int index) {
        return jbList.get(index);
    } 
    
    public static class WrPref {
        
        private final String name;
        private final String tuning;
        
        public WrPref(String Name, String Tuning) {
            this.name = Name;
            this.tuning = Tuning;
        }
        
        public String getName() {
            return name;
        }
        
        public String getTuning() {
            return tuning;
        }
    }
}
