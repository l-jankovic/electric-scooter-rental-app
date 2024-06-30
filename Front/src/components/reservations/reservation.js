import Axios from "../../apis/Axios"
import {  useEffect, useState } from "react"
import { useNavigate, useParams, useRoutes } from "react-router-dom"
import { Row, Col, Form, Button } from "react-bootstrap";


const CreateRes=()=>{


    
    const routeParams = useParams()
    const scooterId = routeParams.id;


    const[reseravation,setReservations]=useState(
        {
             email:""
        }
    );


    


    
    

    
    
    
    




    var navigate = useNavigate()


    const addReservation = () =>{

   
        const dto={
                   email:reseravation.email,
                   trotinetId:scooterId

        }


        console.log(dto);
        const confirmReservation = window.confirm("Confirm your reservation");

        if(confirmReservation){

            dto.email === "" ? alert("You must enter your email!") : Axios.post('/rezervacije', dto).then(res => {
            console.log(res);
            navigate("/scooters");
            
            }).catch(err =>{
                console.error("Status kod:", err.response.status);
                console.error("Poruka:", err.response.data);
                console.error("AxiosError:", err);

                alert("Error has occured");
            })
                }else{
                    navigate('/scooters')
                }

}

  const onValueChange=(e)=>{
        const {name,value}=e.target;




        setReservations({ ...reseravation,[name]:value});
        console.log(e.target.value);
    }  


    

         return (
            
        <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8"  style={{ border: '2px solid #ddd', borderRadius: '5px', padding: '1em' }}>
           
                    <Form>
                        <Form.Group>
                            
                            <Form.Label>Enter your Email to confirm reservation:</Form.Label>
                            <Form.Control 
                                
                                name="email" 
                                onChange={onValueChange} 
                            />
                            <br />
                        </Form.Group>   
                        <Button 
                            className="btn btn-success" 
                            size="lg" 
                            onClick={addReservation}
                        >
                            Reserve scooter
                        </Button>
                    </Form>
         
            </Col>
            <Col></Col>
        </Row>
    );
}


export default CreateRes