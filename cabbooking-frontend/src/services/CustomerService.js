import axios from 'axios'
import { data } from 'react-router-dom';

const REST_API_BASE_URL='http://localhost:6060/api/customer'

export const listAllCustomer = (page,limit)=>{

    let url = REST_API_BASE_URL+'/getAllCustomers'
    return  axios.get(url,{
        params : {
            "page":page,
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


export const updateCustomer = (id,cus_name,email,gender,age,mobileNo)=>{
    let url = REST_API_BASE_URL+`/updateCustomer/${id}`
    return axios.put(url,{
        "name":cus_name,
        "emailId":email,
        "gender":gender,
        "age":age,
        "mobileNo":mobileNo
    })
}


export const getCustomer = (id)=>{
    let url = REST_API_BASE_URL+`/${id}`

    return axios.get(url)
}

export const deleteCustomer = (id)=>{
    let url = REST_API_BASE_URL+`/${id}`
    return axios.delete(url)
}