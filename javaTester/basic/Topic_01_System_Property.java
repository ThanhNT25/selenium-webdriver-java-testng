package basic;

public class Topic_01_System_Property {
	
	public static void main(String[] args) {
		String projectPath = System.getProperty("user.dir");
		String osname = System.getProperty("os.name");
		System.out.println(projectPath);
		System.out.println(osname);
				
	}

}
