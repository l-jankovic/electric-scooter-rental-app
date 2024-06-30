import Axios from "../apis/Axios";
import { jwtDecode } from "jwt-decode";

const login = async (username, password) => {
    const user = {
        username: username,
        password: password
    };

    try {
        const res = await Axios.post("/korisnici/auth", user);
        const decoded_jwt = jwtDecode(res.data);
        window.localStorage.setItem("JWT", res.data); // Čuvanje JWT tokena
        window.localStorage.setItem("role", decoded_jwt.role.authority);
        window.localStorage.setItem("sub", decoded_jwt.sub);
        window.location.replace("http://localhost:3000");
    } catch (e) {
        console.log(e);
        alert("Login failed");
    }
};

const logout = () => {
    window.localStorage.removeItem("JWT");
    window.location.replace("http://localhost:3000");
};

export const Role = () => {
    let role = "";
    if (window.localStorage['role'] == 'ROLE_ADMIN') {
        role = "isAdmin";
    }
    if (window.localStorage['role'] == 'ROLE_KORISNIK') {
        role = "isUser";
    }
    return role;
};

export const IsLoggedIn = () => {
    return !window.localStorage["JWT"];
};

export const Username = () => {
    return window.localStorage['sub'];
};

export default { login, logout };