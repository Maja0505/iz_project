import { Paper, Button, Grid, Typography } from "@material-ui/core";
import axios from "axios";

const AttackDescriptionAndMitigations = ({
  attack,
  mitigations,
  setMitigations,
}) => {
  const showMitigations = () => {
    axios
      .get("/findMitigationsForAttack?attackName=" + attack.attack)
      .then((res) => {
        setMitigations(res.data);
      });
  };

  return (
    <div>
      {attack !== undefined && attack !== null && (
        <>
          <Paper style={{ marginTop: "5%" }}>
            <Grid container>
              <Grid
                container
                item
                xs={3}
                style={{ textAlign: "right", margin: "auto", marginTop: "5%" }}
              >
                <Grid item xs={12}>
                  <Typography variant="inherit">
                    <b>Attack name : </b>
                  </Typography>
                </Grid>
                <Grid item xs={12} style={{ marginTop: "10%" }}>
                  <Typography variant="inherit">
                    <b>Probability : </b>
                  </Typography>
                </Grid>
              </Grid>
              <Grid
                container
                item
                xs={9}
                style={{ textAlign: "center", margin: "auto", marginTop: "5%" }}
              >
                <Grid item xs={12}>
                  <Typography variant="inherit">{attack.attack}</Typography>
                </Grid>
                <Grid item xs={12} style={{ marginTop: "3.3%" }}>
                  <Typography variant="inherit">
                    {attack.probability.toString().substring(0, 4)}
                  </Typography>
                </Grid>
              </Grid>
            </Grid>

            <Button
              variant="text"
              color="inherit"
              onClick={showMitigations}
              style={{ marginTop: "5%" }}
            >
              Show mitigations for attack
            </Button>

            {mitigations.map((m, index) => (
              <Grid container style={{ marginTop: "2%", margin: "auto" }}>
                <Typography
                  variant="inherit"
                  key={index}
                  style={{ margin: "auto" }}
                >
                  {index + 1}
                  {" .  "}
                  {m}
                </Typography>
              </Grid>
            ))}
            <Grid container style={{ height: "20px" }}></Grid>
          </Paper>
          <Grid container style={{ height: "70px" }}></Grid>
        </>
      )}
    </div>
  );
};

export default AttackDescriptionAndMitigations;
