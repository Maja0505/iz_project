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

const Bayes = () => {
  const [alteredDocumentation, setAlteredDocumentation] = useState("false");
  const [openSourceOr3rdPartyComponents, setOpenSourceOr3rdPartyComponents] = useState("false");
  const [errorsInSoftware, setErrorsInSoftware] = useState("false");
  const [suspiciousDataModifications, setSuspiciousDataModifications] = useState("false");
  const [recentlyReceivedUpdates, setRecentlyReceivedUpdates] =useState("false");
  const [recentlyUsedRemovableMedia, setRecentlyUsedRemovableMedia] =useState("false");
  const [companySize, setCompanySize] = useState("0");
  const [denialOfService, setDenialOfService] = useState("false");
  const [suspiciousCodeChanges, setSuspiciousCodeChanges] = useState("false");
  const [softwareInDevelopmentPhase, setSoftwareInDevelopmentPhase] =useState("false");
  const [softwareInDeploymentPhase, setSoftwareInDeploymentPhase] =useState("false");
  const [unauthenticatedPhysicalAccessRecently,setUnauthenticatedPhysicalAccessRecently] = useState("false");
  const [attacks, setAttacks] = useState([]);
  const [showAttacks, setShowAttacks] = useState(false);
  const [showMitigations,setShowMitigations] = useState(false);
  const [mitigations,setMitigations] = useState([]);

  const findAttackForSymptoms = () => {
    axios
      .put("/bayes", {
        Suspicious_code_changes: suspiciousCodeChanges === "true" ? true : false,
        Suspicious_data_modifications: suspiciousDataModifications === "true" ? true : false,
        Company_size: companySize,
        Software_in_development_phase:softwareInDevelopmentPhase === "true" ? true : false,
        Software_in_deployment_phase: softwareInDeploymentPhase === "true" ? true : false,
        Using_open_source_or_3rd_party_components:openSourceOr3rdPartyComponents === "true" ? true : false,
        Unauthorized_physical_access_occured_recently:unauthenticatedPhysicalAccessRecently === "true" ? true : false,
        Recently_received_updates: recentlyReceivedUpdates === "true" ? true : false,
        Recently_used_removable_media: recentlyUsedRemovableMedia === "true" ? true : false,
        Unefectivness_or_errors_in_software: errorsInSoftware === "true" ? true : false,
        Denial_of_service: denialOfService === "true" ? true : false,
        Altered_documentation: alteredDocumentation === "true" ? true : false,
        })
      .then((res) => {
        console.log(res.data);
        setAttacks(res.data);

        setShowAttacks(true);
      });
  };

  const showMitigationsForAttack=(attack)=> {
    console.log(attack);
    axios.get("/findMitigationsForAttack",{params:{attackName:attack}})
      .then(res => {
        setMitigations(res.data);
        setShowMitigations(true);
        setShowAttacks(false);
      })
  };

  const back=() =>{
    setShowMitigations(false);
    setShowAttacks(true);
  }

  const symptomsForm=(
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
          <FormLabel>Recently used open source or 3rd party components</FormLabel>
          <RadioGroup
            row
            style={{ marginTop: "5%" }}
            value={errorsInSoftware}
            onChange={(event) => setOpenSourceOr3rdPartyComponents(event.target.value)}
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
        <FormControl style={{ marginTop: "5%" }}>
          <InputLabel style={{ fontSize: "20px" }}>
            Company size
          </InputLabel>
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
    );

    const showFoundedAttacks=(
      <Grid container style={{backgroundColor:"white",height:"70%",width:"1000px"}}>
        <Grid item xs={12} style={{marginBottom:"50px"}}>
          <label style={{fontSize:"20px",fontColor:"gray"}}>The most similar attacks</label>
        </Grid>
        <Grid item xs={2} >

          {
        attacks.map((attack) =>
        <Grid value={attack} key={attack.attack} style={{display:"flex",width:"100%",}} >
        <Grid item >{attack.attack}</Grid>
        <Grid item  style={{marginLeft:"30px"}}>{attack.probability}</Grid>
        <Grid item>
        <Button
          variant="contained"
          color="primary"
          style={{height:"30px",width:"150px",fontSize:"10px",marginBottom:"20px",marginLeft:"5px"}}
          onClick={()=>showMitigationsForAttack(attack.attack)}
        >
          Show mitigations
        </Button>
        </Grid>
        </Grid>
        )
          }

        </Grid>
      </Grid>
    );

    const showMitigationsForm= (<Grid container style={{backgroundColor:"white",height:"70%",width:"1000px"}}>
    <Grid item xs={12} style={{marginBottom:"50px"}}>
      <label style={{fontSize:"20px",fontColor:"gray"}}>Mitigations for attack</label>
    </Grid>
    <Grid item xs={2} >
   { mitigations.map((mitigation) =>
    <Grid style={{display:"flex",width:"100%",}} >
    <label style={{marginLeft:"50px",widht:"900px"}} >{mitigation}</label>
    </Grid>
      )
   }
   </Grid>
   <Grid>
        <Button
          variant="contained"
          color="primary"
          style={{height:"30px",width:"150px",fontSize:"10px",marginBottom:"20px",marginLeft:"800px"}}
          onClick={()=>back()}
        >
          Back
        </Button>
   </Grid>
</Grid>);



  return (<div>
            {showAttacks === false & showMitigations===false && symptomsForm}
            {showAttacks === true & showMitigations===false && showFoundedAttacks}
            {showMitigations===true & showAttacks === false && showMitigationsForm}
         </div>
   );
};

export default Bayes;
