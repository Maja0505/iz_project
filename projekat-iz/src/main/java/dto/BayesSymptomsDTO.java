package dto;

public class BayesSymptomsDTO {

    private boolean Suspicious_code_changes;
    private boolean Suspicious_data_modifications;
    private int Company_size;
    private boolean Software_in_development_phase;
    private boolean Software_in_deployment_phase;
    private boolean Using_open_source_or_3rd_party_components;
    private boolean Unauthorized_physical_access_occured_recently;
    private boolean Recently_received_updates;
    private boolean Recently_used_removable_media;
    private boolean Unefectivness_or_errors_in_software;
    private boolean Denial_of_service;
    private boolean Altered_documentation;

    public BayesSymptomsDTO() {}

    public BayesSymptomsDTO(boolean Suspicious_code_changes, boolean Suspicious_data_modifications, int Company_size, boolean Software_in_development_phase, boolean Software_in_deployment_phase, boolean Using_open_source_or_3rd_party_components, boolean Unauthorized_physical_access_occured_recently, boolean Recently_received_updates, boolean Recently_used_removable_media, boolean Unefectivness_or_errors_in_software, boolean Denial_of_service, boolean Altered_documentation) {
        this.Suspicious_code_changes = Suspicious_code_changes;
        this.Suspicious_data_modifications = Suspicious_data_modifications;
        this.Company_size = Company_size;
        this.Software_in_development_phase = Software_in_development_phase;
        this.Software_in_deployment_phase = Software_in_deployment_phase;
        this.Using_open_source_or_3rd_party_components = Using_open_source_or_3rd_party_components;
        this.Unauthorized_physical_access_occured_recently = Unauthorized_physical_access_occured_recently;
        this.Recently_received_updates = Recently_received_updates;
        this.Recently_used_removable_media = Recently_used_removable_media;
        this.Unefectivness_or_errors_in_software = Unefectivness_or_errors_in_software;
        this.Denial_of_service = Denial_of_service;
        this.Altered_documentation = Altered_documentation;
    }

    public boolean getSuspicious_code_changes() {
        return Suspicious_code_changes;
    }

    public void setSuspicious_code_changes(boolean suspicious_code_changes) {
        Suspicious_code_changes = suspicious_code_changes;
    }

    public boolean getSuspicious_data_modifications() {
        return Suspicious_data_modifications;
    }

    public void setSuspicious_data_modifications(boolean suspicious_data_modifications) {
        Suspicious_data_modifications = suspicious_data_modifications;
    }

    public int getCompany_size() {
        return Company_size;
    }

    public void setCompany_size(int company_size) {
        Company_size = company_size;
    }

    public boolean getSoftware_in_development_phase() {
        return Software_in_development_phase;
    }

    public void setSoftware_in_development_phase(boolean software_in_development_phase) {
        Software_in_development_phase = software_in_development_phase;
    }

    public boolean getSoftware_in_deployment_phase() {
        return Software_in_deployment_phase;
    }

    public void setSoftware_in_deployment_phase(boolean software_in_deployment_phase) {
        Software_in_deployment_phase = software_in_deployment_phase;
    }

    public boolean getUsing_open_source_or_3rd_party_components() {
        return Using_open_source_or_3rd_party_components;
    }

    public void setUsing_open_source_or_3rd_party_components(boolean using_open_source_or_3rd_party_components) {
        Using_open_source_or_3rd_party_components = using_open_source_or_3rd_party_components;
    }

    public boolean getUnauthorized_physical_access_occured_recently() {
        return Unauthorized_physical_access_occured_recently;
    }

    public void setUnauthorized_physical_access_occured_recently(boolean unauthorized_physical_access_occured_recently) {
        Unauthorized_physical_access_occured_recently = unauthorized_physical_access_occured_recently;
    }

    public boolean getRecently_received_updates() {
        return Recently_received_updates;
    }

    public void setRecently_received_updates(boolean recently_received_updates) {
        Recently_received_updates = recently_received_updates;
    }

    public boolean getRecently_used_removable_media() {
        return Recently_used_removable_media;
    }

    public void setRecently_used_removable_media(boolean recently_used_removable_media) {
        Recently_used_removable_media = recently_used_removable_media;
    }

    public boolean getUnefectivness_or_errors_in_software() {
        return Unefectivness_or_errors_in_software;
    }

    public void setUnefectivness_or_errors_in_software(boolean unefectivness_or_errors_in_software) {
        Unefectivness_or_errors_in_software = unefectivness_or_errors_in_software;
    }

    public boolean getDenial_of_service() {
        return Denial_of_service;
    }

    public void setDenial_of_service(boolean denial_of_service) {
        Denial_of_service = denial_of_service;
    }

    public boolean getAltered_documentation() {
        return Altered_documentation;
    }

    public void setAltered_documentation(boolean altered_documentation) {
        Altered_documentation = altered_documentation;
    }
}

