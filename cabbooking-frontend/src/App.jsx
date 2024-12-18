import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ListAllCustomerComponent from './components/ListAllCustomerComponent'
import HeaderComponent from './components/HeaderComponent'

import {BrowserRouter,Routes,Route} from 'react-router-dom'
import CustomerComponent from './components/CustomerComponent'

function App() {

  return (
    <>
      <BrowserRouter>
        <HeaderComponent/>
        <Routes>
        {/* http://localhost:3000/ */}
          <Route path='/' element = {<ListAllCustomerComponent/>}></Route>
          <Route path='/:page' element = {<ListAllCustomerComponent/>}></Route>


          {/* http://localhost:3000/addCustomer */}
          <Route path='/addCustomer' element={<CustomerComponent/>}></Route>

          {/* http://localhost:3000/updateCustomer/1 */}
          <Route path='/updateCustomer/:id' element={<CustomerComponent/>}></Route>

        </Routes>
        {/* <ListAllCustomerComponent/> */}

        {/* <AddCustomerComponent/> */}
      </BrowserRouter>

    </>
  )
}

export default App
