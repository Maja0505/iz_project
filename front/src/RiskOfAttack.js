import { Grid, Typography, Slider, Button } from "@material-ui/core";

import { useState } from "react";
import axios from "axios";

const RiskOfAttack = () => {
  const [access_vector, set_access_vector] = useState(0);
  const [access_complexity, set_access_complexity] = useState(0);
  const [authentication, set_authentication] = useState(0);
  const [confidenciality, set_confidenciality] = useState(0);
  const [integrity, set_integrity] = useState(0);
  const [availability, set_availability] = useState(0);
  const [risk, set_risk] = useState();

  const calculateRisk = () => {
    set_risk(null);

    var risk = {
      access_vector: access_vector,
      access_complexity: access_complexity,
      authentication: authentication,
      confidentiality: confidenciality,
      integrity: integrity,
      availability: availability,
    };

    axios.put("/fuzzy", risk).then((res) => {
      set_risk(res.data);
    });
  };

  return (
    <div>
      <Grid container>
        <Grid container item xs={12}>
          <Grid item xs={2} />
          <Grid item xs={3} style={{ textAlign: "left" }}>
            <div>
              <Typography variant="overline" color="textSecondary">
                Access Vector
              </Typography>
              <Slider
                defaultValue={0}
                valueLabelDisplay="auto"
                step={1}
                marks
                min={0}
                max={10}
                onChange={(event, newValue) => set_access_vector(newValue)}
              />
            </div>
            <div style={{ marginTop: "25px" }}>
              <Typography variant="overline" color="textSecondary">
                access complexity
              </Typography>
              <Slider
                defaultValue={0}
                valueLabelDisplay="auto"
                step={1}
                marks
                min={0}
                max={10}
                onChange={(event, newValue) => set_access_complexity(newValue)}
              />
            </div>
            <div style={{ marginTop: "25px" }}>
              <Typography variant="overline" color="textSecondary">
                authentication
              </Typography>
              <Slider
                defaultValue={0}
                valueLabelDisplay="auto"
                step={1}
                marks
                min={0}
                max={10}
                onChange={(event, newValue) => set_authentication(newValue)}
              />
            </div>
          </Grid>
          <Grid item xs={2} />
          <Grid item xs={3} style={{ textAlign: "left" }}>
            <div>
              <Typography variant="overline" color="textSecondary">
                confidenciality
              </Typography>
              <Slider
                defaultValue={0}
                valueLabelDisplay="auto"
                step={1}
                marks
                min={0}
                max={10}
                onChange={(event, newValue) => set_confidenciality(newValue)}
              />
            </div>
            <div style={{ marginTop: "25px" }}>
              <Typography variant="overline" color="textSecondary">
                integrity
              </Typography>
              <Slider
                defaultValue={0}
                valueLabelDisplay="auto"
                step={1}
                marks
                min={0}
                max={10}
                onChange={(event, newValue) => set_integrity(newValue)}
              />
            </div>
            <div style={{ marginTop: "25px" }}>
              <Typography variant="overline" color="textSecondary">
                availability
              </Typography>
              <Slider
                defaultValue={0}
                valueLabelDisplay="auto"
                step={1}
                marks
                min={0}
                max={10}
                onChange={(event, newValue) => set_availability(newValue)}
              />
            </div>
          </Grid>
        </Grid>
      </Grid>
      <Grid container>
        <Grid container item xs={12} style={{ marginTop: "5%" }}>
          <Grid item xs={2} />
          <Grid item xs={3} style={{ textAlign: "left" }}>
            <Button
              variant="contained"
              color="primary"
              style={{ margin: "auto" }}
              onClick={calculateRisk}
            >
              Calculate risk
            </Button>
          </Grid>
          <Grid item xs={2} />
          {risk !== undefined && risk !== null && (
            <Grid item xs={3}>
              <Typography variant="h5" style={{ color: "blue" }}>
                Risk : {risk}
              </Typography>
              {risk < 25 && (
                <Typography style={{ color: "green" }} variant="subtitle2">
                  Low
                </Typography>
              )}
              {risk >= 25 && risk < 55 && (
                <Typography style={{ color: "yellow" }} variant="subtitle2">
                  Medium
                </Typography>
              )}
              {risk >= 55 && risk < 85 && (
                <Typography style={{ color: "orange" }} variant="subtitle2">
                  High
                </Typography>
              )}
              {risk >= 85 && (
                <Typography style={{ color: "red" }} variant="subtitle2">
                  Very high
                </Typography>
              )}
            </Grid>
          )}
        </Grid>
      </Grid>
    </div>
  );
};

export default RiskOfAttack;
