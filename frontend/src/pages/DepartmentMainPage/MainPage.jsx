import React from 'react'
import Header from '../../common/Header'
import Sidebar from '../../common/Sidebar'
import ManageDepartmentList from '../../Department/ManageDepartment/ManageDepartmentList'

export default function DepartmentMainPage() {
  return (
    <>
        <div className="flex w-full h-screen overflow-hidden">
            <Sidebar/>
            <div className="flex flex-col w-full  overflow-y-scroll">
                <Header/>
                <ManageDepartmentList/>
            </div>
        </div>
    </>
  )
}
