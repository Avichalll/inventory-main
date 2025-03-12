"use client"

import { useState } from "react"

function AddNewDepartment({ isOpen, onClose, onSubmit }) {
  const [formData, setFormData] = useState({
    departmentName: "",
    departmentMail: "",
    contactNo: "",
    departmentLocation: "",
    deptHead: "",
    deptHeadContact: "",
    deptHeadMail: "",
    activeSinceDate: "",
  })

  const [departmentImage, setDepartmentImage] = useState(null)
  const [isDragging, setIsDragging] = useState(false)

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }))
  }

  const handleFileChange = (e) => {
    const file = e.target.files[0]
    if (file) {
      setDepartmentImage(file)
      setFormData((prev) => ({
        ...prev,
        imageUrl: URL.createObjectURL(file),
      }))
    }
  }

  const handleDragOver = (e) => {
    e.preventDefault()
    setIsDragging(true)
  }

  const handleDragLeave = () => {
    setIsDragging(false)
  }

  const handleDrop = (e) => {
    e.preventDefault()
    setIsDragging(false)

    const file = e.dataTransfer.files[0]
    if (file && file.type.startsWith("image/")) {
      setDepartmentImage(file)
      setFormData((prev) => ({
        ...prev,
        imageUrl: URL.createObjectURL(file),
      }))
    }
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    onSubmit(formData)
    // Reset form
    setFormData({
      departmentName: "",
      departmentMail: "",
      contactNo: "",
      departmentLocation: "",
      deptHead: "",
      deptHeadContact: "",
      deptHeadMail: "",
      activeSinceDate: "",
    })
    setDepartmentImage(null)
  }

  const handleBrowseClick = () => {
    document.getElementById("department-image-upload").click()
  }

  if (!isOpen) return null

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 w-full max-w-lg max-h-[90vh] overflow-y-auto">
        <h2 className="text-xl font-medium text-gray-800 mb-6">New Department</h2>

        <form onSubmit={handleSubmit} className="space-y-4">
          {/* Image Upload Area */}
          <div className="flex flex-col items-center mb-6">
            <div
              className={`border-2 border-dashed rounded-md w-48 h-48 flex flex-col items-center justify-center cursor-pointer ${isDragging ? "border-blue-500 bg-blue-50" : "border-gray-300"}`}
              onDragOver={handleDragOver}
              onDragLeave={handleDragLeave}
              onDrop={handleDrop}
              onClick={handleBrowseClick}
            >
              {departmentImage ? (
                <img
                  src={formData.imageUrl || "/placeholder.svg"}
                  alt="Department"
                  className="w-full h-full object-cover rounded-md"
                />
              ) : (
                <div className="text-center p-4">
                  <p className="text-gray-500 text-sm">Drag image here</p>
                  <p className="text-gray-500 text-sm">or</p>
                  <p className="text-blue-500 text-sm mt-1">Browse Image</p>
                </div>
              )}
            </div>
            <input
              type="file"
              id="department-image-upload"
              onChange={handleFileChange}
              className="hidden"
              accept="image/*"
            />
          </div>

          {/* Form Fields */}
          <div className="space-y-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Department Name</label>
              <input
                type="text"
                name="departmentName"
                value={formData.departmentName}
                onChange={handleChange}
                className="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-1 focus:ring-blue-500"
                placeholder="Enter department name"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Department Mail</label>
              <input
                type="email"
                name="departmentMail"
                value={formData.departmentMail}
                onChange={handleChange}
                className="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-1 focus:ring-blue-500"
                placeholder="Enter department email"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Contact No.</label>
              <input
                type="tel"
                name="contactNo"
                value={formData.contactNo}
                onChange={handleChange}
                className="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-1 focus:ring-blue-500"
                placeholder="Enter dept. contact number"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Department Location</label>
              <input
                type="text"
                name="departmentLocation"
                value={formData.departmentLocation}
                onChange={handleChange}
                className="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-1 focus:ring-blue-500"
                placeholder="Enter dept location"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Dept. Head</label>
              <input
                type="text"
                name="deptHead"
                value={formData.deptHead}
                onChange={handleChange}
                className="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-1 focus:ring-blue-500"
                placeholder="Enter dept head name"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Dept. Head Contact</label>
              <input
                type="tel"
                name="deptHeadContact"
                value={formData.deptHeadContact}
                onChange={handleChange}
                className="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-1 focus:ring-blue-500"
                placeholder="Enter dept head contact no"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Dept. Head Mail</label>
              <input
                type="email"
                name="deptHeadMail"
                value={formData.deptHeadMail}
                onChange={handleChange}
                className="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-1 focus:ring-blue-500"
                placeholder="Enter dept head mail id"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Active Since Date</label>
              <input
                type="date"
                name="activeSinceDate"
                value={formData.activeSinceDate}
                onChange={handleChange}
                className="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-1 focus:ring-blue-500"
                placeholder="Enter date since dept is active"
                required
              />
            </div>
          </div>

          {/* Action Buttons */}
          <div className="flex justify-end gap-4 mt-8">
            <button
              type="button"
              onClick={onClose}
              className="px-6 py-2 border border-gray-300 rounded-md text-gray-600 hover:bg-gray-50 transition-colors"
            >
              Discard
            </button>
            <button
              type="submit"
              className="px-6 py-2 bg-blue-700 text-white rounded-md hover:bg-blue-800 transition-colors"
            >
              Add Department
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default AddNewDepartment

