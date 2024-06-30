import axios from "axios";

const Axios = axios.create({
    baseURL: "http://localhost:8080/api"
})

    Axios.interceptors.request.use( config => {
        let token=window.localStorage['JWT'];
        if (token) {
          config.headers.Authorization = "Bearer " + token;
        }
        return config;
        // config.headers['Authorization']=`Bearer ${token}`
        
    }, // success response interceptor
    err => {
        // // usually you'd look for a 401 status ¯\_(ツ)_/¯
        // if (err.response?.data?.errorMessage === "jwt expired") {
        //   localStorage.removeItem('token');
        //   localStorage.removeItem('user');
        // }

        return Promise.reject(err);
    }
)

export default Axios;