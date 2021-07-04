package main

import "fmt"

func main() {

	var arr [5]int = [...]int{10, 30, 40, 50, 550}
	slice := arr[1:4]
	for i := 0; i < len(slice); i++ {
		fmt.Printf("slice[%v] = %v \t", i, slice[i])
	}
	fmt.Println()
	slice2 := slice[1:2]
	fmt.Printf("%d", slice2[0])
	slice2[0] = 100

	for i := 0; i < len(slice); i++ {
		fmt.Printf("slice[%v] = %v \t", i, slice[i])
	}

	str := "你好世界 world"
	sl := str[1:]
	fmt.Println(sl)
	arr2 := []rune(str)
	arr1 := []byte(str)
	arr1[0] = 's'
	str = string(arr1)
	fmt.Println(str)
	arr2[0] = '北'
	str = string(arr2)
	fmt.Println(str)

}
