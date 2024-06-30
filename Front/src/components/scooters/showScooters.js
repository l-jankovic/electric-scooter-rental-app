import Axios from "../../apis/Axios"
import { useNavigate } from "react-router-dom"
import ButtonForUsers from "./Buttons/buttonForUser";
import { Role } from "../../services/auth";
import { IsLoggedIn } from "../../services/auth";
const ShowScooters =(props) =>{

 
 var navigate = useNavigate()

    const reserve=(scooterId)=>{
        navigate('/reservations/'+scooterId)

    }

    
    const returnScooter=(scooterId)=>{
        navigate('/return/' +scooterId)

    }
    const deleteScooter = (scooterId) => {

   
    
            Axios.delete("/trotineti/" + scooterId)
            .then(res => {

                console.log(res);
                alert("Scooter was deleted")
                window.location.reload()
            })
            .catch(error => {
                console.log(error);
                alert('Error occured please try again!');
            })

    }
    const charge = (scotId) => {

   
        
        Axios.put("/trotineti/charge/" + scotId,{
        Headers:{'Content-Type': 'application/json'
        }
        })
        .then(res => {
           
            console.log(res);
            alert("Recharged")
           window.location.reload();
        })
        .catch(error => {
            console.log(error);
            alert('Error occured please try again!');
        })
}


    const checkBatteryForRent=(batteryLevel)=>{

        if(batteryLevel<10){
           return(   <ButtonForUsers role='isUser' rented={props.scooter.iznajmljen===false}  color={'btn btn-primary'} hidden='true'></ButtonForUsers>
           )
        }else{
            return(
        <ButtonForUsers role='isUser' rented={props.scooter.iznajmljen===false} onClick={()=> reserve(props.scooter.id)} color={'btn btn-primary'}  name='Rent'></ButtonForUsers>
            
            )
        }
    }
   //function validate()
    if(IsLoggedIn()){
    
  return (

       
           <tr>
           <td>{props.scooter.sifra}</td>
           <td>{props.scooter.nivoBat}</td>
           <td>{props.scooter.maxBrzina + " km"}</td>
           <td>{props.scooter.ulica + props.scooter.brojAdresa}</td>
            
 
            {checkBatteryForRent(props.scooter.nivoBat)}
            <ButtonForUsers role='isUser' rented={props.scooter.iznajmljen===true} color={'btn btn-light'} disabled='true'  name='Rented'></ButtonForUsers>
            {Role()==='isUser' && props.scooter.iznajmljen===false&&<td></td>}
            <ButtonForUsers role='isUser' rented={props.scooter.iznajmljen===true} color={'btn btn-secondary'} onClick={()=> returnScooter(props.scooter.id)}  name='Return scooter'></ButtonForUsers>
            <ButtonForUsers role='isAdmin' rented={props.scooter.iznajmljen===false} onClick={()=> deleteScooter(props.scooter.id)} color={'btn btn-danger'}   name='Delete'></ButtonForUsers>
            <ButtonForUsers role='isAdmin' rented={props.scooter.iznajmljen===true}  color={'btn btn-light'} disabled='true'  name='Rented'></ButtonForUsers>
              {Role()==='isAdmin' && props.scooter.iznajmljen===true&&<td></td>}
            <ButtonForUsers role='isAdmin' rented={props.scooter.iznajmljen===false}  color={'btn btn-warning'} onClick={()=> charge(props.scooter.id)}  name='Recharge'></ButtonForUsers>

        </tr>
    )

    }else{

        return(
           <tr>
           <td>{props.scooter.sifra}</td>
           <td>{props.scooter.nivoBat}</td>
           <td>{props.scooter.maxBrzina + " km"}</td>
           <td>{props.scooter.ulica + props.scooter.brojAdresa}</td>
            
         </tr>

    )}
}
     




export default ShowScooters;