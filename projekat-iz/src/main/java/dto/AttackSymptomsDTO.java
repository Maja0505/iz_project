package dto;

public class AttackSymptomsDTO {

    private boolean alteredDocumentation;
    private boolean errorsInSoftware;
    private boolean suspiciousDataModifications;
    private boolean recentlyReceivedUpdates;
    private boolean recentlyUsedRemovableMedia;
    private String typicalSeverity;
    private boolean denialOfService;
    private boolean suspiciousCodeChanges;
    private boolean softwareInDevelopmentPhase;
    private boolean softwareInDeploymentPhase;
    private boolean unauthenticatedPhysicalAccessRecently;
    private String type;

    public AttackSymptomsDTO() {}

    public AttackSymptomsDTO(boolean alteredDocumentation, boolean errorsInSoftware, boolean suspiciousDataModifications, boolean recentlyReceivedUpdates, boolean recentlyUsedRemovableMedia, String typicalSeverity, boolean denialOfService, boolean suspiciousCodeChanges, boolean softwareInDevelopmentPhase, boolean softwareInDeploymentPhase, boolean unauthenticatedPhysicalAccessRecently) {
        this.alteredDocumentation = alteredDocumentation;
        this.errorsInSoftware = errorsInSoftware;
        this.suspiciousDataModifications = suspiciousDataModifications;
        this.recentlyReceivedUpdates = recentlyReceivedUpdates;
        this.recentlyUsedRemovableMedia = recentlyUsedRemovableMedia;
        this.typicalSeverity = typicalSeverity;
        this.denialOfService = denialOfService;
        this.suspiciousCodeChanges = suspiciousCodeChanges;
        this.softwareInDevelopmentPhase = softwareInDevelopmentPhase;
        this.softwareInDeploymentPhase = softwareInDeploymentPhase;
        this.unauthenticatedPhysicalAccessRecently = unauthenticatedPhysicalAccessRecently;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAlteredDocumentation() {
        return alteredDocumentation;
    }

    public void setAlteredDocumentation(boolean alteredDocumentation) {
        this.alteredDocumentation = alteredDocumentation;
    }

    public boolean isErrorsInSoftware() {
        return errorsInSoftware;
    }

    public void setErrorsInSoftware(boolean errorsInSoftware) {
        this.errorsInSoftware = errorsInSoftware;
    }

    public boolean isSuspiciousDataModifications() {
        return suspiciousDataModifications;
    }

    public void setSuspiciousDataModifications(boolean suspiciousDataModifications) {
        this.suspiciousDataModifications = suspiciousDataModifications;
    }

    public boolean isRecentlyReceivedUpdates() {
        return recentlyReceivedUpdates;
    }

    public void setRecentlyReceivedUpdates(boolean recentlyReceivedUpdates) {
        this.recentlyReceivedUpdates = recentlyReceivedUpdates;
    }

    public boolean isRecentlyUsedRemovableMedia() {
        return recentlyUsedRemovableMedia;
    }

    public void setRecentlyUsedRemovableMedia(boolean recentlyUsedRemovableMedia) {
        this.recentlyUsedRemovableMedia = recentlyUsedRemovableMedia;
    }

    public String getTypicalSeverity() {
        return typicalSeverity;
    }

    public void setTypicalSeverity(String typicalSeverity) {
        this.typicalSeverity = typicalSeverity;
    }

    public boolean isDenialOfService() {
        return denialOfService;
    }

    public void setDenialOfService(boolean denialOfService) {
        this.denialOfService = denialOfService;
    }

    public boolean isSuspiciousCodeChanges() {
        return suspiciousCodeChanges;
    }

    public void setSuspiciousCodeChanges(boolean suspiciousCodeChanges) {
        this.suspiciousCodeChanges = suspiciousCodeChanges;
    }

    public boolean isSoftwareInDevelopmentPhase() {
        return softwareInDevelopmentPhase;
    }

    public void setSoftwareInDevelopmentPhase(boolean softwareInDevelopmentPhase) {
        this.softwareInDevelopmentPhase = softwareInDevelopmentPhase;
    }

    public boolean isSoftwareInDeploymentPhase() {
        return softwareInDeploymentPhase;
    }

    public void setSoftwareInDeploymentPhase(boolean softwareInDeploymentPhase) {
        this.softwareInDeploymentPhase = softwareInDeploymentPhase;
    }

    public boolean isUnauthenticatedPhysicalAccessRecently() {
        return unauthenticatedPhysicalAccessRecently;
    }

    public void setUnauthenticatedPhysicalAccessRecently(boolean unauthenticatedPhysicalAccessRecently) {
        this.unauthenticatedPhysicalAccessRecently = unauthenticatedPhysicalAccessRecently;
    }
}