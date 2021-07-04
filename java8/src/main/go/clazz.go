package main

import (
	"encoding/json"
	"fmt"
	"math"
	"strconv"
)

type Cat struct {
	Name  string
	Age   int
	Color string
	Hobby string
}

func (cat Cat) String() string {
	return "Name: " + cat.Name + ",Age: " + strconv.Itoa(cat.Age) + ",Color: " + cat.Color + ",Hobby: " + cat.Hobby
}

var cat Cat

func main() {
	cat.Name = "小白"
	cat.Age = 10
	cat.Color = "blue"
	cat.Hobby = "eat"

	fmt.Println(cat)

	var cat Cat = Cat{Name: "name"}
	fmt.Println(cat)

	var c *Cat = new(Cat)
	fmt.Println(*c)
	c.Name = "c"
	c.Age = 3
	fmt.Println(*c)

	var t *Cat = &Cat{
		Name:  "",
		Age:   0,
		Color: "",
		Hobby: "",
	}

	fmt.Println(&t)
	fmt.Println(*t)

	var p1 Person

	p1.Age = 10
	p1.Name = "xiao ming"
	var p2 *Person = &p1

	fmt.Println((*p2).Age)
	fmt.Println(p2.Age)

	p2.Name = "xiaohong"
	fmt.Printf("p2.name = %v\tp1.name = %v\n", p2.Name, p1.Name)
	fmt.Printf("p2.name = %v\tp1.name = %v\n", (*p2).Name, p1.Name)
	fmt.Println((*p2).Age)

	//println("p1 addr %p\n", &p1)
	//println("p2 addr %p\n", &p2)

	rect := Rect{Point{0, 0}, Point{1, 1}}
	fmt.Println("-----------")
	fmt.Printf("%p\t%p\t%p\t%p\n", &rect.leftUp.x, &rect.leftUp.y, &rect.rightDown.x, &rect.rightDown.y)
	fmt.Printf("%p\t%p\n", &rect.leftUp, &rect.rightDown)
	fmt.Println("-----------")

	var rec Rec
	rec = Rec(rect)
	fmt.Println(rec)

	monster := Monster{
		Name: "zhangsan",
		Age:  330,
	}

	if jsonStr, err := json.MarshalIndent(monster, "", "  "); err == nil {
		fmt.Println(string(jsonStr))
	}
	monster.test()

	var s Circle = Circle{
		radius: 4.0,
	}
	println(s.area())

	var i integer = 10
	i.print()
	i.change()
	fmt.Println("i=", i)
	i.print()

	var m MethodUtils
	m.Print(3, 2)
}

type Person struct {
	Name string
	Age  int
}

type Point struct {
	x, y byte
}

type Rect struct {
	leftUp, rightDown Point
}

type Rec Rect

type Monster struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
}

func (m Monster) test() {
	fmt.Println(m.Age)
}

type Circle struct {
	radius float64
}

func (c *Circle) area() float64 {
	return (math.Pi * c.radius * c.radius) / 2
}

type integer int

func (i integer) print() {
	fmt.Println("i=", i)
}

func (i *integer) change() {
	*i = *i + 1
}

type MethodUtils struct {
}

func (_ MethodUtils) Print(m, n int) {
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			fmt.Print("*")
		}
		fmt.Println()
	}
}
