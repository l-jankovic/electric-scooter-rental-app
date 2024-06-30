import Axios from "../../apis/Axios"
import { useCallback, useEffect, useState } from "react"
import { useNavigate,Link } from "react-router-dom"
import { Row, Col, Form, Button } from "react-bootstrap";


const CreateUser=()=>{


        let user = {
        username:null,
        eMail:null,
        name:null,
        surname:null,
        password:null,
        repeatPassword:null

    }


    const[createdUser,setCreatedUser]=useState(user);

    var navigate = useNavigate();

 

 

    
    const addUser = () =>{

  
        const dto={
                    korisnickoIme:createdUser.username,
                    email:createdUser.eMail,
                    ime:createdUser.name,
                    prezime:createdUser.surname,
                    lozinka:createdUser.password,
                    ponovljenaLozinka:createdUser.repeatPassword


        }

    
        console.log(dto);

        Axios.post('/korisnici',dto).then(res =>{

        console.log(res);
        navigate("/");

    }).catch(err =>{
        console.log(err);
         console.error("AxiosError:", err);

    if (err.response) {
        console.error("Status kod:", err.response.status);
        console.error("Poruka:", err.response.data);
    }
        alert("Doslo je do greske");
    })

}

 const onValueChange=(e)=>{
        console.log(e.target.value)
        let input = e.target;

        let name = input.name;
        let value = input.value;

        let userFromState = createdUser;
        userFromState[name]=value;

        setCreatedUser(userFromState);
        console.log(userFromState);
    }   


    

         return (
            
            <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                <h1>Register</h1>
                    <Form>
                        <Form.Group>
                            <Form.Label>Username</Form.Label>
                            <Form.Control
                                id="username" name="username" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Email</Form.Label>
                            <Form.Control id="eMail" name="eMail" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Name</Form.Label>
                            <Form.Control id="name" name="name" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Surname</Form.Label>
                            <Form.Control  id="surname" name="surname" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Password</Form.Label>
                            <Form.Control  id="password" name="password" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                          <Form.Group>
                            <Form.Label>Repeat Password</Form.Label>
                            <Form.Control  id="passwordRepeat" name="repeatPassword" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                         <Row><Button className="btn btn-danger btn-block" onClick={addUser}>Register</Button> </Row>
                         If you  you have an account click here to <Link to={"/login"}><b>Login</b></Link>
                    </Form>
                </Col>
                <Col></Col>
            </Row>
    )   
}


export default CreateUser;