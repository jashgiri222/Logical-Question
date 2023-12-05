import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
		
		List<Integer>data=Arrays.asList(1,2,3,4);
		List<Integer> list=data.stream().map(j->j*3).collect(Collectors.toList());
		System.out.println("data"+list);
		

		List<String> stringList = Arrays.asList("Hello", "Interview", "Questions", "Answers", "Ram", "for");
		stringList.stream().filter(t->t.length()>3).forEach(System.out::println);

	}
	
	

}
