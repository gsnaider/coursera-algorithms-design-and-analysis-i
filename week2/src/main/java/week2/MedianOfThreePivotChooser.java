package week2;

public class MedianOfThreePivotChooser extends PivotChooser {

	@Override
	public int choosePivot(int[] array, int start, int end) {
		ArrayElement firstElement = new ArrayElement(start, array[start]);
		ArrayElement lastElement = new ArrayElement(end, array[end]);
		
		int middlePosition = (start + end) / 2;
		ArrayElement middleElement = new ArrayElement(middlePosition, array[middlePosition]);
		
		int medianPosition = getMedian(firstElement, middleElement, lastElement);
		
		return medianPosition;

	}

	private int getMedian(ArrayElement elem1, ArrayElement elem2, ArrayElement elem3) {
		ArrayElement[] elems = new ArrayElement[3];
		
		elems[0] = elem1;
		elems[1] = elem2;
		elems[2] = elem3;
		
		for (int i = 0; i < 2; i++){
			for (int j = i + 1; j < 3; j++){
				if(elems[j].getValue() < elems[i].getValue()){
					swap(elems, i, j);
				}
			}
		}
		return elems[1].getPosition();
		
	}
	
	private void swap(ArrayElement[] array, int pos1, int pos2) {
		ArrayElement aux = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = aux;
	}
	
}


