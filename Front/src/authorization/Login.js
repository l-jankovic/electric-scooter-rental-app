import { useState } from "react"
import { Row, Col, Form, Button, Table } from "react-bootstrap"
import auth from "../services/auth"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import { Link } from "react-router-dom";

const Login=()=>{


    const[username,setUsername]=useState("")
    const[password,setPassword]=useState("")

    return(
        <Row className="justify-content-center">
            <Col md={6}>
            <Row><h1>Login</h1></Row>
                <Form className="border rounded p-3 table table-bordered table-striped">
                    <Form.Group>
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text" onChange={(e)=>setUsername(e.target.value)} />
                </Form.Group>
                <Form.Group>
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" onChange={(e)=>setPassword(e.target.value)} />
                </Form.Group>
              </Form>
              <Button className='btn btn-success' onClick={()=>auth.login(username,password)}><FontAwesomeIcon icon={faCheck} />Login</Button>
             <div className="container">If you don't have  an account click here to <Link to={"/registration"}><b>Register</b></Link></div> 
            </Col>
        </Row>




    )

}

export default Login;