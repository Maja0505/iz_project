import { useState } from "react";

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

import AttackDescriptionAndMitigations from "./AttackDescriptionAndMitigations";

const SymptomsOfAttack = () => {
  const [alteredDocumentation, setAlteredDocumentation] = useState("false");
  const [errorsInSoftware, setErrorsInSoftware] = useState("false");
  const [suspiciousDataModifications, setSuspiciousDataModifications] =
    useState("false");
  const [recentlyReceivedUpdates, setRecentlyReceivedUpdates] =
    useState("false");
  const [recentlyUsedRemovableMedia, setRecentlyUsedRemovableMedia] =
    useState("false");
  const [typicalSeverity, setTypicalSeverity] = useState("low");
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
  const [type, setType] = useState("hardware");

  const [attack, setAttack] = useState();
  const [mitigations, setMitigations] = useState([]);

  const findAttackForSymptoms = () => {
    setAttack(null);
    setMitigations([]);
    axios
      .put("/findAttackForSymptoms", {
        alteredDocumentation: alteredDocumentation === "true" ? true : false,
        errorsInSoftware: errorsInSoftware === "true" ? true : false,
        suspiciousDataModifications:
          suspiciousDataModifications === "true" ? true : false,
        recentlyReceivedUpdates:
          recentlyReceivedUpdates === "true" ? true : false,
        recentlyUsedRemovableMedia:
          recentlyUsedRemovableMedia === "true" ? true : false,
        typicalSeverity: typicalSeverity,
        denialOfService: denialOfService === "true" ? true : false,
        suspiciousCodeChanges: suspiciousCodeChanges === "true" ? true : false,
        softwareInDevelopmentPhase:
          softwareInDevelopmentPhase === "true" ? true : false,
        softwareInDeploymentPhase:
          softwareInDeploymentPhase === "true" ? true : false,
        unauthenticatedPhysicalAccessRecently:
          unauthenticatedPhysicalAccessRecently === "true" ? true : false,
        type: type,
      })
      .then((res) => {
        console.log(res.data);
        setAttack(res.data);
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
          <FormControl style={{ marginTop: "5%" }}>
            <InputLabel style={{ fontSize: "20px" }}>
              Typical Severity
            </InputLabel>
            <NativeSelect
              defaultValue={typicalSeverity}
              onChange={(event) => setTypicalSeverity(event.target.value)}
              style={{ marginTop: "40%" }}
            >
              <option value={"low"}>Low</option>
              <option value={"medium"}>Medium</option>
              <option value={"high"}>High</option>
            </NativeSelect>
          </FormControl>
          <FormControl style={{ marginTop: "5%", marginLeft: "5%" }}>
            <InputLabel style={{ fontSize: "20px" }}>Type</InputLabel>
            <NativeSelect
              defaultValue={type}
              onChange={(event) => setType(event.target.value)}
              style={{ marginTop: "34%" }}
            >
              <option value={"hardware"}>Hardware</option>
              <option value={"software"}>Software</option>
            </NativeSelect>
          </FormControl>
          <Button
            variant="contained"
            color="primary"
            onClick={findAttackForSymptoms}
            style={{ marginTop: "15%" }}
          >
            Find attack
          </Button>
        </Grid>
        <Grid item xs={2} />
      </Grid>
      {attack !== undefined && attack !== null && (
        <>
          <Grid container>
            <Grid item xs={2} />
            <Grid item xs={8}>
              <AttackDescriptionAndMitigations
                attack={attack}
                mitigations={mitigations}
                setMitigations={setMitigations}
              />
            </Grid>
            <Grid item xs={2} />
          </Grid>
        </>
      )}
    </div>
  );
};

export default SymptomsOfAttack;
