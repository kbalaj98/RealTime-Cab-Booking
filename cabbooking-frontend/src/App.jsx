import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ListAllCustomerComponent from './components/ListAllCustomerComponent'
import HeaderComponent from './components/HeaderComponent'

import {BrowserRouter,Routes,Route} from 'react-router-dom'
import AddCustomerComponent from './components/AddCustomerComponent'

function App() {

  return (
    <>
      <BrowserRouter>
        <HeaderComponent/>
        <Routes>
        {/* http://localhost:3000/ */}
          <Route path='/' element = {<ListAllCustomerComponent/>}></Route>

          {/* http://localhost:3000/AllCustomers */}
          <Route path='/allCustomers' element ={<ListAllCustomerComponent/>}></Route>

          {/* http://localhost:3000/addCustomer */}
          <Route path='/addCustomer' element={<AddCustomerComponent/>}></Route>

        </Routes>
        {/* <ListAllCustomerComponent/> */}

        {/* <AddCustomerComponent/> */}
      </BrowserRouter>

    </>
  )
}

export default App
