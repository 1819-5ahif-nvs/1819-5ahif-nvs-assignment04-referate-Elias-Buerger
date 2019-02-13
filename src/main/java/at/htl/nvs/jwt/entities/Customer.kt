package at.htl.nvs.jwt.entities

class Customer(name: String, password: String, var nickname: String) : User(name, password)