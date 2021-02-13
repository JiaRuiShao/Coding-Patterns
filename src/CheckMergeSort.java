import java.util.ArrayList;

/*
 * @author JiaRui Shao
 * The driver class CheckMergeSort
 */
public class CheckMergeSort {
    /**
     * main method, to instantiate the MergeSort
     * @param args the arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> myArrayList = new ArrayList<>();
        MergeSort solution = new MergeSort(myArrayList);
        System.out.println(myArrayList);

        myArrayList = solution.process();
        System.out.println(myArrayList);

        myArrayList.add(0);
        myArrayList.add(10);
        myArrayList.add(3);
        myArrayList.add(100);
        System.out.println(myArrayList);
        solution.myArray = myArrayList;

        myArrayList = solution.process();
        System.out.println(myArrayList);

        myArrayList.add(7);
        myArrayList.add(30);
        myArrayList.add(2);
        System.out.println(myArrayList);
        solution.myArray = myArrayList;

        myArrayList = solution.process();
        System.out.println(myArrayList);
    }
}
