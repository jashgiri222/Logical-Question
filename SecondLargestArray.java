import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SecondLargestArray {
	
	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>(Arrays.asList(123, 456,789));

		Set<Integer> set = new TreeSet<>(list);

		list.clear();

		for (int value : set) {
			list.add(value);

		}
		int size =list.size();
		
		System.out.println(list.get(size-2));

	}
}
