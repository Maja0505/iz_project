package model;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class AttackCaseDescription implements CaseComponent {
    private String name;
    private boolean alteredDocumentation;
    private boolean errorsInSoftware;
    private boolean suspiciousDataModifications;
    private boolean recentlyReceivedUpdates;
    private boolean recentlyUsedRemovableMedia;
    private String likelihoodOfAttack;
    private String typicalSeverity;
    private boolean denialOfService;
    private boolean suspiciousCodeChanges;
    private boolean softwareInDevelopmentPhase;
    private boolean softwareInDeploymentPhase;
    private boolean unauthenticatedPhysicalAccessRecently;
    private String type;
    private String description;
    private String mitigations;

    public AttackCaseDescription() {}

    public AttackCaseDescription(String name, boolean alteredDocumentation, boolean errorsInSoftware, boolean suspiciousDataModifications, boolean recentlyReceivedUpdates, boolean recentlyUsedRemovableMedia, String likelihoodOfAttack, String typicalSeverity, boolean denialOfService, boolean suspiciousCodeChanges, boolean softwareInDevelopmentPhase, boolean softwareInDeploymentPhase, boolean unauthenticatedPhysicalAccessRecently, String type, String description, String mitigations) {
        this.name = name;
        this.alteredDocumentation = alteredDocumentation;
        this.errorsInSoftware = errorsInSoftware;
        this.suspiciousDataModifications = suspiciousDataModifications;
        this.recentlyReceivedUpdates = recentlyReceivedUpdates;
        this.recentlyUsedRemovableMedia = recentlyUsedRemovableMedia;
        this.likelihoodOfAttack = likelihoodOfAttack;
        this.typicalSeverity = typicalSeverity;
        this.denialOfService = denialOfService;
        this.suspiciousCodeChanges = suspiciousCodeChanges;
        this.softwareInDevelopmentPhase = softwareInDevelopmentPhase;
        this.softwareInDeploymentPhase = softwareInDeploymentPhase;
        this.unauthenticatedPhysicalAccessRecently = unauthenticatedPhysicalAccessRecently;
        this.type = type;
        this.description = description;
        this.mitigations = mitigations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String isLikelihoodOfAttack() {
        return likelihoodOfAttack;
    }

    public void setLikelihoodOfAttack(String likelihoodOfAttack) {
        this.likelihoodOfAttack = likelihoodOfAttack;
    }

    public String isTypicalSeverity() {
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
                ", alteredDocumentation=" + alteredDocumentation +
                ", errorsInSoftware=" + errorsInSoftware +
                ", suspiciousDataModifications=" + suspiciousDataModifications +
                ", recentlyReceivedUpdates=" + recentlyReceivedUpdates +
                ", recentlyUsedRemovableMedia=" + recentlyUsedRemovableMedia +
                ", likelihoodOfAttack=" + likelihoodOfAttack +
                ", typicalSeverity=" + typicalSeverity +
                ", denialOfService=" + denialOfService +
                ", suspiciousCodeChanges=" + suspiciousCodeChanges +
                ", softwareInDevelopmentPhase=" + softwareInDevelopmentPhase +
                ", softwareInDeploymentPhase=" + softwareInDeploymentPhase +
                ", unauthenticatedPhysicalAccessRecently=" + unauthenticatedPhysicalAccessRecently +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", mitigations='" + mitigations + '\'' +
                '}';
    }

    @Override
    public Attribute getIdAttribute() {
        return null;
    }
}
