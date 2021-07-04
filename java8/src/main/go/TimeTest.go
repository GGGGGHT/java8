package main

import (
	"errors"
	"fmt"
	"math/rand"
)

func main() {
	//res()
	//if err := readConf("config.ini"); err != nil {
	//	panic(err)
	//}
	//
	//var a [26]byte
	//for i := 0; i < 26; i++ {
	//	a[i] = 'A' + byte(i)
	//}
	//fmt.Println("go on")
	//
	//for i := 0; i < len(a); i++ {
	//	fmt.Printf("%c\t", a[i])
	//}
	//var arr = []int{3, 2, 1}
	//for index, value := range arr {
	//	fmt.Printf("arr[%d] = %v\t", index, value)
	//}
	//fmt.Println()
	//changeArr(&arr)
	//
	//println(getMaxVal([]int{1, -10, 9, 5, 233}))
	//fmt.Println()
	//generateIntAndReverse()
	var intArr [5]int = [...]int{1, 22, 33, 444, 555}
	slice := intArr[1:3]
	fmt.Println("intArr=", intArr)
	fmt.Println("slice = ", slice)
	fmt.Println("slice lenght = ", len(slice))
	fmt.Println("slice cap = ", cap(slice))

}

func res() {
	defer func() {
		err := recover()
		if err != nil {
			fmt.Println("err = ", err)
		}
	}()

	num1 := 10
	num2 := 0
	res := num1 / num2
	fmt.Println("res=", res)
}

func readConf(name string) (err error) {
	if name == "config.ini" {
		return nil
	} else {
		return errors.New("read file error...")
	}
}

func changeArr(arr *[]int) {
	len := len(*arr)
	//arr[len - 1] = 3
	(*arr)[len-1] = 3

	for index, value := range *arr {
		fmt.Printf("arr[%d] = %v\t", index, value)
	}
}

func getMaxVal(arr []int) int {
	if len(arr) < 0 {
		return -1
	}
	var max = arr[0]
	for i := 0; i < len(arr); i++ {
		if max < arr[i] {
			max = arr[i]
		}
	}

	return max
}

func generateIntAndReverse() {
	arr := [5]int{}
	for i := 0; i < 5; i++ {
		arr[i] = rand.Int()
	}
	print(arr)
	for i := 0; i < (len(arr) >> 1); i++ {
		arr[i], arr[len(arr)-1-i] = arr[len(arr)-1-i], arr[i]
	}
	print(arr)
}

func print(arr [5]int) {
	for _, val := range arr {
		fmt.Printf("%d\t", val)
	}
	fmt.Println()
}
