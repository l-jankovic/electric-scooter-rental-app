import { useNavigate } from "react-router-dom"
import { useEffect, useState,useCallback } from "react"
import { Button, Col, Row, Table,Form } from "react-bootstrap";
import Axios from "../../apis/Axios";
import { Role } from "../../services/auth";
import ShowScooters from "./showScooters";
import { IsLoggedIn } from "../../services/auth";
const Scooters = ()=>{

const [pageNo, setPageNo] = useState(0) // state za trenutni broj stranice
const [maxPages, setMaxPages] = useState(0) // stat
const [scooters,setScooters]=useState([]);
const [addresses,setAddresses]=useState([]);
 const [searchParams, setSearchParams] = useState({
    addressId:"",
    batFrom:"",
    batTo:""
});

 const navigate= useNavigate();


const getScooters=useCallback(()=>{
    Axios.get(`/trotineti?pageNo=${pageNo}`,{
         Headers:{'Content-Type': 'application/json'
        },
        data: {},
        params:{
          adresaId:searchParams.addressId,
          batOd:searchParams.batFrom,
          batDo:searchParams.batTo
        }
    }).then(res =>{
        console.log(res)
        setMaxPages(res.headers["total-pages"])
        setScooters(res.data)
    }).catch(err =>{
        console.log(err.data)
    })
},[searchParams,pageNo])


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
  getScooters()
  },[pageNo]);

    
 
const handleChange=(e)=>{
    const {name,value}=e.target;




    setSearchParams({ ...searchParams,[name]:value});
    console.log(e.target.value);
} 

const handleSearch=()=>{
  getScooters()
}







const renderScooters = () =>{

return scooters.map((scooter,index)=>{
    
    return <ShowScooters key={scooter.id}  scooter={scooter}  ></ShowScooters>
})
} 

    const goToCreate= ()=>{

    navigate("/scooters/create");

}
const handleEnterKeyPressPass = (event) => {
if (event.key === 'Enter') {
    getScooters()
}
};


return (
  
<Col>
    < Row><h1>Scooters</h1></Row>
                           <Row>
                            <div className="container">
                                <Form>
                                    <Row>
                                        <Col>
                                            <Form.Group>
                                                <Form.Label>Addresses</Form.Label>
                                                <Form.Control as='select' id="addressId" name="addressId" onChange={handleChange} onKeyDown={handleEnterKeyPressPass}>
                                                    <option value={""}>Choose address</option>
                                                    {addresses.map((address, i) => (
                                                        <option key={address.id} value={address.id}>{address.ulica}</option>
                                                    ))}
                                                </Form.Control>
                                            </Form.Group>
                                        </Col>
                                        <Col>
                                            <Form.Group>
                                                <Form.Label>Energy level from</Form.Label>
                                                <Form.Control type="number" id="batForm" name="batFrom" onChange={handleChange} onKeyDown={handleEnterKeyPressPass} placeholder="battery from" />
                                            </Form.Group>
                                        </Col>
                                        <Col>
                                            <Form.Group>
                                                <Form.Label>Energy level to</Form.Label>
                                                <Form.Control type="number" id="batTo" name="batTo" onChange={handleChange} onKeyDown={handleEnterKeyPressPass} placeholder="battery to" />
                                            </Form.Group>
                                        </Col>
                                        <Col className="d-flex align-items-end">
                                            <Button onClick={handleSearch} className="btn btn-primary" style={{ display: 'inline' }}>Search</Button>
                                        </Col>
                                    </Row>
                                </Form>
                            </div>
                        </Row>

                          <br/> <br/> <br/>
                
           

           <Row><Col> 
            <div style={{ display: 'flex', justifyContent: "space-between",marginBottom:'5px' }}>

                    <div >
                    {Role() == 'isAdmin' || IsLoggedIn() &&
                        <Button  variant="primary" onClick={goToCreate} >Create Scooter</Button>
                    }
                </div>
                <div>
                    <Button disabled={pageNo <= 0} onClick={() => setPageNo(pageNo - 1)} className="btn btn-info" style={{ color: 'white' }}>Previous</Button>
                    <Button disabled={maxPages === -1 || pageNo >= maxPages - 1} onClick={() => setPageNo(pageNo + 1)} className="btn btn-info" style={{ color: 'white', marginLeft: '4px' }}>Next</Button>
                </div>


            </div>            
            <Table id="assigment-table"className="table-striped  table-hover"  >
               <thead className="table-dark" >
                    <tr >
                        <th>Scooter code</th>
                        <th>Batery level</th>
                        <th>Max speed "(km/h) </th>
                        <th>Address</th>
                        
                        {IsLoggedIn() &&
                        <th></th>}
                        {Role()== 'isUser' || IsLoggedIn()  && <th></th>}
                         {Role()=='isAdmin' || IsLoggedIn() &&
                        <th></th>}

                    </tr>
                </thead>
                <tbody >
                    {renderScooters()}
        
                </tbody>                  
            </Table>
            

                                
       
                                
          
            </Col></Row>
            
           
          </Col>
   
)


}
export default Scooters;