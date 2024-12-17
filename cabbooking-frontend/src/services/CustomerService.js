import axios from 'axios'
import { data } from 'react-router-dom';

const REST_API_BASE_URL='http://localhost:6060/api/customer'

export const listAllCustomer = (start,limit)=>{

    let url = REST_API_BASE_URL+'/getAllCustomers'
    return  axios.get(url,{
        params : {
            "start":start,
            "limit":limit
        }
    });
}

export const addCustomer = (cus_name,email,gender,age,mobileNo)=>{

    return axios.post(REST_API_BASE_URL,{
        
        "name":cus_name,
        "emailId":email,
        "gender":gender,
        "age":age,
        "mobileNo":mobileNo
        
    })
}