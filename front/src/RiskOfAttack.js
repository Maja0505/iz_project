import { Grid, Typography, Slider, Button } from "@material-ui/core";

import { useState } from "react";

const RiskOfAttack = () => {
  const [access_vector, set_access_vector] = useState(0);
  const [access_complexity, set_access_complexity] = useState(0);
  const [authentication, set_authentication] = useState(0);
  const [confidenciality, set_confidenciality] = useState(0);
  const [integrity, set_integrity] = useState(0);
  const [availability, set_availability] = useState(0);

  const calculateRisk = () => {
    var risk = {
      Access_vector: access_vector,
      Access_complexity: access_complexity,
      Authentication: authentication,
      Confidenciality: confidenciality,
      Integrity: integrity,
      Availability: availability,
    };
    console.log(risk);
  };

  return (
    <div>
      <Grid container style={{ marginTop: "8%" }}>
        <Grid item xs={2} />
        <Grid container item xs={8}>
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
          <Grid item xs={2} />
        </Grid>
        <Grid item xs={2} />
      </Grid>
      <Grid container style={{ marginTop: "5%" }}>
        <Grid item xs={2} />
        <Grid container item xs={8}>
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
          <Grid item xs={3}>
            <Typography variant="h5" style={{ color: "blue" }}>
              Risk : 100
            </Typography>
            <Typography variant="subtitle2">Very high</Typography>
          </Grid>
          <Grid item xs={2} />
        </Grid>
        <Grid item xs={2} />
      </Grid>
    </div>
  );
};

export default RiskOfAttack;
