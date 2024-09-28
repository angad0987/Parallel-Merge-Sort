package Java_Core.MultiThreading.Parallel_Merge_Sort;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Parallel_MergeSort {
    private List<Integer> list;
    private int THRESHOLD=100000;
    private ExecutorService executorService;

    public Parallel_MergeSort(List<Integer> list){
        this.list=list;
        this.executorService= Executors.newCachedThreadPool();
    }
    public void sequentialSort(int left,int right){
        if(right-left==1){
            return;
        }
        int mid=left+(right-left)/2;
        recursiveSort(left,mid);
        recursiveSort(mid,right);
        mergeInPlace(left,mid,right);
    }
    private void recursiveSort(int left,int right){
        if(right-left==1){
            return;
        }
        if(right-left<=THRESHOLD){
            sequentialSort(left,right);
            return;
        }
        int mid=left+(right-left)/2;
         Future<?> leftPart = executorService.submit(() -> {
            recursiveSort(left, mid);
        });
         Future<?> rightPart = executorService.submit(() -> {
            recursiveSort(mid, right);
        });

         try{
             leftPart.get();
             rightPart.get();
         } catch (ExecutionException | InterruptedException e) {
             throw new RuntimeException(e);
         }
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

        //when list is sorted then shutdown the executor
        executorService.shutdown();
    }
}
