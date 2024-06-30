import Axios from "../../apis/Axios"
import {  useEffect, useState } from "react"
import { useNavigate, useParams, useRoutes } from "react-router-dom"
import { Row, Col, Form, Button } from "react-bootstrap";


const ReturningScooter=()=>{


    
    const routeParams = useParams()
    const scooterId = routeParams.id;


    const[returnScooter,setReturnScooter]=useState(
        {    
           
            battery:0,
            email:"",
            addressId:-1
            
        }
    );


    

    
    
const [addresses,setAddresses]=useState([]);

    


    
    
const getAddreses=()=>{
    Axios.get('/adrese').
    then(res=>{
        console.log(res)
        setAddresses(res.data);

    }).catch(err=>{
        console.log(err);
    })

}
    

    
    
    
        useEffect(()=>{
            getAddreses()
        },[]);




    var navigate = useNavigate()


    const updateScooterReturn = () =>{

   
        const dto={
                   adresaId:returnScooter.addressId,
                   email:returnScooter.email,
                   stanjeBaterije:returnScooter.battery


        }


        console.log(dto);
        const confirmReservation = window.confirm("Confirm your return");

        if(confirmReservation){

            dto.email === "" ? alert("You must enter your email!") : Axios.put('/trotineti/return/'+scooterId, dto).then(res => {
            console.log(res);
            navigate("/scooters");
            
            }).catch(err =>{
                console.error("Status kod:", err.response.status);
                console.error("Poruka:", err.response.data);
                console.error("AxiosError:", err);

                alert("Email doesn't match the logged user");
            })
                }else{
                    navigate('/scooters')
                }

}

  const onValueChange=(e)=>{
        const {name,value}=e.target;




        setReturnScooter({ ...returnScooter,[name]:value});
        console.log(e.target.value);
    }  


    

         return (
            
        <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8"  style={{ border: '2px solid #ddd', borderRadius: '5px', padding: '1em' }}>
           
                    <Form>

                        <Form.Group>
                             <Form.Label> Choose an Address</Form.Label>
                                <Form.Control as='select'  name="addressId" onChange={onValueChange} >
                                     <option value={""}>Choose an Address</option>
                                         {addresses.map((address, i) => (
                                             <option key={address.id} value={address.id}>{address.ulica}</option>
                                                ))}
                                               
                                </Form.Control>
                         </Form.Group>
                           <br />
                        <Form.Group>
                             <Form.Label>Enter Battery level</Form.Label>
                             <Form.Control  type="number" name="battery" max="100" onChange={onValueChange}  >
                             </Form.Control>

                        </Form.Group>
                          <br/>     
                        <Form.Group>
                            <Form.Label>Enter your Email:</Form.Label>
                            <Form.Control 
                                
                                name="email" 
                                onChange={onValueChange} />
                            <br />
                        </Form.Group>   
                        <Button 
                            className="btn btn-success" 
                            size="lg" 
                            onClick={updateScooterReturn}
                        >
                            Return scooter 
                        </Button>
                    </Form>
         
            </Col>
            <Col></Col>
        </Row>
    );
}


export default ReturningScooter