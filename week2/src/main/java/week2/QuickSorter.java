package week2;

public class QuickSorter {

	int comparisons;
	
	PivotChooser pivotChooser;
	
	public QuickSorter() {
		comparisons = 0;
		pivotChooser = new MedianOfThreePivotChooser();
	}
	
	public void quicksort(int[] array, int start, int end){
		if(start >= end){
			return;
		}

		comparisons += (end - start);
		
		int pivotPosition = pivotChooser.choosePivot(array, start, end);
		swap(array, start, pivotPosition);
		
		int pivotFinalPlace = partition(array, start, end);
		quicksort(array, start, pivotFinalPlace - 1);
		quicksort(array, pivotFinalPlace + 1, end);
		
	}
	
	
	private void swap(int[] array, int pos1, int pos2) {
		int aux = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = aux;
	}

	private int partition(int[] array, int start, int end) {
		int pivot = array[start];
		
		//first position of the looked elements > pivot
		int i = start + 1;
		
		for(int j = start + 1; j <= end; j++){
			if(array[j] < pivot){
				swap(array, j, i);
				i++;
			}
		}
		
		int pivotFinalPosition = i - 1;
		swap(array, start, pivotFinalPosition);
		return pivotFinalPosition;
	}

	public static void main(String[] args) {
		QuickSorter sorter = new QuickSorter();
		
		int n = 10000;
		
		int[] array = new int[n];
		
		FileReader reader = new FileReader();
		
		reader.readFile(array);
		
		sorter.quicksort(array, 0, n - 1);
		
		System.out.println(sorter.comparisons);

	}

}
