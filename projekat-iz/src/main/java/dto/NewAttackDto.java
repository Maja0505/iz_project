package dto;

public class NewAttackDto {

    private String name;
    private String description;
    private String domain;
    private String typical_severity;
    private String likelihood_of_attack;
    private String mitigations;
    private String weaknesses;
    private String prerequisites;


    public NewAttackDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTypical_severity() {
        return typical_severity;
    }

    public void setTypical_severity(String typical_severity) {
        this.typical_severity = typical_severity;
    }

    public String getLikelihood_of_attack() {
        return likelihood_of_attack;
    }

    public void setLikelihood_of_attack(String likelihood_of_attack) {
        this.likelihood_of_attack = likelihood_of_attack;
    }

    public String getMitigations() {
        return mitigations;
    }

    public void setMitigations(String mitigations) {
        this.mitigations = mitigations;
    }

    public String getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }
}
