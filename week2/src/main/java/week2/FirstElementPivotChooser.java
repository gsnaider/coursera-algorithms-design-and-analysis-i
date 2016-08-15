package week2;

public class FirstElementPivotChooser extends PivotChooser {

	@Override
	public int choosePivot(int[] array, int start, int end) {
		return start;
	}

}
