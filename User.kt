package com.example.testcursoradapter

class User {

    private var id: Int = 0
    private var name: String = ""
    private var age: Int = 0

    constructor(name: String, age: Int) {

        this.name = name
        this.age = age

    }

    constructor() {

    }

    fun getId(): Int {

        return id

    }

    fun getName(): String {

        return name

    }

    fun getAge(): Int {

        return age

    }

    fun setId(newId: Int) {

        this.id = newId

    }

    fun setName(newName: String) {

        this.name = newName

    }

    fun setAge(newAge: Int) {

        this.age = newAge

    }

}