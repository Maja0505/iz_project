package model;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class AttackCaseDescription implements CaseComponent {

    private String name;
    private String prerequisities;
    private String mitigations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrerequisities() {
        return prerequisities;
    }

    public void setPrerequisities(String prerequisities) {
        this.prerequisities = prerequisities;
    }

    public String getMitigations() {
        return mitigations;
    }

    public void setMitigations(String mitigations) {
        this.mitigations = mitigations;
    }

    @Override
    public String toString() {
        return "AttackCaseDescription{" +
                "name='" + name + '\'' +
                ", prerequisities='" + prerequisities + '\'' +
                ", mitigations='" + mitigations + '\'' +
                '}';
    }

    @Override
    public Attribute getIdAttribute() {
        return new Attribute("id",this.getClass());
    }
}
