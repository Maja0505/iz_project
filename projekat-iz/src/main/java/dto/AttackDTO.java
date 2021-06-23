package dto;

public class AttackDTO {
    private String name;
    private String likelihoodOfAttack;
    private String type;
    private String description;
    private double evaluation;

    public AttackDTO() {}

    public AttackDTO(String name, String likelihoodOfAttack, String type, String description) {
        this.name = name;
        this.likelihoodOfAttack = likelihoodOfAttack;
        this.type = type;
        this.description = description;
    }

    public double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLikelihoodOfAttack() {
        return likelihoodOfAttack;
    }

    public void setLikelihoodOfAttack(String likelihoodOfAttack) {
        this.likelihoodOfAttack = likelihoodOfAttack;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
