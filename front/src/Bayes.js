import {
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio,
  Grid,
  NativeSelect,
  InputLabel,
  Button,
} from "@material-ui/core";

import axios from "axios";
import { useState } from "react";

import AttackByBayesAndMitigations from "./AttackByBayesAndMitigations.js";

const Bayes = () => {
  const [alteredDocumentation, setAlteredDocumentation] = useState("false");
  const [openSourceOr3rdPartyComponents, setOpenSourceOr3rdPartyComponents] =
    useState("false");
  const [errorsInSoftware, setErrorsInSoftware] = useState("false");
  const [suspiciousDataModifications, setSuspiciousDataModifications] =
    useState("false");
  const [recentlyReceivedUpdates, setRecentlyReceivedUpdates] =
    useState("false");
  const [recentlyUsedRemovableMedia, setRecentlyUsedRemovableMedia] =
    useState("false");
  const [companySize, setCompanySize] = useState("0");
  const [denialOfService, setDenialOfService] = useState("false");
  const [suspiciousCodeChanges, setSuspiciousCodeChanges] = useState("false");
  const [softwareInDevelopmentPhase, setSoftwareInDevelopmentPhase] =
    useState("false");
  const [softwareInDeploymentPhase, setSoftwareInDeploymentPhase] =
    useState("false");
  const [
    unauthenticatedPhysicalAccessRecently,
    setUnauthenticatedPhysicalAccessRecently,
  ] = useState("false");
  const [attacks, setAttacks] = useState([]);
  const [mitigations1, setMitigations1] = useState([]);
  const [mitigations2, setMitigations2] = useState([]);
  const [mitigations3, setMitigations3] = useState([]);

  const findAttackForSymptoms = () => {
    setAttacks([]);
    setMitigations1([]);
    setMitigations2([]);
    setMitigations3([]);
    axios
      .put("/bayes", {
        suspicious_code_changes:
          suspiciousCodeChanges === "true" ? true : false,
        suspicious_data_modifications:
          suspiciousDataModifications === "true" ? true : false,
        company_size: companySize,
        software_in_development_phase:
          softwareInDevelopmentPhase === "true" ? true : false,
        software_in_deployment_phase:
          softwareInDeploymentPhase === "true" ? true : false,
        using_open_source_or_3rd_party_components:
          openSourceOr3rdPartyComponents === "true" ? true : false,
        unauthorized_physical_access_occured_recently:
          unauthenticatedPhysicalAccessRecently === "true" ? true : false,
        recently_received_updates:
          recentlyReceivedUpdates === "true" ? true : false,
        recently_used_removable_media:
          recentlyUsedRemovableMedia === "true" ? true : false,
        unefectivness_or_errors_in_software:
          errorsInSoftware === "true" ? true : false,
        denial_of_service: denialOfService === "true" ? true : false,
        altered_documentation: alteredDocumentation === "true" ? true : false,
      })
      .then((res) => {
        console.log(res.data);
        setAttacks(res.data);
      });
  };

  return (
    <div>
      <Grid container>
        <Grid item xs={2} />
        <Grid item xs={3} style={{ textAlign: "left" }}>
          <FormControl component="fieldset">
            <FormLabel>Altered Documentation</FormLabel>
            <RadioGroup
              row
              style={{ marginTop: "5%" }}
              value={alteredDocumentation}
              onChange={(event) => setAlteredDocumentation(event.target.value)}
            >
              <FormControlLabel
                value="false"
                control={<Radio color="primary" />}
                label="No"
                labelPlacement="start"
              />
              <FormControlLabel
                value="true"
                control={<Radio color="primary" />}
                label="Yes"
                labelPlacement="start"
              />
            </RadioGroup>
          </FormControl>
          <FormControl component="fieldset" style={{ marginTop: "10%" }}>
            <FormLabel>Errors In Software</FormLabel>
            <RadioGroup
              row
              style={{ marginTop: "5%" }}
              value={errorsInSoftware}
              onChange={(event) => setErrorsInSoftware(event.target.value)}
            >
              <FormControlLabel
                value="false"
                control={<Radio color="primary" />}
                label="No"
                labelPlacement="start"
              />
              <FormControlLabel
                value="true"
                control={<Radio color="primary" />}
                label="Yes"
                labelPlacement="start"
              />
            </RadioGroup>
          </FormControl>

          <FormControl component="fieldset" style={{ marginTop: "10%" }}>
            <FormLabel>
              Recently used open source or 3rd party components
            </FormLabel>
            <RadioGroup
              row
              style={{ marginTop: "5%" }}
              value={openSourceOr3rdPartyComponents}
              onChange={(event) =>
                setOpenSourceOr3rdPartyComponents(event.target.value)
              }
            >
              <FormControlLabel
                value="false"
                control={<Radio color="primary" />}
                label="No"
                labelPlacement="start"
              />
              <FormControlLabel
                value="true"
                control={<Radio color="primary" />}
                label="Yes"
                labelPlacement="start"
              />
            </RadioGroup>
          </FormControl>

          <FormControl component="fieldset" style={{ marginTop: "10%" }}>
            <FormLabel>Suspicious Data Modifications</FormLabel>
            <RadioGroup
              row
              style={{ marginTop: "5%" }}
              value={suspiciousDataModifications}
              onChange={(event) =>
                setSuspiciousDataModifications(event.target.value)
              }
            >
              <FormControlLabel
                value="false"
                control={<Radio color="primary" />}
                label="No"
                labelPlacement="start"
              />
              <FormControlLabel
                value="true"
                control={<Radio color="primary" />}
                label="Yes"
                labelPlacement="start"
              />
            </RadioGroup>
          </FormControl>
          <FormControl component="fieldset" style={{ marginTop: "10%" }}>
            <FormLabel>Recently Received Updates</FormLabel>
            <RadioGroup
              row
              style={{ marginTop: "5%" }}
              value={recentlyReceivedUpdates}
              onChange={(event) =>
                setRecentlyReceivedUpdates(event.target.value)
              }
            >
              <FormControlLabel
                value="false"
                control={<Radio color="primary" />}
                label="No"
                labelPlacement="start"
              />
              <FormControlLabel
                value="true"
                control={<Radio color="primary" />}
                label="Yes"
                labelPlacement="start"
              />
            </RadioGroup>
          </FormControl>
          <FormControl component="fieldset" style={{ marginTop: "10%" }}>
            <FormLabel>Recently Used Removable Media</FormLabel>
            <RadioGroup
              row
              style={{ marginTop: "5%" }}
              value={recentlyUsedRemovableMedia}
              onChange={(event) =>
                setRecentlyUsedRemovableMedia(event.target.value)
              }
            >
              <FormControlLabel
                value="false"
                control={<Radio color="primary" />}
                label="No"
                labelPlacement="start"
              />
              <FormControlLabel
                value="true"
                control={<Radio color="primary" />}
                label="Yes"
                labelPlacement="start"
              />
            </RadioGroup>
          </FormControl>
        </Grid>
        <Grid item xs={2} />
        <Grid item xs={3} style={{ textAlign: "left" }}>
          <FormControl component="fieldset">
            <FormLabel>Suspicious Code Changes</FormLabel>
            <RadioGroup
              row
              style={{ marginTop: "5%" }}
              value={suspiciousCodeChanges}
              onChange={(event) => setSuspiciousCodeChanges(event.target.value)}
            >
              <FormControlLabel
                value="false"
                control={<Radio color="primary" />}
                label="No"
                labelPlacement="start"
              />
              <FormControlLabel
                value="true"
                control={<Radio color="primary" />}
                label="Yes"
                labelPlacement="start"
              />
            </RadioGroup>
          </FormControl>
          <FormControl component="fieldset" style={{ marginTop: "10%" }}>
            <FormLabel>Software In Development Phase</FormLabel>
            <RadioGroup
              row
              style={{ marginTop: "5%" }}
              value={softwareInDevelopmentPhase}
              onChange={(event) =>
                setSoftwareInDevelopmentPhase(event.target.value)
              }
            >
              <FormControlLabel
                value="false"
                control={<Radio color="primary" />}
                label="No"
                labelPlacement="start"
              />
              <FormControlLabel
                value="true"
                control={<Radio color="primary" />}
                label="Yes"
                labelPlacement="start"
              />
            </RadioGroup>
          </FormControl>
          <FormControl component="fieldset" style={{ marginTop: "10%" }}>
            <FormLabel>Software In Deployment Phase</FormLabel>
            <RadioGroup
              row
              style={{ marginTop: "5%" }}
              value={softwareInDeploymentPhase}
              onChange={(event) =>
                setSoftwareInDeploymentPhase(event.target.value)
              }
            >
              <FormControlLabel
                value="false"
                control={<Radio color="primary" />}
                label="No"
                labelPlacement="start"
              />
              <FormControlLabel
                value="true"
                control={<Radio color="primary" />}
                label="Yes"
                labelPlacement="start"
              />
            </RadioGroup>
          </FormControl>
          <FormControl component="fieldset" style={{ marginTop: "10%" }}>
            <FormLabel>Unauthenticated Physical Access Recently</FormLabel>
            <RadioGroup
              row
              style={{ marginTop: "5%" }}
              value={unauthenticatedPhysicalAccessRecently}
              onChange={(event) =>
                setUnauthenticatedPhysicalAccessRecently(event.target.value)
              }
            >
              <FormControlLabel
                value="false"
                control={<Radio color="primary" />}
                label="No"
                labelPlacement="start"
              />
              <FormControlLabel
                value="true"
                control={<Radio color="primary" />}
                label="Yes"
                labelPlacement="start"
              />
            </RadioGroup>
          </FormControl>
          <FormControl component="fieldset" style={{ marginTop: "10%" }}>
            <FormLabel>Denial Of Service</FormLabel>
            <RadioGroup
              row
              style={{ marginTop: "5%" }}
              value={denialOfService}
              onChange={(event) => setDenialOfService(event.target.value)}
            >
              <FormControlLabel
                value="false"
                control={<Radio color="primary" />}
                label="No"
                labelPlacement="start"
              />
              <FormControlLabel
                value="true"
                control={<Radio color="primary" />}
                label="Yes"
                labelPlacement="start"
              />
            </RadioGroup>
          </FormControl>
          <FormControl style={{ marginTop: "5%", marginLeft: "7%" }}>
            <InputLabel style={{ fontSize: "20px" }}>Company size</InputLabel>
            <NativeSelect
              defaultValue={companySize}
              onChange={(event) => setCompanySize(event.target.value)}
              style={{ marginTop: "40%" }}
            >
              <option value={"0"}>Small</option>
              <option value={"1"}>Medium</option>
              <option value={"2"}>Big</option>
            </NativeSelect>
          </FormControl>
          <div>
            <Button
              variant="contained"
              color="primary"
              onClick={findAttackForSymptoms}
              style={{ marginTop: "15%" }}
            >
              Find attacks
            </Button>
          </div>
        </Grid>
        <Grid item xs={2} />
      </Grid>
      {attacks !== undefined && attacks !== null && (
        <>
          <Grid container>
            <Grid item xs={2} />
            <Grid item xs={8}>
              <AttackByBayesAndMitigations
                attack={attacks[0]}
                mitigations={mitigations1}
                setMitigations={setMitigations1}
              />
            </Grid>
            <Grid item xs={2} />
          </Grid>
          <Grid container>
            <Grid item xs={2} />
            <Grid item xs={8}>
              <AttackByBayesAndMitigations
                attack={attacks[1]}
                mitigations={mitigations2}
                setMitigations={setMitigations2}
              />
            </Grid>
            <Grid item xs={2} />
          </Grid>
          <Grid container>
            <Grid item xs={2} />
            <Grid item xs={8}>
              <AttackByBayesAndMitigations
                attack={attacks[2]}
                mitigations={mitigations3}
                setMitigations={setMitigations3}
              />
            </Grid>
            <Grid item xs={2} />
          </Grid>
        </>
      )}
    </div>
  );
};

export default Bayes;