import axios from 'axios'
import React,{useState,useEffect} from 'react'
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Grid,
  Button,
  Link,
  TextField,
  TableContainer
} from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';






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
  
  
  const styles = (theme) => ({
      root: {
        margin: 0,
        padding: theme.spacing(2),
      },
      closeButton: {
        position: 'absolute',
        right: theme.spacing(1),
        top: theme.spacing(1),
        color: theme.palette.grey[500],
        
      },
      formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
      },
      selectEmpty: {
        marginTop: theme.spacing(2),
      },
    });
    
  
const AttacksRdf = () => {

    const [allAttacks,setAllAttacks] = useState([])
    const [openDialog,setOpenDialog] = useState(false)
    const [newAttack,setNewAttack] = useState({
        "name" : "",
        "description":"",
        "domain":"",
        "typical_severity" : "",
        "likelihood_of_attack":"",
        "mitigations":"",
        "weaknesses":"",
        "prerequisites":""
    })

    useEffect(() => {
        axios.get("/get-all-attacks").
         then((res) => {
             setAllAttacks(res.data)
             console.log(res.data)
         })
    }, [])
 
  const classes = useStyles();

  const handleClickOpenDialog = () => {
    setOpenDialog(true)
  }

  const handleClickClose = () => {
    setOpenDialog(false);
};


const DialogTitle = withStyles(styles)((props) => {
    const { children, classes, onClose, ...other } = props;
    return (
      <MuiDialogTitle disableTypography className={classes.root} {...other}>
        <Typography variant="h6">{children}</Typography>
        {onClose ? (
          <IconButton aria-label="close" className={classes.closeButton} onClick={onClose}>
            <CloseIcon />
          </IconButton>
        ) : null}
      </MuiDialogTitle>
    );
  });
  
  const DialogContent = withStyles((theme) => ({
    root: {
      padding: theme.spacing(2),
    },
  }))(MuiDialogContent);
  
  const DialogActions = withStyles((theme) => ({
    root: {
      margin: 0,
      padding: theme.spacing(1),
    },
  }))(MuiDialogActions);

  const handleClickAdd = () => {
      console.log(newAttack.name)
  }


  const CreateDialog = (
    <div>
        <Dialog onClose={handleClickClose} aria-labelledby="customized-dialog-title" open={openDialog} fullWidth='true'
      maxWidth='sm'>
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
              <TextField value={newAttack.name} onChange={(event) => setNewAttack({...newAttack,name: event.target.value})}></TextField>
            </div>
            <div style={{ marginTop: "25px" }}>
              <Typography variant="overline" color="textSecondary">
                Description
              </Typography>
              <TextField value={newAttack.description} onChange={(event) => setNewAttack({...newAttack,description: event.target.value})}></TextField>

            </div>
            <div style={{ marginTop: "25px" }}>
              <Typography variant="overline" color="textSecondary">
              Domain      
              </Typography>
               
            </div>
            <div style={{ marginTop: "25px" }}>
              <Typography variant="overline" color="textSecondary">
              Prerequisites
              </Typography>
              <TextField onChange={(event) => setNewAttack({...newAttack,prerequisites: event.target.value})}></TextField>

            </div>
          </Grid>
          <Grid item xs={2} />
          <Grid item xs={3} style={{ textAlign: "left" }}>
            <div>
              <Typography variant="overline" color="textSecondary">
              Typical_severity
              </Typography>
             
            </div>
            <div style={{ marginTop: "25px" }}>
              <Typography variant="overline" color="textSecondary">
              Likelihood_of_attack
              </Typography>
  

            </div>
            <div style={{ marginTop: "25px" }}>
              <Typography variant="overline" color="textSecondary">
              Mitigations
              </Typography>
              <TextField value={newAttack.mitigations} onChange={(event) => setNewAttack({...newAttack,mitigations: event.target.value})}></TextField>

            </div>
            <div style={{ marginTop: "25px" }}>
              <Typography variant="overline" color="textSecondary">
              Weaknesses
              </Typography>
              <TextField value={newAttack.weaknesses} onChange={(event) => setNewAttack({...newAttack,weaknesses: event.target.value})}></TextField>

            </div>
          </Grid>
        </Grid>
      </Grid>
      <Grid container>
        <Grid container item xs={12} style={{ marginTop: "5%" }}>
          <Grid item xs={2} />
          <Grid item xs={3} style={{ textAlign: "left" }}>
          
          </Grid>
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
    </div>
)


  const TableHeader = (
    <TableHead>
      <TableRow className={classes.hederRow}>
        <TableCell className={classes.hederCell} >
         Name
        </TableCell>
        <TableCell className={classes.hederCell}>
         Descriotion
          </TableCell>
        <TableCell className={classes.hederCell} >
        Domain
        </TableCell>
        <TableCell className={classes.hederCell}>
        Typical_severity
        </TableCell>
        <TableCell className={classes.hederCell}>
        Likelihood of attack
        </TableCell>
        <TableCell className={classes.hederCell}>
        Mitigations
        </TableCell>
        <TableCell className={classes.hederCell}>
        Weaknesses
        </TableCell>
        <TableCell className={classes.hederCell}>
        Prerequisites
        </TableCell>
      </TableRow>
    </TableHead>
  );

  const TableContent = (
    <TableBody>
      {allAttacks.map((row, index) => (
        <TableRow key={index} >
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

  



    return (
        <div>
        <Button onClick={handleClickOpenDialog}>ADD NEW ATTACK</Button>
       <Grid container spacing={1}>
         <Grid item xs={12}>
         <TableContainer style={{ height: "450px", marginTop: "2%" }}>
           <Table>
             {TableHeader}
             {TableContent}
           </Table>
         </TableContainer>
         </Grid>
       </Grid>
       {CreateDialog}
       </div>
    )
}

export default AttacksRdf
