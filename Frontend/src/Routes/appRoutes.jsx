import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Login from '../pages/login'
import Home from "../pages/home"
import Layout from '../components/Layout'

const appRoutes = () => {
  return (
    <BrowserRouter>
        <Routes>
            <Route path='/' element={<Login />}></Route>
            
            <Route element={<Layout/>}>
              <Route path="/home" element={<Home/>}/>
            </Route>

        </Routes>
      
    </BrowserRouter>
  )
}

export default appRoutes
