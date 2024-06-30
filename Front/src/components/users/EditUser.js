import {  useParams } from "react-router-dom"
import Axios from "../../apis/Axios"
import { useCallback, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { Row, Col, Form, Button } from "react-bootstrap";







const  EditUser =()=>{


    const routeParams = useParams()
    const userId = routeParams.id;



    const navigate = useNavigate();


     const [updateUser, setUpdateUser] = useState({
     userId: -1,
     username: "",
     eMail: "",
     name: "",
     surname: ""
        });



      const getUserById = useCallback(()=>{
                Axios.get("/korisnici/"+userId).then(res =>{
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
      },[userId])  

 

    useEffect(() => {
    getUserById();
   
 },[getUserById])

       
       
       
       const valueInputChanged = (e) => {
        let input = e.target;

        let name = input.name;
        let value = input.value;

        let userFromState = updateUser;
        userFromState[name] = value;

        setUpdateUser(userFromState)
        console.log(userFromState)
    }

   
/*     
const izmenaLozinke = () => {

    const dto = {
        korisnickoIme: korisnik.korisnickoIme,
        staraLozinka: oldPassword,
        lozinka: password,
        ponovljenaLozinka: repeatedPassword
    }

    Axios.put('/korisnici/'+ korisnik.id +'?promenaLozinke', dto)
    .then(res => {
        console.log(res)
        {logout()}
      })
      .catch(err => {
        console.log(err)
        alert("Greska, pokusajte ponovo!")
      })
} */


    
    const edit = () => {

      
        const params = {
            id:updateUser.id,
            korisnickoIme:updateUser.username,
            email:updateUser.eMail,
            ime:updateUser.name,
            prezime:updateUser.surname
        }
        
            Axios.put("/korisnici/" + userId, params)
            .then(res => {
                console.log(res);
                navigate("/users");
            })
            .catch(error => {
                console.log(error);
            })

    }


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
            
                          <Button className="btn btn-success" onClick={edit}>Edit</Button>
                    </Form>
                </Col>
                <Col></Col>
            </Row>
    )



}



export default EditUser;