package week2;

public class LastElementPivotChooser extends PivotChooser {

	@Override
	public int choosePivot(int[] array, int start, int end) {
		return end;
	}

}
