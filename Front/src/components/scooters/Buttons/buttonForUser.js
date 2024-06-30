

import { Button} from "react-bootstrap"

import { Role } from "../../../services/auth";


const ButtonForUsers=(props)=>{
const roleMatches = Role()===props.role;
    const rentedMatches = props.rented === undefined || props.rented ;


return(
  roleMatches && rentedMatches &&
<td> <Button disabled={props.disabled} hidden={props.hidden} className={props.color}  onClick={props.onClick}>{props.name}</Button></td>

)

}


export default ButtonForUsers;