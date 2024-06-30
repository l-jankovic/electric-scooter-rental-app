import Axios from "../../apis/Axios"
import {  useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { Row, Col, Form, Button } from "react-bootstrap";


const CreateScooter=()=>{




    const[createdScooter,setCreatedScooter]=useState({
        code:"",
        maxSpeed:"",
        addressId:-1

    });
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


    const addScooter = () =>{

   
        const dto={
                   sifra:createdScooter.code,
                   maxBrzina:createdScooter.maxSpeed,
                   adresaId:createdScooter.addressId

        }


        console.log(dto);

        Axios.post('/trotineti',dto).then(res =>{

        console.log(res);
        navigate("/scooters")

    }).catch(err =>{
        console.error("Status kod:", err.response.status);
        console.error("Poruka:", err.response.data);
         console.error("AxiosError:", err);

        alert("Doslo je do greske");
    })

}

  const onValueChange=(e)=>{
        const {name,value}=e.target;




        setCreatedScooter({ ...createdScooter,[name]:value});
        console.log(e.target.value);
    }  


    

         return (
            
            <Row >
                <Col></Col>
                <Col xs="12" sm="10" md="8">
          
                    <Form>

                                     <Form.Group>
                                        <Form.Label>Scooter code</Form.Label>
                                        <Form.Control name="code" onChange={onValueChange}  >
                                        </Form.Control>
                                        <br />
                                    </Form.Group>  
                                    <Form.Group>
                                        <Form.Label>Max speed</Form.Label>
                                        <Form.Control  type="number" name="maxSpeed" max="30" onChange={onValueChange}  >
                                        </Form.Control>
                                        <br />
                                    </Form.Group>   
                                    <Form.Group>
                                        <Form.Label> Addresses</Form.Label>
                                        <Form.Control as='select'  name="addressId" onChange={onValueChange} >
                                            <option value={""}>Choose an Address</option>
                                                {addresses.map((address, i) => (
                                                    <option key={address.id} value={address.id}>{address.ulica}</option>
                                                ))}
                                               
                                        </Form.Control>
                                        </Form.Group>
                                        <br />
                    <Button className="btn btn-success" size="lg" onClick={addScooter}>addScooter</Button>
                    </Form>
                </Col>
                <Col></Col>
          
            </Row>
    )   
}


export default CreateScooter