public class Divide {

// using merge sort to sort array of strings    
    public static void merge(String a[],int low,int mid,int high) {
        int i = low,j = mid+1,k = low;
        String b[] = new String[high+1];
        while (i<=mid && j<=high) {
            if ((a[i].compareTo(a[j]))<0) { //<--- tweaked part
                b[k] = a[i];
                i++; k++;
            }
            else{
                b[k] = a[j];
                j++; k++;
            }
        }
        while (i<=mid) {
            b[k] = a[i];
            i++; k++;
        }
        while (j <= high) {
            b[k]=a[j];
            j++; k++;
        }
        for (int l = low; l <= high; l++) {
            a[l] = b[l];
        }

    }

    public static void mergeSort(String a[],int low,int high) {
        int mid;
        if (low < high) {
            mid = (low+high)/2;
            mergeSort(a, low, mid);
            mergeSort(a, mid+1, high);
            merge(a,low,mid,high);
        }
    }
    public static void main(String[] args) {
        // Your code goes here
        String a[] = {"cat","mouse","argo","afspa","lund","zand","kota","komali"};
        mergeSort(a, 0, a.length-1);
        for (int i = 0; i < a.length; i++) {
        System.out.print(a[i]+" ");
        }
    }
}

