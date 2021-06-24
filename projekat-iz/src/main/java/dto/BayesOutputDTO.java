package dto;

public class BayesOutputDTO {

    private String attack;
    private float probability;

    public BayesOutputDTO(String attack, float probability) {
        this.attack = attack;
        this.probability = probability;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }
}
