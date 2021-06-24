import "./App.css";

import { useState } from "react";
import { Grid, Paper, Tabs, Tab } from "@material-ui/core";

import SymptomsOfAttack from "./SymptomsOfAttack.js";
import RiskOfAttack from "./RiskOfAttack.js";
import Bayes from "./Bayes.js";

function App() {
  const [tabValue, setTabValue] = useState(0);

  const handleChange = (event, newValue) => {
    setTabValue(newValue);
  };

  return (
    <div className="App">
      <Grid container style={{ marginTop: "4%" }}>
        <Grid item xs={3} />
        <Grid item xs={6}>
          <Paper>
            <Tabs
              value={tabValue}
              indicatorColor="primary"
              textColor="primary"
              onChange={handleChange}
            >
              <Tab label="Symptoms" style={{ margin: "auto" }} />
              <Tab label="Risk" style={{ margin: "auto" }} />
              <Tab label="Bayes" style={{ margin: "auto" }} />
            </Tabs>
          </Paper>
        </Grid>
        <Grid item xs={3} />
      </Grid>
      <Grid container style={{ marginTop: "3%" }}>
        <Grid item xs={2} />
        <Grid item xs={8}>
          {tabValue === 0 && <SymptomsOfAttack />}
          {tabValue === 1 && <RiskOfAttack />}
          {tabValue === 2 && <Bayes />}
        </Grid>
        <Grid item xs={2} />
      </Grid>
    </div>
  );
}

export default App;
