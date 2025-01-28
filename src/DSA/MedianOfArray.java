package DSA;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MedianOfArray {
    public static void main(String[] args) {
    int[] arr1 = {1,2};
    int[] arr2 ={3,4};
        System.out.println("Final result "+findMedianSortedArrays(arr1,arr2));
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length= nums1.length + nums2.length;
        int[] mergedArr = new int[length];

        for(int i=0; i<=nums1.length-1; i++){
            mergedArr[i] = nums1[i];
        }
        int index =0;
        for(int j=nums1.length; j<mergedArr.length; j++){
            mergedArr[j] = nums2[index];
            if(index <nums2.length-1){
                index++;
            }else{
                break;
            }
        }

        Arrays.sort(mergedArr);
        for(int i=0; i< mergedArr.length ; i++) {
            System.out.println("merged array" + mergedArr[i]);
        }
        int mid = (0+ mergedArr.length-1)/2;
        if(mergedArr.length % 2 == 0){
            return (double) (mergedArr[mid] + mergedArr[mid+1])/2;
        }else{
            return (double) (mergedArr[mid]);
        }
    }
}