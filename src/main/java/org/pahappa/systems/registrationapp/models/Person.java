package org.pahappa.systems.registrationapp.models;

import org.w3c.dom.ls.LSOutput;

public class Person {
    private static String name;
    private static int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name is" + name + "age " + + age;
    }

    public boolean equals(Object obj){
        if(this ==obj){
            return true;
        }

        else if (obj== null || this.getClass() != obj.getClass()){
            return false;
        }
        return false;
    }

    public static int hashcode(){
        int x= 7;

        x= 13 * age;
        x= 13 * name.hashCode();

        return x;
    }
    public static void main(String args[]){
        Person person = new Person("Peter", 5);
        Person myperson= new Person ("Peter", 5);
        System.out.println(person);

        System.out.println(person == myperson);

        System.out.println(person.hashCode());
        System.out.println(myperson.hashCode());

        System.out.println(hashcode());
    }
}
