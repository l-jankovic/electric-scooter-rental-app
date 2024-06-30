import Axios from "../../apis/Axios"
import { useNavigate } from "react-router-dom"
import { Button} from "react-bootstrap"
import { jwtDecode } from 'jwt-decode';

const ShowUser =(props) =>{

const token=localStorage.getItem("JWT");
const decoded=token?jwtDecode(token):null;
const isAdmin=decoded?.role?.authority==="ROLE_ADMIN"; 
 var navigate = useNavigate()

    const goToEdit = (UserId) => {
        navigate("/users/" + UserId)
    }

    const deleteUser = (UserId) => {

   
        
            Axios.delete("/korisnici/" + UserId)
            .then(res => {
               
                console.log(res);
                alert("User was deleted")
                window.location.reload()
            })
            .catch(error => {
                console.log(error);
                alert('Error occured please try again!');
            })

    }

//Ako  ne iniciramo u stateu nista onda Movie && Movie.naziv da ne bi pucalo null, tako za svaki parametar
  return (
           <tr>
           <td>{props.user.korisnickoIme}</td>
           <td>{props.user.eMail}</td>
           <td>{props.user.ime}</td>
           <td>{props.user.prezime}</td>
           { isAdmin&&
           <td><Button className="btn btn-warning" onClick={() => goToEdit(props.user.id)}>Edit</Button></td>
            }
            {isAdmin&&
           <td><Button className="btn btn-danger" onClick={() => deleteUser(props.user.id)}>Delete</Button></td>
            }
        </tr>
    )
}
     




export default ShowUser;