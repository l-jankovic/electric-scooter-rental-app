import Axios from "../../apis/Axios"
import { useNavigate } from "react-router-dom"
import { useEffect, useState,useCallback } from "react"
import { Button, Col, Row, Table } from "react-bootstrap";
import ShowUser from "./ShowUser";
import { Role } from "../../services/auth";


const Users = () =>{

    const [users,setUsers]=useState([]);
     const [searchParams, setSearchParams] = useState({
        name: '',
        surname: '',
        email: ''
    });

    const [pageNo, setPageNo] = useState(0) // state za trenutni broj stranice
    const [maxPages, setMaxPages] = useState(0) // stat
    const navigate= useNavigate();


    const getUsers=useCallback(()=>{
            Axios.get(`/korisnici?pageNo=${pageNo}`
        ).then(res =>{
            console.log(res)
            setMaxPages(res.headers["total-pages"])
            setUsers(res.data)
        }).catch(err =>{
            console.log(err)
        })
    },[pageNo])

    


    useEffect(()=>{
        getUsers();
    },[pageNo,getUsers]);
    
    





      const handleChange=(e)=>{
        const {name,value}=e.target;




        setSearchParams({ ...searchParams,[name]:value});
        console.log(e.target.value);
    }

    const handleSearch=()=>{
        getUsers();
    }

        const goToCreate= ()=>{

        navigate("/registration");

    }






const renderKorisnici = () =>{

    return users.map((user)=>{
        return <ShowUser key={user.id} user={user}></ShowUser>
    })
} 
return (
      
    <Col>
        <   Row><h1>Users</h1></Row>
{/* 
                <Row>
        <div container="container">
            <Col>
                <Form>
                    <Form.Group controlId="name">
                        <Form.Label>Name</Form.Label>
                        <Form.Control name="name" type="text" onChange={handleChange} />
                    </Form.Group>
                    <Form.Group controlId="surname">
                        <Form.Label>Surname</Form.Label>
                        <Form.Control name="surname"  onChange={handleChange} />
                    </Form.Group>
                    <Form.Group controlId="email">
                        <Form.Label>Email</Form.Label>
                        <Form.Control name="email"  onChange={handleChange} />
                    </Form.Group>

                     <div container='container' style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                       
                         <Button onClick={handleSearch} style={{margin:'auto',marginTop:'30px'}} className="btn btn-success btn-lg" >Search</Button>
                       
                </div>
                </Form>
            </Col>
        </div>
    </Row>       */}

              
                  <br/>  
                         {Role()==='isAdmin'&&
                <Button className="button button-navy" onClick={() => goToCreate() }>Register</Button>
                }
                <br/>
                
               <Row><Col>
                <Table id="users-table">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Name</th>
                            <th>Surname</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderKorisnici()}
                    </tbody>                  
                </Table>
                </Col></Row>
                           <br/> 
                    <div >
                     <span style={{ display: 'flex', justifyContent: "flex-end" }}>
                        <Button
                            disabled={pageNo <= 0}
                            onClick={() => setPageNo(pageNo - 1)}
                        >
                            Previous
                        </Button>
                        <Button
                            disabled={maxPages === -1 || pageNo >= maxPages - 1}
                            onClick={() => setPageNo(pageNo + 1)}
                        >
                            Next
                        </Button>
                    </span>
                </div>
              </Col>
       
    )








}



export default Users;