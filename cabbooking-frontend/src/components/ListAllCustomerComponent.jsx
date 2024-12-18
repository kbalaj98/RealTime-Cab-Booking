import React,{useEffect, useState} from 'react'
import { deleteCustomer, listAllCustomer } from '../services/CustomerService'
import CustomerComponent from './CustomerComponent'
import {useNavigate,useParams} from 'react-router-dom'


function ListAllCustomerComponent() {

    const [customers, setCustomers]  = useState([])
    let {page} = useParams();
    const limit=10;

    console.log("page:",page)

    if(!page)
    {
        page=0;
    }

    const navigator = useNavigate()

    useEffect(()=>{
        getAllEmployess()
    },[page])

    function getAllEmployess()
    {
        listAllCustomer(page,limit).then((response)=>{
            setCustomers(response.data);
           // console.info("after set response::",response);
        }).catch((err)=>{
            console.error(err);
        })
    }


    function addCustomer()
    {
        navigator('/addCustomer')
    }

    function updateCustomer(cus_id,customer)
    {
        navigator(`/updateCustomer/${cus_id}`,{state:customer});
    }

    function pageNavigate(pageVal)
    {
        if(pageVal===0)
        {
            navigator('/')
        }
        else
        {
            navigator(`/${pageVal}`)
        }
    }

    function previousTab()
    {
        if(page === 0)
            {
                 return <li class="page-item disabled">
                        <a class="page-link" tabindex="-1">Previous</a>
                        </li>
            }
            else
            {
                return   <li class="page-item">
                        <a class="page-link"  onClick={()=>pageNavigate(parseInt(page)-1)}>Previous</a>
                        </li>
            }    
    }

    function nextTab()
    {
        if(customers.length===0 || customers.length<limit)
            {
                return <li class="page-item disabled">
                        <a class="page-link"  tabindex="-1">Next</a>
                        </li>
            }
            else
            {
                return <li class="page-item">
                        <a class="page-link"  onClick={()=>pageNavigate(parseInt(page)+1)}>Next</a>
                        </li>
            }
    }
    function deletingCustomer(cus_id)
    {
        deleteCustomer(cus_id).then((res)=>{

            let data = res.data

            if(data.Status === 'SUCCESS')
            {
                alert(`Customer ${data.id} deleted successfully`)
                getAllEmployess()
            }

        }).catch(err)
        {
            console.log(`delete customer ${cus_id}} ${err}`)
        }
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
                    <th>Mobile No</th>
                    <th>Gender</th>
                    <th>Action</th>
                </tr>

            </thead>
            <tbody>
                {
                    customers.map((customer,index)=>{
                    
                        return <tr key={customer.customerId}>
                            <td>{index+1+(page*limit)} </td>
                            <td>{customer.customerId}</td>
                            <td>{customer.name}</td>
                            <td>{customer.age}</td>
                            <td>{customer.emailId}</td>
                            <td>{customer.mobileNo}</td>
                            <td>{customer.gender}</td>
                            <td><button type="button" class="btn btn-info" onClick={()=>updateCustomer(customer.customerId,customer)}>update</button>
                            <button type="button" class="btn btn-danger" onClick={()=>deletingCustomer(customer.customerId)}
                                 style={{marginLeft:'10px'}}>Delete</button></td>
                        </tr>
                    })
                }
            </tbody>
        </table>
        <ul class="pagination justify-content-center">
            {
                previousTab()
            }
   
            <li class="page-item"><a class="page-link">{page}</a></li>

            {
                nextTab()
            }
            
        </ul>
    </div>
  )
}

export default ListAllCustomerComponent