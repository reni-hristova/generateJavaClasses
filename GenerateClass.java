import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateClass {
	private String classname;
	private String[] variableNames;
	private String[] variableTypes;
	
	/**
	 * Constructor for class GenerateClass.
	 * @param classname The name of the class we want
	 * to write as a String.
	 * @param variableNames The names of the variables
	 * in that class as an Array of String elements.
	 * @param variableTypes The types of those variables
	 * as an Array of String elements.
	 */
	public GenerateClass(String classname, String[] variableNames, String[] variableTypes) {
		this.classname = classname;
		this.variableNames = variableNames;
		this.variableTypes = variableTypes;
	}
	
	/**
	 * This method generates the field variables of the
	 * class we want to write.
	 * @return A String that contains the full list of 
	 * all field variables.
	 */
	public String makeFields() {
		String declaration = ""; 
		for(int i=0; i<variableTypes.length;i++) {
			declaration = declaration.concat("  private " + variableTypes[i] + " " + variableNames[i] + ";\n");
		}
		declaration = declaration.concat("\n");
		return declaration;
	}
	
	/**
	 * This method generates a constructor for the class.
	 * @return The constructor of the class as a String.
	 */
	public String makeConstructor() {
		String constructor = "  public " + classname + "(";
		for(int i=0; i<variableTypes.length;i++) {
			constructor = constructor.concat(variableTypes[i] + " " + variableNames[i]);
			if(i!=variableTypes.length-1) { 
				constructor = constructor.concat(", ");
			}
		}
		constructor = constructor.concat("){");
		if(variableTypes.length==0) {
			constructor = constructor.concat("}\n");
			return constructor;
		}
		constructor = constructor.concat("\n");
		for(int i=0; i<variableTypes.length;i++){
			constructor = constructor.concat("    this." + variableNames[i] + " = " + this.variableNames[i] + ";\n");
		}
		constructor = constructor.concat("  }\n");
		return constructor;
	}
	
	/**
	 * This method generates all the getters for the class.
	 * @return All getters as a String.
	 */
	public String makeGetters() {
		String getters = "";
		for(int i=0; i<variableTypes.length; i++){
			getters = getters.concat("  public " + variableTypes[i] + " get" 
						+ variableNames[i].substring(0, 1).toUpperCase() + variableNames[i].substring(1)
						+ "(){\n    return " + variableNames[i] + ";\n  }\n");
		}
		return getters;
	}
	
	/**
	 * This method generates all the setters for the class.
	 * @return All setters as a String.
	 */
	public String makeSetters(){
		String setters = "";
		for(int i=0; i<variableTypes.length;i++) {
			setters = setters.concat("  public void set" + variableNames[i].substring(0, 1).toUpperCase() 
						+ variableNames[i].substring(1) + "(" + variableTypes[i] + " " + variableNames[i] 
						+ "){\n    this." + variableNames[i] + " = " + variableNames[i] + ";\n  }\n");
		}
		return setters;
	}
	
	/**
	 * This method generates a main method for the class.
	 * @return The main method as a String.
	 */
	public String makeMain() {
		String main = "  public static void main(String[] args) {";
		//additional code can be written here
		main = main.concat("\n  }\n");
		return main;
	}
	
	/**
	 * This method creates a new .java file for the class.
	 */
	public void writeFile() {
		String filename = classname + ".java";
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			out.write("public class " + classname + "{\n");
			out.write(makeFields() + "\n");
			out.write(makeConstructor());
			out.write(makeGetters());
			out.write(makeSetters());
			out.write(makeMain());
			out.write("}\n");
			out.close();
		}
		catch (IOException e) {
			System.out.println("File not found.");
		}
	}
	
	public static void main(String[] args) {
		String[] varNames = {"name", "dob"};
		String[] varTypes = {"String", "Date"};
		GenerateClass Person = new GenerateClass("Person", varNames, varTypes);
		Person.writeFile();
	}
}