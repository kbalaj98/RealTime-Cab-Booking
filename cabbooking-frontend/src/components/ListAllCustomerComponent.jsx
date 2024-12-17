import React,{useEffect, useState} from 'react'
import { listAllCustomer } from '../services/CustomerService'
import AddCustomerComponent from './AddCustomerComponent'
import {useNavigate} from 'react-router-dom'


function ListAllCustomerComponent() {

    const [customers, setCustomers]  = useState([])

    const navigator = useNavigate()

    useEffect(()=>{
        listAllCustomer(0,100).then((response)=>{
            setCustomers(response.data);
            console.info("after set response::",response);
        }).catch((err)=>{
            console.error(err);
        })
        
    },[])


    // const custArr = [
    //     {id:1,name:"arun",age:20},
    //     {id:2,name:"balaji k",age:22},
    //     {id:3,name:"dhanaprashanth",age:25}
    // ]


    function addCustomer()
    {
        navigator('/addCustomer')
    }

    function updateCustomer(cus_id)
    {
        navigator(`/updateCustomer/${cus_id}`)
    }

  return (
    <div className='container'>
        <h2 className='text-center'>List All Customer</h2>
        <button type="button" class="btn btn-info" onClick={addCustomer}>Add Customer</button>
        <table className='table table-striped table-hover table-bordered'>
            <thead>
                <tr>
                    <th>S.No</th>
                    <th>Customer Id</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Email</th>
                    <th>Gender</th>
                </tr>

            </thead>
            <tbody>
                {
                    customers.map((customer,index)=>{
                    
                        return <tr key={customer.customerId}>
                            <td>{index+1}</td>
                            <td>{customer.customerId}</td>
                            <td>{customer.name}</td>
                            <td>{customer.age}</td>
                            <td>{customer.emailId}</td>
                            <td>{customer.gender}</td>
                            <td><button type="button" class="btn btn-info" onClick={()=>updateCustomer(customer.customerId)}>update</button></td>
                        </tr>
                    })
                }
            </tbody>
        </table>
    </div>
  )
}

export default ListAllCustomerComponent