public class Occuran2 {
	public static void main(String[] args) {

		String str = "my name is giri";
		int frq[] = new int[str.length()];
		char string[] = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {

			frq[i] = 1;
			for (int j = i + 1; j < str.length(); j++) {
				if (string[i] == string[j]) {
					frq[i]++;
					string[j] = '0';
				}
			}
		}

		for (int i = 0; i < frq.length; i++) {
			if (string[i] != ' ' && string[i] != '0') {
				System.out.println(string[i] + "-" + frq[i]);
			}
		}

	}

}
