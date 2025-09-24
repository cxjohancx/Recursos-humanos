import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Login from '../pages/login'
import Home from "../pages/home"
import Layout from '../components/Layout'
import PrivateRoute from './PrivateRoute'
import Personal from "../pages/Personal"
import Departamentos from "../pages/Departamentos"


const appRoutes = () => {
  return (
    <BrowserRouter>
        <Routes>
            <Route path='/' element={<Login />}></Route>
            
            <Route element={<PrivateRoute/>}>
              <Route element={<Layout/>}>
                <Route path="/home" element={<Home/>}/>
                <Route path="/personal" element={<Personal/>}/>
                <Route path="/departamentos" element={<Departamentos/>}/>
              </Route>
            </Route>

        </Routes>
      
    </BrowserRouter>
  )
}

export default appRoutes
