import axios from "axios";
import { useState, useEffect } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Grid,
  Button,
  TextField,
  TableContainer,
} from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import Dialog from "@material-ui/core/Dialog";
import Typography from "@material-ui/core/Typography";
import MenuItem from "@material-ui/core/MenuItem";
import Select from "@material-ui/core/Select";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";


const useStyles = makeStyles((theme) => ({
  table: {
    marginTop: "5%",
  },
  hederRow: {
    background: "#4051bf",
  },
  hederCell: {
    cursor: "pointer",
    color: "#ffffff",
    position: "sticky",
    top: 0,
    background: "#4051bf",
  },
  icons: {
    cursor: "pointer",
  },
}));


const AttacksRdf = () => {
  const [allAttacks, setAllAttacks] = useState([]);
  const [openDialog, setOpenDialog] = useState(false);
  const [openDialogUpdate, setOpenDialogUpdate] = useState(false);

  const [selectedValue, setSelectedValue] = useState();
  const [selectedValueCopy, setSelectedValueCopy] = useState();

  const [newAttack, setNewAttack] = useState();

  useEffect(() => {
    axios.get("/get-all-attacks").then((res) => {
      setAllAttacks(res.data);
      console.log(res.data);
    });
  }, []);

  const classes = useStyles();

  const handleClickOpenDialog = () => {
    setOpenDialog(true);
  };

  const handleClickClose = () => {
    axios.get("/get-all-attacks").then((res) => {
        setAllAttacks(res.data);
        console.log(res.data);
        setOpenDialog(false);
        setOpenDialogUpdate(false);
      });
  

  };

  const handleClickUpdate = (attack) => {
    setSelectedValueCopy(attack);
    setSelectedValue(attack);
    setOpenDialogUpdate(true);
    console.log("aaaaaaaa");
  };

  const setValue = (attack) => {
    setSelectedValue(attack);
  };

  const handleClickUpdateAttack = () => {
    axios.put("/update-attack", selectedValue).then((res) => {
      console.log("uspelo");
    });
  };

 const handleClickAdd = () => {
    axios.post("/add-attack", newAttack).then((res) => {
      console.log("uspelo");
    });
  };
  const CreateDialog = (
      <Dialog
        onClose={handleClickClose}
        aria-labelledby="customized-dialog-title"
        open={openDialog}
        fullWidth="true"
        maxWidth="sm"
      >
        <DialogTitle id="customized-dialog-title" onClose={handleClickClose}>
          Form for new attack
        </DialogTitle>
        <DialogContent dividers>
          <Grid container>
            <Grid container item xs={12}>
              <Grid item xs={2} />
              <Grid item xs={3} style={{ textAlign: "left" }}>
                <div>
                  <Typography variant="overline" color="textSecondary">
                    Name
                  </Typography>
                  <TextField
                    color="primary"
                    variant="outlined"
                    size="small"
                    placeholder="Last Name"
                    fullWidth
                    onChange={(e) =>
                        setNewAttack({
                          ...newAttack,
                          name: e.target.value,
                        })
                      }
                  ></TextField>
                </div>
                <div style={{ marginTop: "25px" }}>
                  <Typography variant="overline" color="textSecondary">
                    Description
                  </Typography>
                  <TextField
                    onChange={(e) =>
                      setNewAttack({
                        ...newAttack,
                        description: e.target.value,
                      })
                    }
                  ></TextField>
                </div>
                <div style={{ marginTop: "25px" }}>
                  <Typography variant="overline" color="textSecondary">
                    Domain
                  </Typography>
                  <Select
                    style={{ width: "100%" }}
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    onChange={(event) =>
                      setNewAttack({ ...newAttack, domain: event.target.value })
                    }
                  >
                    <MenuItem value={"SOFTWARE"}>SOFTWARE</MenuItem>
                    <MenuItem value={"HARDWARE"}>HARDWARE</MenuItem>
                    <MenuItem value={"BOTH"}>BOTH</MenuItem>
                  </Select>
                </div>
                <div style={{ marginTop: "25px" }}>
                  <Typography variant="overline" color="textSecondary">
                    Prerequisites
                  </Typography>
                  <TextField
                    onChange={(event) =>
                      setNewAttack({
                        ...newAttack,
                        prerequisites: event.target.value,
                      })
                    }
                  ></TextField>
                </div>
              </Grid>
              <Grid item xs={2} />
              <Grid item xs={3} style={{ textAlign: "left" }}>
                <div>
                  <Typography variant="overline" color="textSecondary">
                    Typical_severity
                  </Typography>
                  <Select
                    style={{ width: "100%" }}
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    onChange={(event) =>
                      setNewAttack({
                        ...newAttack,
                        typical_severity: event.target.value,
                      })
                    }
                  >
                    <MenuItem value={"LOW"}>LOW</MenuItem>
                    <MenuItem value={"MEDIUM"}>MEDIUM</MenuItem>
                    <MenuItem value={"HIGH"}>HIGH</MenuItem>
                    <MenuItem value={"VERY_HIGH"}>VERY_HIGH</MenuItem>
                  </Select>
                </div>
                <div style={{ marginTop: "25px" }}>
                  <Typography variant="overline" color="textSecondary">
                    Likelihood_of_attack
                  </Typography>
                  <Select
                    style={{ width: "100%" }}
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    onChange={(event) =>
                      setNewAttack({
                        ...newAttack,
                        likelihood_of_attack: event.target.value,
                      })
                    }
                  >
                    <MenuItem value={"LOW"}>LOW</MenuItem>
                    <MenuItem value={"MEDIUM"}>MEDIUM</MenuItem>
                    <MenuItem value={"HIGH"}>HIGH</MenuItem>
                  </Select>
                </div>
                <div style={{ marginTop: "25px" }}>
                  <Typography variant="overline" color="textSecondary">
                    Mitigations
                  </Typography>
                  <TextField
                    onChange={(event) =>
                      setNewAttack({
                        ...newAttack,
                        mitigations: event.target.value,
                      })
                    }
                  ></TextField>
                </div>
                <div style={{ marginTop: "25px" }}>
                  <Typography variant="overline" color="textSecondary">
                    Weaknesses
                  </Typography>
                  <TextField
                    onChange={(event) =>
                      setNewAttack({
                        ...newAttack,
                        weaknesses: event.target.value,
                      })
                    }
                  ></TextField>
                </div>
              </Grid>
            </Grid>
          </Grid>
          <Grid container>
            <Grid container item xs={12} style={{ marginTop: "5%" }}>
              <Grid item xs={2} />
              <Grid item xs={3} style={{ textAlign: "left" }}></Grid>
              <Grid item xs={2} />
              <Grid item xs={3}>
                <Button
                  variant="contained"
                  color="primary"
                  style={{ margin: "auto" }}
                  onClick={handleClickAdd}
                >
                  Add
                </Button>
              </Grid>
            </Grid>
          </Grid>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClickClose} autoFocus color="primary">
            Close
          </Button>
        </DialogActions>
      </Dialog>
  );

  const TableHeader = (
    <TableHead>
      <TableRow className={classes.hederRow}>
        <TableCell className={classes.hederCell}>Name</TableCell>
        <TableCell className={classes.hederCell}>Descriotion</TableCell>
        <TableCell className={classes.hederCell}>Domain</TableCell>
        <TableCell className={classes.hederCell}>Typical_severity</TableCell>
        <TableCell className={classes.hederCell}>
          Likelihood of attack
        </TableCell>
        <TableCell className={classes.hederCell}>Mitigations</TableCell>
        <TableCell className={classes.hederCell}>Weaknesses</TableCell>
        <TableCell className={classes.hederCell}>Prerequisites</TableCell>
      </TableRow>
    </TableHead>
  );

  const TableContent = (
    <TableBody>
      {allAttacks.map((row, index) => (
        <TableRow key={index} onClick={() => setValue(row)}>
          <TableCell>{row.name}</TableCell>
          <TableCell>{row.description}</TableCell>
          <TableCell>{row.domain}</TableCell>
          <TableCell>{row.typical_severity}</TableCell>
          <TableCell>{row.likelihood_of_attack}</TableCell>
          <TableCell>{row.mitigations}</TableCell>
          <TableCell>{row.weaknesses}</TableCell>
          <TableCell>{row.prerequisites}</TableCell>
        </TableRow>
      ))}
    </TableBody>
  );

  const CreateDialogUpdate = (
    <div>
      {selectedValue && (
        <Dialog
          onClose={handleClickClose}
          aria-labelledby="customized-dialog-title"
          open={openDialogUpdate}
          fullWidth="true"
          maxWidth="sm"
        >
          <DialogTitle id="customized-dialog-title" onClose={handleClickClose}>
            Form for update selected attack
          </DialogTitle>
          <DialogContent dividers>
            <Grid container>
              <Grid container item xs={12}>
                <Grid item xs={2} />
                <Grid item xs={3} style={{ textAlign: "left" }}>
                  <div>
                    <Typography variant="overline" color="textSecondary">
                      Name
                    </Typography>
                    <TextField
                      disabled={true}
                      value={selectedValue.name}
                      onChange={(event) =>
                        setSelectedValue({
                          ...selectedValue,
                          name: event.target.value,
                        })
                      }
                    ></TextField>
                  </div>
                  <div style={{ marginTop: "25px" }}>
                    <Typography variant="overline" color="textSecondary">
                      Description
                    </Typography>
                    <TextField
                      value={selectedValue.description}
                      onChange={(event) =>
                        setSelectedValue({
                          ...selectedValue,
                          description: event.target.value,
                        })
                      }
                    ></TextField>
                  </div>
                  <div style={{ marginTop: "25px" }}>
                    <Typography
                      variant="overline"
                      style={{ width: "100%" }}
                      color="textSecondary"
                    >
                      Domain
                    </Typography>
                    <Select
                      style={{ width: "100%" }}
                      labelId="demo-simple-select-label"
                      id="demo-simple-select"
                      value={selectedValue.domain}
                      onChange={(event) =>
                        setSelectedValue({
                          ...selectedValue,
                          domain: event.target.value,
                        })
                      }
                    >
                      <MenuItem value={"SOFTWARE"}>SOFTWARE</MenuItem>
                      <MenuItem value={"HARDWARE"}>HARDWARE</MenuItem>
                      <MenuItem value={"BOTH"}>BOTH</MenuItem>
                    </Select>
                  </div>
                  <div style={{ marginTop: "25px" }}>
                    <Typography variant="overline" color="textSecondary">
                      Prerequisites
                    </Typography>
                    <TextField
                      value={selectedValue.prerequisites}
                      onChange={(event) =>
                        setSelectedValue({
                          ...selectedValue,
                          prerequisites: event.target.value,
                        })
                      }
                    ></TextField>
                  </div>
                </Grid>
                <Grid item xs={2} />
                <Grid item xs={3} style={{ textAlign: "left" }}>
                  <div>
                    <Typography variant="overline" color="textSecondary">
                      Typical_severity
                    </Typography>
                    <Select
                      style={{ width: "100%" }}
                      labelId="demo-simple-select-label"
                      id="demo-simple-select"
                      value={selectedValue.typical_severity}
                      onChange={(event) =>
                        setSelectedValue({
                          ...selectedValue,
                          typical_severity: event.target.value,
                        })
                      }
                    >
                      <MenuItem value={"LOW"}>LOW</MenuItem>
                      <MenuItem value={"MEDIUM"}>MEDIUM</MenuItem>
                      <MenuItem value={"HIGH"}>HIGH</MenuItem>
                      <MenuItem value={"VERY_HIGH"}>VERY_HIGH</MenuItem>
                    </Select>
                  </div>
                  <div style={{ marginTop: "25px" }}>
                    <Typography
                      style={{ width: "100%" }}
                      variant="overline"
                      color="textSecondary"
                    >
                      Likelihood_of_attack
                    </Typography>
                    <Select
                      style={{ width: "100%" }}
                      labelId="demo-simple-select-label"
                      id="demo-simple-select"
                      value={selectedValue.likelihood_of_attack}
                      onChange={(event) =>
                        setSelectedValue({
                          ...selectedValue,
                          likelihood_of_attack: event.target.value,
                        })
                      }
                    >
                      <MenuItem value={"LOW"}>LOW</MenuItem>
                      <MenuItem value={"MEDIUM"}>MEDIUM</MenuItem>
                      <MenuItem value={"HIGH"}>HIGH</MenuItem>
                    </Select>
                  </div>
                  <div style={{ marginTop: "25px" }}>
                    <Typography variant="overline" color="textSecondary">
                      Mitigations
                    </Typography>
                    <TextField
                      value={selectedValue.mitigations}
                      onChange={(event) =>
                        setSelectedValue({
                          ...selectedValue,
                          mitigations: event.target.value,
                        })
                      }
                    ></TextField>
                  </div>
                  <div style={{ marginTop: "25px" }}>
                    <Typography variant="overline" color="textSecondary">
                      Weaknesses
                    </Typography>
                    <TextField
                      value={selectedValue.weaknesses}
                      onChange={(event) =>
                        setSelectedValue({
                          ...selectedValue,
                          weaknesses: event.target.value,
                        })
                      }
                    ></TextField>
                  </div>
                </Grid>
              </Grid>
            </Grid>
            <Grid container>
              <Grid container item xs={12} style={{ marginTop: "5%" }}>
                <Grid item xs={2} />
                <Grid item xs={3} style={{ textAlign: "left" }}></Grid>
                <Grid item xs={2} />
                <Grid item xs={3}>
                  <Button
                    variant="contained"
                    color="primary"
                    style={{ margin: "auto" }}
                    onClick={handleClickUpdateAttack}
                    disabled={selectedValue === selectedValueCopy}
                  >
                    Update
                  </Button>
                </Grid>
              </Grid>
            </Grid>
          </DialogContent>
          <DialogActions>
            <Button onClick={handleClickClose} autoFocus color="primary">
              Close
            </Button>
          </DialogActions>
        </Dialog>
      )}
    </div>
  );

  return (
    <div>
      <Button
        color="primary"
        style={{ backgroundColor: "white", marginRight: 10 }}
        onClick={handleClickOpenDialog}
      >
        ADD NEW ATTACK
      </Button>
      <Button
        color="primary"
        style={{ backgroundColor: "white" }}
        disabled={!selectedValue}
        onClick={() => handleClickUpdate(selectedValue)}
      >
        UPDATE SELECTED ATTACK
      </Button>
      <Grid container spacing={1}>
        <Grid item xs={12}>
          {allAttacks !== null && allAttacks !== undefined && allAttacks.length !== 0 ? <TableContainer style={{ height: "450px", marginTop: "2%" }}>
            <Table>
              {TableHeader}
              {TableContent}
            </Table>
          </TableContainer>
          :
          <p>Database is empty</p>}
        </Grid>
      </Grid>

      {CreateDialog}
      {CreateDialogUpdate}

    </div>
  );
};

export default AttacksRdf;
