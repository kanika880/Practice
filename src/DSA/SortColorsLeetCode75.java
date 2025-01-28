package DSA;


public class SortColorsLeetCode75 {

    public static void main(String[] args){

        //Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
        //
        //We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
        //
        //You must solve this problem without using the library's sort function.
        
        int[] arr = {2,0,2,1,1,0};
       sortColorBruteForce(arr);

        System.out.println("=====Optimized solution=========");
       optimisedSortColor(arr);
    }

    public static void sortColorBruteForce(int[] arr){
        for(int i= 0; i< arr.length; i++){
            for (int j= i+1; j< arr.length; j++){
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        //print sorted array
        //Time complexity = O(N^2)
        //Space Complexity = O(1)
        for(int i=0; i< arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void optimisedSortColor(int[] arr){
        int low = 0;
        int high = arr.length-1;
        int mid = 0;
        while(mid < high){
            if(arr[mid] == 0){
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++;
                mid++;
            }else if(arr[mid] == 2){
                int temp = arr[high];
                arr[high] = arr[mid];
                arr[mid] = temp;
                high--;
            }else{
                mid++;
            }
        }
        for(int i : arr) {
            System.out.println(i);
        }

    }

    


}
