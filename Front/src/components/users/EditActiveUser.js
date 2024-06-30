
import Axios from "../../apis/Axios"
import { useCallback, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { Row, Col, Form, Button } from "react-bootstrap";
import auth, { Username } from "../../services/auth";






const  EditActiveUser =()=>{


   



    const navigate = useNavigate();


     const [updateUser, setUpdateUser] = useState({
     userId: -1,
     username: "",
     eMail: "",
     name: "",
     surname: ""
        });
var activeUser= Username();
    const [oldPassword, setOldPassword] = useState("")
    const [password, setPassword] = useState("")
    const [repeatedPassword, setRepeatedPassword] = useState("")

      const getUserById = useCallback(()=>{
                Axios.get("/korisnici/profil/"+activeUser).then(res =>{
                console.log(res)
                setUpdateUser({
                    id:res.data.id,
                    username:res.data.korisnickoIme,
                    eMail:res.data.email,
                    name:res.data.ime,
                    surname:res.data.prezime
                  

                })
    
            }).catch(err=>{
                console.log(err)
            })
      },[activeUser])  

 

    useEffect(() => {
    getUserById();
   
 },[getUserById])

       
       
    const valueInputChanged = (e) => {
    let input = e.target;
    let name = input.name;
    let value = input.value;

    let updatedUser = { ...updateUser };

    updatedUser[name] = value;

 
    setUpdateUser(updatedUser);
};

   
    
const  passwordChange= () => {

    const dto = {
        korisnickoIme: updateUser.username,
        staraLozinka: oldPassword,
        lozinka: password,
        ponovljenaLozinka: repeatedPassword
    }

    Axios.put('/korisnici/'+ updateUser.id +'?promenaLozinke', dto)
    .then(res => {
        console.log(res)
        auth.logout()
      })
      .catch(err => {
        console.log(err)
        alert("Greska, pokusajte ponovo!")
      })
}


    
    const edit = () => {

      
        const params = {
            id:updateUser.id,
            korisnickoIme:updateUser.username,
            email:updateUser.eMail,
            ime:updateUser.name,
            prezime:updateUser.surname
        }

        const confirmDelete = window.confirm( "Confirm data that will be saved!\n\n" +
                                          "Name: " + params.ime + "\n" +
                                          "Surname: " + params.prezime + "\n" +
                                          "eMail: " + params.email + "\n" +
                                          "Username: " + params.korisnickoIme
                                          );
             if (confirmDelete) {
        
            Axios.put("/korisnici/" + updateUser.id, params)
            .then(res => {
                console.log(res);
                navigate("/user");
            })
            .catch(error => {
                console.log(error);
            })
             }else{
                window.location.reload();
             }
    }



const handleEnterKeyPressData = (event) => {
  if (event.key === 'Enter') {
        edit()
  }
};

const handleEnterKeyPressPass = (event) => {
    if (event.key === 'Enter') {
        passwordChange();
    }
};
  

     return (
         <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                    <Form>
                        <Form.Group>
                            <Form.Label>Username</Form.Label>
                            <Form.Control
                                id="username" name="username" value={updateUser.username} onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Email</Form.Label>
                            <Form.Control id="eMail" name="eMail" value={updateUser.eMail} onChange={(e) =>  valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Name</Form.Label>
                            <Form.Control id="name" name="name" value={updateUser.name} onChange={(e) =>  valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Surname</Form.Label>
                            <Form.Control  id="surname" name="surname"  value={updateUser.surname} onChange={(e) =>  valueInputChanged(e)} /> <br />
                        </Form.Group>
            
                        <Row> <Button className="btn btn-success" onClick={edit}>Edit</Button></Row> 
                    
                    <Form.Label htmlFor="staraLozinka">Stara Lozinka</Form.Label>
                    <Form.Control id="staraLozinka" type="password" name="staraLozinka" onChange={(e) => setOldPassword(e.target.value)} onKeyDown={handleEnterKeyPressData}/>

                    <Form.Label htmlFor="lozinka">Lozinka</Form.Label>
                    <Form.Control id="lozinka" type="password" name="lozinka"  onChange={(e) => setPassword(e.target.value)}/>

                    <Form.Label htmlFor="ponovljenaLozinka">Ponovi lozinku</Form.Label>
                    <Form.Control id="ponovljenaLozinka" type="password" name="ponovljenaLozinka"  onChange={(e) => setRepeatedPassword(e.target.value)} onKeyDown={handleEnterKeyPressPass}/>
                    <br/>
                    <Row><Button className="btn btn-dark" onClick={() => passwordChange()}>Change password</Button><p align="center">If you change your password you will be logged out</p></Row>
                                </Form>
                </Col>
                <Col></Col>
            </Row>
    )



}



export default EditActiveUser;