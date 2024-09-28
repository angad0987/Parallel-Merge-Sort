package Java_Core.MultiThreading.Parallel_Merge_Sort;

import java.util.List;

public class SequentialMergeSort {
    private List<Integer> list;

    public SequentialMergeSort(List<Integer> list){
        this.list=list;
    }
    public void recursiveSort(int left,int right){
        if(right-left==1){
            return;
        }
        int mid=left+(right-left)/2;
        recursiveSort(left,mid);
        recursiveSort(mid,right);

        mergeInPlace(left,mid,right);
    }
    private void mergeInPlace(int left,int mid,int right){
        int [] arr=new int[right-left];

        int i=left;
        int j=mid;
        int k=0;
        while(i<mid && j<right){
            if(list.get(i)<list.get(j)){
                arr[k]=list.get(i);
                i++;
            }
            else{
                arr[k]=list.get(j);
                j++;
            }
            k++;
        }
        while(i<mid){
            arr[k]=list.get(i);
            k++;
            i++;
        }
        while(j<right){
            arr[k]=list.get(j);
            k++;
            j++;
        }
        for(int l=0;l<arr.length;l++){
            list.set(left+l,arr[l]);
        }
    }
    public void sort(){
        if(list==null || list.size()==1 || list.size()==0){
            return;
        }
        recursiveSort(0,list.size()-1);
    }
}
