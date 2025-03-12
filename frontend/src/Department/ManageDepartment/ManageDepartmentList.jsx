"use client"

import { useState } from "react"
import AddNewDepartment from "./AddNewDepartment"

export default function ManageDepartmentList() {
  const [isModalOpen, setIsModalOpen] = useState(false)
  const [departments, setDepartments] = useState([
    {
      id: 1,
      name: "OT Department",
      location: "4th floor",
      email: "ot@kcare.com",
      phone: "+91-79888-88888",
      manager: "Vijay Sharma",
      managerPhone: "+91-79888-88888",
      managerEmail: "vijay@kcare.com",
      activeSince: "3rd Nov, 2016",
      image:
        "https://plus.unsplash.com/premium_photo-1682130157004-057c137d96d5?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8aG9zcGl0YWx8ZW58MHx8MHx8fDA%3D",
    },
    {
      id: 2,
      name: "NICU",
      location: "4th floor",
      email: "ot@kcare.com",
      phone: "+91-79888-88888",
      manager: "Vijay Sharma",
      managerPhone: "+91-79888-88888",
      managerEmail: "vijay@kcare.com",
      activeSince: "3rd Nov, 2016",
      image:
        "https://plus.unsplash.com/premium_photo-1682130157004-057c137d96d5?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8aG9zcGl0YWx8ZW58MHx8MHx8fDA%3D",
    },
    {
      id: 3,
      name: "Cardiac",
      location: "4th floor",
      email: "ot@kcare.com",
      phone: "+91-79888-88888",
      manager: "Vijay Sharma",
      managerPhone: "+91-79888-88888",
      managerEmail: "vijay@kcare.com",
      activeSince: "3rd Nov, 2016",
      image:
        "https://plus.unsplash.com/premium_photo-1682130157004-057c137d96d5?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8aG9zcGl0YWx8ZW58MHx8MHx8fDA%3D",
    },
    // ... other department data
  ])

  const handleDelete = (id) => {
    // Handle delete functionality here
    setDepartments(departments.filter((dept) => dept.id !== id))
    console.log(`Delete department with id: ${id}`)
  }

  const handleView = (id) => {
    // Handle view functionality here
    console.log(`View department with id: ${id}`)
  }
  const handleAddDepartment = () => {
    // Create new department object
    setIsModalOpen(false)
  }

  return (
    <div className="p-12">
      <div className="px-4 py-6 w-full bg-white rounded-lg">
        {/* Header */}
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-2xl font-semibold text-secondary">Manage Department</h1>
          <button
            onClick={() => setIsModalOpen(true)}
            className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md transition-colors"
          >
            Add Department
          </button>
        </div>

        {/* Department List */}
        <div className="space-y-4">
          {departments.map((department) => (
            <div
              key={department.id}
              className="flex flex-col md:flex-row border border-gray-200 rounded-lg overflow-hidden"
            >
              {/* Department Image */}
              <div className="w-full md:w-1/5 bg-gray-200 flex items-center justify-center">
                <img
                  src={department.image || "/placeholder.svg"}
                  alt="Product"
                  className="object-cover h-full w-full"
                />
              </div>

              {/* Department Details */}
              <div className="flex-1 p-6">
                <h2 className="text-lg font-medium mb-2 text-secondary">{department.name}</h2>
                <p className="text-tertiary mb-2">
                  {department.location}, {department.email}, {department.phone}
                </p>
                <p className="text-value mb-2">
                  {department.manager}, {department.managerPhone}, {department.managerEmail}
                </p>
                <p className="text-key">Active since: {department.activeSince}</p>
              </div>

              {/* Actions */}
              <div className="flex md:flex-col justify-between md:justify-start items-center p-4 md:w-32">
                <button
                  onClick={() => handleView(department.id)}
                  className="text-blue-600 hover:text-white hover:bg-blue-700 px-4 py-1 border border-blue-600 rounded-md transition-all"
                >
                  View
                </button>
              </div>

              {/* Delete Button */}
              <div className="group bg-red-50 hover:bg-red-500 transition-all cursor-pointer flex items-center justify-center p-4 md:w-24">
                <button
                  onClick={() => handleDelete(department.id)}
                  className="text-red-500 group-hover:text-white transition-all"
                  aria-label="Delete department"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-8 w-8"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth={1.5}
                      d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
                    />
                  </svg>
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>

      {/* Add Department Modal */}
      <AddNewDepartment isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} onSubmit={handleAddDepartment} />
    </div>
  )
}

