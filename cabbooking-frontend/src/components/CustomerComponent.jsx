import React,{useEffect, useState} from 'react'
import { addCustomer,getCustomer,updateCustomer } from '../services/CustomerService'

import {useNavigate,useParams,useLocation} from 'react-router-dom'

export const CustomerComponent = () => {

    const {id} = useParams();

   // const {state} = useLocation();

    const navigate = useNavigate()

    const [name,setName] = useState('')
    const [email,setEmail] = useState('')
    const [gender,setGender] = useState('')
    const [age,setAge] = useState('')
    const [mobileNo,setMobileNo] = useState('')




    const[errors,setError] = useState({
        name:'',
        email:'',
        gender:'',
        age:'',
        mobileNo:''
    })




    useEffect(()=>{
        setCustomerIdValues()
        //console.log("select customer",state);
    },[id])
   

    //update customer value fetching
     function setCustomerIdValues()
     {
        if(id)
        {
            getCustomer(id).then((response)=>{

                let data = response.data;
    
                setName(data.name)
                setAge(data.age)
                setEmail(data.emailId)
                setGender(data.gender)
                setMobileNo(data.mobileNo)
    
            }).catch((e)=>
            {
                console.log(e);
            })
        }
     }

    function saveCustomer(e)
    {
        //prevent alter option
        e.preventDefault()

        if(validation())
        {
            if(id)
            {
                updateCustomer(id,name,email,gender,age,mobileNo).then((response)=>{
                    alertCustomerResponseAndNavigate(response)

                }).catch((err)=>{
                    console.error(err);
                })
            }
            else
            {
                addCustomer(name,email,gender,age,mobileNo).then((response)=>{
            
                    alertCustomerResponseAndNavigate(response)
                    //console.log(customer)
        
                   // document.getElementById('add_cus_res').innerHTML = JSON.stringify(customer)
        
                }).catch((err)=>{
                    console.error(err);
                })
            }
            
        }
    }

    function alertCustomerResponseAndNavigate(response)
    {
        const customer = response.data
        
                    if(response.status===201)
                    {
                        alert(`Customer added successfully customer id=${customer.customerId}`)
                    }
                    else
                    {
                        alert(`Customer updated successfully customer id=${customer.customerId}`)
                    }

                    navigate('/')
    }

    function validation()
    {
        let valid = true

        let error_copy = {... errors}

        if(name.trim())
        {
            error_copy.name = ''
        }
        else
        {
            valid=false
            error_copy.name = 'Name required'
        }

        if(gender!=undefined && gender.trim())
        {
            error_copy.name = ''
        }
        else
        {
            valid=false
            error_copy.email = 'Any gender need to select'
        }

        if(email.trim())
        {
            error_copy.name = ''
        }
        else
        {
            valid=false
            error_copy.email = 'Email required'
        }

        if(age!=0)
        {
            error_copy.age = ''
        }
        else
        {
            valid=false
            error_copy.age = 'age required'
        }

            
        if(mobileNo!=0)
        {
            error_copy.mobileNo = ''
        }
        else
        {
            valid=false
            error_copy.mobileNo = 'mobileNo required'
        }

        setError(error_copy)

        return valid

    }

    function handleName(e)
    {
        setName(e.target.value)
    }

    function handleEmail(e)
    {
        setEmail(e.target.value)
    }

    function handleAge(e)
    {
        setAge(e.target.value)
    }

    function handleMobilNo(e)
    {
        setMobileNo(e.target.value)
    }

    function handleGender(e)
    {
        setGender(e.target.value)
    }

    function pageTitle()
    {
        if(id)
        {
            return <h2 className='text-center'>Update Customer</h2>
        }
        else
        {
            return <h2 className='text-center'>Add Customer</h2>
        }
    }

  return (

   
    <div>
         <br></br>
        <form method='POST' className='container' onLoad={setCustomerIdValues}>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                <div className='card-body'>
                    {pageTitle()}
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Name</label>
                        <div class="col-sm-10">
                            <input name='Name' id="cus_name" 
                            className={`form-control ${errors.name ? 'is-invalid':''}`}
                             placeholder="Arun k" value={name} onChange={handleName}/>
                             {errors.name && <div className='invalid-feedback'>{errors.name}</div>}
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input name='Mail Id' id="cus_email_id" className={`form-control ${errors.email ? 'is-invalid':''}`} placeholder="arun.xxx@gmail.com" value={email} onChange={handleEmail}/>
                            {errors.email && <div className='invalid-feedback'>{errors.email}</div>}

                        </div>

                        
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Gender</label>
                        <div class="col-sm-10">
                            <input type='radio' id="cus_male"  class="form-check-input" name="gender" value='Male' onChange={handleGender} />
                            <label>Male</label>
                            <input type='radio' id="cus_female" class="form-check-input" name="gender" value='Female' onChange={handleGender}/>
                            <label>Female</label>
                            <input type='radio' id="cus_others" class="form-check-input" name="gender" value='Others' onChange={handleGender}/>
                            <label>Others</label>

                            {errors.age && <div className='invalid-feedback'>{errors.age}</div>}
                           
                        </div>
                        
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Age</label>
                        <div class="col-sm-10">
                        <input name='Age' id="cus_age" className={`form-control ${errors.age ? 'is-invalid':''}`} placeholder="24" value={age} onChange={handleAge}/>
                        {errors.gender && <div className='form-check-label'>{errors.gender}</div>}


                        </div>

                        
                    </div >

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Mobile No</label>
                        <div class="col-sm-10">
                        <input name="Mobile No" id='cus_mob' className={`form-control ${errors.mobileNo ? 'is-invalid':''}`} placeholder="9829xxxxxx" value={mobileNo} onChange={handleMobilNo}/>
                        {errors.mobileNo && <div className='invalid-feedback'>{errors.mobileNo}</div>}
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="button" class="btn btn-primary" onClick={saveCustomer}>Submit</button>
                        </div> 
                        {/* <div id='add_cus_res'></div> */}
                    </div>
                </div>

                

            </div>
        </form>

        
    </div>
  )
}

export default CustomerComponent
