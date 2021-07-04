package sort

import "fmt"

func main() {
	var arr = []int{-1, 3, 2, 0, -5, 8}
	BubbleSort(&arr)
}

func BubbleSort(arr *[]int) {
	fmt.Println("排序前arr = ", (*arr))
	for i := 0; i < len(*arr)-1; i++ {
		for j := 0; j < len(*arr)-1-i; j++ {
			if (*arr)[j] < (*arr)[j+1] {
				(*arr)[j], (*arr)[j+1] = (*arr)[j+1], (*arr)[j]
			}
		}
	}
	fmt.Println("排序后arr = ", (*arr))
}
