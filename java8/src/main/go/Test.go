package main

import (
	"fmt"
	"strconv"
	"strings"
	"time"
)

var name string

func test() int {
	fmt.Println("test()")
	return 90
}

func init() {
	fmt.Println("init()...")
}
func main() {
	start := time.Now().Unix()
	test03()
	end := time.Now().Unix()

	fmt.Println("%v\n", end-start)

	println()
	fmt.Println("main()...")
	f := AddUpper()
	fmt.Println(f(1))
	fmt.Println(f(2))
	fmt.Println(f(3))
	s := makeSuffix(".jpg")
	fmt.Println(s("b.jpg"))
	fmt.Println(s("c.ps"))
	fmt.Println(s("d."))

	if r, err := strconv.Atoi("3"); err != nil {
		fmt.Println(err)
	} else {
		fmt.Println(r)
	}

	fmt.Println(strings.Trim("hollowed", "owed"))

}

func AddUpper() func(int) int {
	n := 10
	return func(x int) int {
		n += x
		return n
	}
}

func makeSuffix(suffix string) func(str string) string {
	return func(str string) string {
		if !strings.HasSuffix(str, suffix) {
			return str + suffix
		}

		return str
	}
}
