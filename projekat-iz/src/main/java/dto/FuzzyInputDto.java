package dto;

public class FuzzyInputDto {

    private Integer access_vector;
    private Integer access_complexity;
    private Integer authentication;
    private Integer confidentiality;
    private Integer integrity;
    private Integer availability;

    public FuzzyInputDto() {
    }

    public Integer getAccess_vector() {
        return access_vector;
    }

    public void setAccess_vector(Integer access_vector) {
        this.access_vector = access_vector;
    }

    public Integer getAccess_complexity() {
        return access_complexity;
    }

    public void setAccess_complexity(Integer access_complexity) {
        this.access_complexity = access_complexity;
    }

    public Integer getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Integer authentication) {
        this.authentication = authentication;
    }

    public Integer getConfidentiality() {
        return confidentiality;
    }

    public void setConfidentiality(Integer confidentiality) {
        this.confidentiality = confidentiality;
    }

    public Integer getIntegrity() {
        return integrity;
    }

    public void setIntegrity(Integer integrity) {
        this.integrity = integrity;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }
}
