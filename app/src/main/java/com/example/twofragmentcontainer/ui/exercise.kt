package com.example.twofragmentcontainer.ui
//import java.util.Scanner
//import kotlin.math.max
//
//class exercise {
//
//    fun run_task(arr : Array<Int>) {
//        var changes = 9
//
//        //make numbers correct
//        while(arr[0] % 3 != 0) {
//            arr[0] += changeDifference(arr[0])
//            changes -= 1
//        }
//        while(arr[1] % 3 != 0) {
//            arr[1] += changeDifference(arr[1])
//            changes -= 1
//        }
//        while(arr[2] % 3 != 0) {
//            arr[2] += changeDifference(arr[2])
//            changes -= 1
//        }
//
//        //make 'biggest' triple changes until there are no changes
//        while(changes > 0) {
//            val change0 = tripleChangeDifference(arr[0])
//            val change1 = tripleChangeDifference(arr[1])
//            val change2 = tripleChangeDifference(arr[2])
//            val max =  max(max(change0, change1), change2)
//
//            when(max) {
//                change0 -> arr[0] += change0
//                change1 -> arr[1] += change1
//                change2 -> arr[2] += change2
//            }
//
//            changes -= 3
//        }
//
//        print("" + arr[0] + ", " + arr[1] + ", " + arr[2])
//    }
//
//    fun tripleChangeDifference(num: Int) : Int { //return -1 if not possible
//        var total_change = 0
//        repeat(3) {
//            val change = changeDifference(num + total_change)
//            if(change == -1)
//                return -1
//            else
//                total_change += change
//        }
//        return total_change
//    }
//
//    fun changeDifference(num: Int): Int { //return -1 if not possible
//        var numString = num.toString()
//        for (i in numString.length-1..0) {
//            if(numString[i] != '9') {
//                numString = numString.substring(0,i) + (numString[i].code + 1).toChar() + numString.substring(i+1)
//                return numString.toInt() - num
//            }
//        }
//        return -1
//    }
//}